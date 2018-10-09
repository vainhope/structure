package com.vain.test;

/**
 * @author vain
 * @Description https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * @date 2018/10/9 21:18
 */
public class Solution {

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        System.out.println(new Solution().Find(99, a));
    }

    public boolean Find(int target, int[][] array) {
        if (array.length - 1 == 0) {
            return false;
        }
        int max = array[array.length - 1][array[array.length - 1].length - 1];
        int min = array[0][0];
        if (target < min || target > max) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            int first = array[i][0];
            int last = array[i][array[i].length - 1];
            if (first == target || last == target) {
                return true;
            } else if (first < target && last > target) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
