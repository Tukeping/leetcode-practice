package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=58 lang=java
 *
 * [58] 最后一个单词的长度
 *
 * https://leetcode-cn.com/problems/length-of-last-word/description/
 *
 * algorithms
 * Easy (32.80%)
 * Likes:    185
 * Dislikes: 0
 * Total Accepted:    80.3K
 * Total Submissions: 244.1K
 * Testcase Example:  '"Hello World"'
 *
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 *
 *
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode58 {

    public int lengthOfLastWord(String s) {
        s = s.trim();

        int len = s.length();
        if (len == 0) return 0;

        int wordStart = -1, wordEnd = -1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90
                    || (s.charAt(i) >= 97 && s.charAt(i) <= 122)) { // a ~ z || A ~ Z
                if (wordStart == -1)
                    wordStart = i;
                wordEnd = i;
            } else if (s.charAt(i) == 32) { // blank space
                wordStart = -1;
                wordEnd = -1;
            }
        }
        return wordEnd - wordStart + 1;
    }

    /**
     * 输入: "Hello World"
     * 输出: 5
     */
    @Test
    public void test1() {
        int n = lengthOfLastWord("Hello World");
        assertThat(n, is(5));
    }
}
