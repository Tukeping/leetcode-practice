package com.tukeping.cs.algorithms.sorting.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * 选择排序
 *
 * 最好时间复杂度, 平均时间复杂度, 最坏时间复杂度, 额外空间复杂度,  稳定性,       是否基于比较
 * O(n^2),      O(n^2),      O(n^2),      O(1),          不稳定,       是
 *
 * 算法思路
 * 1. 在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 * 2. 从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。
 * 3. 以此类推，直到所有元素均排序完毕。
 *
 * @author tukeping
 * @date 2020/1/8
 **/
public class SelectionSort {

    public int[] sort(int[] arr) {
        if (arr == null) {
            return null;
        }

        int len = arr.length;

        if (len == 1) {
            return arr;
        }

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (arr[j] < arr[i]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
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

    @Test
    public void test3() {
        int[] arr = sort(null);
        assertThat(arr, is(nullValue()));
    }

    @Test
    public void test4() {
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
     * SelectionSort cost: 13370ms.
     * TimeSort cost: 38ms.
     */
    @Test
    public void test5() {
        int[] arr1 = (new Random()).ints().limit(10_0000).toArray();
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr1[i];
        }

        long start = System.currentTimeMillis();
        sort(arr1);
        System.out.println("SelectionSort cost: " + (System.currentTimeMillis() - start) + "ms.");

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
