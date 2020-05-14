package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/4
 **/
public class LCOF59_2 {

    class MaxQueue {

        private Queue<Integer> queue;
        private PriorityQueue<Integer> maxHeap;

        public MaxQueue() {
            queue = new LinkedList<>();
            maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        }

        public int max_value() {
            return maxHeap.isEmpty() ? -1 : maxHeap.peek();
        }

        public void push_back(int value) {
            queue.add(value);
            maxHeap.add(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) return -1;
            int val = queue.poll();
            maxHeap.remove(val);
            return val;
        }
    }

    /**
     * 输入:
     * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
     * [[],[1],[2],[],[],[]]
     * 输出: [null,null,null,2,1,2]
     */
    @Test
    public void test1() {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        assertThat(maxQueue.max_value(), is(2));
        assertThat(maxQueue.pop_front(), is(1));
        assertThat(maxQueue.max_value(), is(2));
    }

    /**
     * 输入:
     * ["MaxQueue","pop_front","max_value"]
     * [[],[],[]]
     * 输出: [null,-1,-1]
     */
    @Test
    public void test2() {
        MaxQueue maxQueue = new MaxQueue();
        assertThat(maxQueue.pop_front(), is(-1));
        assertThat(maxQueue.max_value(), is(-1));
    }
}
