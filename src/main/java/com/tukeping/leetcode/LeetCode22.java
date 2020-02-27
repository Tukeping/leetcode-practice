package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (72.54%)
 * Likes:    746
 * Dislikes: 0
 * Total Accepted:    74.1K
 * Total Submissions: 101.1K
 * Testcase Example:  '3'
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 * ⁠ "((()))",
 * ⁠ "(()())",
 * ⁠ "(())()",
 * ⁠ "()(())",
 * ⁠ "()()()"
 * ]
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * string | backtracking
 *
 * google | uber | zenefits
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode22 {

    public List<String> generateParenthesis(int n) {
        // corner case
        if (n <= 0) return Collections.emptyList();

        // prepare
        List<String> res = new ArrayList<>();

        // backtracking
        helper(res, "", 0, 0, n);
        return res;
    }

    private void helper(List<String> res, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            res.add(cur);
            return;
        }

        if (open < max) {
            helper(res, cur + "(", open + 1, close, max);
        }

        if (close < open) {
            helper(res, cur + ")", open, close + 1, max);
        }
    }

    /**
     * 例如，给出 n = 3，生成结果为：
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     */
    @Test
    public void test1() {
        List<String> actual = generateParenthesis(3);
        List<String> expect = Arrays.asList(
                "((()))",
                "(()())",
                "(())()",
                "()(())",
                "()()()"
        );
        assertThat(actual, containsInAnyOrder(expect.toArray()));
    }
}
