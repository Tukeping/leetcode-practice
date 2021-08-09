package com.tukeping.leetcode.problems;

import java.util.Arrays;

/**
 * LIS
 *
 * @author tukeping
 * @date 2021/8/8
 **/
public class LeetCode5841 {

    /**
     * LIS的原理
     * 假设原数组为 4, 5, 1, 3, 9
     * 我们可以先预设一个全是INF（无限大）的数组num: INF, INF, INF, INF, INF
     * 然后我们用二分查找依次找出num中第一个大于数组元素的位置，把该位置替换成该元素
     *
     * 遍历到4的时候:
     * 4， INF，INF，INF， INF          || 此时以4结尾最长上升子序列长度为 1
     * 遍历到5的时候:
     * 4， 5， INF， INF， INF          || 此时以5结尾最长上升子序列长度为 2
     * 遍历到1的时候:
     * 1， 5， INF， INF， INF          || 此时以1结尾最长上升子序列长度为 1
     * 遍历到3的时候:
     * 1， 3， INF， INF， INF          || 此时以3结尾最长上升子序列长度为 2
     * 遍历到9的时候:
     * 1， 3， 9，   INF， INF          || 此时以9结尾最长上升子序列长度为 3
     */
    public int[] longestObstacleCourseAtEachPosition(int[] nums) {
        int n = nums.length;
        int[] S = new int[n];
        Arrays.fill(S, Integer.MAX_VALUE);
        int[] LIS = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = bsearch(S, nums[i]);
            S[idx] = nums[i];
            LIS[i] = idx + 1;
        }
        return LIS;
    }

    private int bsearch(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int[] longestObstacleCourseAtEachPositionDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] <= nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }
}
