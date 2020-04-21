package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=80 lang=java
 *
 * [80] 删除排序数组中的重复项 II
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/description/
 *
 * algorithms
 * Medium (54.71%)
 * Likes:    204
 * Dislikes: 0
 * Total Accepted:    38.7K
 * Total Submissions: 70.6K
 * Testcase Example:  '[1,1,1,2,2,3]'
 *
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 *
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
 *
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | two-pointers
 *
 * facebook
 *
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode80 {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) return len;

        int cnt = 1, newLen = len;
        for (int i = 1; i < newLen; ) {
            if (nums[i] == nums[i - 1]) {
                cnt++;
                i++;
            } else {
                if (cnt > 2) {
                    System.arraycopy(nums, i, nums, i - cnt + 2, len - i);
                    newLen -= (cnt - 2);
                    i -= (cnt - 2);
                } else {
                    i++;
                }
                cnt = 1;
            }
        }
        if (cnt > 2) {
            System.arraycopy(nums, newLen - 1, nums, newLen - 1 - cnt + 2, len - newLen + 1);
            newLen -= (cnt - 2);
        }
        return newLen;
    }

    /**
     * 给定 nums = [1,1,1,2,2,3],
     *
     * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void test1() {
        int n = removeDuplicates(new int[]{1, 1, 1, 2, 2, 3});
        assertThat(n, is(5));
    }

    /**
     * 给定 nums = [0,0,1,1,1,1,2,3,3],
     *
     * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void test2() {
        int n = removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3});
        assertThat(n, is(7));
    }

    @Test
    public void test3() {
        int n = removeDuplicates(new int[]{1, 1, 1});
        assertThat(n, is(2));
    }
}