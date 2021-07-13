package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 *
 * https://leetcode-cn.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (47.08%)
 * Likes:    7103
 * Dislikes: 0
 * Total Accepted:    718.7K
 * Total Submissions: 1.5M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */

import org.junit.Test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | hash-table
 *
 * adobe | airbnb | amazon | apple | bloomberg | dropbox | facebook | linkedin | microsoft | uber | yahoo | yelp
 *
 * frequency 5
 *
 * @author tukeping
 * @since 2018/12/15
 **/
public class LeetCode1 {

    public int countPrimesOptimize(int n) {
        if (n <= 2) {
            return 0;
        }
        BitSet bit = new BitSet(n);
        for (int i = 2; i * i < n; i++) {
            if (!bit.get(i)) {
                for (int j = i * i; j < n; j += i) {
                    bit.set(j);
                }
            }
        }
        return n - 2 - bit.cardinality();//排除n和1
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[0];
    }

    /** TimeLimited time: O(n^2) space: O(1) **/
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    @Test
    public void test1() {
        int[] nums = {2, 7, 11, 15};
        int[] ans = twoSum(nums, 9);
        assertThat(ans, is(new int[]{0, 1}));
    }

    @Test
    public void test2() {
        int[] nums = {3, 2, 4};
        int[] ans = twoSum(nums, 6);
        assertThat(ans, is(new int[]{1, 2}));
    }

    @Test
    public void test3() {
        int[] nums = {2, 5, 5, 11};
        int[] ans = twoSum(nums, 10);
        assertThat(ans, is(new int[]{1, 2}));
    }
}
