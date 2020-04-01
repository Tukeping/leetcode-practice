package com.tukeping.leetcode.contest182;

/*
 * 5371. 找到所有好字符串
 *
 * 给你两个长度为 n 的字符串 s1 和 s2 ，以及一个字符串 evil 。请你返回 好字符串 的数目。
 *
 * 好字符串 的定义为：它的长度为 n ，字典序大于等于 s1 ，字典序小于等于 s2 ，且不包含 evil 为子字符串。
 *
 * 由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2, s1 = "aa", s2 = "da", evil = "b"
 * 输出：51
 * 解释：总共有 25 个以 'a' 开头的好字符串："aa"，"ac"，"ad"，...，"az"。还有 25 个以 'c' 开头的好字符串："ca"，"cc"，"cd"，...，"cz"。最后，还有一个以 'd' 开头的好字符串："da"。
 * 示例 2：
 *
 * 输入：n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
 * 输出：0
 * 解释：所有字典序大于等于 s1 且小于等于 s2 的字符串都以 evil 字符串 "leet" 开头。所以没有好字符串。
 * 示例 3：
 *
 * 输入：n = 2, s1 = "gx", s2 = "gz", evil = "x"
 * 输出：2
 *
 *
 * 提示：
 *
 * s1.length == n
 * s2.length == n
 * 1 <= n <= 500
 * 1 <= evil.length <= 50
 * 所有字符串都只包含小写英文字母。
 */

/**
 * @author tukeping
 * @date 2020/3/29
 **/
public class LeetCode5371 {

    private int MOD = 1000000007;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        int[] mp = mpTable(evil.toCharArray());
        long ret = count(s2.toCharArray(), n, mp, evil.toCharArray()) - count(s1.toCharArray(), n, mp, evil.toCharArray()) + MOD;
        if (s1.indexOf(evil) < 0) ret++;
        return (int) (ret % MOD);
    }

    long count(char[] s, int n, int[] mp, char[] needle) {
        int m = mp.length - 1;
        long[] dp = new long[m];
        dp[0] = 1;
        int edge = 0;
        for (int i = 0; i < n; i++) {
            long[] ndp = new long[m];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 26; k++) {
                    int p = j;
                    while (p >= 0 && needle[p] - 'a' != k) {
                        p = mp[p];
                    }
                    if (++p < needle.length) {
                        ndp[p] += dp[j];
                        if (ndp[p] >= MOD) ndp[p] -= MOD;
                    }
                }
            }
            if (edge != needle.length) {
                for (int k = 0; k < s[i] - 'a'; k++) {
                    int p = edge;
                    while (p >= 0 && needle[p] - 'a' != k) {
                        p = mp[p];
                    }
                    if (++p < needle.length) {
                        ndp[p] += 1;
                        if (ndp[p] >= MOD) ndp[p] -= MOD;
                    }
                }

                int p = edge;
                while (p >= 0 && needle[p] != s[i]) {
                    p = mp[p];
                }
                ++p;
                edge = p;
            }

            dp = ndp;
        }

        long ret = 0;
        if (edge != needle.length) ret++;
        for (long v : dp) ret += v;
        return ret % MOD;
    }

    public int[] mpTable(char[] str) {
        int n = str.length;
        int[] mp = new int[n + 1];
        mp[0] = -1;
        for (int i = 1, j = 0; i < n; i++) {
            while (j >= 0 && str[i] != str[j]) j = mp[j];
            mp[i + 1] = ++j;
        }
        return mp;
    }
}
