package com.tukeping.book.sword_means_offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/4
 **/
public class LCOF67 {

    public int strToInt(String str /* -2^31 ~ 2^31 - 1 => -2147483648 ~ 2147483647 **/) {
        int len = str.length(), digits = 0, strIdx = 0, valid = 0;
        boolean symbol = false, negative = false;
        int[] d = new int[]{2, 1, 4, 7, 4, 8, 3, 6, 4, 8};
        List<Integer> digitList = new ArrayList<>();
        while (strIdx < len) {
            char c = str.charAt(strIdx);
            if (strIdx == 0 && digits == 0 && c == 32) {
                strIdx++;
            } else if (digits == 0 && (c == 43 || c == 45)) {
                if (symbol) return 0;
                if (c == 45) negative = true;
                symbol = true;
                strIdx++;
            } else if (digits <= 11 && c >= 48 && c <= 57) {
                strIdx++;
                digitList.add(c - 48);
                digits++;
                if (digits == 11) {
                    List<Integer> tmp = new ArrayList<>(digitList);
                    for (Integer val : digitList) {
                        if (val == 0)
                            tmp.remove(0);
                        else
                            break;
                    }
                    if (tmp.size() == 0)
                        tmp.add(0);
                    digitList.clear();
                    digitList.addAll(tmp);
                    digits = digitList.size();
                    if (digits == 11) {
                        return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                    }
                }
            } else if (digits > 0) {
                break;
            } else {
                return 0;
            }
        }
        if (digits > 1) {
            List<Integer> tmp = new ArrayList<>(digitList);
            for (Integer val : digitList) {
                if (val == 0)
                    tmp.remove(0);
                else
                    break;
            }
            if (tmp.size() == 0)
                tmp.add(0);
            digitList.clear();
            digitList.addAll(tmp);
            digits = digitList.size();
        }
        // check range
        if (digits == 10) {
            for (int i = 0; i < digits; i++) {
                int x = digitList.get(i);
                int y = d[i];
                if (i == digits - 1) {
                    y = !negative ? d[i] - 1 : d[i];
                }
                if (x > y) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                } else if (x < y) {
                    break;
                }
            }
        }
        // sum up
        int sum = 0;
        for (int i = 0; i < digitList.size(); i++) {
            int x = digitList.get(i) * (int) Math.pow(10, digits - i - 1);
            if (!negative)
                sum += x;
            else
                sum -= x;
        }
        return sum;
    }

    /**
     * 输入: "42"
     * 输出: 42
     */
    @Test
    public void test1() {
        int n = strToInt("42");
        assertThat(n, is(42));
    }

    /**
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     */
    @Test
    public void test2() {
        int n = strToInt("   -42");
        assertThat(n, is(-42));
    }

    /**
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     */
    @Test
    public void test3() {
        int n = strToInt("4193 with words");
        assertThat(n, is(4193));
    }

    /**
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     *      因此无法执行有效的转换。
     */
    @Test
    public void test4() {
        int n = strToInt("words and 987");
        assertThat(n, is(0));
    }

    /**
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     */
    @Test
    public void test5() {
        int n = strToInt("-91283472332");
        assertThat(n, is(-2147483648));
    }

    @Test
    public void test6() {
        int n = strToInt("-2147483648");
        assertThat(n, is(-2147483648));
    }

    @Test
    public void test7() {
        int n = strToInt("2147483647");
        assertThat(n, is(2147483647));
    }

    @Test
    public void test8() {
        int n = strToInt("2147483648");
        assertThat(n, is(2147483647));
    }

    @Test
    public void test9() {
        int n = strToInt("-2147483649");
        assertThat(n, is(-2147483648));
    }

    @Test
    public void test10() {
        int n = strToInt("  0000000000012345678");
        assertThat(n, is(12345678));
    }

    @Test
    public void test11() {
        int n = strToInt("+-2");
        assertThat(n, is(0));
    }

    @Test
    public void test12() {
        int n = strToInt("   +0 123");
        assertThat(n, is(0));
    }

    @Test
    public void test13() {
        int n = strToInt("-   234");
        assertThat(n, is(0));
    }
}
