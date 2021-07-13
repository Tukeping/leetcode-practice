package com.tukeping.leetcode.problems;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/3/24
 **/
public class LeetCode44 {

    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // If the pattern caracter = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                ++sIdx;
                ++pIdx;
            }
            // If pattern character = '*'
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was no '*' character in pattern
            else if (starIdx == -1) {
                return false;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was '*' character in pattern before
            else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for (int i = pIdx; i < pLen; i++)
            if (p.charAt(i) != '*') return false;
        return true;
    }

    public boolean isMatch2(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (p.equals(s) || p.equals("*")) return true;
        if (p.isEmpty() || s.isEmpty()) return false;

        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;

        for (int pIdx = 1; pIdx <= pLen; pIdx++) {
            if (p.charAt(pIdx - 1) == '*') {
                int sIdx = 1;
                while ((!dp[pIdx - 1][sIdx - 1]) && (sIdx < sLen + 1)) sIdx++;
                dp[pIdx][sIdx - 1] = dp[pIdx - 1][sIdx - 1];
                while (sIdx < sLen + 1) dp[pIdx][sIdx++] = true;
            } else if (p.charAt(pIdx - 1) == '?') {
                for (int sIdx = 1; sIdx <= sLen; sIdx++)
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1];
            } else {
                for (int sIdx = 1; sIdx <= sLen; sIdx++) {
                    dp[pIdx][sIdx] = dp[pIdx - 1][sIdx - 1]
                            && p.charAt(pIdx - 1) == s.charAt(sIdx - 1);
                }
            }
        }

        return dp[pLen][sLen];
    }

    /**
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     */
    @Test
    public void test1() {
        assertFalse(isMatch("aa", "a"));
    }

    /**
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释: '*' 可以匹配任意字符串。
     */
    @Test
    public void test2() {
        assertTrue(isMatch("aa", "*"));
    }

    /**
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     */
    @Test
    public void test3() {
        assertFalse(isMatch("cb", "?a"));
    }

    /**
     * 输入:
     * s = "adceb"
     * p = "*a*b"
     * 输出: true
     * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     */
    @Test
    public void test4() {
        assertTrue(isMatch("adceb", "*a*b"));
    }

    /**
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输入: false
     */
    @Test
    public void test5() {
        assertFalse(isMatch("acdcb", "a*c?b"));
    }
}
