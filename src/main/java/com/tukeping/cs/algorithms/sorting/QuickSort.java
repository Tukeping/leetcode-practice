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
 * @author tukeping
 * @date 2020/1/8
 **/
public class QuickSort {

    /**
     * 伪代码:
     *
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
    public int[] sort(int[] arr) {

        return null;
    }
}
