package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/21
 **/
public class LeetCode167 {

    public int[] twoSumV3(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n - 1;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum == target) {
                return new int[]{start + 1, end + 1};
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[0];
    }

    public int[] twoSumV2(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int val = target - num;
            int idx = bsearchV2(nums, i + 1, n - 1, val);
            if (idx != -1) {
                return new int[]{i + 1, idx + 1};
            }
        }
        return new int[0];
    }

    private int bsearchV2(int[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return nums[l] == target ? l : -1;
    }

    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int findNum = target - nums[i];
            int findNumIndex = bsearch(nums, i + 1, n - 1, findNum);
            if (findNumIndex != -1) {
                return new int[]{i + 1, findNumIndex + 1};
            }
        }
        return new int[2];
    }

    private int bsearch(int[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return nums[l] == target ? l : -1;
    }
}
