package com.tukeping.leetcode.lcof;

/*
 * 面试题09. 用两个栈实现队列
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 */

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF09 {

    class CQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack1.isEmpty()) return -1;
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
            int head = stack2.pop();
            while (!stack2.isEmpty()) stack1.push(stack2.pop());
            return head;
        }
    }

    /**
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     */
    @Test
    public void test1() {
        CQueue queue = new CQueue();
        queue.appendTail(3);
        assertThat(queue.deleteHead(), is(3));
        assertThat(queue.deleteHead(), is(-1));
    }

    /**
     * 输入：
     * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
     * [[],[],[5],[2],[],[]]
     * 输出：[null,-1,null,null,5,2]
     */
    @Test
    public void test2() {
        CQueue queue = new CQueue();
        assertThat(queue.deleteHead(), is(-1));
        queue.appendTail(5);
        queue.appendTail(2);
        assertThat(queue.deleteHead(), is(5));
        assertThat(queue.deleteHead(), is(2));
    }
}
