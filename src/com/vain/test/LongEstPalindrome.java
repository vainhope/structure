package com.vain.test;

import java.util.HashSet;

/**
 * @author vain
 * @date 2019/5/2 15:48
 * LeetCode: 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如"Aa"不能当做一个回文字符串。注 意:假设字符串的长度不会超过 1010
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 */
public class LongEstPalindrome {
    public static void main(String[] args) {
        System.out.println(longest("abccccdd"));
    }

    private static int longest(String str) {
        if (null == str || str.length() == 0 || " ".equals(str)) {
            return 0;
        }
        int count = 0;
        HashSet<Character> hashSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!hashSet.contains(c)) {
                //单数
                hashSet.add(c);
            } else {
                //双数
                hashSet.remove(c);
                count++;
            }
        }
        //为空 全是双数
        return hashSet.isEmpty() ? count * 2 : count * 2 + 1;
    }
}
