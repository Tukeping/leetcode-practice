package com.tukeping.leetcode.problems;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/24
 **/
public class LeetCode1567 {

    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        if (nums[0] > 0) dp[0][0] = 1;
        else if (nums[0] < 0) dp[0][1] = 1;

        int max = dp[0][0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] > 0 ? dp[i - 1][1] + 1 : 0;
            } else if (nums[i] < 0) {
                int tmp = dp[i - 1][0];
                dp[i][0] = dp[i - 1][1] > 0 ? dp[i - 1][1] + 1 : 0;
                dp[i][1] = tmp + 1;
            } else { // nums[i] == 0
                dp[i][0] = 0;
                dp[i][1] = 0;
            }
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }

    @Test
    public void test() {
        int actual = getMaxLen(new int[]{1, -2, -3, 4});
        Assert.assertEquals(4, actual);
    }
}
