package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=394 lang=java
 *
 * [394] 字符串解码
 *
 * https://leetcode-cn.com/problems/decode-string/description/
 *
 * algorithms
 * Medium (49.14%)
 * Likes:    217
 * Dislikes: 0
 * Total Accepted:    20.6K
 * Total Submissions: 41.9K
 * Testcase Example:  '"3[a]2[bc]"'
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 *
 */

import com.tukeping.leetcode.structures.Pair;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * stack | depth-first-search
 *
 * google | yelp
 *
 * @author tukeping
 * @date 2020/3/18
 **/
public class LeetCode394 {

    public String decodeString(String s) {
        Stack<Pair<Integer, String>> stack = new Stack<>();
        char[] chars = s.toCharArray();
        StringBuilder res = new StringBuilder();
        int multi = 0;
        for (char c : chars) {
            if (c == '[') {
                stack.add(Pair.of(multi, res.toString()));
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                Pair<Integer, String> data = stack.pop();
                res = new StringBuilder(data.second + repeat(res, data.first));
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + (c - 48);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    private StringBuilder repeat(StringBuilder s, int n) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < n; i++) tmp.append(s);
        return tmp;
    }

    /**
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     */
    @Test
    public void test1() {
        String s = decodeString("3[a]2[bc]");
        assertThat(s, is("aaabcbc"));
    }

    @Test
    public void test2() {
        String s = decodeString("3[a2[c]]");
        assertThat(s, is("accaccacc"));
    }

    @Test
    public void test3() {
        String s = decodeString("2[abc]3[cd]ef");
        assertThat(s, is("abcabccdcdcdef"));
    }
}
