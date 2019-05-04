package com.vain.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vain
 * @date 2019/5/2 14:46
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 */
public class MaxStartMatch {

    public static void main(String[] args) {
        String[] str = {"flower", "flow", "flight", "flawe"};
        System.out.println(matchStart(str));
    }

    private static String matchStart(String[] strings) {
        if (null == strings || strings.length == 0) {
            return "";
        }
        List<String> chars = new ArrayList<>(strings.length);
        List<String> startIndex = null;
        for (String string : strings) {
            if (null != string && string.length() > 0 && !" ".equals(string)) {
                if (startIndex == null) {
                    startIndex = new ArrayList<>(0);
                    startIndex.add(string.substring(0, 1));
                    chars.add(string);
                } else {
                    if (startIndex.contains(string.substring(0, 1))) {
                        chars.add(string);
                    } else {
                        return "";
                    }
                }
            }
        }
        StringBuilder match = null;
        String lastMatch = "";
        for (String aChar : chars) {
            for (int i = 1; i < chars.size(); i++) {
                int min = Math.min(aChar.length(), chars.get(i).length());
                if (aChar.charAt(0) == chars.get(i).charAt(0)) {
                    //头部相等
                    match = new StringBuilder();
                    match.append(aChar.charAt(0));
                    for (int j = 1; j < min; j++) {
                        if (aChar.charAt(j) == chars.get(i).charAt(j)) {
                            match.append(aChar.charAt(j));
                        } else {
                            if ("".equals(lastMatch) || lastMatch.length() > match.toString().length()) {
                                lastMatch = match.toString();
                            }
                            break;
                        }
                    }
                }
            }
        }
        return lastMatch;
    }
}
