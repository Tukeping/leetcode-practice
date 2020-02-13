package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (59.84%)
 * Likes:    358
 * Dislikes: 0
 * Total Accepted:    76.6K
 * Total Submissions: 125.7K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 *
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */

import org.junit.Test;

import java.util.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Medium (59.84%)
 *
 * divide-and-conquer | heap
 *
 * amazon | apple | bloomberg | facebook | microsoft | pocketgems
 *
 * topK
 *
 * @author tukeping
 * @date 2020/2/12
 **/
public class LeetCode215 {

    public int findKthLargest(int[] nums, int k) {
        return topK(nums, k);
    }

    private int topK(int[] nums, int k) {
        initHeap(k);

        for (int i = 0; i < nums.length; i++) {
            if (heapSize() < k) {
                offer(nums[i]);
            } else if (nums[i] > peek()) {
                poll();
                offer(nums[i]);
            }
        }

        return peek();
    }

    private int[] nums;
    private int size;

    private void initHeap(int initialCapacity) {
        nums = new int[initialCapacity];
    }

    private int heapSize() {
        return size;
    }

    private void offer(int n) {
        int i = size;
        size = i + 1;
        if (i == 0)
            nums[0] = n;
        else
            shiftUp(i, n);
    }

    private int peek() {
        return nums[0];
    }

    private int poll() {
        int s = --size;
        int root = nums[0];
        int x = nums[s];
        shiftDown(0, x);
        return root;
    }

    private void shiftUp(int k, int n) {
        while (k > 0) {
            int p = parent(k);
            if (n >= nums[p]) break;
            nums[k] = nums[p];
            k = p;
        }
        nums[k] = n;
    }

    private void shiftDown(int k, int n) {
        int half = size >>> 1; // size/2
        while (k < half) {
            int child = lchild(k); // init left child
            int right = child + 1;
            int c = nums[child];

            // 右节点不是必定存在的, 需要判断right<size
            // 如果左节点大于右节点, 那么需要把右节点的下标和值记录下来
            if (right < size && c > nums[right])
                c = nums[child = right];

            // 如果当前需要插入的数字比 左右节点中最小的那个节点 还小
            // 那就说明了 该数组 已经实现 最小堆的结构
            if (n <= c) break;

            nums[k] = c;
            k = child;
        }
        nums[k] = n;
    }

    private int parent(int i) {
        return (i - 1) >>> 1; // (i-1)/2
    }

    private int lchild(int i) {
        return i * 2 + 1;
    }

    private int rchild(int i) {
        return i * 2 + 2;
    }

    public int topK_priorityQueue(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else {
                // 如果发现数据比堆顶元素大，则加入到小顶堆中
                if (num > queue.peek()) {
                    queue.poll();
                    queue.offer(num);
                }
            }
        }

        return queue.poll();
    }

    /**
     * i节点 => 左子节点 2*i + 1
     *      => 右子节点 2*i + 2
     *      => 父节点  (i-1)/2
     */
    private void heapify(int[] nums) {
        for (int p = parent(nums.length - 1); p >= 0; p--)
            shiftDown(nums, p, nums.length);
    }

    private void shiftDown(int[] nums, int p, int len) {
        while (lchild(p) <= len - 1) {
            int leftChild = lchild(p);
            int rightChild = rchild(p);
            int maxChild;

            if (rightChild > len - 1) {
                maxChild = leftChild;
            } else {
                maxChild = (nums[leftChild] > nums[rightChild]) ? leftChild : rightChild;
            }

            if (nums[p] > nums[maxChild]) break;

            swap(nums, p, maxChild);

            p = maxChild;
        }
    }

    private void qsort(int[] nums, int low, int high) {
        if (low > high) return;

        int pivot = pickPivot(low, high);

        pivot = partition(nums, low, high, pivot);

        qsort(nums, low, pivot - 1);
        qsort(nums, pivot + 1, high);
    }

    private int pickPivot(int low, int high) {
        return low == high ? low : (low + high) >>> 1;
    }

    private int partition(int[] nums, int low, int high, int pivot) {
        if (low == high) return low;

        swap(nums, pivot, high);

        for (int i = low; i < high; i++) {
            if (nums[i] > nums[high]) {
                swap(nums, low++, i);
            }
        }

        swap(nums, low, high);

        return low;
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    /**
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     */
    @Test
    public void test1() {
        int n = findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        assertThat(n, is(5));
    }

    /**
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     */
    @Test
    public void test2() {
        int n = findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        assertThat(n, is(4));
    }
}
