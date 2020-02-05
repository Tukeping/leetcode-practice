package com.tukeping.cs.algorithms.backtracking;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 正则表达式
 *
 * @author tukeping
 * @date 2020/1/23
 **/
public class RegularExpression {

    public boolean matchV1(String text, String pattern) {
        // 正则表达式串如果匹配完了，查看字符串如果也匹配完了则表示 这是一个正确的匹配
        if (pattern.isEmpty()) return text.isEmpty();

        if (text.charAt(0) == pattern.charAt(0)) {
            return matchV1(text.substring(1), pattern.substring(1));
        } else {
            return false;
        }
    }

    @Test
    public void test_v1() {
        boolean b = matchV1("aaa", "aaa");
        assertTrue(b);

        b = matchV1("aaa", "aab");
        assertFalse(b);
    }

    /** 支持 '.号' 匹配 **/
    public boolean matchV2(String text, String pattern) {
        // 正则表达式串如果匹配完了，查看字符串如果也匹配完了则表示 这是一个正确的匹配
        if (pattern.isEmpty()) return text.isEmpty();

        if (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.') {
            return matchV2(text.substring(1), pattern.substring(1));
        } else {
            return false;
        }
    }

    @Test
    public void test_v2() {
        boolean b = matchV2("aaa", "aaa");
        assertTrue(b);

        b = matchV2("aaa", "aab");
        assertFalse(b);

        b = matchV2("aaa", "aa.");
        assertTrue(b);

        b = matchV2("aaa", "a.a");
        assertTrue(b);

        b = matchV2("aaa", ".aa");
        assertTrue(b);

        b = matchV2("aaa", "...");
        assertTrue(b);

        b = matchV2("aaa", "a.b");
        assertFalse(b);
    }

    /** 支持'.'号匹配和'*'号匹配 **/
    public boolean matchV3(String text, String pattern) {
        // 正则表达式串如果匹配完了，查看字符串如果也匹配完了则表示 这是一个正确的匹配
        if (pattern.isEmpty()) return text.isEmpty();

        boolean firstMatch = !text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.');

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return matchV3(text, pattern.substring(2)) ||
                    (firstMatch && matchV3(text.substring(1), pattern));
        } else {
            return firstMatch && matchV3(text.substring(1), pattern.substring(1));
        }
    }

    @Test
    public void test_v3() {
        boolean b;

        b = matchV3("aaa", "aaa");
        assertTrue(b);

        b = matchV3("aaa", "aab");
        assertFalse(b);

        b = matchV3("aaa", "aa.");
        assertTrue(b);

        b = matchV3("aaa", "a.a");
        assertTrue(b);

        b = matchV3("aaa", ".aa");
        assertTrue(b);

        b = matchV3("aaa", "...");
        assertTrue(b);

        b = matchV3("aaa", "a.b");
        assertFalse(b);

        b = matchV3("aaa", "a*");
        assertTrue(b);

        b = matchV3("aaa", "a*a*");
        assertTrue(b);

        b = matchV3("aaa", "a*a*a*");
        assertTrue(b);

        b = matchV3("aaa", "a*a*a*a*");
        assertTrue(b);
    }

    /** 使用回溯思想 编写正则表达式匹配算法 **/
    public boolean isMatchByBackTracking(String s, String p) {
        // 正则表达式串如果匹配完了，查看字符串如果也匹配完了则表示 这是一个正确的匹配
        if (p.isEmpty()) return s.isEmpty();

        boolean firstMatch = !s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatchByBackTracking(s, p.substring(2)) ||
                    (firstMatch && isMatchByBackTracking(s.substring(1), p));
        } else {
            return firstMatch && isMatchByBackTracking(s.substring(1), p.substring(1));
        }
    }

    /** 使用回溯思想 不改变原有输入字符串和正则字符串 编写正则表达式匹配算法 **/
    public boolean isMatchLeetCode(String s, String p) {
        // 正则表达式串如果匹配完了，查看字符串如果也匹配完了则表示 这是一个正确的匹配
        if (p.isEmpty()) return s.isEmpty();

        boolean firstMatch = !s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatchLeetCode(s, p.substring(2)) ||
                    (firstMatch && isMatchLeetCode(s.substring(1), p));
        } else {
            return firstMatch && isMatchLeetCode(s.substring(1), p.substring(1));
        }
    }

    private boolean[][] memo;

    public boolean isMatchDp(String s, String p) {
        if (s.isEmpty() || p.isEmpty()) return false;

        memo = new boolean[s.length() + 1][p.length() + 1];

        return dp(0, 0, s, p);
    }

    private boolean dp(int sIndex, int pIndex, String s, String p) {
        if (memo[sIndex][pIndex]) return true;

        boolean ans;

        // 当正则表达式的指针到达最末尾后 判断字符串的指针是否也在末尾，如果是末尾则表示匹配正确，否则未匹配成功
        if (pIndex == p.length()) {
            ans = (sIndex == s.length());
        } else {
            boolean match = sIndex < s.length() &&
                    (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '.');

            // 判断正则字符串中当前指针的下一个指针的字符是*号时需要特殊处理
            if (p.length() > pIndex + 1 && p.charAt(pIndex + 1) == '*') {
                ans = dp(sIndex, pIndex + 2, s, p) ||
                        (match && dp(sIndex + 1, pIndex, s, p));
            } else {
                ans = match && dp(sIndex + 1, pIndex + 1, s, p);
            }
        }

        memo[sIndex][pIndex] = ans;

        return ans;
    }

    public boolean isMatch(String s, String p) {
        return isMatchDp(s, p);
    }

    @Test
    public void test() {
        boolean b;

        /*
         * 示例 1:
         * 输入:
         * s = "aa"
         * p = "a"
         * 输出: false
         * 解释: "a" 无法匹配 "aa" 整个字符串。
         */
        b = isMatch("aa", "a");
        assertFalse(b);

        /*
         * 示例 2:
         * 输入:
         * s = "aa"
         * p = "a*"
         * 输出: true
         * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
         */
        b = isMatch("aa", "a*");
        assertTrue(b);

        /*
         * 示例 3:
         * 输入:
         * s = "ab"
         * p = ".*"
         * 输出: true
         * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
         */
        b = isMatch("ab", ".*");
        assertTrue(b);

        /*
         * 示例 4:
         * 输入:
         * s = "aab"
         * p = "c*a*b"
         * 输出: true
         * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
         */
        b = isMatch("aab", "c*a*b*");
        assertTrue(b);

        /*
         * 示例 5:
         * 输入:
         * s = "mississippi"
         * p = "mis*is*p*."
         * 输出: false
         */
        b = isMatch("mississippi", "mis*is*p*.");
        assertFalse(b);
    }
}
