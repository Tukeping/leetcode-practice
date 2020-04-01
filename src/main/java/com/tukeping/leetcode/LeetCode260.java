package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=260 lang=java
 *
 * [260] 只出现一次的数字 III
 *
 * https://leetcode-cn.com/problems/single-number-iii/description/
 *
 * algorithms
 * Medium (70.53%)
 * Likes:    189
 * Dislikes: 0
 * Total Accepted:    17K
 * Total Submissions: 24K
 * Testcase Example:  '[1,2,1,3,2,5]'
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 *
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 *
 * 注意：
 *
 *
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * bit-manipulation
 *
 * @author tukeping
 * @date 2020/4/1
 **/
public class LeetCode260 {

    public int[] singleNumber(int[] nums) {
        int ans = 0;
        for (int n : nums)
            ans ^= n;
        int h = 1;
        while ((ans & h) == 0)
            h <<= 1;
        int a = 0, b = 0;
        for (int n : nums) {
            if ((n & h) == 0)
                a ^= n;
            else
                b ^= n;
        }
        return new int[]{a, b};
    }

    /**
     * 输入: [1,2,1,3,2,5]
     * 输出: [3,5]
     */
    @Test
    public void test1() {
        int[] ans = singleNumber(new int[]{1, 2, 1, 3, 2, 5});
        assertThat(ans, anyOf(is(new int[]{3, 5}), is(new int[]{5, 3})));
    }
}
