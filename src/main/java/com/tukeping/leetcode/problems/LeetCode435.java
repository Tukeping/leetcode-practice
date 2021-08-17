package com.tukeping.leetcode.problems;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2021/8/15
 **/
public class LeetCode435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
        int n = intervals.length, prevEnd = intervals[0][1];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] < prevEnd) {
                ans++;
            } else {
                prevEnd = intervals[i][1];
            }
        }
        return ans;
    }
}
