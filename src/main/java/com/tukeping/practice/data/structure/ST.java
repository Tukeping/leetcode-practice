package com.tukeping.practice.data.structure;

import org.junit.Test;

import java.util.TreeMap;

/**
 * @author tukeping
 * @date 2021/8/3
 **/
public class ST<Key, Value> {

    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap<>();
    }

    public void put(Key key, Value val) {
        if (val == null) st.remove(key);
        else st.put(key, val);
    }

    public Value get(Key key) {
        return st.get(key);
    }

    public void delete(Key key) {
        st.remove(key);
    }

    public boolean contains(Key key) {
        return st.containsKey(key);
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public int size() {
        return st.size();
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    @Test
    public void test() {
        String s = "SEARCHEXAMPLE";
        ST<String, Integer> st = new ST<>();
        for (int i = 0; i < s.length(); i++) {
            String key = String.valueOf(s.charAt(i));
            st.put(key, i);
        }
        for (String key : st.keys()) {
            System.out.println(key + " " + st.get(key));
        }
    }
}
