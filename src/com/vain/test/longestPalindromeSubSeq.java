package com.vain.test;

/**
 * @author vain
 * @date 2019/5/2 17:14
 */
public class longestPalindromeSubSeq {
    public static void main(String[] args) {
        System.out.println(longest("bbbab"));
    }

    private static int longest(String str) {
        if (null == str || str.length() <= 0 || " ".equals(str)) {
            return 0;
        }
        int len = str.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            //dp[i][i]是回文的中心节点
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    //当str[i]=str[j]时，如果str[i+1...j-1]是回文串，则str[i...j]也是回文串
                    //i 是从大到小 j是从小到大 所以 相等的时候 等于上一次的回文节点+2
                    //上一次的回文节点是 i+1 j-1的位置
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    //不相等为上一节点的最大长度
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                toString(dp);
            }
        }
        return dp[0][len - 1];
    }

    private static void toString(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("---------------------");
    }
}
