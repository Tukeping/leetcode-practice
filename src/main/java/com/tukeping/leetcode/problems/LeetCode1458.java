package com.tukeping.leetcode.problems;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/6/2
 **/
public class LeetCode1458 {

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;

        // 0:最小 1:最大
        int[][][] f = new int[n + 1][m + 1][2];
        f[0][0][0] = f[0][0][1] = 1;

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int val1 = f[i - 1][j - 1][0] * nums1[i - 1];
                int val2 = f[i - 1][j - 1][1] * nums2[j - 1];

                f[i][j - 1][0] = Math.min(f[i - 1][j - 1][0], val1);
                f[i][j - 1][1] = Math.max(f[i - 1][j - 1][1], val1);

                f[i - 1][j][0] = Math.min(f[i - 1][j - 1][0], val2);
                f[i - 1][j][1] = Math.max(f[i - 1][j - 1][1], val2);

                f[i][j][0] = Math.min(f[i][j - 1][0] * f[i - 1][j][1], f[i][j - 1][1] * f[i - 1][j][0]);
                f[i][j][1] = Math.max(f[i][j - 1][0] * f[i - 1][j][0], f[i][j - 1][1] * f[i - 1][j][1]);
            }
        }
        return ans;
    }

    /**
     * 输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6]
     * 输出：18
     * 解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
     * 它们的点积为 (2*3 + (-2)*(-6)) = 18 。
     */
    @Test
    public void test1() {
        int n = maxDotProduct(new int[]{2, 1, -2, 5}, new int[]{3, 0, -6});
        assertThat(n, is(18));
    }
}
