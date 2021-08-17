package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/16
 **/
public class LeetCode633 {

    public boolean judgeSquareSum(int c) {
        int l = 0, r = (int) Math.sqrt(c);
        while (l <= r) {
            int val = l * l + r * r;
            if (val == c) {
                return true;
            } else if (val > c) {
                r--;
            } else {
                l++;
            }
        }
        return false;
    }
}
