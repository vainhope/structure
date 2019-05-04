package com.vain.test;

/**
 * @author vain
 * @date 2019/5/2 15:53
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写
 * "A man, a plan, a canal: Panama"
 */
public class CheckPalidrome {
    public static void main(String[] args) {
        System.out.println(check("A man, a plan, a canal: Panama"));
    }

    private static boolean check(String str) {
        if (null == str || str.length() == 0 || " ".equals(str)) {
            return false;
        }
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            } else {
                //是字母的情况
                if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
