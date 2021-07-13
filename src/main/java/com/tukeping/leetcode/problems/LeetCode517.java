package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=517 lang=java
 *
 * [517] 超级洗衣机
 *
 * https://leetcode-cn.com/problems/super-washing-machines/description/
 *
 * algorithms
 * Hard (40.60%)
 * Likes:    32
 * Dislikes: 0
 * Total Accepted:    1.3K
 * Total Submissions: 3.2K
 * Testcase Example:  '[1,0,5]'
 *
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 *
 * 在每一步操作中，你可以选择任意 m （1 ≤ m ≤ n） 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 *
 *
 * 给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数。如果不能使每台洗衣机中衣物的数量相等，则返回
 * -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入: [1,0,5]
 *
 * 输出: 3
 *
 * 解释:
 * 第一步:    1     0 <-- 5    =>    1     1     4
 * 第二步:    1 <-- 1 <-- 4    =>    2     1     3
 * 第三步:    2     1 <-- 3    =>    2     2     2
 *
 * 示例 2：
 *
 * 输入: [0,3,0]
 *
 * 输出: 2
 *
 * 解释:
 * 第一步:    0 <-- 3     0    =>    1     2     0
 * 第二步:    1     2 --> 0    =>    1     1     1
 *
 * 示例 3:
 *
 * 输入: [0,2,0]
 *
 * 输出: -1
 *
 * 解释:
 * 不可能让所有三个洗衣机同时剩下相同数量的衣物。
 *
 * 提示：
 *
 * n 的范围是 [1, 10000]。
 * 在每台超级洗衣机中，衣物数量的范围是 [0, 1e5]。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/24
 **/
public class LeetCode517 {

    public int findMinMoves(int[] machines) {
        int m = machines.length;
        if (m == 0) return -1;
        if (m == 1) return 0;

        int sum = 0;
        for (int clothes : machines) {
            sum += clothes;
        }
        if (sum % m != 0) return -1;

        int avg = sum / m;
        for (int i = 0; i < m; i++) {
            machines[i] -= avg;
        }

        int curSum = 0, maxSum = 0, ans = 0;
        for (int clothes : machines) {
            curSum += clothes;
            maxSum = Math.max(maxSum, Math.max(Math.abs(curSum), clothes));
            ans = Math.max(ans, maxSum);
        }

        return ans;
    }

    /**
     * 输入: [1,0,5]
     *
     * 输出: 3
     *
     * 解释:
     * 第一步:    1     0 <-- 5    =>    1     1     4
     * 第二步:    1 <-- 1 <-- 4    =>    2     1     3
     * 第三步:    2     1 <-- 3    =>    2     2     2
     */
    @Test
    public void test1() {
        int n = findMinMoves(new int[]{1, 0, 5});
        assertThat(n, is(3));
    }

    /**
     * 输入: [0,3,0]
     *
     * 输出: 2
     *
     * 解释:
     * 第一步:    0 <-- 3     0    =>    1     2     0
     * 第二步:    1     2 --> 0    =>    1     1     1
     */
    @Test
    public void test2() {
        int n = findMinMoves(new int[]{0, 3, 0});
        assertThat(n, is(2));
    }

    /**
     * 输入: [0,2,0]
     *
     * 输出: -1
     *
     * 解释:
     * 不可能让所有三个洗衣机同时剩下相同数量的衣物。
     */
    @Test
    public void test3() {
        int n = findMinMoves(new int[]{0, 2, 0});
        assertThat(n, is(-1));
    }

    @Test
    public void test4() {
        int n = findMinMoves(new int[]{4, 0, 0, 4});
        assertThat(n, is(2));
    }

    @Test
    public void test5() {
        int n = findMinMoves(new int[]{6, 0, 0});
        assertThat(n, is(4));
    }

    @Test
    public void test6() {
        int n = findMinMoves(new int[]{3, 1, 0, 4});
        assertThat(n, is(2));
    }
}
