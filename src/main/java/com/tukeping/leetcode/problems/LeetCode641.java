package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=641 lang=java
 *
 * [641] 设计循环双端队列
 *
 * https://leetcode-cn.com/problems/design-circular-deque/description/
 *
 * algorithms
 * Medium (48.46%)
 * Likes:    22
 * Dislikes: 0
 * Total Accepted:    3.7K
 * Total Submissions: 7.6K
 * Testcase Example:  '["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getRear","isFull","deleteLast","insertFront","getFront"]\n' +
  '[[3],[1],[2],[3],[4],[],[],[],[4],[]]'
 *
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 *
 *
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 *
 *
 * 示例：
 *
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);                    // 返回 true
 * circularDeque.insertLast(2);                    // 返回 true
 * circularDeque.insertFront(3);                    // 返回 true
 * circularDeque.insertFront(4);                    // 已经满了，返回 false
 * circularDeque.getRear();                  // 返回 2
 * circularDeque.isFull();                        // 返回 true
 * circularDeque.deleteLast();                    // 返回 true
 * circularDeque.insertFront(4);                    // 返回 true
 * circularDeque.getFront();                // 返回 4
 *
 *
 *
 *
 * 提示：
 *
 *
 * 所有值的范围为 [1, 1000]
 * 操作次数的范围为 [1, 1000]
 * 请不要使用内置的双端队列库。
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2019/12/29
 **/
public class LeetCode641 {

    class MyCircularDeque {

        Node[] nodes;
        int nodeCount = 0;

        class Node {
            int val;

            public Node(int val) {
                this.val = val;
            }
        }

        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            if (k <= 0) {
                throw new IllegalArgumentException("k must be positive");
            }
            nodes = new Node[k];
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (nodes.length == 0 || nodes.length == nodeCount) {
                return false;
            }

            Node newHead = new Node(value);

            if (nodeCount > 0) {
                System.arraycopy(nodes, 0, nodes, 1, nodeCount);
            }

            nodes[0] = newHead;

            nodeCount++;

            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (nodes.length == 0 || nodes.length == nodeCount) {
                return false;
            }

            Node newLast = new Node(value);

            nodes[nodeCount] = newLast;

            nodeCount++;

            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (nodes.length == 0 || nodeCount == 0) {
                return false;
            }

            nodes[0] = null;

            if (nodeCount > 1) {
                System.arraycopy(nodes, 1, nodes, 0, nodeCount - 1);
            }

            nodeCount--;

            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (nodes.length == 0 || nodeCount == 0) {
                return false;
            }

            nodes[nodeCount - 1] = null;

            nodeCount--;

            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if (nodes.length == 0 || nodeCount == 0) {
                return -1;
            }
            return nodes[0].val;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            if (nodes.length == 0 || nodeCount == 0) {
                return -1;
            }
            return nodes[nodeCount - 1].val;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return nodeCount == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return nodeCount == nodes.length;
        }
    }

    /**
     * Testcase Example:
     * '["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getRear","isFull","deleteLast","insertFront","getFront"]\n' +
     * '[[3],[1],[2],[3],[4],[],[],[],[4],[]]'
     */
    @Test
    public void test() {
        // 设置容量大小为3
        MyCircularDeque circularDeque = new MyCircularDeque(3);
        // 返回 true
        Assert.assertTrue(circularDeque.insertLast(1));
        // 返回 true
        Assert.assertTrue(circularDeque.insertLast(2));
        // 返回 true
        Assert.assertTrue(circularDeque.insertFront(3));
        // 已经满了，返回 false : 3->1->2
        Assert.assertFalse(circularDeque.insertFront(4));
        // 返回 2
        Assert.assertEquals(2, circularDeque.getRear());
        // 返回 true
        Assert.assertTrue(circularDeque.isFull());
        // 返回 true
        Assert.assertTrue(circularDeque.deleteLast());
        // 返回 true
        Assert.assertTrue(circularDeque.insertFront(4));
        // 返回 4
        Assert.assertEquals(4, circularDeque.getFront());
    }

    /**
     * "MyCircularDeque[4]",
     * "insertFront[9]",
     * "deleteLast[]",
     * "getRear[]",
     * "getFront[]",
     * "getFront[]",
     * "deleteFront[]",
     * "insertFront[6]",
     * "insertLast[5]",
     * "insertFront[9]",
     * "getFront[]",
     * "insertFront[6]"]
     */
    @Test
    public void test1() {
        MyCircularDeque circularDeque = new MyCircularDeque(4);
        Assert.assertTrue(circularDeque.insertFront(9));
        Assert.assertTrue(circularDeque.deleteLast());
        Assert.assertEquals(-1, circularDeque.getRear());
        Assert.assertEquals(-1, circularDeque.getFront());
        Assert.assertEquals(-1, circularDeque.getFront());
        Assert.assertFalse(circularDeque.deleteFront());
        Assert.assertTrue(circularDeque.insertFront(6));
        Assert.assertTrue(circularDeque.insertLast(5));
        Assert.assertTrue(circularDeque.insertFront(9)); // 9,6,5
        Assert.assertEquals(9, circularDeque.getFront());
        Assert.assertTrue(circularDeque.insertFront(6)); // 6,9,6,5
    }
}
