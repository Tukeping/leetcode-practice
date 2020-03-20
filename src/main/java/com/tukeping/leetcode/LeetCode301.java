package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=301 lang=java
 *
 * [301] 删除无效的括号
 *
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/description/
 *
 * algorithms
 * Hard (45.21%)
 * Likes:    112
 * Dislikes: 0
 * Total Accepted:    6.9K
 * Total Submissions: 15.3K
 * Testcase Example:  '"()())()"'
 *
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 * 示例 1:
 *
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 *
 *
 * 示例 2:
 *
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 *
 *
 * 示例 3:
 *
 * 输入: ")("
 * 输出: [""]
 *
 */

import org.junit.Test;

import java.util.List;

/**
 * depth-first-search | breadth-first-search
 *
 * facebook
 *
 * @author tukeping
 * @date 2020/3/20
 **/
public class LeetCode301 {

    public List<String> removeInvalidParentheses(String s) {


        return null;
    }

    /**
     * 输入: "()())()"
     * 输出: ["()()()", "(())()"]
     */
    @Test
    public void test1() {

    }

    /**
     * 输入: "(a)())()"
     * 输出: ["(a)()()", "(a())()"]
     */
    @Test
    public void test2() {

    }

    /**
     * 输入: ")("
     * 输出: [""]
     */
    @Test
    public void test3() {

    }
}
