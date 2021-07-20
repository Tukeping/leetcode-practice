package com.tukeping.lcof;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 正则表达式匹配
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LCOF19 {

    private String s, p;
    private int sLen, pLen;

    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        this.sLen = s.length();
        this.pLen = p.length();
        return isMatch(0, 0);
    }

    private boolean isMatch(int sIdx, int pIdx) {
        if (pIdx == pLen) return sIdx == sLen;
        boolean matched = sIdx < sLen
                && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '.');
        if (pIdx < pLen - 1 && p.charAt(pIdx + 1) == '*') {
            return isMatch(sIdx, pIdx + 2) || (matched && isMatch(sIdx + 1, pIdx));
        } else {
            return matched && isMatch(sIdx + 1, pIdx + 1);
        }
    }

    @Test
    public void test1() {
        assertFalse(isMatch("aa", "a"));
        assertTrue(isMatch("aa", "aa"));
        assertFalse(isMatch("ab", "aa"));
        assertFalse(isMatch("aaa", "aaaa"));
    }

    @Test
    public void test2() {
        assertTrue(isMatch("aa", ".."));
        assertTrue(isMatch("a", "."));
        assertTrue(isMatch("ab", ".."));
        assertFalse(isMatch("a", ".."));
        assertFalse(isMatch("aa", "."));
        assertFalse(isMatch("", "."));
        assertTrue(isMatch("ab", ".b"));
        assertTrue(isMatch("ab", "a."));
        assertFalse(isMatch("ab", "a.b"));
    }

    @Test
    public void test3() {
        assertTrue(isMatch("aa", "a*"));
        assertTrue(isMatch("aaa", "a*a*"));
        assertTrue(isMatch("ab", "a*b"));
        assertFalse(isMatch("ab", "b*"));
        assertFalse(isMatch("aba", "ab*"));
        assertTrue(isMatch("aab", "c*a*b"));
    }

    @Test
    public void test4() {
        assertTrue(isMatch("ab", ".*"));
        assertFalse(isMatch("mississippi", "mis*is*p*."));
        assertTrue(isMatch("", ".*"));
    }
}
