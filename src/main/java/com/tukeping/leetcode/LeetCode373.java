package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=373 lang=java
 *
 * [373] 查找和最小的K对数字
 *
 * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/description/
 *
 * algorithms
 * Medium (39.60%)
 * Likes:    53
 * Dislikes: 0
 * Total Accepted:    5.1K
 * Total Submissions: 12.4K
 * Testcase Example:  '[1,7,11]\n[2,4,6]\n3'
 *
 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
 *
 * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * ⁠    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 *
 * 示例 2:
 *
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 *
 * 示例 3:
 *
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 *
 *
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author tukeping
 * @date 2020/2/19
 **/
public class LeetCode373 {

    /*
     * 27/27 cases passed (32 ms)
     * Your runtime beats 55.2 % of java submissions
     * Your memory usage beats 16.1 % of java submissions (57.5 MB)
     */

    static class Pair implements Comparable {
        public int u;
        public int v;

        public Pair(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public static Pair of(int u, int v) {
            return new Pair(u, v);
        }

        public List<Integer> toList() {
            List<Integer> list = new ArrayList<>(2);
            list.add(u);
            list.add(v);
            return list;
        }

        @Override
        public int compareTo(Object o) {
            Pair target = (Pair) o;
            int sourceSum = u + v;
            int targetSum = target.u + target.v;
            return Integer.compare(sourceSum, targetSum);
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pairs.add(Pair.of(nums1[i], nums2[j]));
            }
        }

        pairs.forEach(pair -> {
            if (maxHeap.size() < k) {
                maxHeap.add(pair);
            } else {
                assert maxHeap.peek() != null;
                if (maxHeap.peek().compareTo(pair) >= 0) {
                    maxHeap.poll();
                    maxHeap.add(pair);
                }
            }
        });

        List<List<Integer>> res = new ArrayList<>(maxHeap.size());
        for (Pair pair : maxHeap) {
            res.add(pair.toList());
        }

        return res;
    }

    /**
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     */
    @Test
    public void test1() {
        int[][] actual = ListHelper.asTwoDimArray(kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        int[][] expect = {
                {1, 2},
                {1, 4},
                {1, 6}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    /**
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     */
    @Test
    public void test2() {
        int[][] actual = ListHelper.asTwoDimArray(kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
        int[][] expect = {
                {1, 1},
                {1, 1}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    /**
     * 输入: nums1 = [1,2], nums2 = [3], k = 3
     * 输出: [1,3],[2,3]
     * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
     */
    @Test
    public void test3() {
        int[][] actual = ListHelper.asTwoDimArray(kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3));
        int[][] expect = {
                {1, 3},
                {2, 3}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }
}
