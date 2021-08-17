package com.tukeping.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2021/8/15
 **/
public class LeetCode763 {

    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> ret = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                ret.add(end - start + 1);
                start = end + 1;
            }
        }
        return ret;
    }
}
