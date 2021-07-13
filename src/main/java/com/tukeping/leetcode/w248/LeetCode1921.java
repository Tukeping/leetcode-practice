package com.tukeping.leetcode.w248;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class LeetCode1921 {

    /**
     * int eliminateMaximum(vector<int>& dist, vector<int>& speed) {
     *         vector<double> t;
     *         for (int i = 0; i < dist.size(); ++i) {
     *             t.push_back(double(dist[i]) / speed[i]);
     *         }
     *         sort(t.begin(), t.end());
     *         int ans = 0;
     *         for (int i = 0; i < dist.size(); ++i) {
     *             if (t[i] > i) ++ans;
     *             else return ans;
     *         }
     *         return ans;
     *
     *     }
     */

    public int eliminateMaximum(int[] dist, int[] speed) {
        int ans = 0;
        int n = dist.length;

        double[] t = new double[n];
        for (int i = 0; i < n; i++) {
            t[i] = dist[i] * 1.0 / speed[i];
        }

        Arrays.sort(t);

        for (int i = 0; i < n; i++) {
            if (t[i] > i) ans++;
            else return ans;
        }

        return ans;
    }

    @Test
    public void test() {
        int x = eliminateMaximum(new int[]{1, 3, 4}, new int[]{1, 1, 1});
        Assert.assertEquals(x, 3);
    }

    @Test
    public void test1() {
        int x = eliminateMaximum(new int[]{1, 1, 2, 3}, new int[]{1, 1, 1, 1});
        Assert.assertEquals(x, 1);
    }

    @Test
    public void test2() {
        int x = eliminateMaximum(new int[]{3, 2, 4}, new int[]{5, 3, 2});
        Assert.assertEquals(x, 1);
    }

    @Test
    public void test3() {
        int x = eliminateMaximum(new int[]{4, 2, 8}, new int[]{2, 1, 4});
        Assert.assertEquals(x, 2);
    }

    @Test
    public void test4() {
        int x = eliminateMaximum(new int[]{4, 2, 3}, new int[]{2, 1, 1});
        Assert.assertEquals(x, 3);
    }

    @Test
    public void test5() {
        int x = eliminateMaximum(new int[]{6, 9, 6, 7, 9, 7, 10, 6, 6, 7}, new int[]{1, 1, 1, 1, 2, 1, 2, 1, 1, 2});
        Assert.assertEquals(x, 6);
    }
}
