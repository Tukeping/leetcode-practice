package com.tukeping.misc.book.sword_means_offer;

/*
 * @lc app=leetcode.cn id=65 lang=java
 *
 * [65] 有效数字
 *
 * https://leetcode-cn.com/problems/valid-number/description/
 *
 * algorithms
 * Hard (17.13%)
 * Likes:    85
 * Dislikes: 0
 * Total Accepted:    9K
 * Total Submissions: 50.1K
 * Testcase Example:  '"0"'
 *
 * 验证给定的字符串是否可以解释为十进制数字。
 *
 * 例如:
 *
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 *
 *
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 *
 *
 * 当然，在输入中，这些字符的上下文也很重要。
 *
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * math | string
 *
 * linkedin
 *
 * @author tukeping
 * @date 2020/3/31
 **/
public class LCOF20 {

    private String s;

    public boolean isNumber(String s) {
        s = s.trim();
        this.s = s;

        int len = s.length();
        if (len == 0) return false;
        if (len == 1) return isDigit(0);

        boolean hasExponent = false, hasDot = false;
        int positiveSymbol = 0, negativeSymbol = 0;
        for (int i = 0; i < len; i++) {
            if (isPositiveSymbol(i)) {
                if (positiveSymbol == 2)
                    return false;
                if (i > 0 && !isExponent(i - 1))
                    return false;
                if (i == len - 1 || !isDigit(i + 1) && !isDot(i + 1))
                    return false;
                positiveSymbol++;
            } else if (isNegativeSymbol(i)) {
                if (negativeSymbol == 2)
                    return false;
                if (i > 0 && !isExponent(i - 1))
                    return false;
                if (i == len - 1 || !isDigit(i + 1) && !isDot(i + 1))
                    return false;
                negativeSymbol++;
            } else if (isDot(i)) {
                if (hasExponent)
                    return false;
                if (hasDot)
                    return false;
                boolean beforeDigit = (i == 0 || !isDigit(i - 1));
                boolean afterDigit = (i == len - 1 || !isDigit(i + 1));
                if (beforeDigit && afterDigit)
                    return false;
                hasDot = true;
            } else if (isExponent(i)) {
                if (hasExponent)
                    return false;
                if (i == 0 || i == len - 1)
                    return false;
                if (!isDigit(i + 1) && !isSymbol(i + 1))
                    return false;
                hasExponent = true;
            } else if (!isDigit(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSymbol(int i) {
        return isPositiveSymbol(i) || isNegativeSymbol(i);
    }

    private boolean isNegativeSymbol(int i) {
        return s.charAt(i) == '-';
    }

    private boolean isPositiveSymbol(int i) {
        return s.charAt(i) == '+';
    }

    private boolean isExponent(int i) {
        return s.charAt(i) == 'e';
    }

    private boolean isDigit(int i) {
        return s.charAt(i) >= '0' && s.charAt(i) <= '9';
    }

    private boolean isDot(int i) {
        return s.charAt(i) == '.';
    }

    /**
     * 例如:
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * " -90e3   " => true
     * " 1e" => false
     * "e3" => false
     * " 6e-1" => true
     * " 99e2.5 " => false
     * "53.5e93" => true
     * " --6 " => false
     * "-+3" => false
     * "95a54e53" => false
     *
     * 说明:
     * 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。
     * 这里给出一份可能存在于有效十进制数字中的字符列表：
     *
     * 数字 0-9
     * 指数 "e"
     * 正/负号 "+" "-"
     * 小数点 "."
     */
    @Test
    public void test1() {
        assertTrue(isNumber("0"));
        assertTrue(isNumber("0.1"));
        assertFalse(isNumber("abc"));
        assertFalse(isNumber("1 a"));
        assertTrue(isNumber("2e10"));
        assertTrue(isNumber("-90e3"));
        assertFalse(isNumber(" 1e"));
        assertFalse(isNumber("e3"));
        assertTrue(isNumber(" 6e-1"));
        assertFalse(isNumber(" 99e2.5"));
        assertTrue(isNumber("53.5e93"));
        assertFalse(isNumber(" --6"));
        assertFalse(isNumber("-+3"));
        assertFalse(isNumber("95a54e53"));
    }

    @Test
    public void test2() {
        assertFalse(isNumber(".e1"));
    }

    @Test
    public void test3() {
        assertTrue(isNumber(".1"));
    }

    @Test
    public void test4() {
        assertTrue(isNumber("3."));
    }

    @Test
    public void test5() {
        assertFalse(isNumber("4e+"));
    }

    @Test
    public void test6() {
        assertFalse(isNumber("3-2"));
    }

    @Test
    public void test7() {
        assertFalse(isNumber("-."));
    }

    @Test
    public void test8() {
        assertTrue(isNumber("+.8"));
    }

    @Test
    public void test9() {
        assertFalse(isNumber(" +0e-"));
    }

    @Test
    public void test10() {
        assertTrue(isNumber(" 005047e+6"));
    }
}
