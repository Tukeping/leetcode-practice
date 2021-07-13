package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=349 lang=java
 *
 * [349] 两个数组的交集
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/description/
 *
 * algorithms
 * Easy (67.39%)
 * Likes:    161
 * Dislikes: 0
 * Total Accepted:    51.4K
 * Total Submissions: 75.4K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode349 {

    /*
     * 60/60 cases passed (7 ms)
     * Your runtime beats 21.76 % of java submissions
     * Your memory usage beats 5.03 % of java submissions (39.7 MB)
     */

    public int[] intersection(int[] nums1, int[] nums2) {
        // corner case
        if (nums1 == null || nums2 == null) return null;

        int len1 = nums1.length;
        int len2 = nums2.length;

        // corner case
        if (len1 == 0 || len2 == 0) return new int[0];

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> set = new HashSet<>();
        if (len1 <= len2) {
            for (int num : nums1) {
                if (bsearch(nums2, 0, len2 - 1, num)) {
                    set.add(num);
                }
            }
        } else {
            for (int num : nums2) {
                if (bsearch(nums1, 0, len1 - 1, num)) {
                    set.add(num);
                }
            }
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean bsearch(int[] array, int start, int end, int target) {
        if (start > end)
            return false;

        int mid = (start + end) / 2;

        if (array[mid] == target)
            return true;

        boolean b;
        if (array[mid] > target) {
            b = bsearch(array, start, mid - 1, target);
        } else {
            b = bsearch(array, mid + 1, end, target);
        }

        return b;
    }

    /**
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2]
     */
    @Test
    public void test1() {
        int[] res = intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        assertThat(ListHelper.asList(res), containsInAnyOrder(2));
    }

    /**
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [9,4]
     */
    @Test
    public void test2() {
        int[] res = intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        assertThat(ListHelper.asList(res), containsInAnyOrder(9, 4));
    }

    @Test
    public void test3() {
        int[] res = intersection(new int[]{}, new int[]{});
        assertThat(res, is(new int[]{}));
    }

    @Test
    public void test4() {
        int[] res = intersection(new int[]{1}, new int[]{1});
        assertThat(res, is(new int[]{1}));
    }
}
