package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=27 lang=java
 *
 * [27] 移除元素
 *
 * https://leetcode-cn.com/problems/remove-element/description/
 *
 * algorithms
 * Easy (56.80%)
 * Likes:    483
 * Dislikes: 0
 * Total Accepted:    135.1K
 * Total Submissions: 235.3K
 * Testcase Example:  '[3,2,2,3]\n3'
 *
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 *
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 示例 2:
 *
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *
 * 注意这五个元素可为任意顺序。
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
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | two-pointers
 *
 * Unknown
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode27 {

    public int removeElement(int[] nums, int val) {
        int cur, gap, srcPos, destPos, moveLen, newLen = nums.length, restLen = newLen;

        for (cur = 0, gap = 0; restLen > 0; cur++, restLen--, gap = 0) {
            if (nums[cur] != val) {
                continue;
            }
            srcPos = cur;
            while (cur + gap < newLen && nums[cur + gap] == val) {
                gap++;
                restLen--;
            }
            destPos = cur + gap;
            moveLen = destPos - srcPos;
            System.arraycopy(nums, destPos, nums, srcPos, restLen);
            newLen -= moveLen;
        }

        return newLen;
    }

    /**
     * 给定 nums = [3,2,2,3], val = 3,
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void test1() {
        int len = removeElement(new int[]{3, 2, 2, 3}, 3);
        assertThat(len, is(2));
    }

    /**
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * 注意这五个元素可为任意顺序。
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void test2() {
        int len = removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2);
        assertThat(len, is(5));
    }

    @Test
    public void test3() {

    }
}