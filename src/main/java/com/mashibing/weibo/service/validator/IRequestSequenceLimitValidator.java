package com.mashibing.weibo.service.validator;

import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.exception.WeiboSequenceRuleException;

import java.lang.reflect.Method;

/**
 * description  IRequestSequenceLimitService <BR>
 * <p>
 * author: zhao.song
 * date: created in 13:51  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
public interface IRequestSequenceLimitValidator {

    void doCheckLimit(Method method, Object[] args, SequenceLimitSelector[] selectorArray) throws WeiboSequenceRuleException;
}
