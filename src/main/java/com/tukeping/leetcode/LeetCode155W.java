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
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
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
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * @author tukeping
 * @date 2019/11/21
 **/
public class LeetCode155W {

    Stack<Integer> data = new Stack<>();
    Stack<Integer> sortedData = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public LeetCode155W() {

    }

    public void push(int x) {
        data.push(x);
        if (sortedData.isEmpty() || sortedData.peek() < x) {
            sortedData.add(x);
        } else {
            sortedData.add(sortedData.peek());
        }
    }

    public void pop() {
        data.pop();
        sortedData.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return sortedData.peek();
    }

    @Test
    public void test() {
        LeetCode155W minStack = new LeetCode155W();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }
}
