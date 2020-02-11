package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=43 lang=java
 *
 * [43] 字符串相乘
 *
 * https://leetcode-cn.com/problems/multiply-strings/description/
 *
 * algorithms
 * Medium (41.05%)
 * Likes:    260
 * Dislikes: 0
 * Total Accepted:    42.2K
 * Total Submissions: 101.5K
 * Testcase Example:  '"2"\n"3"'
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 说明：
 *
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 *
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | string
 *
 * @author tukeping
 * @date 2020/2/10
 **/
public class LeetCode43 {

    /**
     * 说明：
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     *
     * 使用初中学的数学乘法方式:
     *     123
     *     456
     * -------
     *     738
     *    6150
     *   49200
     * -------
     *   56088
     *
     */

    /**
     * 311/311 cases passed (13 ms)
     * Your runtime beats 40.15 % of java submissions
     * Your memory usage beats 5.03 % of java submissions (42 MB)
     */
    private int numAsciiBase = 48;

    public String multiply(String num1, String num2) {
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        int n1Len = n1.length;
        int n2Len = n2.length;

        char[] result = new char[n1Len + n2Len];
        Arrays.fill(result, '0');

        // 低位对齐
        for (int i = n2Len - 1; i >= 0; i--) {
            int n2Idx = (n2Len - 1) - i;
            int lineLen = n1Len + n2Idx + 1;
            char[] line = new char[lineLen];
            Arrays.fill(line, '0');
            int up = 0, k; // 进位

            for (k = n1Len - 1; k >= 0; k--) {
                int x = c2n(n2[i]);
                int y = c2n(n1[k]);
                int n1Idx = (n1Len - 1) - k;
                int cur = c2n(line[n2Idx + n1Idx]);

                int s = x * y + cur + up;
                up = s / 10;

                int idx = (n2Idx + n1Idx);
                line[idx] = n2c(s % 10);
            }
            // 考虑所有累加完毕后 还多出来一次 进位, 需要额外增加补充进去
            if (up > 0) {
                result[lineLen - 1] = n2c(up);
            }

            int resultUp = 0;
            // line 和 result 按位 相加, 从低位到高位
            for (k = 0; k < lineLen; k++) {
                int a = c2n(result[k]);
                int b = c2n(line[k]);
                int c = a + b + resultUp;
                resultUp = c / 10;
                result[k] = n2c(c % 10);
            }
            // 考虑所有累加完毕后 还多出来一次 进位, 需要额外增加补充进去
            if (resultUp > 0) {
                result[k] = n2c(resultUp);
            }
        }

        return reverseChars(result);
    }

    private String reverseChars(char[] chars) {
        StringBuilder sb = new StringBuilder();
        boolean isHead = true, onesPlace;
        for (int i = chars.length - 1; i >= 0; i--) {
            onesPlace = sb.length() == 0 && i == 0; // 只剩下个位数了, 需要保留0
            if (isHead && chars[i] == '0') {
                if (onesPlace) {
                    sb.append(chars[i]);
                } else {
                    // ignore append
                }
            } else if (chars[i] != '0') {
                isHead = false;
                sb.append(chars[i]);
            } else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    /** ascii: 48 ~ 57 => number: 0 ~ 9 **/
    private int c2n(char numberChar) {
        return numberChar - numAsciiBase;
    }

    private char n2c(int n) {
        return (char) (n + numAsciiBase);
    }

    /**
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     */
    @Test
    public void test1() {
        String s = multiply("2", "3");
        assertThat(s, is("6"));
    }

    /**
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     */
    @Test
    public void test2() {
        String s = multiply("123", "456");
        assertThat(s, is("56088"));
    }

    /**
     * 输入: num1 = "123456789", num2 = "987654321"
     * 输出: "121932631112635269"
     */
    @Test
    public void test3() {
        String s = multiply("123456789", "987654321");
        assertThat(s, is("121932631112635269"));
    }

    /**
     * 输入: num1 = "0", num2 = "0"
     * 输出: "0"
     */
    @Test
    public void test4() {
        String s = multiply("0", "0");
        assertThat(s, is("0"));
    }
}
