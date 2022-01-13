package com.mashibing.weibo.service.validator;

import com.mashibing.weibo.config.RequestSequenceLimitConfig;
import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.dto.MethodParamHolder;
import com.mashibing.weibo.exception.WeiboSequenceRuleException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description  AbstractRequestSequenceLimitValidator <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:23  2022/1/12
 * company: TRS信息技术有限公司
 * version 1.0
 */
public abstract class AbstractRequestSequenceLimitValidator implements IRequestSequenceLimitValidator {

    @Autowired
    private RequestSequenceLimitConfig sequenceLimitConfig;


    @Override
    public void doCheckLimit(Method method, Object[] args, SequenceLimitSelector[] selectorArray) throws WeiboSequenceRuleException {
        try {
            Map<String, Map<String, List<String>>> limitConfig = sequenceLimitConfig.getLimitConfig();
            if (args == null || args.length == 0) {
                return;
            }

            for (SequenceLimitSelector selector : selectorArray) {

                if (method.getParameterCount() <= 0) {
                    //如果没有参数传入，则不做处理
                    return;
                }
                // 2.处理限制的uri集合
                Map<String, List<String>> limitUriMap = limitConfig.get(selector.limitType().getChannelType());
                String[] postfix = selector.configPostfix();
                List<String> limitUriList = new ArrayList<>();
                for (String value : postfix) {
                    limitUriList.addAll(limitUriMap.get(value));
                }

                List<MethodParamHolder> holderList = handle(method, args, limitUriList, selector);
                if (holderList == null || holderList.size() == 0) {
                    return;
                }
                // 修改参数值
                for (MethodParamHolder holder : holderList) {
                    int index = holder.getIndex();
                    args[index] = holder.getParam();
                }
            }
        } catch (Throwable e) {
            throw new WeiboSequenceRuleException(e.getMessage(), e);
        }
    }

    protected abstract List<MethodParamHolder> handle(Method method, Object[] args, List<String> limitUriList, SequenceLimitSelector selector);
}
