package com.tukeping.leetcode.contest116;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2020/4/8
 **/
public class LeetCode961 {

    public int repeatedNTimes(int[] A) {
        int n = A.length;
        int half = n / 2;
        Arrays.sort(A);
        int cnt = 1;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1]) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (cnt == half) {
                return A[i];
            }
        }
        return 0;
    }
}
