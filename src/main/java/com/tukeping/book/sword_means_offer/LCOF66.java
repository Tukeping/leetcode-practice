package com.tukeping.book.sword_means_offer;

/*
 * 面试题66. 构建乘积数组
 *
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 * 不能使用除法。
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 * 提示：
 *
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/30
 **/
public class LCOF66 {

    public int[] constructArr(int[] a) {
        int len = a.length;
        if(len == 0) return new int[0];

        int[] ans = new int[len];
        ans[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = a[i - 1] * ans[i - 1];
        }
        int R = 1;
        for (int i = len - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R = R * a[i];
        }
        return ans;
    }

    public int[] constructArr2(int[] a) {
        int len = a.length;
        if(len == 0) return new int[0];

        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = a[i - 1] * left[i - 1];
        }
        int[] right = new int[len];
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = a[i + 1] * right[i + 1];
        }
        for (int i = 0; i < len; i++) {
            a[i] = left[i] * right[i];
        }
        return a;
    }

    /**
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     */
    @Test
    public void test1() {
        int[] ret = constructArr(new int[]{1, 2, 3, 4, 5});
        assertThat(ret, is(new int[]{120, 60, 40, 30, 24}));
    }
}
