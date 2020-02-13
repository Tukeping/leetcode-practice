package com.tukeping.cs.algorithms.sorting;

/*
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

import com.tukeping.tools.Strings;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/1/8
 */
public class QuickSort {

    private boolean enableDebug = false;

    public void sort(int[] nums) {
        qsort(nums, 0, nums.length - 1);
    }

    /**
     * 快排的核心思路就是:
     * 1. qsort用了递归手段, 需要先设置一个递归退出条件就是 low > high,
     *    这个条件之所以成立是因为每次切分 数字堆的时候 low都是+1, high都是-1
     *    所以不断递归下去终归 low会超过high
     * 2. 选择一个pivot基准数字。ps: 内部选择pivot的算法可以是简单的比如取mid, 也可以是复杂的比如取首位和mid然后再取中位数
     * 3. 开始基于 基准数字 进行`分区`。只要比基准数字小的都在 基准数字的左边, 比基准数字大的都是在基准数字的右边
     * 4. 分区递归: 分成 low 到 pivot - 1 和 pivot + 1 到 high 两组数字堆 继续 递归迭代下去。轮回到步骤1。
     *
     * qsort in-place, time: O(nLog(n)) space: O(1)
     */
    private void qsort(int[] nums, int low, int high) {
        if (low > high) return;

        int pivot = pickPivot(low, high);

        if (enableDebug) printPartition(low, high, pivot);

        pivot = partition(nums, low, high, pivot);

        qsort(nums, low, pivot - 1);
        qsort(nums, pivot + 1, high);
    }

    /** simple version => pivot = (low + high) / 2 **/
    private int pickPivot(int low, int high) {
        return (low == high) ? low : (low + high) >>> 1;
    }

    /**
     * 这个分区函数是快速排序算法的核心逻辑:
     * 1. 由于是需要 原地算法 不使用额外的空间 则把 基准数跟最末尾的数 进行交换
     * 2. 遍历 从low到high这一段的数字 判断遍历中的每一个数字 如果比基准数小则 进行交换 (这个是升序逻辑, 降序的话就是比基准数大则交换)
     * 3. 把基准数从末尾再交换到分界线边，这个分界线就是 比基准数小的那一堆数字的最末尾
     */
    private int partition(int[] arr, int low, int high, int pivot) {
        if (low == high) return low;

        swap(arr, pivot, high); // put pivot to high place, in-place strategy
        if (enableDebug) printArr(arr, pivot, pivot, high, true);

        for (int i = low; i <= high - 1; i++) {
            if (enableDebug) printCompare(arr, i, high);
            if (arr[i] < arr[high]) { // currentValue < pivotValue => asc sort
                // low++ 是为了提高分界线, low往左的数字都是比基准数字小的
                // 或者这么理解, i可以理解为未被判断的数字 跟着循环遍历走
                // 而low是已经跟基准数比较完后的 已排序完成后的数字堆分界线
                swap(arr, low++, i);
                if (enableDebug) printArr(arr, high, low - 1, i, false);
            }
        }

        // 把基准数换到 已排序完的比基准数小的数字堆的 最右边, 分界线下标就是low
        swap(arr, low, high); // put pivot back place from high
        if (enableDebug) printArr(arr, low, low, high, true);

        return low;
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /** pivot, swap(a, b) **/
    private void printArr(int[] arr, int pivot, int a, int b, boolean isSwapPivot) {
        System.out.println(Strings.arrayToString(arr) + " [pivot=" + pivot +
                ", low=" + a + ", high=" + b + " | " +
                a + "<=>" + b + "]" + (isSwapPivot ? " swap pivot" : ""));
    }

    private void printCompare(int[] arr, int a, int b) {
        System.out.println("arr[" + a + "] " + arr[a] +
                " vs " + arr[b] + " arr[" + b + "]" +
                ((arr[a] < arr[b]) ? ", a < b => swap(a, b)" : ", a >= b"));
    }

    private AtomicInteger count = new AtomicInteger(1);

    private void printPartition(int left, int right, int pivotIndex) {
        System.out.println("\n-------partition " + count.getAndIncrement() + ":" +
                " low:" + left + " high:" + right +
                " pickPivot:" + pivotIndex + "-------");
    }

    private int pickPivot0(int[] arr, int left, int right) {
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

    private void printArr1(int[] arr, int pivot, int a, int b) {
        String leftArr = Strings.arrayToString(arr, n -> n < arr[pivot]);
        String rightArr = Strings.arrayToString(arr, n -> n >= arr[pivot]);
        System.out.println(leftArr + "," + rightArr +
                " [pivot=" + pivot + ", low=" + a + ", high=" + b + " | " +
                a + "<=>" + b + "]");
    }

    @Test
    public void test1() {
        int[] arr = new int[]{3, 7, 8, 5, 2, 1, 9, 5, 4};
        System.out.println("origin: " + Strings.arrayToString(arr));

        enableDebug = true;
        sort(arr);
        System.out.println("ordered: " + Strings.arrayToString(arr));

        assertThat(arr, is(new int[]{1, 2, 3, 4, 5, 5, 7, 8, 9}));
    }

    @Test
    public void test2() {
        int[] arr = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("origin: " + Strings.arrayToString(arr));

        enableDebug = true;
        sort(arr);
        System.out.println("ordered: " + Strings.arrayToString(arr));

        assertThat(arr, is(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }
}
