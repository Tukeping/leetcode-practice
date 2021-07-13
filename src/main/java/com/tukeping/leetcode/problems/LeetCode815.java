package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/28
 **/
public class LeetCode815 {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        return bfs(buildGraph(routes), routes, S, T);
    }

    private HashMap<Integer, HashSet<Integer>> buildGraph(int[][] routes) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j : routes[i]) {
                HashSet<Integer> val = graph.getOrDefault(j, new HashSet<>());
                val.add(i);
                graph.put(j, val);
            }
        }
        return graph;
    }

    private int bfs(HashMap<Integer, HashSet<Integer>> graph, int[][] routes, int S, int T) {
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{S, 0});

        HashSet<Integer> visitedStops = new HashSet<>();
        visitedStops.add(S);
        boolean[] visitedRoutes = new boolean[routes.length];

        while (!Q.isEmpty()) {
            int size = Q.size();
            while (size-- > 0) {
                int[] cur = Q.poll();
                int stop = cur[0], bus = cur[1];
                if (stop == T) return bus;
                for (int rt : graph.get(stop)) {
                    if (visitedRoutes[rt]) continue;
                    for (int st : routes[rt]) {
                        if (visitedStops.contains(st)) continue;
                        visitedStops.add(st);
                        Q.add(new int[]{st, bus + 1});
                    }
                    visitedRoutes[rt] = true;
                }
            }
        }
        return -1;
    }

    /**
     * 示例:
     * 输入:
     * routes = [[1, 2, 7], [3, 6, 7]]
     * S = 1
     * T = 6
     * 输出: 2
     * 解释:
     * 最优策略是先乘坐第一辆公交车到达车站 7, 然后换乘第二辆公交车到车站 6。
     */
    @Test
    public void test1() {
        int[][] routes = {
                {1, 2, 7}, {3, 6, 7}
        };
        int n = numBusesToDestination(routes, 1, 6);
        assertThat(n, is(2));
    }
}
