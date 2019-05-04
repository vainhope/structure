package com.vain.test;

/**
 * @author vain
 * @date 2019/5/2 14:39
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {

    public static void main(String[] args) {
        System.out.println(ReplaceSpace.replace(" We Are Happy "));
    }

    private static String replace(String string) {
        if (null == string || "".equals(string)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (String.valueOf(c).equals(" ")) {
                stringBuilder.append("%20");
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
