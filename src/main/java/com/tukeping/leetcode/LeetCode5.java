package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (27.85%)
 * Likes:    1811
 * Dislikes: 0
 * Total Accepted:    195.2K
 * Total Submissions: 679.1K
 * Testcase Example:  '"babad"'
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;

/**
 * string | dynamic-programming
 *
 * amazon | bloomberg | microsoft
 *
 * @author tukeping
 * @date 2020/3/3
 **/
public class LeetCode5 {

    /** Manacher's Algorithm time: O(n) space: O(n) **/
    public String longestPalindrome6(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int i_mirror = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[i_mirror]); // 防止超出 R
            } else {
                P[i] = 0; // 等于 R 的情况
            }

            // 碰到之前讲的三种情况时候，需要利用中心扩展法
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // 判断是否需要更新 R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // 找出 P 的最大值
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标

        return s.substring(start, start + maxLen);
    }

    public String preProcess(String s) {
        int n = s.length();
        if (n == 0) return "^$";
        String ret = "^";
        for (int i = 0; i < n; i++) ret += "#" + s.charAt(i);
        ret += "#$";
        return ret;
    }

    /** ExpandAroundCenter's Algorithm time: O(n^2) space: O(1) **/
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        int start = 0, maxLen = 1, len = s.length();
        for (int i = 0; i < len; i++) { // `i` is split middle point
            int len1 = expandAroundCenter(s, len, i, i);
            int len2 = expandAroundCenter(s, len, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                start = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int expandAroundCenter(String s, int len, int L, int R) {
        while (L >= 0 && R < len && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return (R - L + 1) - 2; // R - L - 1
    }

    /** DP time: O(n^2) space: O(n) **/
    public String longestPalindrome4(String s) {
        if (s == null || s.length() <= 1) return s;
        int start = 0, maxLen = 1, len = s.length();
        boolean[] dp = new boolean[len];
        for (int j = 1; j < len; j++) {   // j 是 结束处的索引下标
            for (int i = 0; i < j; i++) { // i 是 开始处的索引下标
                // 状态转换方程
                // 需要说明一下: 如果首位两个字符一样, 则需要判断内部字符串是否是回文,
                // 而 dp[i+1] 恰好表示的就是 开始+1的内部索引下标处的是否为回文的标记信息
                dp[i] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1]);
                if (dp[i] && j - i + 1 > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /** DP time: O(n^2) space: O(n^2) **/
    public String longestPalindrome3(String s) {
        if (s == null || s.length() <= 1) return s;
        int start = 0, maxLen = 1, len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && (j - i + 1) > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /** DP time: O(n^2) space: O(n^2) **/
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) return s;
        int start = 0, maxLen = 1, len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && (j - i + 1) > maxLen) {
                        start = i;
                        maxLen = j - i + 1;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /** BruteForce time: O(n^3) space: O(1) **/
    public String longestPalindrome1(String s) {
        if (s == null || s.length() <= 1) return s;
        int len = s.length(), start = 0, maxLen = 1;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((j - i + 1) > maxLen && isPalindrome(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i <= end && j >= start; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    /**
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     */
    @Test
    public void test1() {
        String res = longestPalindrome("babad");
        assertThat(res, anyOf(is("bab"), is("aba")));
    }

    /**
     * 输入: "cbbd"
     * 输出: "bb"
     */
    @Test
    public void test2() {
        String res = longestPalindrome("cbbd");
        assertThat(res, is("bb"));
    }

    @Test
    public void test3() {
        String res = longestPalindrome("ac");
        assertThat(res, anyOf(is("a"), is("c")));
    }

    @Test
    public void test4() {
        String res = longestPalindrome("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println(res);
    }

    @Test
    public void test5() {
        String res = longestPalindrome("ccc");
        assertThat(res, is("ccc"));
    }

    @Test
    public void test6() {
        String res = longestPalindrome("aaaa");
        assertThat(res, is("aaaa"));
    }

    @Test
    public void test7() {
        String res = longestPalindrome("abcba");
        assertThat(res, is("abcba"));
    }
}
