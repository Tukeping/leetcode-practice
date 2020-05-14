package com.tukeping.cp.leetcode.contest.weekly.contest186;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class LeetCode5394 {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                List<Integer> list = map.getOrDefault(i + j, new ArrayList<>());
                list.add(nums.get(i).get(j));
                map.put(i + j, list);
            }
        }
        return map.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .mapToInt(val -> val)
                .toArray();
    }

    @Test
    public void test1() {
        int[][] a = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        int[] ans = findDiagonalOrder(ListHelper.asIntegerList(a));
        int[] expect = {
                1, 4, 2, 7, 5, 3, 8, 6, 9
        };
        assertThat(ans, is(expect));
    }

    @Test
    public void test2() {
        int[][] a = {
                {14, 12, 19, 16, 9}, {13, 14, 15, 8, 11}, {11, 13, 1}
        };
        int[] ans = findDiagonalOrder(ListHelper.asIntegerList(a));
        int[] expect = {
                14, 13, 12, 11, 14, 19, 13, 15, 16, 1, 8, 9, 11
        };
        assertThat(ans, is(expect));
    }

    /**
     * 输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
     * 输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
     */
    @Test
    public void test3() {
        int[][] a = {
                {1, 2, 3, 4, 5}, {6, 7}, {8}, {9, 10, 11}, {12, 13, 14, 15, 16}
        };
        int[] ans = findDiagonalOrder(ListHelper.asIntegerList(a));
        int[] expect = {
                1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16
        };
        assertThat(ans, is(expect));
    }

    /**
     * 输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
     * 输出：[1,4,2,5,3,8,6,9,7,10,11]
     */
    @Test
    public void test4() {
        int[][] a = {
                {1, 2, 3}, {4}, {5, 6, 7}, {8}, {9, 10, 11}
        };
        int[] ans = findDiagonalOrder(ListHelper.asIntegerList(a));
        int[] expect = {
                1, 4, 2, 5, 3, 8, 6, 9, 7, 10, 11
        };
        assertThat(ans, is(expect));
    }

    /**
     * 输入：nums = [[1,2,3,4,5,6]]
     * 输出：[1,2,3,4,5,6]
     */
    @Test
    public void test5() {
        int[][] a = {
                {1, 2, 3, 4, 5, 6}
        };
        int[] ans = findDiagonalOrder(ListHelper.asIntegerList(a));
        int[] expect = {
                1, 2, 3, 4, 5, 6
        };
        assertThat(ans, is(expect));
    }
}
