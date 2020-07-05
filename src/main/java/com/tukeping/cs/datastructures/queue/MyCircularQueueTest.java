package com.tukeping.cs.datastructures.queue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/5/8
 **/
class MyCircularQueue {
    private int[] data;
    private int k, size, head, tail;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.k = k;
        this.head = this.tail = -1;
        this.data = new int[k];
        Integer.highestOneBit(6);
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        if (isEmpty()) head++;
        tail = (tail + 1) % k;
        size++;
        data[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            head = tail = -1;
            return false;
        }
        head = (head + 1) % k;
        size--;
        if (isEmpty()) {
            head = tail = -1;
        }
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) return -1;
        return data[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) return -1;
        return data[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == k;
    }
}

public class MyCircularQueueTest {

    @Test
    public void test1() {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        assertTrue(circularQueue.enQueue(1));  // 返回 true
        assertTrue(circularQueue.enQueue(2));  // 返回 true
        assertTrue(circularQueue.enQueue(3));  // 返回 true
        assertFalse(circularQueue.enQueue(4));  // 返回 false，队列已满
        assertThat(circularQueue.Rear(), is(3));  // 返回 3
        assertTrue(circularQueue.isFull());  // 返回 true
        assertTrue(circularQueue.deQueue());  // 返回 true
        assertTrue(circularQueue.enQueue(4));  // 返回 true
        assertThat(circularQueue.Rear(), is(4));  // 返回 4
    }

    @Test
    public void test2() {
        MyCircularQueue circularQueue = new MyCircularQueue(6);
        assertTrue(circularQueue.enQueue(6));
        assertThat(circularQueue.Rear(), is(6));
        assertThat(circularQueue.Rear(), is(6));
        assertTrue(circularQueue.deQueue());
        assertTrue(circularQueue.enQueue(5));
        assertThat(circularQueue.Rear(), is(5));
        assertTrue(circularQueue.deQueue());
        assertThat(circularQueue.Front(), is(-1));
        assertFalse(circularQueue.deQueue());
        assertFalse(circularQueue.deQueue());
        assertFalse(circularQueue.deQueue());
    }

    @Test
    public void test3() {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        assertTrue(circularQueue.enQueue(2));
        assertThat(circularQueue.Rear(), is(2));
        assertThat(circularQueue.Front(), is(2));
        assertTrue(circularQueue.deQueue());
        assertThat(circularQueue.Front(), is(-1));
        assertFalse(circularQueue.deQueue());
        assertThat(circularQueue.Front(), is(-1));
        assertTrue(circularQueue.enQueue(4));
        assertTrue(circularQueue.enQueue(2));
        assertTrue(circularQueue.enQueue(2));
        assertFalse(circularQueue.enQueue(3));
    }

    @Test
    public void test4() {
        MyCircularQueue circularQueue = new MyCircularQueue(81);
        assertTrue(circularQueue.enQueue(69));
        assertTrue(circularQueue.deQueue());
        assertTrue(circularQueue.enQueue(92));
        assertTrue(circularQueue.enQueue(12));
        assertTrue(circularQueue.deQueue());
        assertFalse(circularQueue.isFull());
        assertFalse(circularQueue.isFull());
        assertThat(circularQueue.Front(), is(12));
    }
}