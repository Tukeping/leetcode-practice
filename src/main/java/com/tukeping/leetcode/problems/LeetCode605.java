package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/15
 **/
public class LeetCode605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int zeroCount = 0, ans = 0, len = flowerbed.length;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 0) {
                zeroCount++;
            } else if (zeroCount > 0) {
                if (i - zeroCount > 0) zeroCount--;
                ans += zeroCount / 2;
                zeroCount = 0;
            }
        }
        if (zeroCount == len) {
            ans += (zeroCount + 1) / 2;
        } else if (zeroCount > 0) {
            ans += zeroCount / 2;
        }
        return ans >= n;
    }
}
