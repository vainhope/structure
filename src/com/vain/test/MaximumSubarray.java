package com.vain.test;

/**
 * @author vain
 * @date 2019/5/2 19:23
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] strings = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(max(strings));
    }

    private static int max(int[] nums) {
        int max = nums[0];
        int total = 0;
        for (int num : nums) {
            if (total <= 0) {
                total = num;
            } else {
                total += num;
            }
            max = Math.max(max, total);
        }
        return max;
    }
}
