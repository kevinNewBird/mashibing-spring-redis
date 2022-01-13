package com.mashibing.weibo.service.handler;

import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.dto.MethodParamHolder;

import java.util.List;

/**
 * description  ISequenceRuleConverter <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:05  2022/1/12
 * company: TRS信息技术有限公司
 * version 1.0
 */
public interface ISequenceRuleHandler {

    /**
     * description   自定义规则字段转换器，返回需要的规则字段  <BR>
     *
     * @param flagHolderList:
     * @return {@link List< MethodParamHolder >}
     * @author zhao.song  2022/1/12  14:07
     */
    List<MethodParamHolder> handle(List<MethodParamHolder> flagHolderList, List<MethodParamHolder> modifyHolderList, List<String> limitUriList, SequenceLimitSelector selector);
}
