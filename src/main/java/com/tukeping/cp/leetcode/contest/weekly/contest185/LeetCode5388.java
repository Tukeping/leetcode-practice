package com.tukeping.cp.leetcode.contest.weekly.contest185;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/19
 **/
public class LeetCode5388 {

    public String reformat(String s) {
        // 48 ~ 57, 97 ~ 122
        int n = s.length();
        if (n <= 1) return s;

        char[] chars = s.toCharArray();

        int digitCnt = 0;
        int letterCnt = 0;
        List<Character> digit = new ArrayList<>();
        List<Character> letter = new ArrayList<>();
        for (char c : chars) {
            if (48 <= c && c <= 57) {
                digit.add(c);
                digitCnt++;
            } else if (97 <= c && c <= 122) {
                letter.add(c);
                letterCnt++;
            }
        }

        if (Math.abs(digitCnt - letterCnt) > 1) return "";

        StringBuilder ans = new StringBuilder();
        boolean startLetter = letterCnt >= digitCnt;

        int dIdx = 0, lIdx = 0;
        while (dIdx < digitCnt || lIdx < letterCnt) {
            if (startLetter) {
                if (lIdx < letterCnt)
                    ans.append(letter.get(lIdx++));
                if (dIdx < digitCnt)
                    ans.append(digit.get(dIdx++));
            } else {
                if (dIdx < digitCnt)
                    ans.append(digit.get(dIdx++));
                if (lIdx < letterCnt)
                    ans.append(letter.get(lIdx++));
            }
        }
        return ans.toString();
    }

    @Test
    public void test1() {
        String ans = reformat("a0b1c2");
        assertThat(ans, anyOf(is("a0b1c2"), is("0a1b2c"), is("0c2a1b")));
    }

    @Test
    public void test2() {
        String ans = reformat("leetcode");
        assertThat(ans, is(""));
    }

    @Test
    public void test3() {
        String ans = reformat("1229857369");
        assertThat(ans, is(""));
    }

    @Test
    public void test4() {
        String ans = reformat("covid2019");
        assertThat(ans, is("c2o0v1i9d"));
    }

    @Test
    public void test5() {
        String ans = reformat("ab123");
        assertThat(ans, is("1a2b3"));
    }
}
