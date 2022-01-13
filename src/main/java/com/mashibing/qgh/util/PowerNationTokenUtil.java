package com.mashibing.qgh.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.net.URLEncoder;
import java.util.*;
import java.util.stream.IntStream;

/**
 * description  PowerNationTokenUtil 学习强国请求token生成工具类 <BR>
 * <p>
 * author: zhao.song
 * date: created in 16:02  2021/12/29
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class PowerNationTokenUtil {

    /**
     * 生成接口签名
     *
     * @param uri    请求接口路径
     * @param method HTTP方法
     * @param secret 分配密钥
     * @param params URL参数列表
     * @return
     */
    public static String generateToken(String uri, String method,
                                       String secret, Map<String, String[]> params) {
        Objects.requireNonNull(uri, "请求资源路径为空！");
        Objects.requireNonNull(method, "请求方法为空！");
        Objects.requireNonNull(secret, "密钥为空!");

        StringBuilder builder = new StringBuilder();
        builder.append(secret).append("&");
        builder.append(method.toUpperCase()).append("&");
        builder.append(encode(uri));
        Map<String, String[]> cloneMap = new HashMap<>(params);
        cloneMap.remove("token");
        String[] keys = sortKeys(cloneMap.keySet().toArray(new
                String[0]));
        StringBuilder kv = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            String[] values = params.get(key);
            if (values != null && values.length > 0) {
                kv.append(key).append("=").append(values[0]);
            }
            if (i < (keys.length - 1)) {
                kv.append("&");
            }
        }
        if (kv.length() > 0) {
            builder.append("&").append(encode(kv.toString()));
        }
        return DigestUtils.md5Hex(builder.toString());
    }

    public static String generateTokenV2(String uri, String method,
                                         String secret, Map<String, String[]> params) {
        Objects.requireNonNull(uri, "请求资源路径为空！");
        Objects.requireNonNull(method, "请求方法为空！");
        Objects.requireNonNull(secret, "密钥为空!");

        StringJoiner token = new StringJoiner("&");
        token.add(secret);
        token.add(method.toUpperCase());
        token.add(encode(uri));

        Map<String, String[]> cloneMap = new TreeMap<>(params);
        cloneMap.remove("token");

        cloneMap.forEach((key, valueArray) -> {
            if (Objects.nonNull(valueArray) && valueArray.length > 0) {
                token.add(key + "=" + valueArray[0]);
            }

        });

        return DigestUtils.md5Hex(token.toString());
    }

    /**
     * key排序
     *
     * @param keys key数组
     * @return 排序后的key数组
     */
    public static String[] sortKeys(String[] keys) {
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                String temp = "";
                if (keys[i].compareTo(keys[j]) > 0) {
                    temp = keys[j];
                    keys[j] = keys[i];
                    keys[i] = temp;
                }
            }
        }
        return keys;
    }

    /**
     * UTF-8编码
     *
     * @param value 待编码数据
     * @return 编码后数据
     */
    private static String encode(String value) {
        String result = null;
        try {
            result = URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    public static void main(String[] args) {
        Random rand = new Random();

        int checkNum = 100_0000;

        String uri = "https://www.baidu.com";
        String method = "GET";
        String secret = "akjfalksfjo1d3fs";
        IntStream.range(0, checkNum).forEach(index -> {
            Map<String, String[]> params = new HashMap<>();
            IntStream.range(0, 10).forEach(innerIndex -> params.put(
                    "key" + rand.nextInt(100), new String[]{"value" + rand.nextInt(1000)}));
            String oldRuleToken = generateToken(uri, method, secret, params);
            String newRuleToken = generateToken(uri, method, secret, params);
            if (index == 0) {
                System.out.println("oldRuleToken: " + oldRuleToken);
                System.out.println("newRuleToken: " + newRuleToken);
            }

            if (!oldRuleToken.equals(newRuleToken)) {
                throw new IllegalArgumentException("生成的token不一致！！！");
            }
        });
    }
}
