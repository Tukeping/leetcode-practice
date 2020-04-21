package com.tukeping.leetcode.weekly.contest179;

/*
 * 5355. T 秒后青蛙的位置
 *
 * https://leetcode-cn.com/problems/frog-position-after-t-seconds/
 *
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 *
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 * 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [fromi, toi] 意味着存在一条直接连通 fromi 和 toi 两个顶点的边。
 *
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/8
 **/
public class LeetCode5355 {

    public double frogPosition(int n, int[][] edges, int t, int target) {
        int m = edges.length;
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = edges[i][0] - 1;
            to[i] = edges[i][1] - 1;
        }
        int[][] g = packU(n, from, to);

        int[][] pars = parents3(g, 0);
        int[] par = pars[0], dep = pars[2];

        target--;
        if (dep[target] > t) return 0.;
        double ret = 1;
        for (int i = par[target]; i != -1; i = par[i]) {
            ret /= i == 0 ? g[i].length : g[i].length - 1;
        }
        if (dep[target] == t) {
            return ret;
        }
        if (target != 0 && g[target].length == 1) {
            return ret;
        } else if (n == 1) {
            return ret;
        } else {
            return 0.;
        }
    }

    private int[][] parents3(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, q, depth};
    }

    private int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    /**
     * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
     * 输出：0.16666666666666666
     * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
     */
    @Test
    public void test1() {
        int[][] edges = {
                {1, 2},
                {1, 3},
                {1, 7},
                {2, 4},
                {2, 6},
                {3, 5}
        };
        double n = frogPosition(7, edges, 2, 4);
        assertThat(n, is(0.16666666666666666D));
    }

    /**
     * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
     * 输出：0.3333333333333333
     * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。
     */
    @Test
    public void test2() {
        int[][] edges = {
                {1, 2},
                {1, 3},
                {1, 7},
                {2, 4},
                {2, 6},
                {3, 5}
        };
        double n = frogPosition(7, edges, 1, 7);
        assertThat(n, is(0.3333333333333333D));
    }

    /**
     * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
     * 输出：0.16666666666666666
     */
    @Test
    public void test3() {
        int[][] edges = {
                {1, 2},
                {1, 3},
                {1, 7},
                {2, 4},
                {2, 6},
                {3, 5}
        };
        double n = frogPosition(7, edges, 20, 6);
        assertThat(n, is(0.16666666666666666D));
    }
}
