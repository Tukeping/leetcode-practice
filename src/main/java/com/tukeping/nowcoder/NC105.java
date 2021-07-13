package com.tukeping.nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/4
 **/
public class NC105 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 如果目标值存在返回下标，否则返回 -1
     * @param nums int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public int search(int[] nums, int target) {
        int size = nums.length;
        if (size == 0) return -1;
        return bsearch(nums, 0, size - 1, target);
    }

    private int bsearch(int[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return nums[l] == target ? l : -1;
    }

    @Test
    public void test() {
        int actual = search(new int[]{1, 2, 4, 4, 5}, 4);
        Assert.assertEquals(2, actual);
        actual = search(new int[]{1, 2, 4, 4, 5}, 3);
        Assert.assertEquals(-1, actual);
        actual = search(new int[]{1, 1, 1, 1, 1}, 1);
        Assert.assertEquals(0, actual);
    }
}
