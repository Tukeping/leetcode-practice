package com.tukeping.leetcode.problems;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2021/8/17
 **/
public class LeetCode204 {

    public int countPrimes(int n) {
        if (n <= 2) return 0;
        int i = 3, sqrtn = (int) Math.sqrt(n), count = n / 2;
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);
        while (i <= sqrtn) {
            for (int j = i * i; j < n; j += 2 * i) {
                if (prime[j]) {
                    --count;
                    prime[j] = false;
                }
            }
            do {
                i += 2;
            } while (i <= sqrtn && !prime[i]);
        }
        return count;
    }
}
