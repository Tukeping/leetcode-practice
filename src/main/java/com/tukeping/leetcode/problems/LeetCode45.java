package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=45 lang=java
 *
 * [45] 跳跃游戏 II
 *
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (33.54%)
 * Likes:    412
 * Dislikes: 0
 * Total Accepted:    38K
 * Total Submissions: 112.6K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 *
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | greedy
 *
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode45 {

    public int jumpV2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = 100_0000;
            for (int j = Math.min(nums[i] + i, n - 1); j > i; j--) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        // for(int i = 0; i < n; i++) {
        //     System.out.println("dp["+i+"] = " + dp[i]);
        // }
        return dp[0];
    }

    public int jump(int[] nums) {
        int len = nums.length;
        int canJumpSteps = nums[0];
        if (canJumpSteps >= len)
            return len == 1 ? 0 : 1;

        int cur = 0, cnt = 0;
        while (cur < len) {
            if (canJumpSteps == 0)
                return 0;
            if (cur + canJumpSteps >= len - 1)
                return cnt + 1;
            int maxJumpStep = Integer.MIN_VALUE;
            int nextIndex = cur + 1;
            int maxJumpIndex = cur + canJumpSteps;
            for (int future = nextIndex; future < len && future <= maxJumpIndex; future++) {
                int greedySteps = nums[future] + future;
                if (greedySteps > maxJumpStep) {
                    maxJumpStep = greedySteps;
                    canJumpSteps = nums[future];
                    nextIndex = future;
                }
            }
            cur = nextIndex;
            cnt++;
        }
        return 0;
    }

    /**
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     */
    @Test
    public void test1() {
        int n = jump(new int[]{2, 3, 1, 1, 4});
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int n = jump(new int[]{1});
        assertThat(n, is(0));
    }

    @Test
    public void test3() {
        int n = jump(new int[]{2, 1});
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int n = jump(new int[]{2, 0, 2, 0, 1});
        assertThat(n, is(2));
    }

    @Test
    public void test5() {
        int n = jump(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0});
        assertThat(n, is(2));
    }

    @Test
    public void test6() {
        int n = jump(new int[]{4, 1, 1, 3, 1, 1, 1});
        assertThat(n, is(2));
    }

    @Test
    public void test7() {
        int n = jump(new int[]{3, 4, 3, 2, 5, 4, 3});
        assertThat(n, is(3));
    }
}
