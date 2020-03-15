package com.tukeping.leetcode.structures;

/**
 * @author tukeping
 * @date 2020/2/21
 **/
public class Pair<K, V> {
    public K first;
    public V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <K, V> Pair<K,V> of(K key, V value) {
        return new Pair<>(key, value);
    }
}
