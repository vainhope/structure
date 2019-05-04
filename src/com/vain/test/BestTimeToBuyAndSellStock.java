package com.vain.test;

/**
 * @author vain
 * @date 2019/5/2 19:32
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] number = {7, 1, 5, 3, 6, 4};
//        int[] number = {7, 6, 4, 3, 1};
        System.out.println(sell(number));
    }

    private static int sell(int[] number) {
        int max = 0;
        for (int i = 0; i < number.length; i++) {
            for (int j = i + 1; j < number.length; j++) {
                if (number[j] > number[i]) {
                    //大于才能卖
                    if (number[j] - number[i] > max) {
                        max = number[j] - number[i];
                    }
                }
            }
        }
        return max;
    }
}
