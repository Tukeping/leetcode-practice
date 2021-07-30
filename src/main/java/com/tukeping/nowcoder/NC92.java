package com.tukeping.nowcoder;

/**
 * @author tukeping
 * @date 2021/7/29
 **/
public class NC92 {

    public String LCS(String s1, String s2) {
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) return "-1";

        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        for (int i = m, j = n; dp[i][j] >= 1; ) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                ret.append(s1.charAt(i - 1));
                // 对角线移动
                i--;
                j--;
            } else if (dp[i - 1][j] == dp[i][j - 1]) { // 对角线移动
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) { // 上移
                i--;
            } else { // dp[i-1][j] < dp[i][j-1] // 平移
                j--;
            }
        }
        ret.reverse();
        return ret.length() == 0 ? "-1" : ret.toString();
    }
}
