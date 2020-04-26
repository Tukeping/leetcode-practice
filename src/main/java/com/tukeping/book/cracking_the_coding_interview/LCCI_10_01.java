package com.tukeping.book.cracking_the_coding_interview;

/*
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 *
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/3
 **/
public class LCCI_10_01 {

    public void merge(int[] A, int m, int[] B, int n) {
        if (n == 0 || A.length == 0) return;

        if (m == 0 && A.length == n) {
            System.arraycopy(B, 0, A, 0, n);
        }

        int i = 0, j = 0, m1 = m;
        while (i < m || j < n) {
            if (j < n && A[i] > B[j]) {
                System.arraycopy(A, i, A, i + 1, m1);
                A[i++] = B[j++];
            } else if (m1 > 0) { // A[i] <= B[j]
                m1--;
                i++;
            } else if (m1 == 0) { // A[i] <= B[j]
                A[i++] = B[j++];
            }
        }
    }

    /**
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     */
    @Test
    public void test1() {
        int[] A = new int[]{1, 2, 3, 0, 0, 0};
        merge(A, 3, new int[]{2, 5, 6}, 3);
        assertThat(A, is(new int[]{1, 2, 2, 3, 5, 6}));
    }

    @Test
    public void test2() {
        int[] A = new int[]{1};
        merge(A, 1, new int[]{}, 0);
        assertThat(A, is(new int[]{1}));
    }

    @Test
    public void test3() {
        int[] A = new int[]{1, 2, 4, 5, 6, 0};
        merge(A, 5, new int[]{3}, 1);
        assertThat(A, is(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
