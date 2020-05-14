package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/31
 **/
public class LCOF14_1 {

    public int cuttingRope(int n /* 2 <= n <= 58 **/) {
        int maxProduct = 1;
        while (n > 0) {
            if (n <= 2) {
                return maxProduct == 1 ? 1 : maxProduct * n;
            } else if (n == 3 && maxProduct > 1) {
                return maxProduct * 3;
            } else if (n <= 4) {
                n = n - 2;
                maxProduct = maxProduct * 2;
            } else { // n > 4
                n = n - 3;
                maxProduct = maxProduct * 3;
            }
        }
        return maxProduct;
    }

    /** time: O(n) space: O(n) **/
    public int cuttingRope1(int n /* 2 <= n <= 58 **/) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        if (n == 5) return 6;
        if (n == 6) return 9;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        dp[5] = 6;
        dp[6] = 9;

        for (int i = 7; i <= n; i++) {
            dp[i] = dp[i - 3] * 3;
        }
        return dp[n];
    }

    /** time: O(n) space: O(1) **/
    public int cuttingRope0(int n /* 2 <= n <= 58 **/) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        if (n == 5) return 6;
        if (n == 6) return 9;

        int pre3 = 4, pre2 = 6, pre1 = 9, cur = 0;
        for (int i = 7; i <= n; i++) {
            cur = pre3 * 3;
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }

    /**
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     */
    @Test
    public void test1() {
        int n = cuttingRope(2);
        assertThat(n, is(1));
    }

    /**
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     */
    @Test
    public void test2() {
        int n = cuttingRope(10);
        assertThat(n, is(36));
    }

    @Test
    public void test3() {
        int n = cuttingRope(11);
        assertThat(n, is(54));
    }

    @Test
    public void test4() {
        int n = cuttingRope(3);
        assertThat(n, is(2));
    }

    @Test
    public void test5() {
        int n = cuttingRope(6);
        assertThat(n, is(9));
    }
}
