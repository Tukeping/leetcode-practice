package com.tukeping.leetcode.lcof;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/3
 **/
public class LCOF46 {

    private int[] dp = new int[11]; // memorization

    public int translateNum(int num /* 0 <= num < 2^31 (2147483648) **/) {
        if (num <= 9) return 1;
        return fn(num, 0);
    }

    private int fn(int n, int depth) {
        if (dp[depth] != 0) return dp[depth];
        if (n == 0) {
            return 1;
        }
        int cur = n % 10;
        int pre = (n / 10) % 10;
        int ans = (pre == 1 || pre == 2 && cur <= 5) ?
                fn(n / 10, depth + 1) + fn(n / 100, depth + 2) :
                fn(n / 10, depth + 1);
        dp[depth] = ans;
        return ans;
    }

    public int translateNum5(int num /* 0 <= num < 2^31 (2147483648) **/) {
        if (num == 0) return 1;
        return ((num / 10) % 10 == 1 || (num / 10) % 10 == 2 && num % 10 <= 5) ?
                translateNum5(num / 10) + translateNum5(num / 100) :
                translateNum5(num / 10);
    }

    public int translateNum4(int num /* 0 <= num < 2^31 (2147483648) **/) {
        if (num <= 9) return 1;
        return fn2(num);
    }

    private int fn2(int n) {
        if (n == 0) {
            return 1;
        }
        int cur = n % 10;
        int pre = (n / 10) % 10;
        if (pre == 1 || pre == 2 && cur <= 5) {
            return fn2(n / 10) + fn2(n / 100);
        } else {
            return fn2(n / 10);
        }
    }

    public int translateNum3(int num) {
        if (num <= 9) return 1;

        List<Integer> numList = new ArrayList<>();
        while (num > 0) {
            numList.add(0, num % 10);
            num /= 10;
        }

        int len = numList.size(), i = 1;
        int[] nums = new int[len + 1];
        for (int n : numList)
            nums[i++] = n;

        int pre2 = 1, pre1 = 1, cur = 0;
        for (i = 2; i <= len; i++) {
            if (nums[i - 1] == 1 || nums[i - 1] == 2 && nums[i] <= 5) {
                cur = pre2 + pre1;
            } else {
                cur = pre1;
            }
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }

    public int translateNum2(int num) {
        if (num <= 9) return 1;

        List<Integer> numList = new ArrayList<>();
        while (num > 0) {
            numList.add(0, num % 10);
            num /= 10;
        }

        int len = numList.size(), i = 1;
        int[] nums = new int[len + 1];
        for (int n : numList)
            nums[i++] = n;

        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (i = 2; i <= len; i++) {
            if (nums[i - 1] == 1 || nums[i - 1] == 2 && nums[i] <= 5) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[len];
    }

    /**
     * 输入: 12258
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     */
    @Test
    public void test1() {
        int n = translateNum(12258);
        assertThat(n, is(5));
    }

    @Test
    public void test2() {
        int n = translateNum(26);
        assertThat(n, is(1));
    }

    @Test
    public void test3() {
        int n = translateNum(506);
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int n = translateNum(1250156147);
        assertThat(n, is(12));
    }

    @Test
    public void test5() {
        int n = translateNum(99999);
        assertThat(n, is(1));
    }
}
