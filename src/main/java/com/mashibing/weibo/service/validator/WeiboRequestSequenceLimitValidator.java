package com.mashibing.weibo.service.validator;

import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.dto.MethodParamHolder;
import com.mashibing.weibo.service.handler.ISequenceRuleHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description  WeiboRequestSequenceLimitValidator <BR>
 * <p>
 * author: zhao.song
 * date: created in 13:53  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Component("sinaRequestValidator")
public class WeiboRequestSequenceLimitValidator extends AbstractRequestSequenceLimitValidator {

    @Resource
    protected Map<String, ISequenceRuleHandler> ruleHandlerMap;

    /**
     * description   处理request参数  <BR>
     *
     * @param method:
     * @param args:
     * @param limitUriList:
     * @param selector:
     * @return {@link List< MethodParamHolder >}
     * @author zhao.song  2022/1/12  15:02
     */
    @Override
    protected List<MethodParamHolder> handle(Method method, Object[] args, List<String> limitUriList, SequenceLimitSelector selector) {
        Parameter[] inputParamArray = method.getParameters();
        List<MethodParamHolder> optionHolderList = new ArrayList<>();
        List<MethodParamHolder> modifyHolderList = new ArrayList<>();
        for (Parameter param : inputParamArray) {
            // 如果要求的类型与当前参数类型匹配，且参数名一致
            // 表明该参数即为我们需要的参数用于后续的微博池子分配的参数
            Class<?>[] typeArray = selector.type();
            List<MethodParamHolder> tmpOptionHolderList
                    = parseSelectorAnnotation(args, typeArray, param, selector);

            // 确定好鉴名参数后，处理需要修改的参数（具体的属性值修改交由具体的WeiboRuleHandler处理）
            Class<?>[] replaceTypeArray = selector.replaceType();
            List<MethodParamHolder> tmpModifyHolderList
                    = parseSelectorAnnotation(args, replaceTypeArray, param, selector);


            if (!tmpOptionHolderList.isEmpty()) {
                optionHolderList.addAll(tmpOptionHolderList);
            }
            if (!tmpModifyHolderList.isEmpty()) {
                modifyHolderList.addAll(tmpModifyHolderList);
            }

        }
        if (optionHolderList.isEmpty() || modifyHolderList.isEmpty()) {
            return null;
        }
        ISequenceRuleHandler handler = ruleHandlerMap.get(selector.ruleHandler());
        Assert.notNull(handler, String.format("频次校验处理器【%s】未注册！", selector.ruleHandler()));
        return handler.handle(optionHolderList, modifyHolderList, limitUriList, selector);

    }

    private List<MethodParamHolder> parseSelectorAnnotation(Object[] args, Class<?>[] typeArray
            , Parameter param, SequenceLimitSelector selector) {
        List<MethodParamHolder> holderList = new ArrayList<>();
        for (int i = 0; i < typeArray.length; i++) {
            Class<?> paramClass = typeArray[i];
            String paramName = selector.name()[i];
            if (paramClass.isAssignableFrom(param.getType())
                    && paramName.equals(param.getName())) {
                MethodParamHolder<Object> holder = new MethodParamHolder<>()
                        .setParam(args[i]).setIndex(i);
                holderList.add(holder);
            }
        }
        return holderList;
    }
}
