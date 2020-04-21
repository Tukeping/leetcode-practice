package com.tukeping.leetcode.weekly.contest179;

/*
 * 5354. 通知所有员工所需的时间
 *
 * https://leetcode-cn.com/problems/time-needed-to-inform-all-employees/
 *
 * 公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。
 *
 * 在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[headID] = -1。题目保证从属关系可以用树结构显示。
 *
 * 公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。
 *
 * 第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）。
 *
 * 返回通知所有员工这一紧急消息所需要的 分钟数 。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/8
 **/
public class LeetCode5354 {

    private int ret = 0;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int[][] ch = parentToChildren(manager, n);
        dfs(headID, ch, informTime, 0);
        return ret;
    }

    private void dfs(int cur, int[][] ch, int[] it, int time) {
        ret = Math.max(ret, time);
        time += it[cur];
        for (int e : ch[cur]) {
            dfs(e, ch, it, time);
        }
    }

    public int[][] parentToChildren(int[] par, int n) {
        int[] ct = new int[n];
        for (int i = 0; i < n; i++) {
            if (par[i] >= 0) {
                ct[par[i]]++;
            }
        }
        int[][] g = new int[n][];
        for (int i = 0; i < n; i++) {
            g[i] = new int[ct[i]];
        }
        for (int i = 0; i < n; i++) {
            if (par[i] >= 0) {
                g[par[i]][--ct[par[i]]] = i;
            }
        }
        return g;
    }

    /**
     * 输入：n = 1, headID = 0, manager = [-1], informTime = [0]
     * 输出：0
     * 解释：公司总负责人是该公司的唯一一名员工。
     */
    @Test
    public void test1() {
        int n = numOfMinutes(1, 0, new int[]{-1}, new int[]{0});
        assertThat(n, is(0));
    }

    /**
     * 输入：n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
     * 输出：1
     * 解释：id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
     * 上图显示了公司员工的树结构。
     */
    @Test
    public void test2() {
        int n = numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0});
        assertThat(n, is(1));
    }

    /**
     * 输入：n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1]
     * 输出：21
     * 解释：总负责人 id = 6。他将在 1 分钟内通知 id = 5 的员工。
     * id = 5 的员工将在 2 分钟内通知 id = 4 的员工。
     * id = 4 的员工将在 3 分钟内通知 id = 3 的员工。
     * id = 3 的员工将在 4 分钟内通知 id = 2 的员工。
     * id = 2 的员工将在 5 分钟内通知 id = 1 的员工。
     * id = 1 的员工将在 6 分钟内通知 id = 0 的员工。
     * 所需时间 = 1 + 2 + 3 + 4 + 5 + 6 = 21 。
     */
    @Test
    public void test3() {
        int n = numOfMinutes(7, 6, new int[]{1, 2, 3, 4, 5, 6, -1}, new int[]{0, 6, 5, 4, 3, 2, 1});
        assertThat(n, is(21));
    }

    /**
     * 输入：n = 15, headID = 0, manager = [-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6], informTime = [1,1,1,1,1,1,1,0,0,0,0,0,0,0,0]
     * 输出：3
     * 解释：第一分钟总负责人通知员工 1 和 2 。
     * 第二分钟他们将会通知员工 3, 4, 5 和 6 。
     * 第三分钟他们将会通知剩下的员工。
     */
    @Test
    public void test4() {
        int n = numOfMinutes(15, 0, new int[]{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6}, new int[]{1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0});
        assertThat(n, is(3));
    }

    /**
     * 输入：n = 4, headID = 2, manager = [3,3,-1,2], informTime = [0,0,162,914]
     * 输出：1076
     */
    @Test
    public void test5() {
        int n = numOfMinutes(4, 2, new int[]{3, 3, -1, 2}, new int[]{0, 0, 162, 914});
        assertThat(n, is(1076));
    }
}
