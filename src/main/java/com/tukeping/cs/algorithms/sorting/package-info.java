/**
 * 排序算法 Sorting algorithm
 * ------
 * 名称,    平均时间复杂度, 最好时间复杂度, 最坏时间复杂度, 额外空间复杂度,  稳定性, 备注
 * 冒泡算法, O(n^2),      O(n),        O(n^2),      O(1),          ✔,    适合'小'数据
 * 插入排序, O(n^2),      O(n),        O(n^2),      O(1),          ✔,    适合'小'数据
 * 选择排序, O(n^2),      O(n^2),      O(n^2),      O(1),          ✖,    适合'小'数据
 * 希尔排序, O(n^{4/3}),  O(n log n),  O(n^{4/3}),  O(1),          ✖,    适合'大'数据
 * 快速排序, O(n log n),  O(n),        O(n^2),      O(log n),      ✖,    适合'大'数据
 * 归并排序, O(n log n),  O(n log n),  O(n log n),  O(n),          ✔,    适合'大'数据
 * 计数排序, O(n+r),      ,            O(n+r),      O(n+r),        ✔,    如果r是O(n) 则平均复杂度是O(n)
 * 基数排序, O(n*(k/d)),  ,            O(n*(k/d)),  O(2^d),        ✔,    k/d递归层数, 2^d数组数量
 * 堆排序,   O(n log n),  O(n log n),  O(n log n),  O(1),          ✖,    适合'大'数据
 * 桶排序,   O(n+r),      ,            O(n+r),      O(n+r),        ✔,    如果r是O(n) 则平均复杂度是O(n)
 *
 * ------
 * 比较类型的排序: 通过比较来决定元素间的相对次序，时间复杂度不能突破O(n log n)，因此称为非线性时间比较类型排序。
 * 非比较类型的排序: 不通过比较来决定元素间的相对次序，可以突破基于排序的时间下限，以线性时间运行，因此称为线性时间的非比较类型排序。
 *
 * 比较类排序:
 *         交换排序:
 *                 冒泡排序 (Bubble sort)
 *                 快速排序 (Quick sort)
 *         插入排序:
 *                 插入排序 (Insertion sort)
 *                 希尔排序 (Shell sort)
 *         选择排序:
 *                 选择排序 (Selection sort)
 *                 堆排序   (Heap sort)
 *         归并排序:
 *                 归并排序 (Merge sort)
 * 非比较排序:
 *         计数排序 (Counting sort)
 *         桶排序   (Bucket sort)
 *         基数排序 (Radix sort)
 *
 * @author tukeping
 * @date 2020/1/7
 **/
package com.tukeping.cs.algorithms.sorting;