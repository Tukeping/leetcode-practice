package com.tukeping.leetcode.problems;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tukeping
 * @date 2021/7/19
 **/
public class LeetCode217 {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            else set.add(num);
        }
        return false;
    }
}
