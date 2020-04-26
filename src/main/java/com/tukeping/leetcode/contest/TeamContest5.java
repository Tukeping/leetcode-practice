package com.tukeping.leetcode.contest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/25
 **/
public class TeamContest5 {

    public int[] visitOrder(int[][] a, String direction) {
        int n = a.length;

        int k = 0;
        for (int i = 0; i < n; ++i) if (a[i][0] < a[k][0]) k = i;

        List<Integer> ret = new ArrayList<>();
        ret.add(k);

        boolean[] visited = new boolean[n];
        visited[k] = true;

        for (int step = 0; step < n - 2; step++) {
            char d = direction.charAt(step);
            int next = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    if (next < 0)
                        next = i;
                    else {
                        int c = (a[i][0] - a[k][0]) * (a[next][1] - a[k][1]) - (a[next][0] - a[k][0]) * (a[i][1] - a[k][1]);
                        if (d == 'L' && c > 0 || d == 'R' && c < 0) next = i;
                    }
                }
            }
            k = next;
            visited[k] = true;
            ret.add(k);
        }

        for (int i = 0; i < n; i++) if (!visited[i]) ret.add(i);

        return ret.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 输入：points = [[1,1],[1,4],[3,2],[2,1]], direction = "LL"
     * 输入：[0,2,1,3]
     * 解释：[0,2,1,3] 是符合"LL"的方案之一。在 [0,2,1,3] 方案中，0->2->1 是左转方向， 2->1->3 也是左转方向
     */
    @Test
    public void test1() {
        int[][] points = {
                {1, 1}, {1, 4}, {3, 2}, {2, 1}
        };
        int[] ans = visitOrder(points, "LL");
        assertThat(ans, anyOf(is(new int[]{0, 2, 1, 3}), is(new int[]{0, 3, 2, 1})));
    }

    /**
     * 输入：points = [[1,3],[2,4],[3,3],[2,1]], direction = "LR"
     * 输入：[0,3,1,2]
     * 解释：[0,3,1,2] 是符合"LR"的方案之一。在 [0,3,1,2] 方案中，0->3->1 是左转方向， 3->1->2 是右转方向
     */
    @Test
    public void test2() {
        int[][] points = {
                {1, 3}, {2, 4}, {3, 3}, {2, 1}
        };
        int[] ans = visitOrder(points, "LR");
        int[] expect = {0, 3, 1, 2};
        assertThat(ans, is(expect));
    }
}
