package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=460 lang=java
 *
 * [460] LFU缓存
 *
 * https://leetcode-cn.com/problems/lfu-cache/description/
 *
 * algorithms
 * Hard (34.39%)
 * Likes:    147
 * Dislikes: 0
 * Total Accepted:    7.6K
 * Total Submissions: 19.9K
 * Testcase Example:  '["LFUCache","put","put","get","put","get","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]'
 *
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 *
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) -
 * 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 *
 * 一个项目的使用次数就是该项目被插入后对其调用 get 和 put 函数的次数之和。使用次数会在对应项目被移除后置为 0。
 *
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 *
 * 示例：
 *
 * LFUCache cache = new LFUCache(2);
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回 1
 * cache.put(3, 3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4, 4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 */

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * design
 *
 * amazon | google
 *
 * @author tukeping
 * @date 2020/4/5
 **/
public class LeetCode460 {

    class LFUCache {
        class Node {
            int key, value, freq;
            Node pre;
            Node next;

            public Node() {}

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.freq = 0;
            }
        }

        class DoublyLinkedList {
            Node head;
            Node tail;
            int size;

            public DoublyLinkedList() {
                head = new Node();
                tail = new Node();
                head.next = tail;
                tail.pre = head;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public Node getFirst() {
                if (head.next == tail)
                    return null;
                return head.next;
            }

            public void remove(Node node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                size--;
            }

            public void addLast(Node node) {
                tail.pre.next = node;
                node.next = tail;
                node.pre = tail.pre;
                tail.pre = node;
                size++;
            }
        }

        private int size;
        private int capacity;
        private int minFreq;
        private HashMap<Integer, Node> cache;
        private HashMap<Integer, DoublyLinkedList> freqTable;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.minFreq = 0;
            this.cache = new HashMap<>(capacity, 1.0F);
            this.freqTable = new HashMap<>(capacity, 1.0F);
        }

        public int get(int key) {
            if (!cache.containsKey(key))
                return -1;
            Node node = cache.get(key);
            increase(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (this.capacity <= 0)
                return;

            Node node = upsertCache(key, value);

            if (size > capacity) {
                int deleted = delete(freqTable.get(minFreq).getFirst());
                cache.remove(deleted);
                size--;
            }

            increase(node);
        }

        private Node upsertCache(int key, int value) {
            Node node;
            if (cache.containsKey(key)) {
                node = cache.get(key);
                node.value = value;
            } else {
                node = new Node(key, value);
                cache.put(key, node);
                size++;
            }
            return node;
        }

        private void increase(Node node) {
            delete(node);
            node.freq++;
            addNode(node);
            updateMinFreq(node);
        }

        private int delete(Node node) {
            if (freqTable.containsKey(node.freq)) {
                freqTable.get(node.freq).remove(node);
                if (freqTable.get(node.freq).isEmpty()) {
                    freqTable.remove(node.freq);
                }
            }
            return node.key;
        }

        private void addNode(Node node) {
            DoublyLinkedList dl;
            if (freqTable.containsKey(node.freq)) {
                dl = freqTable.get(node.freq);
            } else {
                dl = new DoublyLinkedList();
                freqTable.put(node.freq, dl);
            }
            dl.addLast(node);
        }

        private void updateMinFreq(Node node) {
            if (node.freq == 1) {
                minFreq = 1;
            } else if (minFreq == node.freq - 1
                    && (freqTable.get(minFreq) == null || freqTable.get(minFreq).isEmpty())) {
                minFreq = node.freq;
            }
        }
    }

    @Test
    public void test1() {
        LFUCache cache = new LFUCache(2 /* capacity (缓存容量) */);
        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1), is(1));
        cache.put(3, 3);    // 去除 key 2
        assertThat(cache.get(2), is(-1)); // 返回 -1 (未找到key 2)
        assertThat(cache.get(3), is(3));
        cache.put(4, 4);    // 去除 key 1
        assertThat(cache.get(1), is(-1)); // 返回 -1 (未找到 key 1)
        assertThat(cache.get(3), is(3));
        assertThat(cache.get(4), is(4));
    }

    @Test
    public void test2() {
        LFUCache cache = new LFUCache(3);
        cache.put(2, 2);
        cache.put(1, 1);
        assertThat(cache.get(2), is(2));
        assertThat(cache.get(1), is(1));
        assertThat(cache.get(2), is(2));
        cache.put(3, 3);
        cache.put(4, 4);
        assertThat(cache.get(3), is(-1));
        assertThat(cache.get(2), is(2));
        assertThat(cache.get(1), is(1));
        assertThat(cache.get(4), is(4));
    }

    @Test
    public void test3() {
        LFUCache cache = new LFUCache(2);
        cache.put(3, 1);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.put(4, 4);
        assertThat(cache.get(2), is(2));
    }

    @Test
    public void test4() {
        LFUCache cache = new LFUCache(0);
        cache.put(0, 0);
        assertThat(cache.get(0), is(-1));
    }
}
