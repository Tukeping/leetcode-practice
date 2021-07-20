package com.tukeping.leetcode.problems;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/19
 **/
public class LeetCode704 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return nums[l] == target ? l : -1;
    }

    @Test
    public void test() {
        int actual = search(new int[]{-1, 0, 3, 5, 9, 12}, 2);
        Assert.assertEquals(-1, actual);
    }
}
