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

    @Test
    public void testBackTracking() {
        boolean b;

        /*
         * 示例 1:
         * 输入:
         * s = "aa"
         * p = "a"
         * 输出: false
         * 解释: "a" 无法匹配 "aa" 整个字符串。
         */
        b = isMatchByBackTracking("aa", "a");
        assertFalse(b);

        /*
         * 示例 2:
         * 输入:
         * s = "aa"
         * p = "a*"
         * 输出: true
         * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
         */
        b = isMatchByBackTracking("aa", "a*");
        assertTrue(b);

        /*
         * 示例 3:
         * 输入:
         * s = "ab"
         * p = ".*"
         * 输出: true
         * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
         */
        b = isMatchByBackTracking("ab", ".*");
        assertTrue(b);

        /*
         * 示例 4:
         * 输入:
         * s = "aab"
         * p = "c*a*b"
         * 输出: true
         * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
         */
        b = isMatchByBackTracking("aab", "c*a*b*");
        assertTrue(b);

        /*
         * 示例 5:
         * 输入:
         * s = "mississippi"
         * p = "mis*is*p*."
         * 输出: false
         */
        b = isMatchByBackTracking("mississippi", "mis*is*p*.");
        assertFalse(b);
    }

    private boolean matched = false;
    private char[] pattern;
    private int plen;

    public boolean match(String origin, String matcher) {
        char[] text = origin.toCharArray();
        int tlen = text.length;

        pattern = matcher.toCharArray();
        plen = pattern.length;

        matched = false;

        rmatch(0, 0, text, tlen);

        return matched;
    }

    private void rmatch(int ti, int pj, char[] text, int tlen) {
        if (matched) return; // 如果已经匹配了，就不要继续递归了
        if (pj == plen) { // 正则表达式到结尾了
            if (ti == tlen) matched = true; // 文本串也到结尾了
            return;
        }

        if (pattern[pj] == '*') { // *号 匹配任意个字符 重复零次或更多次
            for (int k = 0; k <= tlen - ti; k++) {
                rmatch(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '.') { // .号 匹配除换行符以外的任意字符

        } else if (pattern[pj] == '?') { // ?号 匹配0个或者1个字符 重复零次或一次
            rmatch(ti, pj + 1, text, tlen);
            rmatch(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配
            rmatch(ti + 1, pj + 1, text, tlen);
        }
    }

    @Test
    public void test1() {
        String s = "aa";
        String p = "a";
        boolean b = match(s, p);
        assertFalse(b);
    }

    @Test
    public void test1_1() {
        String s = "aa";
        String p = "aa";
        boolean b = match(s, p);
        assertTrue(b);
    }

    @Test
    public void test2() {
        String s = "aa";
        String p = "a*";
        boolean b = match(s, p);
        assertTrue(b);
    }

    @Test
    public void test3() {
        String s = "ab";
        String p = "?*";
        boolean b = match(s, p);
        assertTrue(b);
    }

    @Test
    public void test4() {
        String s = "aab";
        String p = "c*a*b";
        boolean b = match(s, p);
        assertTrue(b);
    }

    @Test
    public void test5() {
        String s = "mississippi";
        String p = "mis*is*p*?";
        boolean b = match(s, p);
        assertFalse(b);
    }

    @Test
    public void test6() {
        String s = "b";
        String p = "a*b";
        boolean b = match(s, p);
        assertTrue(b);
    }

    @Test
    public void test7() {
        String s = "ab";
        String p = "a*b";
        boolean b = match(s, p);
        assertTrue(b);
    }

    @Test
    public void test8() {
        String s = "aab";
        String p = "a*b";
        boolean b = match(s, p);
        assertTrue(b);
    }

    @Test
    public void test9() {
        String s = "aa";
        String p = "a*b";
        boolean b = match(s, p);
        assertFalse(b);
    }
}
