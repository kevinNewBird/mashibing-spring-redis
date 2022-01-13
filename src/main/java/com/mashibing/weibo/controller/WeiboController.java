package com.mashibing.weibo.controller;

import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.annotation.SequenceLimitSelectors;
import com.mashibing.weibo.constant.SelectorLimitTypeEnum;
import com.trs.common.http2.HttpRequest;
import com.trs.log.exception.RecordableException;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;

/**
 * description  WeiboController <BR>
 * <p>
 * author: zhao.song
 * date: created in 12:26  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@RestController
public class WeiboController {


    @GetMapping("/c/sina/openapi")
    @ResponseBody
    @SequenceLimitSelectors(validator = "sinaRequestValidator", value = {
            @SequenceLimitSelector(limitType = SelectorLimitTypeEnum.SINA_UID_LIMIT, name = "request", requiredUidName = "access_token"
                    , type = ServletRequest.class, replaceName = "request", replaceType = ServletRequest.class
                    , ruleHandler = "trsCommericalRuleRequestHandler", configPostfix = {"uidsRuleUrls"})
    })
    public ResponseEntity doGet(HttpServletRequest request) throws IOException, RecordableException {
        return doGet(request,"http://localhost:8080/common/internal");
    }


    private ResponseEntity doGet(HttpServletRequest request, String url) throws RecordableException, IOException {
        Request.Builder builder = getBaseRequest(request);
        String param = request.getQueryString();
        if (!StringUtils.isBlank(param)) {
            builder.url(url);
        } else {
            builder.url(url + "?" + param);
        }
        return changeResponseToResponseEntity(new HttpRequest().execute(builder.build()));
    }

    private Request.Builder getBaseRequest(HttpServletRequest request){
        Request.Builder builder = new Request.Builder();
        Enumeration<String> headers = request.getHeaderNames();
        Headers.Builder newHeaders = new Headers.Builder();
        while (headers.hasMoreElements()){
            String header = headers.nextElement();
            if (!"host".equalsIgnoreCase(header)){
                newHeaders.add(header,request.getHeader(header));
            }
        }
        builder.headers(newHeaders.build());
        return builder;
    }


    private ResponseEntity changeResponseToResponseEntity(Response response) throws IOException {
        Set<String> headers = response.headers().names();
        HttpHeaders returnHeaders = new HttpHeaders();
        for (String header : headers) {
            returnHeaders.add(header, response.header(header));
        }
        return ResponseEntity.status(response.code()).headers(returnHeaders).body(response.body().string());
    }
}
