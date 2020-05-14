package com.tukeping.cp.leetcode.contest.weekly.contest135;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/20
 **/
public class LeetCode1309 {

    public int minScoreTriangulation(int[] A) {
        int length = A.length;
        int[][] dp = new int[length][length];
        for (int[] item : dp)
            Arrays.fill(item, Integer.MAX_VALUE);
        for (int i = 0; i < length; ++i)
            dp[i][(i + 1) % length] = 0; // 两个点构不成三角形，初始化为0

        for (int len = 2; len < length; ++len) {
            for (int i = 0; i < length; ++i) {
                int j = (i + len) % length;
                for (int k = (i + 1) % length; k != j; k = (k + 1) % length)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
            }
        }
        return dp[0][length - 1];
    }

    public int minScoreTriangulation2(int[] arr) {
        int n = arr.length;
        int startIdx = 0;
        int minVal = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (minVal > arr[i]) {
                minVal = arr[i];
                startIdx = i;
            }
        }

        int sum = 0, limit = n - 2;
        while (limit > 0) {
            int a = arr[(startIdx = startIdx % n)];
            int b = arr[(startIdx = (++startIdx) % n)];
            int c = arr[(startIdx = (++startIdx) % n)];
            sum += a * b * c;
            limit--;

            System.out.print(a + "*" + b + "*" + c);
            if (limit > 0) System.out.print(" + ");
        }
        System.out.println(" = " + sum);

        return sum;
    }

    /**
     * 输入：[1,2,3]
     * 输出：6
     * 解释：多边形已经三角化，唯一三角形的分数为 6。
     */
    @Test
    public void test1() {
        int n = minScoreTriangulation(new int[]{1, 2, 3});
        assertThat(n, is(6));
    }

    /**
     * 输入：[3,7,4,5]
     * 输出：144
     * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
     */
    @Test
    public void test2() {
        int n = minScoreTriangulation(new int[]{3, 7, 4, 5});
        assertThat(n, is(144));
    }

    /**
     * 输入：[1,3,1,4,1,5]
     * 输出：13
     * 解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
     */
    @Test
    public void test3() {
        int n = minScoreTriangulation(new int[]{1, 3, 1, 4, 1, 5});
        assertThat(n, is(13));
    }
}
