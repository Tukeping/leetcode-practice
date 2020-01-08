/**
 * 排序算法, Sorting algorithm
 * ------
 *
 * 比较类型的排序: 通过比较来决定元素间的相对次序，时间复杂度不能突破O(n*logN)，因此称为非线性时间比较类型排序。
 * 非比较类型的排序: 不通过比较来决定元素间的相对次序，可以突破基于排序的时间下限，以线性时间运行，因此称为线性时间的非比较类型排序。
 *
 * 十大算法分类：
 * 比较类排序：
 *         交换排序：
 *                 冒泡排序 (Bubble sort)
 *                 快速排序 (Quick sort)
 *         插入排序：
 *                 插入排序 (Insertion sort)
 *                 希尔排序 (Shell sort)
 *         选择排序：
 *                 选择排序 (Selection sort)
 *                 堆排序  (Heap sort)
 *         归并排序：
 *                 归并排序 (Merge sort)
 * 非比较排序：
 *         计数排序 (Counting sort)
 *         桶排序 (Bucket sort)
 *         基数排序 (Radix sort)
 *
 * ------
 * 名称,    最好时间复杂度, 平均时间复杂度, 最坏时间复杂度, 额外空间复杂度,  稳定性,       算法框架
 * 冒泡算法, O(n),        O(n^2),      O(n^2),      O(1),         是(原地排序),  元素对比交换
 *
 * ------
 * Q: 哪些算法是工业级产品最会使用的排序算法？
 *
 * Q: 哪些算法适合大数据的排序算法？
 *
 * Q: 哪些算法适合分布式的排序算法？
 *
 *
 * @author tukeping
 * @date 2020/1/7
 **/
package com.tukeping.cs.algorithms.sorting;