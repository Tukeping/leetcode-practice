package com.tukeping.leetcode.weekly.contest180;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/*
 * 5357. 设计一个支持增量操作的栈
 *
 * 请你设计一个支持下述操作的栈。
 *
 * 实现自定义栈类 CustomStack ：
 *
 * CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
 * void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
 * int pop()：返回栈顶的值，或堆栈为空时返回 -1 。
 * void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
 *
 * 示例：
 *
 * 输入：
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * 输出：
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 * 解释：
 * CustomStack customStack = new CustomStack(3); // 栈是空的 []
 * customStack.push(1);                          // 栈变为 [1]
 * customStack.push(2);                          // 栈变为 [1, 2]
 * customStack.pop();                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
 * customStack.push(2);                          // 栈变为 [1, 2]
 * customStack.push(3);                          // 栈变为 [1, 2, 3]
 * customStack.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
 * customStack.increment(5, 100);                // 栈变为 [101, 102, 103]
 * customStack.increment(2, 100);                // 栈变为 [201, 202, 103]
 * customStack.pop();                            // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
 * customStack.pop();                            // 返回 202 --> 返回栈顶值 102，栈变为 [201]
 * customStack.pop();                            // 返回 201 --> 返回栈顶值 101，栈变为 []
 * customStack.pop();                            // 返回 -1 --> 栈为空，返回 -1
 *
 * 提示：
 *
 * 1 <= maxSize <= 1000
 * 1 <= x <= 1000
 * 1 <= k <= 1000
 * 0 <= val <= 100
 * 每种方法 increment，push 以及 pop 分别最多调用 1000 次
 */

/**
 * @author tukeping
 * @date 2020/3/15
 **/
public class LeetCode5357 {

    class CustomStack {
        int maxSize;
        List<Integer> stack;

        CustomStack(int _maxSize) {
            maxSize = _maxSize;
            stack = new ArrayList<>();
        }

        void push(int x) {
            if ((int) stack.size() < maxSize)
                stack.add(x);
        }

        int pop() {
            if (stack.isEmpty())
                return -1;

            int x = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            return x;
        }

        void increment(int k, int val) {
            for (int i = 0; i < Math.min(k, (int) stack.size()); i++)
                stack.set(i, stack.get(i) + val);
        }
    }

    class CustomStack2 {

        private int size;
        private int[] stack;

        public CustomStack2(int maxSize) {
            stack = new int[maxSize];
        }

        public void push(int x) {
            if (size == stack.length) {
                return;
            }
            stack[size] = x;
            size++;
        }

        public int pop() {
            if (size == 0) {
                return -1;
            }
            int x = stack[size - 1];
            size--;
            return x;
        }

        public void increment(int k, int val) {
            if (k >= size) {
                for (int i = 0; i < stack.length; i++) {
                    stack[i] += val;
                }
            } else { // k < size
                for (int i = 0; i < k; i++) {
                    stack[i] += val;
                }
            }
        }
    }

    @Test
    public void test1() {
        CustomStack customStack = new CustomStack(3); // 栈是空的 []
        customStack.push(1);                               // 栈变为 [1]
        customStack.push(2);                               // 栈变为 [1, 2]
        assertThat(customStack.pop(), is(2));           // 返回 2 --> 返回栈顶值，栈变为 [1]
        customStack.push(2);                               // 栈变为 [1, 2]
        customStack.push(3);                               // 栈变为 [1, 2, 3]
        customStack.push(4);                               // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
        customStack.increment(5, 100);                 // 栈变为 [101, 102, 103]
        customStack.increment(2, 100);                 // 栈变为 [201, 202, 103]
        assertThat(customStack.pop(), is(103));         // 返回 103 --> 返回栈顶值 03，栈变为 [201, 202]
        assertThat(customStack.pop(), is(202));         // 返回 202 --> 返回栈顶值 02，栈变为 [201]
        assertThat(customStack.pop(), is(201));         // 返回 201 --> 返回栈顶值 01，栈变为 []
        assertThat(customStack.pop(), is(-1));          // 返回 -1 --> 栈为空，返回 -1
    }
}
