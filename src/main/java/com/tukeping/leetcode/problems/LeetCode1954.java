package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/2
 **/
public class LeetCode1954 {

    public long minimumPerimeter(long neededApples) {
        long l = 1L, r = 100000L, ans = 100000000000000L;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (calc(mid) >= neededApples) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans * 8;
    }

    private long calc(long x) {
        return 2 * (2 * x + 1) * (1 + x) * x;
    }
}
