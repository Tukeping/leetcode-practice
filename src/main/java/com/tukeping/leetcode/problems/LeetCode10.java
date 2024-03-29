package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (25.44%)
 * Likes:    882
 * Dislikes: 0
 * Total Accepted:    47.4K
 * Total Submissions: 184.5K
 * Testcase Example:  '"aa"\n"a"'
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 *
 *
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 *
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 *
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 *
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 *
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 *
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/1/22
 **/
public class LeetCode10 {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && p.charAt(j - 1) == s.charAt(i - 1);
                } else if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                    dp[i][j] = dp[i][j - 2];
                } else {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i][j - 2];
                }
            }
        }
        return dp[m][n];
    }

    private String s, p;
    private int sLen, pLen;

    public boolean isMatchV2(String s, String p) {
        this.s = s;
        this.p = p;
        this.sLen = s.length();
        this.pLen = p.length();
        return isMatch(0, 0);
    }

    private boolean isMatch(int sIdx, int pIdx) {
        // 正则表达式串如果匹配完了，查看字符串如果也匹配完了则表示 这是一个正确的匹配
        if (pIdx == pLen) return sIdx == sLen;
        boolean matched = sIdx < sLen
                && (p.charAt(pIdx) == s.charAt(sIdx) || p.charAt(pIdx) == '.');
        if (pIdx < p.length() - 1 && p.charAt(pIdx + 1) == '*') {
            return isMatch(sIdx, pIdx + 2) || (matched && isMatch(sIdx + 1, pIdx));
        } else {
            return matched && isMatch(sIdx + 1, pIdx + 1);
        }
    }

    private boolean[][] memo;

    public boolean isMatch0(String s, String p) {
        memo = new boolean[s.length() + 1][p.length() + 1];
        return isMatch(0, 0, s, p);
    }

    private boolean isMatch(int sIndex, int pIndex, String s, String p) {
        if (memo[sIndex][pIndex]) return true;

        boolean ans;
        // 当正则表达式的指针到达最末尾后 判断字符串的指针是否也在末尾，如果是末尾则表示匹配正确，否则未匹配成功
        if (pIndex == p.length()) {
            ans = (sIndex == s.length());
        } else {
            boolean matched = sIndex < s.length() &&
                    (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '.');
            // 判断正则字符串中当前指针的下一个指针的字符是*号时需要特殊处理
            if (p.length() > pIndex + 1 && p.charAt(pIndex + 1) == '*') {
                ans = isMatch(sIndex, pIndex + 2, s, p) ||
                        (matched && isMatch(sIndex + 1, pIndex, s, p));
            } else {
                ans = matched && isMatch(sIndex + 1, pIndex + 1, s, p);
            }
        }

        memo[sIndex][pIndex] = ans;
        return ans;
    }

    @Test
    public void test1() {
        String s = "aa";
        String p = "a";
        assertFalse(isMatch(s, p));
    }

    @Test
    public void test2() {
        String s = "aa";
        String p = "a*";
        assertTrue(isMatch(s, p));
    }

    @Test
    public void test3() {
        String s = "ab";
        String p = ".*";
        assertTrue(isMatch(s, p));
    }

    @Test
    public void test4() {
        String s = "aab";
        String p = "c*a*b";
        assertTrue(isMatch(s, p));
    }

    @Test
    public void test5() {
        String s = "mississippi";
        String p = "mis*is*p*.";
        assertFalse(isMatch(s, p));
    }

    @Test
    public void test6() {
        String s = "";
        String p = ".*";
        assertTrue(isMatch(s, p));
    }

    @Test
    public void test7() {
        String s = "aa";
        String p = "a*b*c*d*";
        assertTrue(isMatch(s, p));
    }
}
