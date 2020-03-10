package com.tukeping.leetcode;

/*
 * 5353. 灯泡开关 III
 *
 * 房间中有 n 枚灯泡，编号从 1 到 n，自左向右排成一排。最初，所有的灯都是关着的。
 *
 * 在 k  时刻（ k 的取值范围是 0 到 n - 1），我们打开 light[k] 这个灯。
 *
 * 灯的颜色要想 变成蓝色 就必须同时满足下面两个条件：
 *
 * 灯处于打开状态。
 * 排在它之前（左侧）的所有灯也都处于打开状态。
 * 请返回能够让 所有开着的 灯都 变成蓝色 的时刻 数目 。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/8
 **/
public class LeetCode5353 {

    public int numTimesAllBlue(int[] light) {
        if (light == null) return 0;

        int n = light.length;

        if (n == 1) return 1;
        if (n == 2) return light[0] < light[1] ? 2 : 1;

        int ans = 0;
        int max = -1, i;
        int[] lights = new int[n];
        for (int l : light) {
            lights[l - 1] = 1;
            max = Math.max(max, l - 1);
            for (i = max; i >= 0; i--) {
                if (lights[i] == 0) break;
            }
            if (i == -1) ans++;
        }

        return ans;
    }

    /**
     * 输入：light = [2,1,3,5,4]
     * 输出：3
     * 解释：所有开着的灯都变蓝的时刻分别是 1，2 和 4 。
     */
    @Test
    public void test1() {
        int n = numTimesAllBlue(new int[]{2, 1, 3, 5, 4});
        assertThat(n, is(3));
    }

    /**
     * 输入：light = [3,2,4,1,5]
     * 输出：2
     * 解释：所有开着的灯都变蓝的时刻分别是 3 和 4（index-0）。
     */
    @Test
    public void test2() {
        int n = numTimesAllBlue(new int[]{3, 2, 4, 1, 5});
        assertThat(n, is(2));
    }

    /**
     * 输入：light = [4,1,2,3]
     * 输出：1
     * 解释：所有开着的灯都变蓝的时刻是 3（index-0）。
     * 第 4 个灯在时刻 3 变蓝。
     */
    @Test
    public void test3() {
        int n = numTimesAllBlue(new int[]{4, 1, 2, 3});
        assertThat(n, is(1));
    }

    /**
     * 输入：light = [2,1,4,3,6,5]
     * 输出：3
     */
    @Test
    public void test4() {
        int n = numTimesAllBlue(new int[]{2, 1, 4, 3, 6, 5});
        assertThat(n, is(3));
    }

    /**
     * 输入：light = [1,2,3,4,5,6]
     * 输出：6
     */
    @Test
    public void test5() {
        int n = numTimesAllBlue(new int[]{1, 2, 3, 4, 5, 6});
        assertThat(n, is(6));
    }
}
