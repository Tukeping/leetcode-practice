package com.tukeping.leetcode.problems;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/23
 **/
public class LeetCode1893 {

    public boolean isCovered(int[][] ranges, int left, int right) {
        boolean[] flag = new boolean[51];
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                flag[i] = true;
            }
        }
        for (int i = left; i <= right; i++) {
            if (!flag[i]) return false;
        }
        return true;
    }

    @Test
    public void test() {
        int[][] ranges = new int[][]{
                {1, 2}, {3, 4}, {5, 6}
        };
        boolean actual = isCovered(ranges, 2, 5);
        Assert.assertTrue(actual);
    }
}
