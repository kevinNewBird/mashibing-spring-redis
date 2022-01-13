package com.mashibing.weibo.config;

import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.annotation.SequenceLimitSelectors;
import com.mashibing.weibo.exception.WeiboSequenceRuleException;
import com.mashibing.weibo.service.validator.IRequestSequenceLimitValidator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * description  SinaSelectorAspectConfig <BR>
 * <p>
 * author: zhao.song
 * date: created in 11:11  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Component
@Aspect
@EnableAspectJAutoProxy
@Slf4j
public class SequenceSelectorAspectConfig {


    @Resource
    private Map<String, IRequestSequenceLimitValidator> validator;

    @Pointcut("@annotation(com.mashibing.weibo.annotation.SequenceLimitSelectors)")
    public void pointcut() {

    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Object[] args = joinPoint.getArgs();
            Method method = methodSignature.getMethod();
            if (method.isAnnotationPresent(SequenceLimitSelectors.class)) {
                SequenceLimitSelectors selectorsAnnotation = method.getAnnotation(SequenceLimitSelectors.class);
                Map<String, Class<?>> paramsMapping = new HashMap<String, Class<?>>();
                SequenceLimitSelector[] selectorArray = selectorsAnnotation.value();
//                Assert.state(selectorArray.length > 0, "未配置sinaSelector，请检查！");
                IRequestSequenceLimitValidator channelValidator = validator.get(selectorsAnnotation.validator());
//                Assert.notNull(channelValidator, String.format("%s频次限制校验器未配置！", selectorsAnnotation.validator()));
                // 校验频次
                channelValidator.doCheckLimit(method, args, selectorArray);
                return joinPoint.proceed(args);
            }

            return joinPoint.proceed(args);
        } catch (WeiboSequenceRuleException e) {
            log.error("接口请求频次达到限制校验发生异常！", e);
            throw e;
        } catch (Throwable e) {
            log.error("接口请求频次达到限制校验发生异常！", e);
            throw new WeiboSequenceRuleException(e.getMessage(), e);
        }

    }
}
