package com.tukeping.leetcode.structures;

/**
 * @author tukeping
 * @date 2020/2/21
 **/
public class Pair<K, V> {
    public K key;
    public V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Pair<K,V> of(K key, V value) {
        return new Pair<>(key, value);
    }
}