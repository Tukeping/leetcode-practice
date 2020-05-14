package com.tukeping.cp.leetcode.contest.biweekly.contest24;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/18
 **/
public class LeetCode5373 {

    public int findMinFibonacciNumbers(int k) {
        long[] fib = new long[50];
        fib[0] = fib[1] = 1;

        int i = 2;
        while ((fib[i] = fib[i - 1] + fib[i - 2]) <= k) i++;

        int cnt = 0;
        while (k != 0) {
            if (k >= fib[i]) {
                k -= fib[i];
                cnt++;
            }
            i--;
        }
        return cnt;
    }

    public int findMinFibonacciNumbers2(int k) {
        if (k == 1) return 1;
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(1);
        int pre1 = 1, pre2 = 1, cur;
        while ((pre1 + pre2) <= k) {
            cur = pre1 + pre2;
            pre1 = pre2;
            pre2 = cur;
            nums.add(cur);
        }
        int cnt = 0, len = nums.size();
        for (int i = len - 1; i >= 0; ) {
            int num = nums.get(i);
            if (k - num == 0) {
                cnt++;
                break;
            } else if (k - num > 0) {
                cnt++;
                k -= num;
                i = bsearch(nums, k, 0, i);
            }
        }
        return cnt;
    }

    private int bsearch(List<Integer> nums, int target, int start, int end) {
        if (start > end) return end;
        int mid = (start + end) >> 1;
        if (nums.get(mid) == target) {
            return mid;
        } else if (nums.get(mid) > target) {
            return bsearch(nums, target, start, mid - 1);
        } else {
            return bsearch(nums, target, mid + 1, end);
        }
    }

    @Test
    public void test1() {
        int n = findMinFibonacciNumbers(7);
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int n = findMinFibonacciNumbers(10);
        assertThat(n, is(2));
    }

    @Test
    public void test3() {
        int n = findMinFibonacciNumbers(19);
        assertThat(n, is(3));
    }
}
