package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/3
 **/
public class LCOF41 {

    class MedianFinder {
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;
        int count;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            int half = ++count;
            half = (half % 2 == 0) ? half / 2 : half / 2 + 1;

            if (maxHeap.isEmpty() || num < maxHeap.peek()) {
                maxHeap.add(num);
            } else if (minHeap.size() < half) {
                minHeap.add(num);
            }

            while (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }
            while (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.isEmpty()) {
                return 0.0;
            } else if (maxHeap.size() == minHeap.size() + 1) { // 奇数
                return maxHeap.peek();
            } else { // 偶数
                return (maxHeap.peek().doubleValue() + minHeap.peek().doubleValue()) / 2.0;
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
