package com.tukeping.nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC19 {

    /**
     * max sum of the subarray
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxsumofSubarray(int[] arr) {
        int size = arr.length;
        int[] ret = new int[size + 1];
        int max = 0;
        for (int i = 1; i <= size; i++) {
            ret[i] = Math.max(ret[i - 1] + arr[i - 1], arr[i - 1]);
            max = Math.max(max, ret[i]);
        }
        return max;
    }

    @Test
    public void test() {
        int actual = maxsumofSubarray(new int[]{1, -2, 3, 5, -2, 6, -1});
        Assert.assertEquals(actual, 12);
    }
}
