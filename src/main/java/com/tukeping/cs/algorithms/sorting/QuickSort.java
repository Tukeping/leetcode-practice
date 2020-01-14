package com.tukeping.cs.algorithms.sorting;

/**
 * 快速排序 又称 分区交换排序
 *
 * 最好时间复杂度, 平均时间复杂度, 最坏时间复杂度, 额外空间复杂度,  稳定性,       是否基于比较
 * O(n log n),   O(n log n),   O(n^2),        O(1),      不稳定,       不是(分治思想)
 *
 * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为较小和较大的2个子序列，然后递归地排序两个子序列。
 * 步骤为:
 * 1. 挑选基准值：从数列中挑出一个元素，称为“基准”（pivot），
 * 2. 分割：重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（与基准值相等的数可以到任何一边）。在这个分割结束之后，对基准值的排序就已经完成，
 * 3. 递归排序子序列：递归地将小于基准值元素的子序列和大于基准值元素的子序列排序。
 * 4. 递归到最底部的判断条件是数列的大小是零或一，此时该数列显然已经有序。
 *
 * tips: 选取基准值有数种具体方法，此选取方法对排序的时间性能有决定性影响。
 *
 **/

/*
 * function partition(a, left, right, pivotIndex)
 *  {
 *      pivotValue = a[pivotIndex]
 *      swap(a[pivotIndex], a[right]) // 把pivot移到結尾
 *      storeIndex = left
 *      for i from left to right-1
 *      {
 *          if a[i] <= pivotValue
 *           {
 *              swap(a[storeIndex], a[i])
 *              storeIndex = storeIndex + 1
 *           }
 *      }
 *      swap(a[right], a[storeIndex]) // 把pivot移到它最後的地方
 *      return storeIndex
 *  }
 *
 *  procedure quicksort(a, left, right)
 *      if right > left
 *          select a pivot value a[pivotIndex]
 *          pivotNewIndex := partition(a, left, right, pivotIndex)
 *          quicksort(a, left, pivotNewIndex-1)
 *          quicksort(a, pivotNewIndex+1, right)
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * @author tukeping
 * @date 2020/1/8
 */
public class QuickSort {

    public int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private AtomicInteger count = new AtomicInteger(1);

    private void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int pivotIndex = pickPivotIndex(arr, left, right);
        printPartitionInfo(arr, left, right, pivotIndex);
        pivotIndex = partition(arr, left, right, pivotIndex);

        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    private int pickPivotIndex(int[] arr, int left, int right) {
        if (left == right) return left;

        int mid = (left + right) / 2;
        if (mid == left) return left;

        int min = 0;
        if (arr[left] > arr[mid]) {
            min = mid;
            mid = left;
        }
        if (arr[right] < arr[min]) {
            mid = min;
        } else if (arr[right] < arr[mid]) {
            mid = right;
        }
        return mid;
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        if (left == right) return left;

        swap(arr, pivot, right);
        printArr(arr, pivot, left, right);

        int pivotValue = arr[right];
        int actualLeft = left;

        for (int index = left; index <= right - 1; index++) {
            int curValue = arr[index];
            if (curValue < pivotValue) { // asc
                swap(arr, actualLeft, index);
                printArr(arr, right, actualLeft, index);
                actualLeft++;
            }
        }

        swap(arr, actualLeft, right);
        printArr1(arr, actualLeft, actualLeft, right);

        return actualLeft;
    }

    private void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private void printArr(int[] arr, int pivot, int a, int b) {
        String arrStr = Arrays.stream(arr).boxed().map(String::valueOf).collect(Collectors.joining(","));
        int aValue = arr[a];
        int bValue = arr[b];
        System.out.println(arrStr
                + " [pivot=" + pivot + "(" + arr[pivot] + ")" + ", left=" + a + ", right=" + b + "  "
                + aValue + "<=>" + bValue + "]");
    }

    private void printArr1(int[] arr, int pivot, int a, int b) {
        String leftArr = Arrays.stream(arr).filter(n -> n < arr[pivot]).boxed().map(String::valueOf).collect(Collectors.joining(","));
        String rightArr = Arrays.stream(arr).filter(n -> n >= arr[pivot]).boxed().map(String::valueOf).collect(Collectors.joining(","));
        int aValue = arr[a];
        int bValue = arr[b];
        System.out.println(leftArr + " <" + arr[pivot] + "> " + rightArr
                + " [pivot=" + pivot + "(" + arr[pivot] + ")" + ", left=" + a + ", right=" + b + "  "
                + aValue + "<=>" + bValue + "]");
    }

    private void printPartitionInfo(int[] arr, int left, int right, int pivotIndex) {
        System.out.println("\n-------partition:" + count.getAndIncrement() +
                " left:" + left + " right:" + right +
                " pivot:" + pivotIndex + "(" + arr[pivotIndex] + ")" +
                "[" + arr[left] + "," + arr[(left + right) / 2] + "," + arr[right] + "]" +
                "-------");
    }

    @Test
    public void test() {
        int[] arr = new int[]{3, 7, 8, 5, 2, 1, 9, 5, 4};
        System.out.println("origin: " +
                Arrays.stream(arr).boxed().map(String::valueOf).collect(joining(",")));

        int[] ret = sort(arr);
        System.out.println("\nordered: " +
                Arrays.stream(ret).boxed().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
