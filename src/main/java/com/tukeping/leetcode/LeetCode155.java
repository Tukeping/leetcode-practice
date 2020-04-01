package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=155 lang=java
 *
 * [155] 最小栈
 *
 * https://leetcode-cn.com/problems/min-stack/description/
 *
 * algorithms
 * Easy (50.70%)
 * Likes:    345
 * Dislikes: 0
 * Total Accepted:    61.8K
 * Total Submissions: 121.9K
 * Testcase Example:  '["MinStack","push","push","push","getMin","pop","top","getMin"]\n' +
  '[[],[-2],[0],[-3],[],[],[],[]]'
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * stack | design
 *
 * amazon | bloomberg | google | snapchat | uber | zenefits
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode155 {

    class MinStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMini;

        public MinStack() {
            stackData = new Stack<>();
            stackMini = new Stack<>();
        }

        public void push(int x) {
            stackData.push(x);
            if (stackMini.isEmpty() || getMin() > x) stackMini.push(x);
            else stackMini.push(getMin());
        }

        public void pop() {
            stackData.pop();
            stackMini.pop();
        }

        public int top() {
            return stackData.peek();
        }

        public int getMin() {
            return stackMini.peek();
        }
    }

    @Test
    public void test1() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertThat(minStack.getMin(), is(-3));    // --> 返回 -3.
        minStack.pop();
        assertThat(minStack.top(), is(0));        // --> 返回 0.
        assertThat(minStack.getMin(), is(-2));    // --> 返回 -2.
    }

    @Test
    public void test2() {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        assertThat(minStack.getMin(), is(0));
        minStack.pop();
        assertThat(minStack.getMin(), is(0));
    }
}
