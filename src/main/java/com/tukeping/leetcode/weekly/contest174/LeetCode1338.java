package com.tukeping.leetcode.weekly.contest174;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/10
 **/
public class LeetCode1338 {

    public int minSetSize(int[] arr) {
        int[] f = new int[100001];
        for (int v : arr) {
            f[v]++;
        }
        Arrays.sort(f);
        int n = arr.length;
        int r = 0;
        int ans = 0;
        for (int i = f.length - 1; i >= 0; i--) {
            r += f[i];
            ans++;
            if (r * 2 >= n) {
                break;
            }
        }
        return ans;
    }

    public int minSetSize2(int[] arr) {
        int len = arr.length;
        if (len == 1) return 1;

        int halfLen = len / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> sortedList = new ArrayList<>(map.entrySet());
        Collections.sort(sortedList, (o1, o2) -> o2.getValue() - o1.getValue());

        int deletedLen = 0;
        int ans = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            deletedLen += sortedList.get(i).getValue();
            ans++;
            if (deletedLen >= halfLen) {
                return ans;
            }
        }
        return ans;
    }

    /**
     * 输入：arr = [3,3,3,3,5,5,5,2,2,7]
     * 输出：2
     * 解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
     * 大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
     * 选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
     */
    @Test
    public void test1() {
        int n = minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7});
        assertThat(n, is(2));
    }

    /**
     * 输入：arr = [7,7,7,7,7,7]
     * 输出：1
     * 解释：我们只能选择集合 {7}，结果数组为空。
     */
    @Test
    public void test2() {
        int n = minSetSize(new int[]{7, 7, 7, 7, 7, 7});
        assertThat(n, is(1));
    }

    /**
     * 输入：arr = [1,9]
     * 输出：1
     */
    @Test
    public void test3() {
        int n = minSetSize(new int[]{1, 9});
        assertThat(n, is(1));
    }

    /**
     * 输入：arr = [1000,1000,3,7]
     * 输出：1
     */
    @Test
    public void test4() {
        int n = minSetSize(new int[]{1000, 1000, 3, 7});
        assertThat(n, is(1));
    }

    /**
     * 输入：arr = [1,2,3,4,5,6,7,8,9,10]
     * 输出：5
     */
    @Test
    public void test5() {
        int n = minSetSize(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertThat(n, is(5));
    }
}
