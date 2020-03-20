package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=55 lang=java
 *
 * [55] 跳跃游戏
 *
 * https://leetcode-cn.com/problems/jump-game/description/
 *
 * algorithms
 * Medium (37.19%)
 * Likes:    501
 * Dislikes: 0
 * Total Accepted:    69K
 * Total Submissions: 180K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 *
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * array | greedy
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/3/16
 **/
public class LeetCode55 {

    public boolean canJump(int[] nums) {
        int maxPos = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > maxPos) return false;
            if (i + nums[i] > maxPos) maxPos = i + nums[i];
        }
        return maxPos >= len - 1;
    }

    public boolean canJump4(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = lastPos; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    enum Index {
        GOOD, BAD, UNKNOWN
    }

    public boolean canJump3(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[nums.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int fp = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= fp; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }

    private Index[] memo;

    public boolean canJump1(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[nums.length - 1] = Index.GOOD;
        return canJump1(nums, 0);
    }

    private boolean canJump1(int[] nums, int p) {
        if (memo[p] != Index.UNKNOWN) {
            return memo[p] == Index.GOOD;
        }
        int fp = Math.min(p + nums[p], nums.length - 1);
        for (int i = fp; i > p; i--) {
            if (canJump1(nums, i)) {
                memo[p] = Index.GOOD;
                return true;
            }
        }
        memo[p] = Index.BAD;
        return false;
    }

    /** BackTracking **/
    public boolean canJump2(int[] nums) {
        return canJump2(nums, 0);
    }

    private boolean canJump2(int[] nums, int p) {
        if (p == nums.length - 1) {
            return true;
        }
        int fp = Math.min(p + nums[p], nums.length - 1);
        for (int i = fp; i > p; i--) {
            if (canJump2(nums, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
     */
    @Test
    public void test1() {
        boolean b = canJump(new int[]{2, 3, 1, 1, 4});
        assertTrue(b);
    }

    /**
     * 输入: [3,2,1,0,4]
     * 输出: false
     * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     */
    @Test
    public void test2() {
        boolean b = canJump(new int[]{3, 2, 1, 0, 4});
        assertFalse(b);
    }
}
