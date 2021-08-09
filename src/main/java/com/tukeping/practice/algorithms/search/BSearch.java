package com.tukeping.practice.algorithms.search;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/8/9
 **/
public class BSearch {

    /**
     * 查找第一个值等于给定值的元素
     */
    public int bsearch(int[] a, int target) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (a[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    @Test
    public void test() {
        int actual = bsearch(new int[]{1, 2, 3, 3, 3, 5, 6}, 3);
        Assert.assertEquals(actual, 2);
    }
}
