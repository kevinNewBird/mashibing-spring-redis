package com.mashibing.util;

import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * description  TestEascapeUtil <BR>
 * <p>
 * author: zhao.song
 * date: created in 9:33  2021/11/19
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class TestEscapeUtil {

    public static void main(String[] args) {
        // unicode编码解码
//        String unicodeStr = StringEscapeUtils.escapeJava("abc点我");
//        System.out.println(unicodeStr);
//        String deunicodeStr = StringEscapeUtils.unescapeJava(unicodeStr);
//        System.out.println(deunicodeStr);

        String result = EmojiParser.parseToUnicode("\uDBC3\uDD01");
        System.out.println(result);
        String s = EmojiParser.parseToAliases(result);
        System.out.println(s);
//        System.out.println(EmojiParser.parseToUnicode(result));
//        String s2 = EmojiParser.parseToAliases(s);
//        System.out.println(s2);
        System.out.println(Objects.requireNonNull(new ArrayList<>().contains("false")));
        StringBuilder sb = new StringBuilder();
        String sMobile="";
        sb.append("1").append(",").append(sMobile);
        System.out.println(sb.toString().split(",").length);
        String key = "messageId,chnlDocIds,objectType,sendUser,sendTime,oprKey,tenantIds,objectIds,trueName,status,userName,passWord,isAdmin,crTime,crUser,email,mobile";
        String value = "1_1640673175449,,10,system,2021-12-28 14:32:55,2,0,1,tmyadmin,30,admin,90C6309ECA0D6A7,0,2002-08-12 00:00:00,null,1232@qq.com,null";
        System.out.println(key.split(",").length);
        System.out.println(value.split(",").length);
    }
}
