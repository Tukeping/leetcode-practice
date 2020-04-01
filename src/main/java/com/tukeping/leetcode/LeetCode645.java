package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=645 lang=java
 *
 * [645] 错误的集合
 *
 * https://leetcode-cn.com/problems/set-mismatch/description/
 *
 * algorithms
 * Easy (41.36%)
 * Likes:    78
 * Dislikes: 0
 * Total Accepted:    11.6K
 * Total Submissions: 27.9K
 * Testcase Example:  '[1,2,2,4]'
 *
 * 集合 S 包含从1到 n
 * 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 *
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1:
 *
 *
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 *
 *
 * 注意:
 *
 *
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 *
 *
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/1
 **/
public class LeetCode645 {

    public int[] findErrorNums(int[] nums) {
        int xor = 0, xor0 = 0, xor1 = 0;
        for (int n : nums)
            xor ^= n;
        for (int i = 1; i <= nums.length; i++)
            xor ^= i;
        int rightMostBit = xor & -xor;
        for (int n : nums) {
            if ((n & rightMostBit) != 0)
                xor1 ^= n;
            else
                xor0 ^= n;
        }
        for (int i = 1; i <= nums.length; i++) {
            if ((i & rightMostBit) != 0)
                xor1 ^= i;
            else
                xor0 ^= i;
        }
        for (int num : nums) {
            if (num == xor0)
                return new int[]{xor0, xor1};
        }
        return new int[]{xor1, xor0};
    }

    public int[] findErrorNums4(int[] nums) {
        Arrays.sort(nums);
        int dup = -1, missing = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                dup = nums[i];
            else if (nums[i] > nums[i - 1] + 1)
                missing = nums[i - 1] + 1;
        }
        return new int[]{dup, nums[nums.length - 1] != nums.length ? nums.length : missing};
    }

    public int[] findErrorNums3(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] ans = new int[2];
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ans[0] = nums[i];
                if (i < len - 2) {
                    System.arraycopy(nums, i + 2, nums, i + 1, len - i - 2);
                }
                break;
            }
        }
        for (int i = 1; i <= len; i++) {
            if (nums[i - 1] != i) {
                ans[1] = i;
                break;
            }
        }
        return ans;
    }

    /** time: O(n) space: O(n) **/
    public int[] findErrorNums2(int[] nums) {
        int len = nums.length;

        int[] idxNums = new int[len];
        for (int i = 1; i <= len; i++)
            idxNums[i - 1] = i;

        int[] finalNums = new int[len * 2];
        System.arraycopy(nums, 0, finalNums, 0, len);
        System.arraycopy(idxNums, 0, finalNums, len, len);

        int[] ans = findNumber(finalNums);
        int[] ret = new int[2];

        for (int n : nums) {
            if (n == ans[0]) {
                ret = ans;
                break;
            } else if (n == ans[1]) {
                ret[0] = ans[1];
                ret[1] = ans[0];
                break;
            }
        }
        return ret;
    }

    private int[] findNumber(int[] nums) {
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
     * 输入: nums = [1,2,2,4]
     * 输出: [2,3]
     */
    @Test
    public void test1() {
        int[] ans = findErrorNums(new int[]{1, 2, 2, 4});
        assertThat(ans, is(new int[]{2, 3}));
    }

    @Test
    public void test2() {
        int[] ans = findErrorNums(new int[]{1, 5, 3, 2, 2, 7, 6, 4, 8, 9});
        assertThat(ans, is(new int[]{2, 10}));
    }
}
