package com.tukeping.leetcode.contest107;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/8
 **/
public class LeetCode926 {

    public int minFlipsMonoIncr(String S) {
        int n = S.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = (S.charAt(i - 1) == '1') ? 1 : 0;
            dp[i] = dp[i] + dp[i - 1];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, n - i - (dp[n] - dp[i]) + dp[i]);
        }
        return ans;
    }

    /**
     * 输入："00110"
     * 输出：1
     * 解释：我们翻转最后一位得到 00111.
     */
    @Test
    public void test1() {
        int n = minFlipsMonoIncr("00110");
        assertThat(n, is(1));
    }

    /**
     * 输入："010110"
     * 输出：2
     * 解释：我们翻转得到 011111，或者是 000111。
     */
    @Test
    public void test2() {
        int n = minFlipsMonoIncr("010110");
        assertThat(n, is(2));
    }

    /**
     * 输入："00011000"
     * 输出：2
     * 解释：我们翻转得到 00000000。
     */
    @Test
    public void test3() {
        int n = minFlipsMonoIncr("00011000");
        assertThat(n, is(2));
    }
}
