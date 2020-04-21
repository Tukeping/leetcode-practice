package com.tukeping.leetcode.weekly.contest128;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/13
 **/
public class LeetCode1101 {

    public int shipWithinDays(int[] weights, int D) {
        int low = 0, high = 50000 * 500 * 2;
        while (high - low > 1) {
            int mid = (low + high) >> 1;
            if (ok(weights, mid, D)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }

    private boolean ok(int[] ws, int g, int d) {
        int cnt = 1;
        int rem = g;
        for (int w : ws) {
            if (w > g) return false;
            if (rem - w >= 0) {
                rem -= w;
            } else {
                cnt++;
                rem = g - w;
            }
        }
        return cnt <= d;
    }

    public int shipWithinDays2(int[] weights, int D) {
        int len = weights.length;

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += weights[i];
        }

        int ans = sum / D; // init by avg
        int i = 0;
        while (i < len) { // D is already over and weights is also use over.
            int day = D;
            int gIdx = 0;
            i = 0;
            int[] group = new int[D];

            while (day > 0) {
                while (i < len && group[gIdx] + weights[i] <= ans) {
                    group[gIdx] += weights[i++];
                }
                gIdx++;
                day--;
            }

            if (i < len) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
     * 输出：15
     * 解释：
     * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
     * 第 1 天：1, 2, 3, 4, 5
     * 第 2 天：6, 7
     * 第 3 天：8
     * 第 4 天：9
     * 第 5 天：10
     * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
     */
    @Test
    public void test1() {
        int n = shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
        assertThat(n, is(15));
    }

    /**
     * 输入：weights = [3,2,2,4,1,4], D = 3
     * 输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     */
    @Test
    public void test2() {
        int n = shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3);
        assertThat(n, is(6));
    }

    /**
     * 输入：weights = [1,2,3,1,1], D = 4
     * 输出：3
     * 解释：
     * 第 1 天：1
     * 第 2 天：2
     * 第 3 天：3
     * 第 4 天：1, 1
     */
    @Test
    public void test3() {
        int n = shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4);
        assertThat(n, is(3));
    }
}
