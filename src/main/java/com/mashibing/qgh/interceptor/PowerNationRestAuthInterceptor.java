package com.mashibing.qgh.interceptor;

import com.google.common.cache.*;
import com.mashibing.qgh.config.PowerNation3ndAccessConfig;
import com.mashibing.qgh.util.PowerNationTokenUtil;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.rmi.ServerException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * description  PowerNationRestAuthInterceptor <BR>
 * <p>
 * author: zhao.song
 * date: created in 18:14  2021/12/29
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Slf4j
public class PowerNationRestAuthInterceptor implements ClientHttpRequestInterceptor {

    private static LoadingCache<String, Field> uriCache = CacheBuilder.newBuilder()
            // 被动删除：某个key被创建后，1小时候被删除
            .expireAfterWrite(Duration.ofSeconds(3))
            .initialCapacity(8).maximumSize(16).recordStats()
            .removalListener(new RemovalListener<String, Field>() {
                @Override
                public void onRemoval(RemovalNotification<String, Field> removalNotification) {
                    log.info("过期删除：key=" + removalNotification.getKey() + " ,value=" + removalNotification.getValue() + "!\n移除原因:" + removalNotification.getCause());
                }
            }).build(new CacheLoader<String, Field>() {
                @Override
                public Field load(String fieldName) throws Exception {
                    Field field = URI.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    return field;
                }
            });

    // 第三方认证配置
    private PowerNation3ndAccessConfig pnConfig;

    private static List<String> uriFieldsToReflect;

    private static List<String> specialUriFieldsToReflect;

    static {
        List<String> tmpUriFieldsToReflect = new ArrayList<>();
        Collections.addAll(tmpUriFieldsToReflect, "string", "query", "decodedQuery", "schemeSpecificPart");
        uriFieldsToReflect = Collections.unmodifiableList(tmpUriFieldsToReflect);


        List<String> tmpSpecialUriFieldsToReflect = new ArrayList<>();
        Collections.addAll(tmpSpecialUriFieldsToReflect, "string", "schemeSpecificPart");
        specialUriFieldsToReflect = Collections.unmodifiableList(tmpSpecialUriFieldsToReflect);
    }

    public PowerNationRestAuthInterceptor(PowerNation3ndAccessConfig pnConfig) {
        Assert.notNull(pnConfig, "强国号私密配置为空！");
        Assert.notNull(pnConfig.getOpenApiUrl(), "强国号私密的请求地址配置为空！");
        Assert.notNull(pnConfig.getSecretKey(), "强国号私密配置的密钥key为空！");
        Assert.notNull(pnConfig.getSecretAccess(), "强国号私密配置的密钥值为空！");

        this.pnConfig = pnConfig;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        boolean isPowerNation = request.getURI().toString().startsWith(pnConfig.getOpenApiUrl());
        if (isPowerNation) {
            // 处理签名
            modifyURIByReflect(request);
            ClientHttpResponse result = execution.execute(request, body);
            if (!result.getStatusCode().is2xxSuccessful()) {
                log.error("请求强国号服务器失败:" + result.getStatusText());
                throw new ServerException("请求强国号服务器失败:" + result.getStatusText());
            } else {
                //TODO 待处理（强国号返回的特有的错误结果）,统一日志记录等

                return result;
            }
        }

        return execution.execute(request, body);
    }


    /**
     * description   处理强国号请求的token拼接  <BR>
     *
     * @param request:
     * @return
     * @author zhao.song  2021/12/30  16:18
     */
    private void modifyURIByReflect(HttpRequest request) {
        URI uriInstance = request.getURI();
        String uri = uriInstance.getPath();
        String methodStr = request.getMethodValue();

        // 生成强国号需要的token
        String headline = uriInstance.getQuery();
        Map<String, String[]> tokenMap = parseRequestQueryHeadline(headline);
        String token = PowerNationTokenUtil.generateToken(uri, methodStr, pnConfig.getSecretAccess(), tokenMap);
        // 封装请求行，并塞回请求对象中
        for (String fieldName : uriFieldsToReflect) {
            Try.run(() -> {
                Field field = uriCache.get(fieldName);
                String fieldValue = (String) field.get(uriInstance);
                field.set(uriInstance, StringUtils.hasText(fieldValue) ? fieldValue.concat("&token=" + token)
                        : specialUriFieldsToReflect.contains(fieldName) ? "?token=" + token : "token=" + token);
            }).onFailure(e -> System.out.println("发射修改强国号URI发生异常！"));

        }
    }

    public static String generateQueryHeadline(Map<String, String[]> params){
        StringJoiner lineJoiner = new StringJoiner("&");

        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            lineJoiner.add(entry.getKey() + "=" + entry.getValue()[0]);
        }
        return lineJoiner.toString();
    }

    /**
     * description   解析请求行参数  <BR>
     *
     * @param queryHeadline:
     * @return {@link Map< String, String[]>}
     * @author zhao.song  2021/12/30  15:43
     */
    private Map<String, String[]> parseRequestQueryHeadline(String queryHeadline) {
        Map<String, String[]> parsedMap = new HashMap<>();
        if (StringUtils.hasText(queryHeadline)) {
            parsedMap = Arrays.stream(queryHeadline.split("&"))
                    .collect(Collectors.toMap(line -> line.split("=")[0]
                            , line -> {
                                String[] kv = line.split("=");
                                if (kv.length == 1) {
                                    return new String[]{""};
                                }
                                return new String[]{kv[1]};
                            }));
        }
        parsedMap.put("timestamp", new String[]{String.valueOf(System.currentTimeMillis())});
        parsedMap.put("access_key", new String[]{pnConfig.getSecretKey()});

        return parsedMap;
    }

    private static final Pattern positiveNumRegexPattern = Pattern.compile("[1-9][0-9]*");

    public static void main(String[] args) throws ExecutionException, IOException, InterruptedException {

        Matcher matcher = positiveNumRegexPattern.matcher("40");
        Assert.state(matcher.find(), "lllll");
        while (matcher.find()) {
            System.out.println(matcher.group());

        }
//        Field field = uriCache.get("string");
//
//        TimeUnit.SECONDS.sleep(5);
//
//        Field field2 = uriCache.get("query");
//        System.out.println("-------------");


    }
}
