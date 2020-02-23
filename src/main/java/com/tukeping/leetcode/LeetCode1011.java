package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1011 lang=java
 *
 * [1011] 在 D 天内送达包裹的能力
 *
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/description/
 *
 * algorithms
 * Medium (49.48%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    3.3K
 * Total Submissions: 6.6K
 * Testcase Example:  '[1,2,3,4,5,6,7,8,9,10]\n5'
 *
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 * 示例 1：
 *
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 *
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9),
 * (10) 是不允许的。
 *
 *
 * 示例 2：
 *
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 *
 *
 * 示例 3：
 *
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 *
 * 提示：
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tree | depth-first-search
 *
 * Unknown
 *
 * Not optimal solution
 *
 * @author tukeping
 * @date 2020/2/22
 **/
public class LeetCode1011 {

    /**
     * 82/82 cases passed (661 ms)
     * Your runtime beats 5.18 % of java submissions
     * Your memory usage beats 5.37 % of java submissions (45.1 MB)
     */

    public int shipWithinDays(int[] weights, int D) {
        int ic = Integer.MIN_VALUE;

        for (int weight : weights) {
            if (weight > ic) {
                ic = Math.max(ic, weight);
            }
        }

        int d, curSum, i, c = ic;
        while (true) {
            d = D;
            curSum = 0;
            i = 0;
            for (; i < weights.length; i++) {
                curSum += weights[i];
                if (curSum == c) {
                    d--;
                    curSum = 0;
                } else if (curSum > c) {
                    d--;
                    i--;
                    curSum = 0;
                } else if (i == weights.length - 1) {
                    d--;
                }
                if (d == 0) {
                    break;
                }
            }
            if (d >= 0 && i >= weights.length - 1) {
                return c;
            }
            c++;
        }
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
     *
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
