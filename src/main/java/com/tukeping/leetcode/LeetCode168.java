package com.tukeping.leetcode;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode168 {

    public String convertToTitle(int n) {
        // A ~ Z , 65 ~ 90
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            n--;
            ans.insert(0, (char) (65 + (n % 26)));
            n /= 26;
        }
        return ans.toString();
    }

    @Test
    public void test1() {
        String ans = convertToTitle(27);
        assertThat(ans, is("AA"));
    }

    /**
     * 输入: 701
     * 输出: "ZY"
     */
    @Test
    public void test2() {
        String ans = convertToTitle(701);
        assertThat(ans, is("ZY"));
    }

    /**
     * 输入: 28
     * 输出: "AB"
     */
    @Test
    public void test3() {
        String ans = convertToTitle(28);
        assertThat(ans, is("AB"));
    }

    /**
     * 输入: 1
     * 输出: "A"
     */
    @Test
    public void test4() {
        String ans = convertToTitle(1);
        assertThat(ans, is("A"));
    }
}
