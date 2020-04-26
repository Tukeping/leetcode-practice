package com.tukeping.leetcode.contest.weekly.contest88;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/11
 **/
public class LeetCode849 {

    public int maxDistToClosest(int[] seats) {
        int len = seats.length;
        int maxZero = 0;
        int i = 0;

        while (i < len) {
            int sum = 0, start = i, end = i;
            while (i < len && seats[i] == 0) {
                sum++;
                end = i;
                i++;
            }
            if (start > 0 && end < len - 1) {
                sum = (sum + 1) / 2;
            }
            if (sum > maxZero || (sum == maxZero && end == len - 1)) {
                maxZero = sum;
            }
            while (i < len && seats[i] == 1) {
                i++;
            }
        }
        return maxZero;
    }

    /**
     * 输入：[1,0,0,0,1,0,1]
     * 输出：2
     * 解释：
     * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
     * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
     * 因此，他到离他最近的人的最大距离是 2 。
     */
    @Test
    public void test1() {
        int n = maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1});
        assertThat(n, is(2));
    }

    /**
     * 输入：[1,0,0,0]
     * 输出：3
     * 解释：
     * 如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
     * 这是可能的最大距离，所以答案是 3 。
     */
    @Test
    public void test2() {
        int n = maxDistToClosest(new int[]{1, 0, 0, 0});
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        int n = maxDistToClosest(new int[]{0, 0, 0, 1});
        assertThat(n, is(3));
    }

    @Test
    public void test4() {
        int n = maxDistToClosest(new int[]{0, 1, 1, 1, 0, 0, 1, 0, 0});
        assertThat(n, is(2));
    }

    @Test
    public void test5() {
        int n = maxDistToClosest(new int[]{0, 0, 1, 1, 0, 0, 1, 0, 1});
        assertThat(n, is(2));
    }

    @Test
    public void test6() {
        int n = maxDistToClosest(new int[]{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1});
        assertThat(n, is(3));
    }
}
