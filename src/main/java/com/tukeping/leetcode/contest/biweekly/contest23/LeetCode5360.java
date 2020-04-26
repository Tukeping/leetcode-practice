package com.tukeping.leetcode.contest.biweekly.contest23;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/4
 **/
public class LeetCode5360 {

    public int countLargestGroup(int n) {
        if (n == 1) return 1;

        // <sumUp, count>
        Map<Integer, Integer> map = new HashMap<>();
        AtomicInteger maxCount = new AtomicInteger(1);
        for (int i = 1; i <= n; i++) {
            int x = sumUp(i);
            if (map.containsKey(x)) {
                int y = map.get(x) + 1;
                map.put(x, y);
                if (y > maxCount.get()) {
                    maxCount.set(y);
                }
            } else {
                map.put(x, 1);
            }
        }

        AtomicInteger ans = new AtomicInteger(0);
        map.forEach((k, v) -> {
            if (v == maxCount.get()) {
                ans.getAndIncrement();
            }
        });

        return ans.get();
    }

    private int sumUp(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    @Test
    public void test1() {
        int n = countLargestGroup(13);
        assertThat(n, is(4));
    }

    @Test
    public void test2() {
        int n = countLargestGroup(2);
        assertThat(n, is(2));
    }

    @Test
    public void test3() {
        int n = countLargestGroup(15);
        assertThat(n, is(6));
    }

    @Test
    public void test4() {
        int n = countLargestGroup(24);
        assertThat(n, is(5));
    }
}
