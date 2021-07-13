package com.tukeping.cs.datastructures.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2021/7/4
 **/
public class LRUTest {

    @Test
    public void test1() {
        LRU cache = new LRU(2 /* 缓存容量 */);
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
        LRU cache = new LRU(10 /* 缓存容量 */);
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
        LRU cache = new LRU(1);
        cache.put(2, 1);
        assertThat(cache.get(2), is(1));
    }
}
