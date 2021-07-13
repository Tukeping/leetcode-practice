package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode119 {

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0)
            return Arrays.asList(1);
        if (rowIndex == 1)
            return Arrays.asList(1, 1);

        int[] pre = new int[]{1, 1};
        int[] cur = null;

        for (int i = 2; i <= rowIndex; i++) {
            cur = new int[i + 1];
            cur[0] = 1;
            cur[i] = 1;
            for (int j = 1; j < i; j++) {
                cur[j] = pre[j - 1] + pre[j];
            }
            pre = cur;
        }

        List<Integer> ans = new ArrayList<>(rowIndex + 1);
        for (int value : cur)
            ans.add(value);

        return ans;
    }

    /**
     * 输入: 3
     * 输出: [1,3,3,1]
     */
    @Test
    public void test1() {
        List<Integer> ans = getRow(3);
        List<Integer> expect = Arrays.asList(1, 3, 3, 1);
        assertThat(ans, is(expect));
    }
}
