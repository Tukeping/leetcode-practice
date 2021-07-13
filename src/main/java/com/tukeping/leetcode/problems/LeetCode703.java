package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=703 lang=java
 *
 * [703] 数据流中的第K大元素
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/description/
 *
 * algorithms
 * Easy (41.33%)
 * Likes:    106
 * Dislikes: 0
 * Total Accepted:    13.8K
 * Total Submissions: 32.7K
 * Testcase Example:  '["KthLargest","add","add","add","add","add"]\n' +
  '[[3,[4,5,8,2]],[3],[5],[10],[9],[4]]'
 *
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 *
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用
 * KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 *
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 *
 *
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 */

import org.junit.Test;

import java.util.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/19
 **/
public class LeetCode703 {

    /*
     * 10/10 cases passed (23 ms)
     * Your runtime beats 71.87 % of java submissions
     * Your memory usage beats 89.21 % of java submissions (44.9 MB)
     */

    class KthLargest {
        private int limit;
        private PriorityQueue<Integer> heap;

        public KthLargest(int k, int[] nums) {
            this.limit = k;
            this.heap = new PriorityQueue<>(k);
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (heap == null) {
                throw new RuntimeException("please init KthLargest with construct");
            }
            if (heap.size() < limit) {
                heap.add(val);
            } else if (heap.peek() < val) {
                heap.poll();
                heap.add(val);
            }
            return heap.peek();
        }
    }

    @Test
    public void test1() {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        assertThat(kthLargest.add(3), is(4));
        assertThat(kthLargest.add(5), is(5));
        assertThat(kthLargest.add(10), is(5));
        assertThat(kthLargest.add(9), is(8));
        assertThat(kthLargest.add(4), is(8));
    }
}
