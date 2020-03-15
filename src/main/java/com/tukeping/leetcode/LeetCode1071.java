package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1071 lang=java
 *
 * [1071] 字符串的最大公因子
 *
 * https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/description/
 *
 * algorithms
 * Easy (48.57%)
 * Likes:    104
 * Dislikes: 0
 * Total Accepted:    18.9K
 * Total Submissions: 32.8K
 * Testcase Example:  '"ABCABC"\n"ABC"'
 *
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 *
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 * 示例 1：
 *
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 *
 * 示例 2：
 *
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 *
 * 示例 3：
 *
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *
 * 提示：
 *
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/3/12
 **/
public class LeetCode1071 {

    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    private int gcdLoop(int a, int b) {
        while (b != 0) {
            int c = b;
            b = a % b;
            a = c;
        }
        return a;
    }

    /**
     * 输入：str1 = "ABCABC", str2 = "ABC"
     * 输出："ABC"
     */
    @Test
    public void test1() {
        String res = gcdOfStrings("ABCABC", "ABC");
        assertThat(res, is("ABC"));
    }

    /**
     * 输入：str1 = "ABABAB", str2 = "ABAB"
     * 输出："AB"
     */
    @Test
    public void test2() {
        String res = gcdOfStrings("ABABAB", "ABAB");
        assertThat(res, is("AB"));
    }

    /**
     * 输入：str1 = "LEET", str2 = "CODE"
     * 输出：""
     */
    @Test
    public void test3() {
        String res = gcdOfStrings("LEET", "CODE");
        assertThat(res, is(""));
    }
}
