package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=295 lang=java
 *
 * [295] 数据流的中位数
 *
 * https://leetcode-cn.com/problems/find-median-from-data-stream/description/
 *
 * algorithms
 * Hard (40.70%)
 * Likes:    122
 * Dislikes: 0
 * Total Accepted:    10.9K
 * Total Submissions: 25.4K
 * Testcase Example:  '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n' +
  '[[],[1],[2],[],[3],[]]'
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * heap | design
 *
 * google
 *
 * @author tukeping
 * @date 2020/2/28
 **/
public class LeetCode295 {

    class MedianFinder {
        private PriorityQueue<Integer> maxHeap; // with comparator , left
        private PriorityQueue<Integer> minHeap; // by default , right
        private AtomicInteger count;

        /** initialize your data structure here. */
        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            minHeap = new PriorityQueue<>();
            count = new AtomicInteger(0);
        }

        public void addNum(int num) {
            // find maxHeap and minHeap ratio
            int n = count.incrementAndGet();
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = n / 2 + 1;
            }

            // put heap
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else if (minHeap.size() < n) {
                minHeap.add(num);
            }

            // adjust heap element
            while (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }
            while (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == 0) {
                return 0D;
            } else if (maxHeap.size() == 1 && minHeap.size() == 0) {
                return maxHeap.peek();
            } else if (maxHeap.size() == minHeap.size() + 1) {
                return maxHeap.peek();
            } else if (minHeap.size() > 0) {
                return (minHeap.peek().doubleValue() + maxHeap.peek().doubleValue()) / 2.0D;
            } else {
                return 0D;
            }
        }
    }

    @Test
    public void test1() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), is(1.5D));
        medianFinder.addNum(3);
        assertThat(medianFinder.findMedian(), is(2D));
    }

    @Test
    public void test2() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), is(6.0D));
        medianFinder.addNum(10);
        assertThat(medianFinder.findMedian(), is(8.0D));
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), is(6.0D));
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), is(6.0D));
        medianFinder.addNum(5);
        assertThat(medianFinder.findMedian(), is(6.0D));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), is(5.5D));
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), is(6.0D));
        medianFinder.addNum(3);
        assertThat(medianFinder.findMedian(), is(5.5D));
        medianFinder.addNum(1);
        assertThat(medianFinder.findMedian(), is(5.0D));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), is(4.0D));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), is(3.0D));
    }
}
