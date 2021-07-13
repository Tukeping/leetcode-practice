package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=91 lang=java
 *
 * [91] 解码方法
 *
 * https://leetcode-cn.com/problems/decode-ways/description/
 *
 * algorithms
 * Medium (22.49%)
 * Likes:    280
 * Dislikes: 0
 * Total Accepted:    31.5K
 * Total Submissions: 136.7K
 * Testcase Example:  '"12"'
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * string | dynamic-programming
 *
 * facebook | microsoft | uber
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode91 {

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;

        int len = s.length();
        int[] f = new int[len + 1];
        f[0] = 1;
        f[1] = 1;

        for (int i = 1, x = 2; i < len; i++, x++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    f[x] = f[x - 2];
                } else {
                    return 0;
                }
            } else if (s.charAt(i - 1) == '1'
                    || (s.charAt(i - 1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6')) {
                f[x] = f[x - 1] + f[x - 2];
            } else {
                f[x] = f[x - 1];
            }
        }
        return f[len];
    }

    /**
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
     */
    @Test
    public void test1() {
        int n = numDecodings("12");
        assertThat(n, is(2));
    }

    /**
     * 输入: "226"
     * 输出: 3
     * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     */
    @Test
    public void test2() {
        int n = numDecodings("226");
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        int n = numDecodings("0");
        assertThat(n, is(0));
    }

    @Test
    public void test4() {
        int n = numDecodings("01");
        assertThat(n, is(0));
    }

    @Test
    public void test5() {
        int n = numDecodings("22067");
        assertThat(n, is(1));
    }
}
