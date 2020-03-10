package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=128 lang=java
 *
 * [128] 最长连续序列
 *
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/description/
 *
 * algorithms
 * Hard (47.03%)
 * Likes:    259
 * Dislikes: 0
 * Total Accepted:    28K
 * Total Submissions: 58.2K
 * Testcase Example:  '[100,4,200,1,3,2]'
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 */

import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | union-find
 *
 * facebook | google
 *
 * @author tukeping
 * @date 2020/3/6
 **/
public class LeetCode128 {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;

        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curNum = num;
                int curStreak = 1;

                while (set.contains(curNum + 1)) {
                    curNum++;
                    curStreak++;
                }

                longestStreak = Math.max(longestStreak, curStreak);
            }
        }

        return longestStreak;
    }

    /**
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */
    @Test
    public void test1() {
        int n = longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        assertThat(n, is(4));
    }
}
