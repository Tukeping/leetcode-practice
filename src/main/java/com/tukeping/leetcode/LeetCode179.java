package com.tukeping.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode179 {

    public String largestNumber(int[] nums) {
        int len = nums.length;

        String[] numStrs = new String[len];
        for (int i = 0; i < len; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStrs, (a, b) -> (b + a).compareTo(a + b));

        // sp case
        if ("0".equals(numStrs[0])) {
            return "0";
        }

        StringBuilder ans = new StringBuilder();
        for (String str : numStrs) {
            ans.append(str);
        }
        return ans.toString();
    }

    /**
     * 输入: [10,2]
     * 输出: 210
     */
    @Test
    public void test1() {
        String n = largestNumber(new int[]{10, 2});
        assertThat(n, is("210"));
    }

    /**
     * 输入: [3,30,34,5,9]
     * 输出: 9534330
     */
    @Test
    public void test2() {
        String n = largestNumber(new int[]{3, 30, 34, 5, 9});
        assertThat(n, is("9534330"));
    }
}
