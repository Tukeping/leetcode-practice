package com.tukeping.leetcode.contest.weekly.contest249;

/**
 * @author tukeping
 * @date 2021/7/14
 **/
public class LeetCode1929 {

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n * 2];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i];
        }
        for (int i = n; i < 2 * n; i++) {
            ret[i] = nums[i - n];
        }
        return ret;
    }
}
