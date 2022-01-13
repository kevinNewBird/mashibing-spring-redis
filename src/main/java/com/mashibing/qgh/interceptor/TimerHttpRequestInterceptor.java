package com.mashibing.qgh.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StopWatch;

import java.io.IOException;

/**
 * description  TimerHttpRequestInterceptor <BR>
 * <p>
 * author: zhao.song
 * date: created in 17:06  2021/12/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Slf4j
public class TimerHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        ClientHttpResponse result = execution.execute(request, body);
        watch.stop();
        long wasteDuration = watch.getTotalTimeMillis();
        log.info("发送rest请求耗时：{}ms,{} {} headers:{}", wasteDuration,request.getMethod(),request.getURI(),request.getHeaders());
        return result;
    }
}
