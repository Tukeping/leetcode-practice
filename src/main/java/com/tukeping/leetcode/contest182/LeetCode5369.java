package com.tukeping.leetcode.contest182;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/*
 * 5369. 统计作战单位数
 *
 * n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
 *
 * 每 3 个士兵可以组成一个作战单位，分组规则如下：
 *
 * 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
 * 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
 * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：rating = [2,5,3,4,1]
 * 输出：3
 * 解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
 * 示例 2：
 *
 * 输入：rating = [2,1,3]
 * 输出：0
 * 解释：根据题目条件，我们无法组建作战单位。
 * 示例 3：
 *
 * 输入：rating = [1,2,3,4]
 * 输出：4
 *
 *
 * 提示：
 *
 * n == rating.length
 * 1 <= n <= 200
 * 1 <= rating[i] <= 10^5
 */

/**
 * @author tukeping
 * @date 2020/3/29
 **/
public class LeetCode5369 {

    public int numTeams(int[] rating) {
        int len = rating.length;
        if (len <= 2) return 0;

        int cnt = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (rating[i] < rating[j] && rating[j] < rating[k]) {
                        cnt++;
                    } else if (rating[i] > rating[j] && rating[j] > rating[k]) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * 输入：rating = [2,5,3,4,1]
     * 输出：3
     * 解释：我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
     */
    @Test
    public void test1() {
        int n = numTeams(new int[]{2, 5, 3, 4, 1});
        assertThat(n, is(3));
    }

    /**
     * 输入：rating = [2,1,3]
     * 输出：0
     * 解释：根据题目条件，我们无法组建作战单位。
     */
    @Test
    public void test2() {
        int n = numTeams(new int[]{2, 1, 3});
        assertThat(n, is(0));
    }

    /**
     * 输入：rating = [1,2,3,4]
     * 输出：4
     */
    @Test
    public void test3() {
        int n = numTeams(new int[]{1, 2, 3, 4});
        assertThat(n, is(4));
    }
}
