package com.tukeping.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/5/19
 **/
public class LeetCode408 {

    public boolean validWordAbbreviation(String word, String abbr) {
        int len = abbr.length(), wordLen = word.length();
        int abbrLen = 0, num = 0;
        for (int i = 0; i < len; ++i) {
            if (abbr.charAt(i) >= 'a' && abbr.charAt(i) <= 'z') {
                abbrLen += num + 1;
                num = 0;
                if (abbrLen > wordLen || abbr.charAt(i) != word.charAt(abbrLen - 1)) return false;
            } else {
                if (num == 0 && abbr.charAt(i) == '0') return false; // 不能出现前导零
                num = num * 10 + abbr.charAt(i) - '0';
            }
        }
        return abbrLen + num == wordLen;
    }

    public boolean validWordAbbreviation2(String word, String abbr) {
        int i = 0, j = 0;
        int n = word.length(), m = abbr.length();
        StringBuilder digit = new StringBuilder();
        while (i < n && j < m) {
            if (isLetter(abbr.charAt(j))) {
                if (digit.length() > 0) {
                    if (digit.charAt(0) == '0') return false;
                    int cnt = Integer.parseInt(digit.toString());
                    if (i + cnt >= n) return false;

                    digit.setLength(0);
                    i += cnt;
                }
                if (word.charAt(i) != abbr.charAt(j)) return false;
                i++;
                j++;
            } else if (isDigit(abbr.charAt(j))) {
                while (j < m && isDigit(abbr.charAt(j))) {
                    digit.append(abbr.charAt(j));
                    j++;
                }
            }
        }

        if (i == n && j < m) return false;

        if (digit.length() > 0) {
            if (digit.charAt(0) == '0') return false;
            i += Integer.parseInt(digit.toString());
        }
        return i == n;
    }

    private boolean isLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 给定 s = "internationalization", abbr = "i12iz4n":
     * 函数返回 true.
     */
    @Test
    public void test1() {
        boolean b = validWordAbbreviation("internationalization", "i12iz4n");
        assertTrue(b);
    }

    /**
     * 给定 s = "apple", abbr = "a2e":
     * 函数返回 false.
     */
    @Test
    public void test2() {
        boolean b = validWordAbbreviation("apple", "a2e");
        assertFalse(b);
    }

    @Test
    public void test3() {
        boolean b = validWordAbbreviation("internationalization", "i5a11o1");
        assertTrue(b);
    }

    @Test
    public void test4() {
        boolean b = validWordAbbreviation("a", "01");
        assertFalse(b);
    }

    @Test
    public void test5() {
        boolean b = validWordAbbreviation("hi", "hi1");
        assertFalse(b);
    }
}
