package com.tukeping.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC88 {

    public int findKth(int[] a, int n, int K) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : a) {
            minHeap.add(num);
            if (minHeap.size() > K) {
                minHeap.poll();
            }
        }
        return minHeap.isEmpty() ? -1 : minHeap.poll();
    }

    @Test
    public void test() {
        int actual = findKth(new int[]{1, 3, 5, 2, 2}, 5, 3);
        Assert.assertEquals(actual, 2);
        actual = findKth(new int[]{10, 10, 9, 9, 8, 7, 5, 6, 4, 3, 4, 2}, 12, 3);
        Assert.assertEquals(actual, 9);
    }
}
