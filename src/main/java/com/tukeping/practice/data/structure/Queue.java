package com.tukeping.practice.data.structure;

import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/8/1
 **/
public class Queue {

    class LinkedQueue {

        private class Node {
            String item;
            Node next;
        }

        private Node first, last;

        public void enqueue(String item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
        }

        public String dequeue() {
            String item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            return item;
        }

        public boolean isEmpty() {
            return first == null;
        }
    }

    @Test
    public void test() {
        LinkedQueue queue = new LinkedQueue();

    }
}
