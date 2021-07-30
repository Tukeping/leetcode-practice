package com.tukeping.algorithms.filter;

import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;

/**
 * @author tukeping
 * @date 2021/7/30
 **/
public class BloomFilter {
    private static final int DEFAULT_SIZE = 1 << 30;
    private static final int[] SEEDS = new int[]{3, 5, 7, 11, 13, 19, 23, 37};
    private static final int SEED_SIZE = 8;
    private final BitSet bits = new BitSet(DEFAULT_SIZE);

    public void add(String value) {
        for (char c : value.toCharArray()) {
            bits.set(hash(c), true);
        }
    }

    public boolean test(String value) {
        if (value == null || value.isEmpty()) return false;
        else {
            boolean ret = true;
            for (char c : value.toCharArray()) {
                ret = ret && bits.get(hash(c));
            }
            return ret;
        }
    }

    private int hash(char c) {
        int hash = SEEDS[c % SEED_SIZE] + c;
        return hash % DEFAULT_SIZE;
    }

    @Test
    public void test() {
        String value = "www.baidu.com";
        add(value);
        boolean flag = test(value);
        Assert.assertTrue(flag);
        flag = test("www.google.com");
        Assert.assertFalse(flag);
    }
}
