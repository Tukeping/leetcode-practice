package com.tukeping.cs.algorithms.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * 希尔排序
 *
 * 来历: 1959年由Donald Shell发明的
 *
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的:
 * 1. 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
 * 2. 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位
 *
 * 最好时间复杂度, 平均时间复杂度, 最坏时间复杂度, 额外空间复杂度,  稳定性,       是否基于比较
 * O(n log n),   O(n log n),   O(n log n),        O(1),  不稳定,       是
 *
 * 算法思路:
 * 1. 取步长序列, 由大到小, 且不能大于等于数组的长度
 * 2. 遍历步长序列, 每次遍历对子序列进行插入排序
 * 3. 当最后步长为1时, 进行一次全数组的插入排序, 此时数组由于以上两步骤已经是一个几乎有序的数组了。
 *
 * @author tukeping
 * @date 2020/1/8
 **/
public class ShellSort {

    public int[] sort(int[] arr) {
        return sortV2(arr);
    }

    public int[] sortV1(int[] arr) {
        if (arr == null) {
            return null;
        }

        if (arr.length == 1) {
            return arr;
        }

        int n = arr.length;

        for (int gap = n / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < n; i++) {
                int tmp = arr[i];
                int k = i - gap;
                while (k >= 0 && arr[k] > tmp) {
                    arr[k + gap] = arr[k];
                    k = k - gap;
                }
                arr[k + gap] = tmp;
            }
        }

        return arr;
    }

    public int[] sortV2(int[] arr) {
        if (arr == null) {
            return null;
        }

        if (arr.length == 1) {
            return arr;
        }

        int n = arr.length;

        Stack<Integer> gaps = generateGap(n);

        for (int gap = gaps.pop(); gap >= 1; gap = nextGap(gaps)) {
            for (int i = gap; i < n; i++) {
                int tmp = arr[i];
                int k = i - gap;
                while (k >= 0 && arr[k] > tmp) {
                    arr[k + gap] = arr[k];
                    k = k - gap;
                }
                arr[k + gap] = tmp;
            }
        }

        return arr;
    }

    private Stack<Integer> generateGap(int n) {
        Stack<Integer> gaps = new Stack<>();

        List<Integer> gapList = new ArrayList<>();
        for (int i = 0; ; i++) {
            int a = formula1(i);
            if (a >= n) {
                break;
            }
            gapList.add(a);
        }

        for (int i = 0; ; i++) {
            int b = formula2(i);
            if (b >= n) {
                break;
            }
            gapList.add(b);
        }

        gapList.sort(Comparator.naturalOrder());
        for (Integer gap : gapList) {
            gaps.push(gap);
        }

        return gaps;
    }

    private int nextGap(Stack<Integer> gaps) {
        if (gaps.isEmpty()) {
            return -1;
        }
        return gaps.pop();
    }

    /**  9*4^i - 9*2^i + 1  **/
    private int formula1(int i) {
        int a = Double.valueOf(Math.pow(4, i)).intValue();
        int b = Double.valueOf(Math.pow(2, i)).intValue();
        // 1, 19, 109, 505...
        return 9 * a - 9 * b + 1;
    }

    /**  (2^(k+2))*(2^(k+2) - 3) + 1  **/
    private int formula2(int i) {
        int a = Double.valueOf(Math.pow(2, i + 2)).intValue();
        // 5, 41, 209, 929...
        return a * (a - 3) + 1;
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
     * ShellSort cost: 29ms.
     * TimeSort cost: 20ms.
     */
    @Test
    public void test8() {
        races(100); // 1百
        races(1_0000); // 1万
        races(10_0000); // 10万
        races(100_0000); // 100万
        races(1000_0000); // 1千万
        races(1_0000_0000); // 1亿
    }

    private void races(int n) {
        int[] baseLine = (new Random()).ints().limit(n).toArray();
        int[] arr1 = new int[baseLine.length];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = baseLine[i];
        }
        int[] arr2 = new int[baseLine.length];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = baseLine[i];
        }

        System.out.println("---------------------");
        System.out.println("n: " + n);

        long start = System.currentTimeMillis();
        sortV1(arr1);
        System.out.println("ShellSortV1 cost: " + (System.currentTimeMillis() - start) + "ms.");

        start = System.currentTimeMillis();
        sortV2(arr2);
        System.out.println("ShellSortV2 cost: " + (System.currentTimeMillis() - start) + "ms.");

        // JDK自带的Tim排序算法
        start = System.currentTimeMillis();
        Arrays.sort(baseLine);
        System.out.println("TimeSort cost: " + (System.currentTimeMillis() - start) + "ms.");

        assertThat(arr1, is(baseLine));
        assertThat(arr2, is(baseLine));
    }
}
