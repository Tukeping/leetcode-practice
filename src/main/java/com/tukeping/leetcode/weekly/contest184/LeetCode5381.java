package com.tukeping.leetcode.weekly.contest184;

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode5381 {

    public int[] processQueries(int[] queries, int m) {
        int[] P = new int[m];
        for (int i = 0; i < m; i++) {
            P[i] = i + 1;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            for (int j = 0; j < m; j++) {
                if (P[j] == q) {
                    ans[i] = j;
                    for (int k = j; k >= 1; k--) {
                        P[k] = P[k - 1];
                    }
                    P[0] = q;
                    break;
                }
            }
        }
        return ans;
    }

    public int[] processQueries2(int[] queries, int m) {
        LinkedList<Integer> P = new LinkedList<>();
        for (int i = 1; i <= m; i++)
            P.add(i);

        int len = queries.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int idx = P.indexOf(queries[i]);
            ans[i] = idx;
            P.remove((Integer) queries[i]);
            P.addFirst(queries[i]);
        }
        return ans;
    }

    /**
     * 输入：queries = [3,1,2,1], m = 5
     * 输出：[2,1,2,1]
     * 解释：待查数组 queries 处理如下：
     * 对于 i=0: queries[i]=3, P=[1,2,3,4,5], 3 在 P 中的位置是 2，接着我们把 3 移动到 P 的起始位置，得到 P=[3,1,2,4,5] 。
     * 对于 i=1: queries[i]=1, P=[3,1,2,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,3,2,4,5] 。
     * 对于 i=2: queries[i]=2, P=[1,3,2,4,5], 2 在 P 中的位置是 2，接着我们把 2 移动到 P 的起始位置，得到 P=[2,1,3,4,5] 。
     * 对于 i=3: queries[i]=1, P=[2,1,3,4,5], 1 在 P 中的位置是 1，接着我们把 1 移动到 P 的起始位置，得到 P=[1,2,3,4,5] 。
     * 因此，返回的结果数组为 [2,1,2,1] 。
     */
    @Test
    public void test1() {
        int[] ans = processQueries(new int[]{3, 1, 2, 1}, 5);
        assertThat(ans, is(new int[]{2, 1, 2, 1}));
    }

    /**
     * 输入：queries = [4,1,2,2], m = 4
     * 输出：[3,1,2,0]
     */
    @Test
    public void test2() {
        int[] ans = processQueries(new int[]{4, 1, 2, 2}, 4);
        assertThat(ans, is(new int[]{3, 1, 2, 0}));
    }

    /**
     * 输入：queries = [7,5,5,8,3], m = 8
     * 输出：[6,5,0,7,5]
     */
    @Test
    public void test3() {
        int[] ans = processQueries(new int[]{7, 5, 5, 8, 3}, 8);
        assertThat(ans, is(new int[]{6, 5, 0, 7, 5}));
    }
}
