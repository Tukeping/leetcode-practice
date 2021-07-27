package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/27
 **/
public class LeetCode1014 {

    public int maxScoreSightseeingPair(int[] nums) {
        // nums[i] + nums[j] + i - j => (nums[i] + i) + (nums[j] - j)
        int n = nums.length;
        int ans = 0, max = nums[0];
        for (int j = 1; j < n; j++) {
            ans = Math.max(ans, max + nums[j] - j);
            max = Math.max(max, nums[j] + j);
            // for(int p1 = i; p1 >= 0; p1--) {
            //     for(int p2 = p1 - 1; p2 >= 0; p2--) {
            //         int val = nums[p1] + nums[p2] - (p1 - p2);
            //         max = Math.max(max, val);
            //     }
            // }
        }
        return ans;
    }
}
