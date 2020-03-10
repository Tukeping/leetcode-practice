package com.tukeping.leetcode;

/*
 * 5352. 生成每种字符都是奇数个的字符串
 *
 * 给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
 *
 * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/8
 **/
public class LeetCode5352 {

    public String generateTheString(int n) {
        if (n == 1) return "a";

        char[] chars = new char[n];
        if (n % 2 == 0) { // 偶数
            for (int i = 0; i < n - 1; i++) {
                chars[i] = 'a';
            }
            chars[n - 1] = 'b';
        } else { // 奇数
            for (int i = 0; i < n; i++) {
                chars[i] = 'a';
            }
        }

        return new String(chars);
    }

    /**
     * 输入：n = 4
     * 输出："pppz"
     * 解释："pppz" 是一个满足题目要求的字符串，因为 'p' 出现 3 次，且 'z' 出现 1 次。当然，还有很多其他字符串也满足题目要求
     */
    @Test
    public void test1() {
        String res = generateTheString(4);
        assertThat(res, is("aaab"));
    }

    /**
     * 输入：n = 2
     * 输出："xy"
     * 解释："xy" 是一个满足题目要求的字符串，因为 'x' 和 'y' 各出现 1 次。当然，还有很多其他字符串也满足题目要求，比如："ag" 和 "ur"。
     */
    @Test
    public void test2() {
        String res = generateTheString(2);
        assertThat(res, is("ab"));
    }

    /**
     * 输入：n = 7
     * 输出："holasss"
     */
    @Test
    public void test3() {
        String res = generateTheString(7);
        assertThat(res, is("aaaaaaa"));
    }
}
