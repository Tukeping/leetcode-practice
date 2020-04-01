package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=137 lang=java
 *
 * [137] 只出现一次的数字 II
 *
 * https://leetcode-cn.com/problems/single-number-ii/description/
 *
 * algorithms
 * Medium (66.01%)
 * Likes:    287
 * Dislikes: 0
 * Total Accepted:    24.4K
 * Total Submissions: 36.8K
 * Testcase Example:  '[2,2,3,2]'
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,3,2]
 * 输出: 3
 *
 *
 * 示例 2:
 *
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * bit-manipulation
 *
 * @author tukeping
 * @date 2020/4/1
 **/
public class LeetCode137 {

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            int bit = 1 << i;
            for (int num : nums) {
                if ((num & bit) != 0)
                    cnt++;
            }
            if (cnt % 3 != 0)
                ans |= bit;
        }
        return ans;
    }

    /**
     * 输入: [2,2,3,2]
     * 输出: 3
     */
    @Test
    public void test1() {
        int n = singleNumber(new int[]{2, 2, 3, 2});
        assertThat(n, is(3));
    }

    /**
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     */
    @Test
    public void test2() {
        int n = singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99});
        assertThat(n, is(99));
    }
}
