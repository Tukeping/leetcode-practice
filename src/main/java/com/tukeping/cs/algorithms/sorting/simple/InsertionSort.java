package com.tukeping.cs.algorithms.sorting.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * 插入排序
 *
 * Insertion Sort 和打扑克牌时，从牌桌上逐一拿起扑克牌，在手上排序的过程相同。
 * 举例：
 * Input: {5 2 4 6 1 3}。
 * 首先拿起第一张牌, 手上有 {5}。
 * 拿起第二张牌 2, 把 2 insert 到手上的牌 {5}, 得到 {2 5}。
 * 拿起第三张牌 4, 把 4 insert 到手上的牌 {2 5}, 得到 {2 4 5}。
 * 以此类推。
 *
 * 最好时间复杂度, 平均时间复杂度, 最坏时间复杂度, 额外空间复杂度,  稳定性,       是否基于比较
 * O(n),        O(n^2),      O(n^2),      O(1),          稳定,         是
 *
 * 算法思路
 * 一般来说，插入排序都采用in-place在数组上实现。
 * 1. 从第一个元素开始，该元素可以认为已经被排序
 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5. 将新元素插入到该位置后
 * 6. 重复步骤2~5
 *
 * tips:
 * 可以采用二分查找法来减少“比较操作”的数目，而由于“交换操作”的数目不变，算法的时间复杂度依旧为O(n^2)。
 * 该算法可以认为是插入排序的一个变种，称为二分查找插入排序。
 *
 * @author tukeping
 * @date 2020/1/8
 **/
public class InsertionSort {

    public int[] sort(int[] arr) {
        if (arr == null) {
            return null;
        }

        if (arr.length == 1) {
            return arr;
        }

        int len = arr.length;

        for (int tableOfPoker = 1; tableOfPoker < len; tableOfPoker++) {
            for (int handOfPoker = tableOfPoker - 1; handOfPoker >= 0; handOfPoker--) {
                if (arr[handOfPoker + 1] < arr[handOfPoker]) {
                    int tmp = arr[handOfPoker + 1];
                    arr[handOfPoker + 1] = arr[handOfPoker];
                    arr[handOfPoker] = tmp;
                }
            }
        }

        return arr;
    }

    @Test
    public void test1() {
        int[] arr = sort(new int[]{3, 2, 1});
        assertThat(arr, is(new int[]{1, 2, 3}));
    }

    @Test
    public void test2() {
        int[] arr = sort(new int[]{1, 3, 2});
        assertThat(arr, is(new int[]{1, 2, 3}));
    }

    @Test
    public void test3() {
        int[] arr = sort(new int[]{1, 2, 3});
        assertThat(arr, is(new int[]{1, 2, 3}));
    }

    @Test
    public void test4() {
        int[] arr = sort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        assertThat(arr, is(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void test5() {
        int[] arr = sort(new int[]{5, 4, 3, 9, 1, 6, 7, 2, 8});
        assertThat(arr, is(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void test6() {
        int[] arr = sort(null);
        assertThat(arr, is(nullValue()));
    }

    @Test
    public void test7() {
        int[] arr = sort(new int[]{10});
        assertThat(arr, is(new int[]{10}));
    }

    /**
     * 系统参数:
     * CPU: Quad-Core Intel Core i7 (2.3 GHz / 4核)
     * MEM: 16 GB (DDR3 / 1600 MHz)
     * OS: macOS Catalina 10.15.2
     *
     * 耗时:
     * InsertionSort cost: 5067ms.
     * TimeSort cost: 32ms.
     */
    @Test
    public void test8() {
        int[] arr1 = (new Random()).ints().limit(10_0000).toArray();
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr1[i];
        }

        long start = System.currentTimeMillis();
        sort(arr1);
        System.out.println("InsertionSort cost: " + (System.currentTimeMillis() - start) + "ms.");

        // JDK自带的Tim排序算法
        start = System.currentTimeMillis();
        Arrays.sort(arr2);
        System.out.println("TimeSort cost: " + (System.currentTimeMillis() - start) + "ms.");

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.println("arr1 is not equals to arr2. index:" + i);
                break;
            }
        }

        assertThat(arr1, is(arr2));
    }
}
