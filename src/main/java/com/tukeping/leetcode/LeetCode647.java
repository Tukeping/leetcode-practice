package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=647 lang=java
 *
 * [647] 回文子串
 *
 * https://leetcode-cn.com/problems/palindromic-substrings/description/
 *
 * algorithms
 * Medium (61.17%)
 * Likes:    200
 * Dislikes: 0
 * Total Accepted:    19.9K
 * Total Submissions: 32.6K
 * Testcase Example:  '"abc"'
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 *
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 * 注意:
 *
 * 输入的字符串长度不会超过1000。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * string | dynamic-programming
 *
 * facebook | linkedin
 *
 * @author tukeping
 * @date 2020/3/22
 **/
public class LeetCode647 {

    public int countSubstrings(String S) {
        char[] A = new char[2 * S.length() + 3];
        A[0] = '@';
        A[1] = '#';
        A[A.length - 1] = '$';
        int t = 2;
        for (char c : S.toCharArray()) {
            A[t++] = c;
            A[t++] = '#';
        }

        int[] Z = new int[A.length];
        int center = 0, right = 0;
        for (int i = 1; i < Z.length - 1; ++i) {
            if (i < right)
                Z[i] = Math.min(right - i, Z[2 * center - i]);
            while (A[i + Z[i] + 1] == A[i - Z[i] - 1])
                Z[i]++;
            if (i + Z[i] > right) {
                center = i;
                right = i + Z[i];
            }
        }
        int ans = 0;
        for (int v : Z) ans += (v + 1) / 2;
        return ans;
    }

    public int countSubstrings2(String s) {
        int ans = 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = j; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 输入: "abc"
     * 输出: 3
     * 解释: 三个回文子串: "a", "b", "c".
     */
    @Test
    public void test1() {
        int n = countSubstrings("abc");
        assertThat(n, is(3));
    }

    /**
     * 输入: "aaa"
     * 输出: 6
     * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
     */
    @Test
    public void test2() {
        int n = countSubstrings("aaa");
        assertThat(n, is(6));
    }
}
