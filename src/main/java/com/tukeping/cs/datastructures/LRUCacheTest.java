package com.tukeping.cs.datastructures;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/9
 **/
class LRUCache {

    class Node {
        int key;
        int val;
        Node pre;
        Node next;
    }

    class Deque {
        int capacity;
        int size;
        Node head, tail;

        public Deque(int capacity) {
            this.capacity = capacity;
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

        public Integer removeLast() {
            if (isEmpty()) return null;

            Node tmp = tail;

            tail = tail.pre;
            tail.next = null;

            size--;
            return tmp.key;
        }

        public void offerFirst(Node node) {
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

        public Node getFirst() {
            return head;
        }

        public Node getLast() {
            return tail;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    int capacity;
    Map<Integer, Node> cache;
    Deque deque;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        deque = new Deque(capacity);
    }

    public void put(int key, int value) {
        boolean addNew = !cache.containsKey(key);

        Node node = cache.getOrDefault(key, new Node());
        node.key = key;
        node.val = value;
        cache.put(key, node);

        if (!addNew) deque.remove(node);
        deque.offerFirst(node);

        if (deque.size() > capacity) {
            Integer oldKey = deque.removeLast();
            if (addNew) cache.remove(oldKey);
        }
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        deque.remove(node);
        deque.offerFirst(node);
        if (deque.size() > capacity) deque.removeLast();
        return node.val;
    }
}

public class LRUCacheTest {

    @Test
    public void test1() {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1), is(1));       // 返回  1
        cache.put(3, 3);                            // 该操作会使得密钥 2 作废
        assertThat(cache.get(2), is(-1));      // 返回 -1 (未找到)
        cache.put(4, 4);                            // 该操作会使得密钥 1 作废
        assertThat(cache.get(1), is(-1));      // 返回 -1 (未找到)
        assertThat(cache.get(3), is(3));       // 返回  3
        assertThat(cache.get(4), is(4));       // 返回  4
    }

    @Test
    public void test2() {
        LRUCache cache = new LRUCache(10 /* 缓存容量 */);
        cache.put(10, 13);
        cache.put(3, 17);
        cache.put(6, 11);
        cache.put(10, 5);
        cache.put(9, 10);
        assertThat(cache.get(13), is(-1));
        cache.put(2, 19);
        assertThat(cache.get(2), is(19));
        assertThat(cache.get(3), is(17));
        cache.put(5, 25);
        assertThat(cache.get(8), is(-1));
        cache.put(9, 22);
        cache.put(5, 5);
        cache.put(1, 30);
        assertThat(cache.get(11), is(-1));
        cache.put(9, 12);
        assertThat(cache.get(7), is(-1));
        assertThat(cache.get(5), is(5));
        assertThat(cache.get(8), is(-1));
        assertThat(cache.get(9), is(12));
        cache.put(4, 30);
        cache.put(9, 3);
        assertThat(cache.get(9), is(3));
        assertThat(cache.get(10), is(5));
        assertThat(cache.get(10), is(5));
        cache.put(6, 14);
        cache.put(3, 1);
        assertThat(cache.get(3), is(1));
        cache.put(10, 11);
        assertThat(cache.get(8), is(-1));
        cache.put(2, 14);
    }

    @Test
    public void test3() {
        LRUCache cache = new LRUCache(1);
        cache.put(2, 1);
        assertThat(cache.get(2), is(1));
    }
}
