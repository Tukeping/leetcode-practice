package com.tukeping.systemdesign;

import java.util.LinkedList;

/**
 * @author tukeping
 * @date 2019/12/23
 **/
public class LRUCache {

    private int capacity = 10;

    private LinkedList<Integer> cache = new LinkedList<>();

    public void add(Integer value) {
        if (null == value) {
            return;
        }

        boolean cacheFull = cache.size() >= capacity;

        cache.remove(value);
        cache.addFirst(value);

        if (cacheFull) {
            cache.removeLast();
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache();
        lruCache.add(1);
        lruCache.add(2);
        lruCache.add(3);
        lruCache.add(4);
        lruCache.add(5);
        lruCache.add(6);
        lruCache.add(7);
        lruCache.add(8);
        lruCache.add(9);
        lruCache.add(10);
        lruCache.add(11);
        lruCache.add(1);

        lruCache.cache.forEach(System.out::println);
    }
}
