package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=432 lang=java
 *
 * [432] 全 O(1) 的数据结构
 *
 * https://leetcode-cn.com/problems/all-oone-data-structure/description/
 *
 * algorithms
 * Hard (34.73%)
 * Likes:    31
 * Dislikes: 0
 * Total Accepted:    2.2K
 * Total Submissions: 6.4K
 * Testcase Example:  '["AllOne","getMaxKey","getMinKey"]\n[[],[],[]]'
 *
 * 实现一个数据结构支持以下操作：
 *
 *
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。如果这个 key
 * 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 *
 * 挑战：以 O(1) 的时间复杂度实现所有操作。
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * design
 *
 * uber
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode432 {

    class AllOne {
        class KeyNode {
            String key;
            int freq;

            private KeyNode(String key) {
                this.key = key;
                this.freq = 1;
            }

            @Override
            public int hashCode() {
                return key.hashCode();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                KeyNode keyNode = (KeyNode) o;
                return Objects.equals(key, keyNode.key);
            }
        }

        class FreqNode {
            int freq;
            FreqNode prev;
            FreqNode next;
            Set<KeyNode> set; // keep the insertion order

            private FreqNode(int freq, FreqNode prev, FreqNode next) {
                this.freq = freq;
                this.prev = prev;
                this.next = next;
                set = new LinkedHashSet<>();
            }
        }

        private Map<String, KeyNode> keyMap;
        private Map<Integer, FreqNode> freqMap;
        private FreqNode head, tail;

        /** Initialize your data structure here. */
        public AllOne() {
            head = null;
            tail = null;
            keyMap = new HashMap<>();
            freqMap = new HashMap<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if (keyMap.containsKey(key))
                increase(key);
            else
                insertKeyNode(key);
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if (keyMap.containsKey(key))
                decrease(key);
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            return (tail == null) ? "" : tail.set.iterator().next().key;
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            return (head == null) ? "" : head.set.iterator().next().key;
        }

        // helper function
        // increase freq of key, update val if necessary
        private void increase(String key) {
            KeyNode keynode = keyMap.get(key);
            FreqNode freqnode = freqMap.get(keynode.freq);
            keynode.freq += 1;
            FreqNode nextFreqNode = freqnode.next;
            if (nextFreqNode == null) {
                nextFreqNode = new FreqNode(keynode.freq, freqnode, null);
                freqnode.next = nextFreqNode;
                tail = nextFreqNode;
                freqMap.put(keynode.freq, nextFreqNode);
            }
            if (nextFreqNode.freq > keynode.freq) {
                nextFreqNode = insertFreqNodePlus1(keynode.freq, freqnode);
            }
            unlinkKey(keynode, freqnode);
            linkKey(keynode, nextFreqNode);
        }

        private void decrease(String key) {
            KeyNode keynode = keyMap.get(key);
            if (keynode.freq == 1) {
                keyMap.remove(key);
                freqMap.get(1).set.remove(keynode);
                if (freqMap.get(1).set.size() == 0) {
                    deleteFreqNode(freqMap.get(1));
                }
                return;
            }
            FreqNode freqnode = freqMap.get(keynode.freq);
            keynode.freq -= 1;
            FreqNode prevFreqNode = freqnode.prev;
            if (prevFreqNode == null) {
                prevFreqNode = new FreqNode(keynode.freq, null, freqnode);
                freqnode.prev = prevFreqNode;
                head = prevFreqNode;
                freqMap.put(keynode.freq, prevFreqNode);
            }
            if (prevFreqNode.freq < keynode.freq) {
                prevFreqNode = insertFreqNodeSub1(keynode.freq, freqnode);
            }
            unlinkKey(keynode, freqnode);
            linkKey(keynode, prevFreqNode);
        }

        // Inserts a new KeyNode<key, value> with freq 1.
        private void insertKeyNode(String key) {
            KeyNode keynode = new KeyNode(key);
            keyMap.put(key, keynode);
            if (!freqMap.containsKey(1)) {
                FreqNode freqnode = new FreqNode(1, null, head);
                freqnode.next = head;
                if (head != null) head.prev = freqnode;
                if (tail == null) tail = freqnode;
                head = freqnode;
                freqMap.put(1, freqnode);
            }
            linkKey(keynode, freqMap.get(1));
        }

        // insert a new freqnode with new freq after given "freqnode"
        private FreqNode insertFreqNodePlus1(int freq, FreqNode freqnode) {
            FreqNode newfnode = new FreqNode(freq, freqnode, freqnode.next);
            freqMap.put(freq, newfnode);
            if (freqnode.next != null) freqnode.next.prev = newfnode;
            if (freqnode == tail) tail = newfnode;
            freqnode.next = newfnode;
            return newfnode;
        }

        // insert a new freqnode with new freq before given "freqnode"
        private FreqNode insertFreqNodeSub1(int freq, FreqNode freqnode) {
            FreqNode newfnode = new FreqNode(freq, freqnode.prev, freqnode);
            freqMap.put(freq, newfnode);
            if (freqnode.prev != null) freqnode.prev.next = newfnode;
            if (head == freqnode) head = newfnode;
            freqnode.prev = newfnode;
            return newfnode;
        }

        // Unlink keyNode from freqNode
        private void unlinkKey(KeyNode keynode, FreqNode freqnode) {
            freqnode.set.remove(keynode);
            if (freqnode.set == null || freqnode.set.size() == 0) deleteFreqNode(freqnode);
        }

        // Link keyNode to freqNode
        private void linkKey(KeyNode keynode, FreqNode freqnode) {
            freqnode.set.add(keynode);
        }

        // delete freqnode if there is no appending keynode under this freq
        private void deleteFreqNode(FreqNode freqnode) {
            FreqNode prev = freqnode.prev, next = freqnode.next;
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            if (head == freqnode) head = next;
            if (tail == freqnode) tail = prev;
            freqMap.remove(freqnode.freq);
        }
    }

    @Test
    public void test1() {
        AllOne one = new AllOne();
        one.inc("x"); // x = 1
        one.inc("y"); // y = 1
        one.inc("z"); // z = 1
        one.inc("x"); // x = 2
        one.dec("x"); // x = 1
        one.inc("x"); // x = 2
        one.dec("y"); // remove y
        one.dec("y"); // ignore
        String max = one.getMaxKey(); // x
        assertThat(max, is("x"));
        String min = one.getMinKey(); // z
        assertThat(min, is("z"));
    }
}
