package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=448 lang=java
 *
 * [448] 找到所有数组中消失的数字
 *
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/description/
 *
 * algorithms
 * Easy (54.59%)
 * Likes:    288
 * Dislikes: 0
 * Total Accepted:    28K
 * Total Submissions: 49.3K
 * Testcase Example:  '[4,3,2,7,8,2,3,1]'
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 *
 */

import com.tukeping.tools.Arrays;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * array
 *
 * google
 *
 * @author tukeping
 * @date 2020/3/3
 **/
public class LeetCode448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null) return null;
        if (nums.length == 0) return Collections.emptyList();

        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int p = Math.abs(nums[i]) - 1;
            nums[p] = -Math.abs(nums[p]);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }

    /**
     * 输入:
     * [4,3,2,7,8,2,3,1]
     * 输出:
     * [5,6]
     */
    @Test
    public void test1() {
        int[] actual = Arrays.asArray(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        int[] expect = {5, 6};
        Arrays.check(actual, expect);
    }
}
