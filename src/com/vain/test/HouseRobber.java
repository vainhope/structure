package com.vain.test;

/**
 * @author vain
 * @date 2019/5/5 22:15
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] arrays = {2, 7, 9, 3, 1};
        System.out.println(rob(arrays));
    }

    private static int rob(int[] num) {
        if (null == num || num.length == 0) {
            return 0;
        }
        if (num.length == 1) {
            return num[0];
        }
        int[] money = new int[num.length];
        money[0] = num[0];
        //取最大
        money[1] = Math.max(num[0], num[1]);
        for (int i = 2; i < num.length; i++) {
            //取前二个+当前值 或 前一个 二种取法
            money[i] = Math.max(money[i - 2] + num[i], money[i - 1]);
        }
        return money[num.length - 1];
    }
}
