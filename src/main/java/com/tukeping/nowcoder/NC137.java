package com.tukeping.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC137 {

    Deque<Integer> nums = new ArrayDeque<>();
    Deque<Character> operator = new ArrayDeque<>();

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回表达式的值
     * @param s string字符串 待计算的表达式
     * @return int整型
     */
    public int solve(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                nums.add(c - '0');
                if (i > 0) {
                    if (chars[i - 1] == '*') {
                        nums.add(fireLastOp());
                    } else if (chars[i - 1] >= '0' && chars[i - 1] <= '9') {
                        nums.add(nums.removeLast() + nums.removeLast() * 10);
                    }
                }
            } else if (c == '+' || c == '-' || c == '*') {
                operator.add(c);
            } else if (c == ')') {
                nums.add(fireLastOp());
            } else if (c == '(') { // ignore
            } else {
                throw new RuntimeException("not support op:" + c);
            }
        }
        while (!operator.isEmpty()) {
            nums.addFirst(fireFrontOp());
        }
        return nums.removeFirst();
    }

    private int fireLastOp() {
        char op = operator.removeLast();
        int b = nums.removeLast();
        int a = nums.removeLast();
        return fireOp(op, a, b);
    }

    private int fireFrontOp() {
        char op = operator.removeFirst();
        int a = nums.removeFirst();
        int b = nums.removeFirst();
        return fireOp(op, a, b);
    }

    private int fireOp(char op, int a, int b) {
        switch (op) {
            case '+': {
                return a + b;
            }
            case '-': {
                return a - b;
            }
            case '*': {
                return a * b;
            }
            default:
                throw new RuntimeException("not support op:" + op);
        }
    }

    @Test
    public void test() {
        int actual = solve("1+2");
        Assert.assertEquals(actual, 3);
    }

    @Test
    public void test1() {
        int actual = solve("(2*(3-4))*5");
        Assert.assertEquals(actual, -10);
    }

    @Test
    public void test2() {
        int actual = solve("3+2*3*4-1");
        Assert.assertEquals(actual, 26);
    }

    @Test
    public void test3() {
        int actual = solve("100+100");
        Assert.assertEquals(actual, 200);
    }

    @Test
    public void test4() {
        int actual = solve("1-2-3");
        Assert.assertEquals(actual, -4);
    }
}
