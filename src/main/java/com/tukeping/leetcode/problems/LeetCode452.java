package com.tukeping.leetcode.problems;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2021/8/15
 **/
public class LeetCode452 {

    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        Arrays.sort(points, (o1, o2) -> {
            if (o1[1] > o2[1]) return 1;
            else if (o1[1] < o2[1]) return -1;
            else return 0;
        });
        int prevEnd = points[0][1];
        int ans = 1;
        for (int[] cur : points) {
            if (cur[0] > prevEnd) {
                prevEnd = cur[1];
                ans++;
            }
        }
        return ans;
    }
}
