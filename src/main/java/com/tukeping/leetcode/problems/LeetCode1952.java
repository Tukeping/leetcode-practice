package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/2
 **/
public class LeetCode1952 {

    public boolean isThree(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) cnt++;
        }
        return cnt == 3;
    }
}
