package com.tukeping.cs.algorithms.sorting.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * 冒泡排序
 *
 * 最好时间复杂度, 平均时间复杂度, 最坏时间复杂度, 额外空间复杂度,  稳定性,       是否基于比较
 * O(n),        O(n^2),      O(n^2),      O(1),          稳定,         是
 *
 * 算法思路:
 * 1. 比较相邻的元素。如果前一个比后一个大，则交换它们。
 * 2. 对每一对相邻元素做相同的操作，从第一对元素到结尾的最后一对。这步做完后，最后一个元素会是值最大的元素。
 * 3. 针对所有元素重复以上步骤，除了最后一个元素。
 * 4. 循环每次对越来越少的元素重复步骤，直到没有任何一对元素需要做比较值。
 *
 * @author tukeping
 * @date 2020/1/7
 **/
public class BubbleSort {

    /** time: O(n^2) space: O(n) + O(1) **/
    public int[] sort(int[] arr) {
        int len = arr.length;
        for (int pairCount = 0; pairCount < len - 1; pairCount++) {
            // 对比次数逐渐减少, (len-1) - pairCount
            for (int compareIndex = 0; compareIndex < len - 1 - pairCount; compareIndex++) {
                // 比较相邻的元素。如果前一个比后一个大，则交换它们。
                if (arr[compareIndex] > arr[compareIndex + 1]) {
                    int tmp = arr[compareIndex];
                    arr[compareIndex] = arr[compareIndex + 1];
                    arr[compareIndex + 1] = tmp;
                }
            }
        }
        return arr;
    }

    @Test
    public void test1() {
        int[] arr = sort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        assertThat(arr, is(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void test2() {
        int[] arr = sort(new int[]{5, 4, 3, 9, 1, 6, 7, 2, 8});
        assertThat(arr, is(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    /**
     * 系统参数:
     * CPU: Quad-Core Intel Core i7 (2.3 GHz / 4核)
     * MEM: 16 GB (DDR3 / 1600 MHz)
     * OS: macOS Catalina 10.15.2
     *
     * 耗时:
     * BubbleSort cost: 14781ms.
     * TimeSort cost: 16ms.
     */
    @Test
    public void test3() {
        // 创建随机函数
        Random random = new Random();
        int limit = 10_0000;

        // 随机获得{limit}长度的数组
        int[] arr1 = random.ints().limit(limit).toArray();

        // 拷贝成两份数组
        // deep clone arr1 to arr2
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr1[i];
        }

        // 第一份数组使用自己写的冒泡算法排序
        long start = System.currentTimeMillis();
        sort(arr1);
        System.out.println("BubbleSort cost: " + (System.currentTimeMillis() - start) + "ms.");

        // 第二份数组使用JDK自带的Tim排序算法
        start = System.currentTimeMillis();
        Arrays.sort(arr2);
        System.out.println("TimeSort cost: " + (System.currentTimeMillis() - start) + "ms.");

        // 对比数组1和数组2 是否在排序后是一样的正确结果
        // compare two array
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.println("arr1 is not equals to arr2. index:" + i);
                break;
            }
        }

        // 设置断言
        // assert arr1 is the same as arr2
        assertThat(arr1, is(arr2));
    }
}
