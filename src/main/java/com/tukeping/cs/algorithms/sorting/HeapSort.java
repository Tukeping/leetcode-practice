package com.tukeping.cs.algorithms.sorting;

import com.tukeping.tools.Strings;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * 堆排序（英语：HeapSort）是指利用堆这种数据结构所设计的一种排序算法。
 * 堆是一个近似完全二叉树的结构，并同时满足堆积的性质：即子节点的键值或索引总是小于（或者大于）它的父节点。
 *
 * 堆节点的访问
 * 通常堆是通过一维数组来实现的。在数组起始位置为0的情形中：
 * 父节点i的左子节点在位置(2i+1);
 * 父节点i的右子节点在位置(2i+2);
 * 子节点i的父节点在位置floor((i-1)/2);
 *
 * @author tukeping
 * @date 2020/1/17
 **/
public class HeapSort {

    public void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            maxHeapify(arr, len - i);
            swap(arr, 0, len - 1 - i);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /** 最大堆排序 **/
    private void maxHeapify(int[] arr, int heapSize) {
        for (int i = heapSize - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
    }

    private void heapify(int[] arr, int root, int heapSize) {
        if (root >= heapSize) return;

        int max = root;
        int left = root * 2 + 1;
        int right = root * 2 + 2;

        if (left < heapSize) {
            if (arr[left] > arr[max]) {
                max = left;
            }
        }

        if (right < heapSize) {
            if (arr[right] > arr[max]) {
                max = right;
            }
        }

        if (max != root) {
            swap(arr, root, max);
            heapify(arr, max, heapSize);
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{9, 2, 1, 7, 6, 8, 5, 3, 4};
        System.out.println(Strings.arrayToString(arr));

        int[] arrCopy = Arrays.copyOf(arr, arr.length);

        Arrays.sort(arrCopy);
        sort(arr);

        System.out.println(Strings.arrayToString(arr));

        assertArrayEquals(arr, arrCopy);
    }
}
