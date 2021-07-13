package com.tukeping.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2021/7/5
 **/
public class NC41 {

    /**
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxLength(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0;
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>(); // <arr[index], index>
        for (int right = 0; right < arr.length; right++) {
            Integer dupIndex = map.get(arr[right]);
            if (dupIndex == null) {
                int len = right - left + 1;
                max = Math.max(max, len);
                map.put(arr[right], right);
            } else {
                while (left <= dupIndex) {
                    map.remove(arr[left]);
                    left++;
                }
                map.put(arr[right], right);
            }
        }
        return max;
    }

    @Test
    public void test() {
        int actual = maxLength(new int[]{2, 3, 4, 5});
        Assert.assertEquals(4, actual);
        actual = maxLength(new int[]{2, 2, 3, 4, 3});
        Assert.assertEquals(3, actual);
        actual = maxLength(new int[]{9});
        Assert.assertEquals(1, actual);
        actual = maxLength(new int[]{1, 2, 3, 1, 2, 3, 2, 2});
        Assert.assertEquals(3, actual);
        actual = maxLength(new int[]{2, 2, 3, 4, 8, 99, 3});
        Assert.assertEquals(5, actual);
    }
}
