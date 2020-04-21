package com.tukeping.leetcode.weekly.contest109;

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode933 {

    class RecentCounter {

        private LinkedList<Integer> data;
        private int size;

        public RecentCounter() {
            this.data = new LinkedList<>();
            this.size = 0;
        }

        public int ping(int t) {
            size++;
            data.addLast(t);
            while (size > 0) {
                if (t - 3000 > data.getFirst()) {
                    data.removeFirst();
                    size--;
                } else {
                    break;
                }
            }
            return size;
        }
    }

    /**
     * 输入：inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
     * 输出：[null,1,2,3,3]
     */
    @Test
    public void test1() {
        RecentCounter recentCounter = new RecentCounter();
        assertThat(recentCounter.ping(1), is(1));
        assertThat(recentCounter.ping(100), is(2));
        assertThat(recentCounter.ping(3001), is(3));
        assertThat(recentCounter.ping(3002), is(3));
    }
}
