package com.mashibing.weibo.service.handler;

import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.dto.MethodParamHolder;
import com.mashibing.weibo.wrapper.ParameterRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description  RequestToStringRuleHandler <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:16  2022/1/12
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Component("trsCommericalRuleRequestHandler1")
public class RequestToStringRuleHandler implements ISequenceRuleHandler {


    /**
     * description   自定义规则字段转换器，返回需要的规则字段  <BR>
     *
     * @param flagHolderList
     * @param modifyHolderList
     * @param limitUriList
     * @param selector
     * @return {@link List< MethodParamHolder >}
     * @author zhao.song  2022/1/12  14:07
     */
    @Override
    public List<MethodParamHolder> handle(List<MethodParamHolder> flagHolderList, List<MethodParamHolder> modifyHolderList, List<String> limitUriList, SequenceLimitSelector selector) {
        if (flagHolderList.size() != 1 || modifyHolderList.size() != 1) {
            return null;
        }
        MethodParamHolder methodParamHolder = flagHolderList.get(0);
        Object param = methodParamHolder.getParam();
        if (ServletRequest.class.isAssignableFrom(param.getClass())) {
            HttpServletRequest actualRequest = (HttpServletRequest) param;
            String requestURI = actualRequest.getRequestURI();
            boolean isNeedLimit = false;
            for (String limitUri : limitUriList) {
                if (requestURI.contains(limitUri)) {
                    isNeedLimit = true;
                    break;
                }
            }
            if (!isNeedLimit) {
                return null;
            }
            String uid = actualRequest.getParameter(selector.requiredUidName());
            if (!StringUtils.hasText(uid)) {
                return null;
            }
            List<String> uidList = Arrays.stream(uid.split("~")).collect(Collectors.toList());
            if (uidList.isEmpty()) {
                return null;
            }
            // TODO 选择access_token
            String selectedToken = "abcdefg";
            HashMap<String, String[]> newParamMap = new HashMap<>(actualRequest.getParameterMap());
            newParamMap.put("access_token", new String[]{selectedToken});
            actualRequest = new ParameterRequestWrapper(actualRequest, newParamMap);
            modifyHolderList.get(0).setParam(actualRequest);
        }
        throw new RuntimeException("发生异常！");
//        return modifyHolderList;
    }
}
