package com.tukeping.leetcode.contest.weekly.w250;

/**
 * @author tukeping
 * @date 2021/7/19
 **/
public class LeetCode5814 {

    public int addRungs(int[] rungs, int dist) {
        int cur = 0, next, diff;
        int ans = 0;
        for (int i = 0; i < rungs.length; i++) {
            next = rungs[i];
            diff = next - cur;
            if (dist >= diff) {
            } else {
                ans += diff / dist;
                if (diff % dist == 0) {
                    ans--;
                }
            }
            cur = next;
        }
        return ans;
    }
}
