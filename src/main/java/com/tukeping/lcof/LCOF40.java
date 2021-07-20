package com.tukeping.lcof;

/*
 * 面试题40. 最小的k个数
 *
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/20
 **/
public class LCOF40 {

    private void qsort(int[] arr, int l, int r, int k) {
        int pivot = pickPivot(l, r);
        pivot = partition(arr, l, r, pivot);
        int cnt = pivot - l + 1;
        if (cnt > k) qsort(arr, l, pivot - 1, k);
        else if (cnt < k) qsort(arr, pivot + 1, r, k - cnt);
    }

    private int partition(int[] arr, int l, int r, int p) {
        swap(arr, p, r);
        for (int i = l; i <= r - 1; i++) {
            if (arr[i] < arr[r]) {
                swap(arr, l++, i);
            }
        }
        swap(arr, l, r);
        return l;
    }

    private int pickPivot(int l, int r) {
        return (l + r) / 2;
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];
        qsort(arr, 0, arr.length - 1, k);
        int[] ret = new int[k];
        System.arraycopy(arr, 0, ret, 0, k);
        return ret;
    }

    public int[] getLeastNumbers3(int[] arr, int k) {
        if (k == 0) return new int[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) maxHeap.add(arr[i]);
        for (int i = k; i < arr.length; i++) {
            if (maxHeap.peek() > arr[i]) {
                if (maxHeap.size() >= k) {
                    maxHeap.poll();
                    maxHeap.add(arr[i]);
                }
            }
        }
        int[] ret = new int[k];
        for (int i = k - 1; i >= 0; i--) ret[i] = maxHeap.poll();
        return ret;
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }

    /**
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     */
    @Test
    public void test1() {
        int[] arr = getLeastNumbers(new int[]{3, 2, 1}, 2);
        assertThat(arr, is(new int[]{1, 2}));
    }

    /**
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     */
    @Test
    public void test2() {
        int[] arr = getLeastNumbers(new int[]{0, 1, 2, 1}, 1);
        assertThat(arr, is(new int[]{0}));
    }

    @Test
    public void test3() {
        int[] arr = getLeastNumbers(new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4}, 8);
        assertThat(arr, is(new int[]{0, 0, 1, 1, 2, 2, 2, 3}));
    }

    @Test
    public void test4() {
        int[] arr = getLeastNumbers(new int[]{0, 0, 0, 2, 0, 5}, 0);
        assertThat(arr, is(new int[]{}));
    }
}
