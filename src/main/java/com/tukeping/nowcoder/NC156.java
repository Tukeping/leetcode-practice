package com.tukeping.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC156 {

    /**
     * 例子：3，3，3，2(k=3)
     * 3：011
     * 3：011
     * 3：011
     * 2：010
     * t: 043
     * t%3
     * r: 010 =  2
     *
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param arr int一维数组
     * @param k int
     * @return int
     */
    public int foundOnceNumber(int[] arr, int k) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : arr) {
                sum += (num >> i) & 1;
            }
            ans ^= (sum % k) << i;
        }
        return ans;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param arr int一维数组
     * @param k int
     * @return int
     */
    public int foundOnceNumberV1(int[] arr, int k) {
        int[] bits = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                bits[j] += arr[i] & 1;
                arr[i] >>>= 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= bits[31 - i] % k;
        }
        return ans;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param arr int一维数组
     * @param k int
     * @return int
     */
    public int foundOnceNumberV2(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.merge(num, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    @Test
    public void test() {
        int actual = foundOnceNumber(new int[]{5, 4, 1, 1, 5, 1, 5}, 3);
        Assert.assertEquals(actual, 4);
    }
}
