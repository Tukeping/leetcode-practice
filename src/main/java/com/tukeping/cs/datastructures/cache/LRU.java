package com.tukeping.cs.datastructures.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2020/5/9
 **/
public class LRU {

    class Node {
        int key;
        int val;
        Node pre, next;
    }

    class Deque {
        int size;
        Node head, tail;

        public Deque() {
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void remove(Node node) {
            if (isEmpty()) {
                return;
            } else if (node.key == head.key) {
                head = head.next;
                if (head != null) head.pre = null;
            } else if (node.key == tail.key) {
                tail = tail.pre;
                if (tail != null) tail.next = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            size--;
        }

        public void addFirst(Node node) {
            if (isEmpty()) {
                head = tail = node;
            } else {
                node.next = head;
                node.pre = null;
                head.pre = node;
                head = node;
            }
            size++;
        }

        public Integer removeLast() {
            if (isEmpty()) return null;

            Node tmp = tail;

            tail = tail.pre;
            tail.next = null;

            size--;
            return tmp.key;
        }
    }

    int capacity;
    Map<Integer, Node> cache = new HashMap<>();
    Deque queue = new Deque();

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        Node node = cache.get(key);

        queue.remove(node);
        queue.addFirst(node);

        if (queue.size() > capacity) {
            queue.removeLast();
        }

        return node.val;
    }

    public void put(int key, int value) {
        boolean addNew = !cache.containsKey(key);

        Node node = cache.getOrDefault(key, new Node());
        node.key = key;
        node.val = value;

        cache.put(key, node);

        if (!addNew) queue.remove(node);
        queue.addFirst(node);

        if (queue.size() > capacity) {
            Integer oldKey = queue.removeLast();
            if (addNew) cache.remove(oldKey);
        }
    }
}
