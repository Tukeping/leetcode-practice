package com.tukeping.nowcoder;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC17 {
    public int getLongestPalindrome(String A, int n) {
        char[] c = A.toCharArray();
        boolean[] dp = new boolean[n];
        int max = 0;
        for (int end = 1; end < n; end++) {
            for (int start = 0; start < end; start++) {
                dp[start] = (c[start] == c[end]) && (end - start + 1 <= 3 || dp[start + 1]);
                if (dp[start]) {
                    max = Math.max(max, end - start + 1);
                }
            }
        }
        return max;
    }
}
