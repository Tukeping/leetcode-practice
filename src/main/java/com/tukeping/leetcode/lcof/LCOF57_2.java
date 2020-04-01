package com.tukeping.leetcode.lcof;

/*
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/6
 **/
public class LCOF57_2 {

    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        int i = 1;
        while (target > 0) {
            target -= i++;
            if (target > 0 && target % i == 0) {
                int[] ans = new int[i];
                int start = target / i;
                for (int k = start, idx = 0; k < start + i; k++, idx++) ans[idx] = k;
                res.add(ans);
            }
        }
        Collections.reverse(res);
        return res.toArray(new int[0][]);
    }

    public int[][] findContinuousSequenceTwoPoint(int target) {
        int max = target / 2 + 1;
        int low = 1, high = 2, sum;
        List<int[]> res = new ArrayList<>();
        while (low < high && high <= max) {
            sum = (low + high) * (high - low + 1) / 2;
            if (sum == target) {
                int[] ans = new int[high - low + 1];
                for (int i = low, j = 0; i <= high; i++, j++) ans[j] = i;
                res.add(ans);
                low++;
            } else if (sum < target) {
                high++;
            } else { // sum > target
                low++;
            }
        }

        return res.toArray(new int[0][]);
    }

    public int[][] findContinuousSequenceBruteForce(int target) {
        if (target <= 1) return new int[0][0];

        int max = target / 2 + 1;
        int sum;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> singleList;

        for (int i = 1; i <= max; i++) {
            sum = 0;
            singleList = new ArrayList<>();
            for (int j = i; j <= max; j++) {
                sum += j;
                singleList.add(j);
                if (sum >= target) {
                    break;
                }
            }
            if (sum == target) {
                list.add(singleList);
            }
        }

        int[][] ans = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> L = list.get(i);
            ans[i] = new int[L.size()];
            for (int j = 0; j < L.size(); j++) {
                ans[i][j] = L.get(j);
            }
        }

        return ans;
    }

    public int[][] findContinuousSequenceMath(int target) {
        if (target <= 1) return new int[0][0];
        int max = target / 2 + 1;


        return null;
    }

    /**
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     */
    @Test
    public void test1() {
        int[][] ans = findContinuousSequence(9);
        int[][] expect = {
                {2, 3, 4},
                {4, 5}
        };
        assertThat(ans, is(expect));
    }

    /**
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     */
    @Test
    public void test2() {
        int[][] ans = findContinuousSequence(15);
        int[][] expect = {
                {1, 2, 3, 4, 5},
                {4, 5, 6},
                {7, 8}
        };
        assertThat(ans, is(expect));
    }
}
