package com.tukeping.leetcode.problems;

/*
 * 1392. 最长快乐前缀
 *
 * 「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
 *
 * 给你一个字符串 s，请你返回它的 最长快乐前缀。
 *
 * 如果不存在满足题意的前缀，则返回一个空字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "level"
 * 输出："l"
 * 解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
 * 示例 2：
 *
 * 输入：s = "ababab"
 * 输出："abab"
 * 解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
 * 示例 3：
 *
 * 输入：s = "leetcodeleet"
 * 输出："leet"
 * 示例 4：
 *
 * 输入：s = "a"
 * 输出：""
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 只含有小写英文字母
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/23
 **/
public class LeetCode1392 {

    public String longestPrefix(String S) {
        char[] s = S.toCharArray();
        int[] z = Z(s);
        for (int i = 1; i < s.length; i++) {
            if (z[i] == s.length - i) {
                return S.substring(i);
            }
        }
        return "";
    }

    public int[] Z(char[] str) {
        int n = str.length;
        int[] z = new int[n];
        if (n == 0) return z;
        z[0] = n;
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i > r) {
                l = r = i;
                while (r < n && str[r - l] == str[r]) r++;
                z[i] = r - l;
                r--;
            } else {
                if (z[i - l] < r - i + 1) {
                    z[i] = z[i - l];
                } else {
                    l = i;
                    while (r < n && str[r - l] == str[r]) r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
        return z;
    }

    public String longestPrefix3(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = -1;
        for (int i = 1; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            int k = dp[i - 1];
            while (k >= 0 && c != s.charAt(k)) {
                k = dp[k];
            }
            dp[i] = k + 1;
        }
        return s.substring(0, dp[s.length()]);
    }

    public String longestPrefix2(String s) {
        int base = 131;
        long[] p = new long[100002];
        p[0] = 1;
        long[] hash = new long[100002];
        hash[0] = 0;
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            hash[i] = hash[i - 1] * base + s.charAt(i - 1) - 'a' + 1;
            p[i] = p[i - 1] * base;
        }
        for (int i = len - 1; i >= 1; i--) {
            long pre = hash[i];
            long suf = hash[len] - hash[len - i] * p[i];
            if (pre == suf) {
                return s.substring(0, i);
            }
        }
        return "";
    }

    /**
     * 输入：s = "level"
     * 输出："l"
     * 解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
     */
    @Test
    public void test1() {
        String res = longestPrefix("level");
        assertThat(res, is("l"));
    }

    /**
     * 输入：s = "ababab"
     * 输出："abab"
     * 解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
     */
    @Test
    public void test2() {
        String res = longestPrefix("ababab");
        assertThat(res, is("abab"));
    }

    /**
     * 输入：s = "leetcodeleet"
     * 输出："leet"
     */
    @Test
    public void test3() {
        String res = longestPrefix("leetcodeleet");
        assertThat(res, is("leet"));
    }

    /**
     * 输入：s = "a"
     * 输出：""
     */
    @Test
    public void test4() {
        String res = longestPrefix("a");
        assertThat(res, is(""));
    }
}
