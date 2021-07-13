package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU缓存机制
 *
 * https://leetcode-cn.com/problems/lru-cache/description/
 *
 * algorithms
 * Medium (44.72%)
 * Likes:    405
 * Dislikes: 0
 * Total Accepted:    32.6K
 * Total Submissions: 71.6K
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) -
 * 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 缓存容量 );
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * design
 *
 * amazon | bloomberg | facebook | google | microsoft | palantir | snapchat | twitter | uber | yahoo | zenefits
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode146 {

    class LRUCache {
        private int capacity;
        private LinkedList<Integer> queue;
        private Map<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.queue = new LinkedList<>();
            this.cache = new HashMap<>(capacity, 1.0F);
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                queue.remove((Integer) key);
                queue.addFirst(key);
                if (queue.size() > capacity) {
                    cache.remove(queue.removeLast());
                }
                return cache.get(key);
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                queue.remove((Integer) key);
                queue.addFirst(key);
                cache.put(key, value);
            } else {
                queue.addFirst(key);
                cache.put(key, value);
                if (queue.size() > capacity) {
                    cache.remove(queue.removeLast());
                }
            }
        }
    }

    /**
     * LRUCache cache = new LRUCache(2)
     * cache.put(1,1);
     * cache.put(2,2);
     * cache.get(1);       // 返回  1
     * cache.put(3,3);     // 该操作会使得密钥 2 作废
     * cache.get(2);       // 返回 -1 (未找到)
     * cache.put(4,4);     // 该操作会使得密钥 1 作废
     * cache.get(1);       // 返回 -1 (未找到)
     * cache.get(3);       // 返回  3
     * cache.get(4);       // 返回  4
     **/
    @Test
    public void test1() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1), is(1));   // 返回  1
        cache.put(3, 3);                        // 该操作会使得 2 作废
        assertThat(cache.get(2), is(-1));  // 返回 -1 (未找到)
        cache.put(4, 4);                        // 该操作会使得 1 作废
        assertThat(cache.get(1), is(-1));  // 返回 -1 (未找到)
        assertThat(cache.get(3), is(3));   // 返回  3
        assertThat(cache.get(4), is(4));   // 返回  4
    }

    /**
     * 输入: ["LRUCache",
     *       "put",
     *       "put",
     *       "get",
     *       "put",
     *       "put",
     *       "get"]
     *      [[2],
     *       [2,1],
     *       [2,2],
     *       [2],
     *       [1,1],
     *       [4,1],
     *       [2]]
     * 输出: [null,null,null,2,null,null,-1]
     */
    @Test
    public void test2() {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        assertThat(cache.get(2), is(2));
        cache.put(1, 1);
        cache.put(4, 1);
        assertThat(cache.get(2), is(-1));
    }
}
