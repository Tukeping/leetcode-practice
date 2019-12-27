package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1248 lang=java
 *
 * [1248] 统计「优美子数组」
 *
 * https://leetcode-cn.com/problems/count-number-of-nice-subarrays/description/
 *
 * algorithms
 * Medium (46.83%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    1.9K
 * Total Submissions: 4K
 * Testcase Example:  '[1,1,2,1,1]\n3'
 *
 * 给你一个整数数组 nums 和一个整数 k。
 *
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 *
 * 请返回这个数组中「优美子数组」的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 *
 *
 * 示例 2：
 *
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 *
 *
 * 示例 3：
 *
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个整数数组 nums 和一个整数 k。
 *
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 *
 * 请返回这个数组中「优美子数组」的数目。
 *
 * @author tukeping
 * @date 2019/11/4
 **/
public class LeetCode1248W {

    /**
     * 输入：nums = [1,1,2,1,1], k = 3
     * 输出：2
     * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
     *
     * 1 1 2 1 1
     * (i + 1) * (len - j) = 1 * 2 = 2
     */
    @Test
    public void test1() {
        int[] nums = new int[]{1,1,2,1,1};
        int k = 3;

        int n = numberOfSubarrays(nums, k);

        Assert.assertEquals(2, n);
    }

    /**
     * 输入：nums = [2,4,6], k = 1
     * 输出：0
     * 解释：数列中不包含任何奇数，所以不存在优美子数组。
     */
    @Test
    public void test2() {
        int[] nums = new int[]{2,4,6};
        int k = 1;

        int n = numberOfSubarrays(nums, k);

        Assert.assertEquals(0, n);
    }

    /**
     * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * 输出：16
     *
     * 1 2 2 1
     * (i + 1) * (len - j) = 4 * 4 = 16
     */
    @Test
    public void test3() {
        int[] nums = new int[]{2,2,2,1,2,2,1,2,2,2};
        int k = 2;

        int n = numberOfSubarrays(nums, k);

        Assert.assertEquals(16, n);
    }

    /**
     * 输入：nums = [2044,96397,50143] , k = 1
     * 输出：3
     */
    @Test
    public void test4() {
        int[] nums = new int[]{2044,96397,50143};
        int k = 1;

        int n = numberOfSubarrays(nums, k);

        Assert.assertEquals(3, n);
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0, currentOddNum = 0;
        int oddIndexStart = -1, oddIndexEnd = -1;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // 找到 奇数
            if(nums[i] % 2 != 0) {
                stack.add(i);

                currentOddNum ++;

                // 第一次标记 奇数 位置
                if(oddIndexStart == -1) {
                    oddIndexStart = i;
                    stack.pop();
                }
                // 又找到了下一个 奇数 位置
                else if(oddIndexEnd != -1) {
                    // 计算子数组个数
                    count += (oddIndexStart + 1) * (i - oddIndexEnd);
                    // start后的下一个奇数的位置
                    oddIndexStart = stack.pop();
                    oddIndexEnd = i;
                }

                // 如果当前找到的奇数个数符合连续k个奇数个数, 只会命中一次
                if(currentOddNum == k) {
                    oddIndexEnd = i;
                }
            }
        }

        if(oddIndexEnd - oddIndexStart + 1 == k) {
            count += nums.length - oddIndexEnd;
        }

        return count;
    }
}
