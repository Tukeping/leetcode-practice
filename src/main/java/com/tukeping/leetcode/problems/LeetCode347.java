package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=347 lang=java
 *
 * [347] 前 K 个高频元素
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (59.10%)
 * Likes:    242
 * Dislikes: 0
 * Total Accepted:    35.6K
 * Total Submissions: 59.2K
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 *
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table | heap
 *
 * pocketgems | yelp
 *
 * topK
 *
 * @author tukeping
 * @date 2020/2/13
 **/
public class LeetCode347 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int val : nums) map.merge(val, 1, Integer::sum);
        return map.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private HashMap<Integer, Integer> map;
    private int[] queue;
    private int size;

    /**
     * 算法的时间复杂度必须优于 O(n log n) , n 是数组的大小
     */
    public List<Integer> topKFrequent2(int[] nums, int k) {
        initHeap(k);

        for (int i = 0; i < nums.length; i++) {
            increaseFrequent(nums[i]);
            if (heapSize() < k) {
                if (!existInHeap(nums[i]))
                    offer(nums[i]);
            } else if (!existInHeap(nums[i]) && compare(nums[i], peek()) > 0) {
                poll();
                offer(nums[i]);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (heapSize() > 0) {
            result.add(poll());
        }

        Collections.reverse(result);
        result.sort((a, b) -> {
            Integer aF = getFrequent(a);
            Integer bF = getFrequent(b);
            if (aF.equals(bF)) {
                return a.compareTo(b); // 升序 number value
            } else {
                return bF.compareTo(aF); // 降序 频次
            }
        });

        return result;
    }

    private boolean existInHeap(int n) {
        return findHeap(n) != -1;
    }

    private int findHeap(int n) {
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] == n) return i;
        }
        return -1;
    }

    private int compare(int a, int b) {
        int aF = getFrequent(a);
        int bF = getFrequent(b);
        if (aF == bF)
            return 0;
        else if (aF > bF)
            return 1;
        else
            return -1;
    }

    private int getFrequent(int n) {
        return map.getOrDefault(n, 0);
    }

    private void increaseFrequent(int n) {
        int c = getFrequent(n);
        map.put(n, ++c);
        int k = findHeap(n);
        if (k != -1) {
            shiftDown(k, n);
        }
    }

    private void initHeap(int initialCapacity) {
        queue = new int[initialCapacity];
        map = new HashMap<>();
    }

    private int heapSize() {
        return size;
    }

    private int peek() {
        return queue[0];
    }

    private int poll() {
        int i = --size;
        int x = queue[0];
        int y = queue[i];
        shiftDown(0, y);
        return x;
    }

    private void offer(int n) {
        int i = size;
        size++;
        if (i == 0)
            queue[0] = n;
        else
            shiftUp(i, n);
    }

    private void shiftUp(int k, int n) {
        while (k > 0) {
            int p = (k - 1) / 2;
            if (compare(n, queue[p]) > 0) break;
            queue[k] = queue[p];
            k = p;
        }
        queue[k] = n;
    }

    private void shiftDown(int k, int n) {
        int half = size / 2;
        while (k < half) {
            int child = 2 * k + 1; // init by left child
            int right = child + 1;
            int c = queue[child];

            if (right < size && compare(queue[child], queue[right]) > 0)
                c = queue[child = right];

            if (compare(c, n) > 0) break;

            queue[k] = c;
            k = child;
        }
        queue[k] = n;
    }

    /**
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     */
    @Test
    public void test1() {
        List<Integer> ret = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        assertThat(ret, is(Arrays.asList(1, 2)));
    }

    /**
     * 输入: nums = [1], k = 1
     * 输出: [1]
     */
    @Test
    public void test2() {
        List<Integer> ret = topKFrequent(new int[]{1}, 1);
        assertThat(ret, is(Arrays.asList(1)));
    }

    /**
     * 输入: nums = [4,1,-1,2,-1,2,3], k = 2
     * 输出: [-1,2]
     */
    @Test
    public void test3() {
        List<Integer> ret = topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2);
        assertThat(ret, is(Arrays.asList(-1, 2)));
    }

    /**
     * 输入: [5,3,1,1,1,3,73,1], k = 2
     * 输出: [1,3]
     */
    @Test
    public void test4() {
        List<Integer> ret = topKFrequent(new int[]{5, 3, 1, 1, 1, 3, 73, 1}, 2);
        assertThat(ret, is(Arrays.asList(1, 3)));
    }

    /**
     * 输入: [-1,1,4,-4,3,5,4,-2,3,-1], k = 3
     * 输出: [-1,3,4]
     */
    @Test
    public void test5() {
        List<Integer> ret = topKFrequent(new int[]{-1, 1, 4, -4, 3, 5, 4, -2, 3, -1}, 3);
        assertThat(ret, is(Arrays.asList(-1, 3, 4)));
    }
}
