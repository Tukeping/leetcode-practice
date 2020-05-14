package com.tukeping.cp.leetcode.contest.weekly.contest187;

import org.junit.Test;

import java.util.Objects;
import java.util.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/3
 **/
public class LeetCode5402 {

    static class Pair<K, V> {
        public K first;
        public V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public static <K, V> Pair<K, V> of(K key, V value) {
            return new Pair<>(key, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return first.equals(pair.first) &&
                    second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;

        // value, index
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>((o1, o2) -> o1.first - o2.first);
        PriorityQueue<Pair<Integer, Integer>> maxHeap = new PriorityQueue<>((o1, o2) -> o2.first - o1.first);

        int ans = 0, winSize = 0;
        for (int i = 0, j = 0; j < n && i <= j; ) {
            winSize = j - i + 1;

            Pair<Integer, Integer> pair = Pair.of(nums[j], j);

            if (minHeap.size() < winSize) {
                minHeap.offer(pair);
            }
            if (maxHeap.size() < winSize) {
                maxHeap.offer(pair);
            }

            Pair<Integer, Integer> mxP = maxHeap.peek();
            Pair<Integer, Integer> minP = minHeap.peek();
            int mx = mxP.first;
            int min = minP.first;

            int absMinus = Math.abs(mx - min);
            if (absMinus <= limit) {
                ans = Math.max(ans, winSize);
                j++;
            } else {
                Pair<Integer, Integer> outDate = Pair.of(nums[i], i);
                minHeap.remove(outDate);
                maxHeap.remove(outDate);
                i++;
                j++;
            }
        }
        return ans;
    }

    /**
     * 输入：nums = [8,2,4,7], limit = 4
     * 输出：2
     * 解释：所有子数组如下：
     * [8] 最大绝对差 |8-8| = 0 <= 4.
     * [8,2] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
     * [2] 最大绝对差 |2-2| = 0 <= 4.
     * [2,4] 最大绝对差 |2-4| = 2 <= 4.
     * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
     * [4] 最大绝对差 |4-4| = 0 <= 4.
     * [4,7] 最大绝对差 |4-7| = 3 <= 4.
     * [7] 最大绝对差 |7-7| = 0 <= 4.
     * 因此，满足题意的最长子数组的长度为 2 。
     */
    @Test
    public void test1() {
        int n = longestSubarray(new int[]{8, 2, 4, 7}, 4);
        assertThat(n, is(2));
    }

    /**
     * 输入：nums = [10,1,2,4,7,2], limit = 5
     * 输出：4
     * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
     */
    @Test
    public void test2() {
        int n = longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5);
        assertThat(n, is(4));
    }

    /**
     * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
     * 输出：3
     */
    @Test
    public void test3() {
        int n = longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0);
        assertThat(n, is(3));
    }
}
