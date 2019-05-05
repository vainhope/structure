package com.vain.test;

/**
 * @author vain
 * @date 2019/5/5 21:58
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int intArray[][] = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(intArray));
    }

    private static int minPathSum(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int height = grid.length;
        int wight = grid[0].length;
        int[][] res = new int[height][wight];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < wight; j++) {
                if (i == 0) {
                    //如果是最左侧 只能选择上一节点
                    res[i][j] = j == 0 ? grid[0][0] : res[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    //如果是最上侧 只能选择左边一节点
                    res[i][j] = res[i - 1][j] + grid[i][j];
                } else {
                    //不在最上侧和左侧的时候 最近距离应该是左边+grid[i][j]的值或者是上边+grid[i][j]的值
                    res[i][j] = Math.min(res[i][j - 1] + grid[i][j], res[i - 1][j] + grid[i][j]);
                }
            }
        }
        return res[height - 1][wight - 1];
    }


}
