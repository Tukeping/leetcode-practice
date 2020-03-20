package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=287 lang=java
 *
 * [287] 寻找重复数
 *
 * https://leetcode-cn.com/problems/find-the-duplicate-number/description/
 *
 * algorithms
 * Medium (63.34%)
 * Likes:    460
 * Dislikes: 0
 * Total Accepted:    41.1K
 * Total Submissions: 65K
 * Testcase Example:  '[1,3,4,2,2]'
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和
 * n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 *
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | two-pointers | binary-search
 *
 * bloomberg
 *
 * @author tukeping
 * @date 2020/3/18
 **/
public class LeetCode287 {

    /** bsearch time: O(n log n) space: O(1) **/
    public int findDuplicate(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) count++;
            }
            // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个
            // 此时重复元素一定出现在 [1, 4] 区间里
            if (count > mid) {
                end = mid;       // [start, mid]
            } else {
                start = mid + 1; // [mid+1, end]
            }
        }
        return start;
    }

    /** time: O(n) space(1) ~ 2(F + A) = F + A + B + A => F = B **/
    public int findDuplicate2(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        int p1 = nums[0];
        int p2 = slow;
        while (p1 != p2) {
            p1 = nums[p1];
            p2 = nums[p2];
        }
        return p1;
    }

    /**
     * 输入: [1,3,4,2,2]
     * 输出: 2
     */
    @Test
    public void test1() {
        int n = findDuplicate(new int[]{1, 3, 4, 2, 2});
        assertThat(n, is(2));
    }

    /**
     * 输入: [3,1,3,4,2]
     * 输出: 3
     */
    @Test
    public void test2() {
        int n = findDuplicate(new int[]{3, 1, 3, 4, 2});
        assertThat(n, is(3));
    }
}
