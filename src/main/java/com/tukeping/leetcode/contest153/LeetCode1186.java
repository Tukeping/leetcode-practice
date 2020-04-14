package com.tukeping.leetcode.contest153;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/10
 **/
public class LeetCode1186 {

    public int maximumSum(int[] arr) {
        int len = arr.length;
        if (len == 1) return arr[0];

        int[][] dp = new int[len][len];
        dp[0][1] = arr[0];

        int maxSum = Integer.MIN_VALUE;
        for (int end = 1; end < len; end++) {
            for (int start = end - 1; start >= 1; start--) {
                dp[start][end] = dp[start - 1][end] + Math.max(0, arr[start]);
                maxSum = Math.max(dp[start][end], maxSum);
            }
        }
        return maxSum;
    }

    /**
     * 输入：arr = [1,-2,0,3]
     * 输出：4
     * 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
     */
    @Test
    public void test1() {
        int n = maximumSum(new int[]{1, -2, 0, 3});
        assertThat(n, is(4));
    }
}
