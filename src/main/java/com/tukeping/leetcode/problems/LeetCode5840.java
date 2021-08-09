package com.tukeping.leetcode.problems;

import java.util.Stack;

/**
 * @author tukeping
 * @date 2021/8/8
 **/
public class LeetCode5840 {

    public int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (s.charAt(i) == ']') {
                    char c = stack.peek();
                    if (c == '[') {
                        stack.pop();
                    } else {
                        stack.push(']');
                    }
                } else { // '['
                    stack.push(s.charAt(i));
                }
            }
        }
        int size = stack.size();
        if (size == 0) return 0;
        else {
            int extra = size % 4 == 0 ? 0 : 1;
            return size / 4 + extra;
        }
    }
}
