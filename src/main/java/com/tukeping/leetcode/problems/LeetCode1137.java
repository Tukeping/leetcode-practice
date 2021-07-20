package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/19
 **/
public class LeetCode1137 {

    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int preprepre = 0;
        int prepre = 1;
        int pre = 1;
        int cur = 0;
        for (int i = 3; i <= n; i++) {
            cur = preprepre + prepre + pre;
            preprepre = prepre;
            prepre = pre;
            pre = cur;
        }
        return cur;
    }
}
