package com.tukeping.leetcode.contest180;

import java.util.ArrayList;
import java.util.List;

/*
 * 5356. 矩阵中的幸运数
 *
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 *
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *
 * 示例 1：
 *
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 *
 * 示例 2：
 *
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 *
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 */

/**
 * @author tukeping
 * @date 2020/3/15
 **/
public class LeetCode5356 {

    public List<Integer> luckyNumbers(int[][] matrix) {
        int n = matrix.length;
        int m = matrix.length == 0 ? 0 : matrix[0].length;
        List<Integer> lucky = new ArrayList<>();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int minimum = Integer.MAX_VALUE;

                for (int k = 0; k < m; k++)
                    minimum = Math.min(minimum, matrix[i][k]);

                int maximum = Integer.MIN_VALUE;

                for (int k = 0; k < n; k++)
                    maximum = Math.max(maximum, matrix[k][j]);

                if (matrix[i][j] == minimum && matrix[i][j] == maximum)
                    lucky.add(matrix[i][j]);
            }

        return lucky;
    }

    public List<Integer> luckyNumbers2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int minX, minY;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            minX = i;
            minY = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < matrix[minX][minY]) {
                    minY = j;
                }
            }
            boolean isMax = true;
            for (int k = 0; k < m; k++) { // k = row
                if (k != minX && matrix[k][minY] > matrix[minX][minY]) {
                    isMax = false;
                    break;
                }
            }
            if (isMax) res.add(matrix[minX][minY]);
        }
        return res;
    }
}
