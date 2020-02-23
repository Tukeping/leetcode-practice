package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=153 lang=java
 *
 * [153] 寻找旋转排序数组中的最小值
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (50.04%)
 * Likes:    140
 * Dislikes: 0
 * Total Accepted:    32.4K
 * Total Submissions: 64.5K
 * Testcase Example:  '[3,4,5,1,2]'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7]  可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 *
 * 示例 2:
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | binary-search
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/2/22
 **/
public class LeetCode153 {

    /*
     * 146/146 cases passed (0 ms)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 20.52 % of java submissions (38.2 MB)
     */

    /**
     * 找旋转点, 如果找到旋转点 则旋转点那个位置的元素是最小的, 否则没有找到旋转点则直接取第0个元素
     *
     * 找旋转点搜索算法使用二分查找算法, 可以达到O(log n)的时间复杂度, O(log n)的空间复杂度
     */
    public int findMin(int[] nums) {
        return bsearch(nums, 0, nums.length);
    }

    private int bsearch(int[] nums, int start, int end) {
        // 二分搜索结束条件
        if (start > end) return nums[0];

        int mid = (start + end) / 2;

        if (mid == nums.length - 1) {
            if (mid == 0) {
                return nums[0];
            } else if (mid > 0 && nums[mid - 1] < nums[mid]) {
                return nums[0];
            } else {
                return nums[nums.length - 1];
            }
        }

        // 不满足升序 该数字后面就是 旋转点, 则直接返回 mid + 1
        if (nums[mid] > nums[mid + 1])
            return nums[mid + 1];

        // 满足升序, 需要考虑两种情况, 第一种情况是找到的mid在旋转后的前半段, 另一种情况是在后半段
        // 判断是否在前半段 只需要跟第0个元素比较一下即可。如果比第0个元素大 就在前半段，否则就在后半段

        if (nums[mid] >= nums[0]) { // mid in left, rotation in right
            return bsearch(nums, mid + 1, end);
        } else { // mid in right, rotation in left
            return bsearch(nums, start, mid - 1);
        }
    }

    /**
     * 输入: [3,4,5,1,2]
     * 输出: 1
     */
    @Test
    public void test1() {
        int n = findMin(new int[]{3, 4, 5, 1, 2});
        assertThat(n, is(1));
    }

    /**
     * 输入: [4,5,6,7,0,1,2]
     * 输出: 0
     */
    @Test
    public void test2() {
        int n = findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
        assertThat(n, is(0));
    }

    @Test
    public void test3() {
        int n = findMin(new int[]{3, 4, 5, 6, 1, 2});
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int n = findMin(new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2});
        assertThat(n, is(1));
    }

    @Test
    public void test5() {
        int n = findMin(new int[]{5, 6, 1, 2, 3, 4});
        assertThat(n, is(1));
    }

    @Test
    public void test6() {
        int n = findMin(new int[]{1});
        assertThat(n, is(1));
    }

    @Test
    public void test7() {
        int n = findMin(new int[]{1, 2});
        assertThat(n, is(1));
    }

    @Test
    public void test8() {
        int n = findMin(new int[]{2, 1});
        assertThat(n, is(1));
    }

    @Test
    public void test9() {
        int n = findMin(new int[]{4, 5, 1, 2, 3});
        assertThat(n, is(1));
    }
}
