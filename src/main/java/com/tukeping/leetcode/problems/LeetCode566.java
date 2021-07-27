package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/22
 **/
public class LeetCode566 {

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;
        if (n * m != r * c) return mat;

        int[][] newMat = new int[r][c];
        int ri = 0;
        int cj = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cj == c) {
                    cj = 0;
                    ri++;
                }
                newMat[ri][cj] = mat[i][j];
                cj++;
            }
        }
        return newMat;
    }
}
