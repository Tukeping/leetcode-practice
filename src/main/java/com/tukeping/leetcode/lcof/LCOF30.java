package com.tukeping.leetcode.lcof;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF30 {

    class MinStack {
        Stack<Integer> stack;
        PriorityQueue<Integer> minHeap;

        public MinStack() {
            stack = new Stack<>();
            minHeap = new PriorityQueue<>();
        }

        public void push(int x) {
            stack.push(x);
            minHeap.add(x);
        }

        public void pop() {
            int x = stack.pop();
            minHeap.remove(x);
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minHeap.isEmpty() ? -1 : minHeap.peek();
        }
    }

    @Test
    public void test1() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertThat(minStack.min(), is(-3));
        minStack.pop();
        assertThat(minStack.top(), is(0));
        assertThat(minStack.min(), is(-2));
    }

    @Test
    public void test2() {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        assertThat(minStack.min(), is(0));
        minStack.pop();
        assertThat(minStack.min(), is(0));
    }
}
