package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=18 lang=java
 *
 * [18] 四数之和
 *
 * https://leetcode-cn.com/problems/4sum/description/
 *
 * algorithms
 * Medium (37.39%)
 * Likes:    427
 * Dislikes: 0
 * Total Accepted:    68.6K
 * Total Submissions: 183.5K
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 * ⁠ [-1,  0, 0, 1],
 * ⁠ [-2, -1, 1, 2],
 * ⁠ [-2,  0, 0, 2]
 * ]
 *
 *
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * array | hash-table | two-pointers
 *
 * linkedin
 *
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode18 {

    /** time: O(n^3) space: O(1) **/
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int start = j + 1, end = len - 1;
                while (start < end) {
                    int fourSum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (fourSum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        while (start < end && nums[start + 1] == nums[start])
                            start++;
                        while (start < end && nums[end - 1] == nums[end])
                            end--;
                        start++;
                        end--;
                    } else if (fourSum > target) {
                        end--;
                    } else { // fourSum < target
                        start++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     */
    @Test
    public void test1() {
        List<List<Integer>> res = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {-1, 0, 0, 1},
                {-2, -1, 1, 2},
                {-2, 0, 0, 2}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    @Test
    public void test2() {
        List<List<Integer>> res = fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {-3, -2, 2, 3},
                {-3, -1, 1, 3},
                {-3, 0, 0, 3},
                {-3, 0, 1, 2},
                {-2, -1, 0, 3},
                {-2, -1, 1, 2},
                {-2, 0, 0, 2},
                {-1, 0, 0, 1}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    @Test
    public void test3() {
        List<List<Integer>> res = fourSum(new int[]{0, 0, 0, 0}, 0);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {0, 0, 0, 0}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }
}
