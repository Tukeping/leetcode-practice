package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertFalse;

/**
 * @author tukeping
 * @date 2020/5/15
 **/
public class LeetCode678 {

    public boolean checkValidString(String s) {
        if (s == null || s.isEmpty()) return true;

        Stack<Character> stack = new Stack<>();
        Stack<Character> tmpStack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty()) return false;

                while (!stack.isEmpty() && stack.peek() != '(') {
                    tmpStack.push(stack.pop());
                }

                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }

                while (!tmpStack.isEmpty()) stack.push(tmpStack.pop());
            } else stack.push(c);
        }

        int[] cnt = new int[2];
        while (!stack.isEmpty()) {
            char x = stack.pop();
            System.out.print(x + " ");
            if (x == '*') cnt[0]++;
            else cnt[1]++;
        }
        System.out.println();
        System.out.println("cnt[0] = " + cnt[0] + ", cnt[1] = " + cnt[1]);
        return cnt[0] >= cnt[1];
    }

    @Test
    public void test1() {
        boolean b = checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*");
        assertFalse(b);
    }
}
