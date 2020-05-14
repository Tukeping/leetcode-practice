package com.tukeping.cp.leetcode.contest.weekly.contest187;

/**
 * @author tukeping
 * @date 2020/5/3
 **/
public class LeetCode5403 {

    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += mat[i][0];
        }

        if (k == 1) return sum;

        int[] col = new int[m];
        int cnt = 1;
        int minValue, minRowIdx = 0;
        while (cnt != k) {
            minValue = Integer.MAX_VALUE;
            minRowIdx = -1;
            for (int i = 0; i < m; i++) {
                if (col[i] + 1 == n) continue;
                int cand = mat[i][col[i] + 1];
                if (cand < minValue) {
                    minValue = cand;
                    minRowIdx = i;
                }
            }
            cnt++;
            col[minRowIdx]++;

            System.out.println("cnt = " + cnt + ", k = " + k);
            for (int i = 0; i < m; i++) System.out.print(" " + col[i]);
            System.out.println();
        }
        return sum - mat[minRowIdx][col[minRowIdx] - 1] + mat[minRowIdx][col[minRowIdx]];
    }
}
