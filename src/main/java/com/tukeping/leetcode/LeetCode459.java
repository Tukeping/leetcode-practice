package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=459 lang=java
 *
 * [459] 重复的子字符串
 *
 * https://leetcode-cn.com/problems/repeated-substring-pattern/description/
 *
 * algorithms
 * Easy (44.15%)
 * Likes:    141
 * Dislikes: 0
 * Total Accepted:    12.2K
 * Total Submissions: 27.3K
 * Testcase Example:  '"abab"'
 *
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 *
 * 输入: "abab"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 *
 * 示例 2:
 *
 *
 * 输入: "aba"
 *
 * 输出: False
 *
 *
 * 示例 3:
 *
 *
 * 输入: "abcabcabcabc"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/1/9
 **/
public class LeetCode459 {

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        if (n == 0) return false;

        char[] p = s.toCharArray();

        int[] next = new int[n + 1];
        next[0] = -1;

        int i = 0, k = -1;
        while (i < n) {
            if (k == -1 || p[i] == p[k]) {
                i++;
                k++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
        return next[n] > 0 && n % (n - next[n]) == 0;
    }

    @Test
    public void test0() {
        assertFalse(repeatedSubstringPattern("a"));
    }

    @Test
    public void test1() {
        assertTrue(repeatedSubstringPattern("aaaa"));
    }

    @Test
    public void test2() {
        assertFalse(repeatedSubstringPattern("aab"));
    }

    @Test
    public void test3() {
        assertFalse(repeatedSubstringPattern("aabc"));
    }

    @Test
    public void test4() {
        assertTrue(repeatedSubstringPattern("abab"));
    }

    @Test
    public void test5() {
        assertTrue(repeatedSubstringPattern("abcabc"));
    }

    @Test
    public void test6() {
        assertFalse(repeatedSubstringPattern("abcabca"));
    }

    @Test
    public void test7() {
        assertFalse(repeatedSubstringPattern("abcabcabd"));
    }

    @Test
    public void test8() {
        assertFalse(repeatedSubstringPattern("aba"));
    }

    @Test
    public void test9() {
        assertTrue(repeatedSubstringPattern("ababababab"));
    }

    public boolean repeatedSubstringPattern2(String s) {
        return regularTrick(s);
    }

    /**
     * 找规律法
     *
     * 1. 字符串的第一个字符就是重复子字符串的第一个字符
     * 2. 字符串的最后一个字符就是重复子字符串的最后一个字符
     * 3. 将字符串拼接两遍设置成 SS = S + S (S是函数输入的字符串)
     * 4. 去除SS字符串中的第一个字符和最后一个字符设置成 T = SS.substring(1, SS.length() - 1)
     * 5. 如果T字符串里包含完整的S字符串 那么S就是周期性的字符串，否则就不是
     *
     * tips: 重复子字符串就是
     *       int i = T.indexOf(S);
     *       String repeatedString = S.substring(0, i+1);
     */
    public boolean regularTrick(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }
}
