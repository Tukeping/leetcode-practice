package com.tukeping.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2021/7/21
 **/
public class LeetCode350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] numbers = new int[1000];
        for (int num : nums1) {
            numbers[num]++;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (numbers[nums2[i]] > 0) {
                ret.add(nums2[i]);
                numbers[nums2[i]]--;
            }
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }
}
