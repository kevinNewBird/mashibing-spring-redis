package com.mashibing.weibo.exception;

import com.mashibing.weibo.vo.WeiboErrorResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * description  微博频次限制异常 <BR>
 * <p>
 * author: zhao.song
 * date: created in 11:46  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(WeiboSequenceRuleException.class)
    public Object handleWeiboException(WeiboSequenceRuleException exception) {
        WeiboErrorResponseResult responseResult = WeiboErrorResponseResult.builder().error_code(500)
                .error_message("中间件微博频次校验内部发生异常！" + exception.getMessage())
                .request("新媒体中间件频次处理异常！").build();
        return ResponseEntity.status(HttpStatus.OK).body(responseResult);
    }

}
