package com.tukeping.leetcode.contest.weekly.contest135;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/20
 **/
public class LeetCode1037 {

    public boolean isBoomerang2(int[][] arr) {
        int a = arr[1][1] - arr[0][1]; // y2 - y1
        int b = arr[0][0] - arr[1][0]; // x1 - x2
        int c = -a * arr[0][0] - b * arr[0][1]; // -a * x1 - b * y1
        int ans = a * arr[2][0] + b * arr[2][1] + c; // a * x3 + b * y3 + c == 0, in the same line
        return ans != 0;
    }

    private int det(int x1, int y1, int x2, int y2, int x3, int y3) {
        return x1 * y2 - x2 * y1 + x2 * y3 - x3 * y2 + x3 * y1 - x1 * y3;
    }

    public boolean isBoomerang(int[][] p) {
        return det(p[0][0], p[0][1], p[1][0], p[1][1], p[2][0], p[2][1]) != 0;
    }

    /**
     * 输入：[[1,1],[2,2],[3,3]]
     * 输出：false
     */
    @Test
    public void test1() {
        assertFalse(isBoomerang(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }

    /**
     * 输入：[[1,1],[2,3],[3,2]]
     * 输出：true
     */
    @Test
    public void test2() {
        assertTrue(isBoomerang(new int[][]{{1, 1}, {2, 3}, {3, 2}}));
    }
}
