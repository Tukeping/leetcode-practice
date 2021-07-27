package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 *
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (40.26%)
 * Likes:    1276
 * Dislikes: 0
 * Total Accepted:    172.7K
 * Total Submissions: 428.7K
 * Testcase Example:  '"()"'
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 *
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 *
 *
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 *
 *
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 *
 *
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 *
 *
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * string | stack
 *
 * airbnb | amazon | bloomberg | facebook | google | microsoft | twitter | zenefits
 *
 * frequency 5
 *
 * @author tukeping
 * @since 2018/12/15
 **/
public class LeetCode20 {

    public boolean isValidV2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) return false;
                else {
                    char p = stack.pop();
                    if (p != '(') return false;
                }
            } else if (c == '}') {
                if (stack.isEmpty()) return false;
                else {
                    char p = stack.pop();
                    if (p != '{') return false;
                }
            } else if (c == ']') {
                if (stack.isEmpty()) return false;
                else {
                    char p = stack.pop();
                    if (p != '[') return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return true;
        }

        char[] chars = s.toCharArray();
        char last = chars[0];

        Stack<Character> stack = new Stack<>();
        stack.push(last);

        for (int i = 1; i < chars.length; i++) {
            char current = chars[i];
            if ((last == '(' && current == ')') ||
                    (last == '[' && current == ']') ||
                    (last == '{' && current == '}')) {
                stack.pop();
                last = stack.isEmpty() ? ' ' : stack.lastElement();
            } else {
                stack.push(current);
                last = current;
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test1() {
        Assert.assertTrue(isValid("()"));
    }

    @Test
    public void test2() {
        Assert.assertTrue(isValid("()[]{}"));
    }

    @Test
    public void test3() {
        Assert.assertFalse(isValid("(]"));
    }

    @Test
    public void test4() {
        Assert.assertFalse(isValid("([)]"));
    }

    @Test
    public void test5() {
        Assert.assertTrue(isValid("{[]}"));
    }
}
