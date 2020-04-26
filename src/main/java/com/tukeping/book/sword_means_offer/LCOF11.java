package com.tukeping.book.sword_means_offer;

/*
 * 面试题11. 旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF11 {

    public int minArray(int[] numbers) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (numbers[mid] > numbers[end]) start = mid + 1;
            else if (numbers[mid] < numbers[end]) end = mid;
            else end--;
        }
        return numbers[start];
    }

    /**
     * 输入：[3,4,5,1,2]
     * 输出：1
     */
    @Test
    public void test1() {
        int n = minArray(new int[]{3, 4, 5, 1, 2});
        assertThat(n, is(1));
    }

    /**
     * 输入：[2,2,2,0,1]
     * 输出：0
     */
    @Test
    public void test2() {
        int n = minArray(new int[]{2, 2, 2, 0, 1});
        assertThat(n, is(0));
    }

    @Test
    public void test3() {
        int n = minArray(new int[]{1, 3, 5});
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int n = minArray(new int[]{10, 1, 10, 10, 10});
        assertThat(n, is(1));
    }

    @Test
    public void test5() {
        int n = minArray(new int[]{3, 3, 1, 3});
        assertThat(n, is(1));
    }

    @Test
    public void test6() {
        int n = minArray(new int[]{3, 3, 3, 1, 3});
        assertThat(n, is(1));
    }

    @Test
    public void test7() {
        int n = minArray(new int[]{3, 3, 3, 3, 3, 1, 3});
        assertThat(n, is(1));
    }

    @Test
    public void test8() {
        int n = minArray(new int[]{1, 2, 0, 0, 1});
        assertThat(n, is(0));
    }

    @Test
    public void test9() {
        int n = minArray(new int[]{1, 2, 0, 1, 1, 1});
        assertThat(n, is(0));
    }
}
