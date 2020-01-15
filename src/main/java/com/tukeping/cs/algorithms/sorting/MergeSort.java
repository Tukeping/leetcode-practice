package com.tukeping.cs.algorithms.sorting;

import com.tukeping.tools.Strings;
import org.junit.Test;

/**
 * 归并排序（英语：Merge sort，或mergesort）
 * 是创建在归并操作上的一种有效的排序算法，效率为 O(n log n)（大O符号）。
 * 1945年由约翰·冯·诺伊曼首次提出。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用，且各层分治递归可以同时进行。
 *
 * 采用分治法:
 *  分割：递归地把当前序列平均分割成两半。
 *  集成：在保持元素顺序的同时将上一步得到的子序列集成到一起（归并）。
 *
 * 递归法（Top-down）
 * 1. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * 2. 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 3. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * 4. 重复步骤3直到某一指针到达序列尾
 * 5. 将另一序列剩下的所有元素直接复制到合并序列尾
 *
 * 迭代法（Bottom-up）
 * 原理如下（假设序列共有n个元素）
 * 1. 将序列每相邻两个数字进行归并操作，形成ceil(n/2)个序列，排序后每个序列包含两/一个元素
 * 2. 若此时序列数不是1个则将上述序列再次归并，形成ceil(n/4)个序列，每个序列包含四/三个元素
 * 3. 重复步骤2，直到所有元素排序完毕，即序列数为1
 *
 * @author tukeping
 * @date 2020/1/14
 **/
public class MergeSort {

    private int[] sort(int[] arr) {
        int[] sortedArr = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, sortedArr);
        return arr;
    }

    private void mergeSort(int[] arr, int left, int right, int[] sortedArr) {
        if (left >= right) {
            return;
        }

        // 1. split array
        int mid = (left + right) / 2;

        // 2. sort left and right array Divide and Conquer
        mergeSort(arr, left, mid, sortedArr);
        mergeSort(arr, mid + 1, right, sortedArr);

        // 3. merge two sorted array
        mergeArray(arr, left, mid, right, sortedArr);
    }

    private void mergeArray(int[] arr, int left, int mid, int right, int[] sortedArr) {
        // two points
        int p1 = left;
        int p2 = mid + 1;

        // current sorted array index
        int curSortedArrIndex = 0;

        // move points
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] <= arr[p2]) { // asc
                sortedArr[curSortedArrIndex] = arr[p1];
                p1++;
            } else {
                sortedArr[curSortedArrIndex] = arr[p2];
                p2++;
            }
            curSortedArrIndex++;
        }

        // get rest leftArray to sortedArray
        while (p1 <= mid) {
            sortedArr[curSortedArrIndex++] = arr[p1++];
        }

        // get rest rightArray to sortedArray
        while (p2 <= mid) {
            sortedArr[curSortedArrIndex++] = arr[p2++];
        }

        // put back to origin array
        if (curSortedArrIndex >= 0)
            System.arraycopy(sortedArr, 0, arr, left, curSortedArrIndex);
    }

    @Test
    public void test() {
        int[] arr = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("origin: " + Strings.arrayToString(arr));

        int[] ret = sort(arr);
        System.out.println("ordered: " + Strings.arrayToString(ret));
    }
}
