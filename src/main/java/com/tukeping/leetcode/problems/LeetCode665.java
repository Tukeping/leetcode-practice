package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/15
 **/
public class LeetCode665 {

    public boolean checkPossibility(int[] nums) {
        int count = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) { // 要么缩小nums[i-1]，要么放大nums[i]
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    nums[i - 1] = nums[i]; // 缩小nums[i-1]，因为nums[i]比nums[i-2]大
                } else {
                    nums[i] = nums[i - 1]; // 放大nums[i]，因为nums[i]太小了比nums[i-2]还小
                }
                if (++count > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
