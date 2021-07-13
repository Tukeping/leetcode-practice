package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=28 lang=java
 *
 * [28] 实现 strStr()
 *
 * https://leetcode-cn.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (39.18%)
 * Likes:    363
 * Dislikes: 0
 * Total Accepted:    127.5K
 * Total Submissions: 322.6K
 * Testcase Example:  '"hello"\n"ll"'
 *
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * two-pointers | string
 *
 * apple | facebook | microsoft | pocketgems
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode28 {

    /*
     * 说明:
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
     * 这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     */

    /*
     * 74/74 cases passed (1 ms)
     * Your runtime beats 78.39 % of java submissions
     * Your memory usage beats 5.02 % of java submissions (41.9 MB)
     */

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) return 0;
        if (haystack == null || haystack.isEmpty()) return -1;
        if (needle.length() > haystack.length()) return -1;
        if (needle.length() == haystack.length()) return haystack.equals(needle) ? 0 : -1;

        int p2 = 0, first = -1;
        for (int p1 = 0; p1 < haystack.length(); p1++) {
            if (haystack.charAt(p1) == needle.charAt(p2)) {
                first = p1;
                p1++;
                p2++;
                if (p2 >= needle.length())
                    break;
                for (; p2 < needle.length(); p1++, p2++) {
                    if (p1 >= haystack.length()) break;
                    if (haystack.charAt(p1) != needle.charAt(p2)) break;
                }
                if (p2 == needle.length()) {
                    return first;
                } else {
                    p1 = first;
                    p2 = 0;
                    first = -1;
                }
            }
        }

        return first;
    }

    /**
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     */
    @Test
    public void test1() {
        int n = strStr("hello", "ll");
        assertThat(n, is(2));
    }

    /**
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     */
    @Test
    public void test2() {
        int n = strStr("aaaaa", "bba");
        assertThat(n, is(-1));
    }

    @Test
    public void test3() {
        int n = strStr("", "a");
        assertThat(n, is(-1));
    }

    @Test
    public void test4() {
        int n = strStr("", "");
        assertThat(n, is(0));
    }

    @Test
    public void test5() {
        int n = strStr("aaa", "a");
        assertThat(n, is(0));
    }

    @Test
    public void test6() {
        int n = strStr("aaa", "aaaa");
        assertThat(n, is(-1));
    }

    @Test
    public void test7() {
        int n = strStr("mississippi", "issi");
        assertThat(n, is(1));
    }

    @Test
    public void test8() {
        int n = strStr("mississippi", "issip");
        assertThat(n, is(4));
    }

    @Test
    public void test9() {
        int n = strStr("mississippi", "issipi");
        assertThat(n, is(-1));
    }
}
