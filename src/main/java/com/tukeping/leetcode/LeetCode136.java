package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=136 lang=java
 *
 * [136] 只出现一次的数字
 *
 * https://leetcode-cn.com/problems/single-number/description/
 *
 * algorithms
 * Easy (64.49%)
 * Likes:    1081
 * Dislikes: 0
 * Total Accepted:    153.6K
 * Total Submissions: 234.5K
 * Testcase Example:  '[2,2,1]'
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 *
 *
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table | bit-manipulation
 *
 * airbnb | palantir
 *
 * @author tukeping
 * @date 2020/2/29
 **/
public class LeetCode136 {

    public int singleNumber(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, null);
            }
        }

        Set<Integer> keys = map.keySet();
        return keys.iterator().next();
    }

    /**
     * 输入: [2,2,1]
     * 输出: 1
     */
    @Test
    public void test1() {
        int n = singleNumber(new int[]{2, 2, 1});
        assertThat(n, is(1));
    }

    /**
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */
    @Test
    public void test2() {
        int n = singleNumber(new int[]{4, 1, 2, 1, 2});
        assertThat(n, is(4));
    }
}
