package com.vain.test;

/**
 * @author vain
 * @date 2019/5/2 16:10
 * 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 */
public class LongestSubstringPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    private static String longestPalindrome(String string) {
        if (null == string || string.length() < 2) {
            return string;
        }
        int start = 0, end = 0;
        for (int i = 0; i < string.length() - 1; i++) {
            // i 标识回文中心点的位置
            //奇数情况
            int length1 = expandAroundCenter(string, i, i);
            //偶数情况
            int length2 = expandAroundCenter(string, i, i + 1);
            int max = Math.max(length1, length2);
            if (max > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return string.substring(start, end + 1);
    }

    private static int expandAroundCenter(String string, int left, int right) {
        while (left >= 0 && right < string.length() && string.charAt(left) == string.charAt(right)) {
            //根据回文中心点向两边扩散
            left--;
            right++;
        }
        return right - left - 1;
    }
}
