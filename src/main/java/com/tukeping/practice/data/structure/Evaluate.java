package com.tukeping.practice.data.structure;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * @author tukeping
 * @date 2021/8/1
 **/
public class Evaluate {

    public int calc(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') ;
            else if (c == '+') ops.push(c);
            else if (c == '*') ops.push(c);
            else if (c == ')') {
                char op = ops.pop();
                if (op == '+') vals.push(vals.pop() + vals.pop());
                else if (op == '*') vals.push(vals.pop() * vals.pop());
            } else vals.push(c - '0');
        }

        return vals.pop();
    }

    @Test
    public void test() {
        int ret = calc("(1+((2+3)*(4*5)))");
        Assert.assertEquals(ret, 101);
    }
}
