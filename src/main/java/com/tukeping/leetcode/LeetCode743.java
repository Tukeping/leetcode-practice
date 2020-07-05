package com.tukeping.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/18
 **/
public class LeetCode743 {

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = buildGraph(times);
        return bfs(graph, times, n, k);
    }

    private int bfs(Map<Integer, List<int[]>> graph, int[][] times, int n, int k) {
        PriorityQueue<int[]> Q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        Q.offer(new int[]{0, k});

        Map<Integer, Integer> dist = new HashMap<>();

        while(!Q.isEmpty()) {
            int[] cur = Q.poll();
            int d = cur[0], node = cur[1];
            if(dist.containsKey(node)) continue;
            dist.put(node, d);
            if(!graph.containsKey(node)) continue;
            for(int[] edge : graph.get(node)) {
                int v = edge[0], w = edge[1];
                if(!dist.containsKey(v))
                    Q.offer(new int[]{d + w, v});
            }
        }

        if(dist.size() != n) return -1;

        int ans = 0;
        for(int d : dist.values())
            ans = Math.max(ans, d);
        return ans;
    }

    private Map<Integer, List<int[]>> buildGraph(int[][] times) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] edge : times) {
            int u = edge[0], v = edge[1], w = edge[2];
            if(!graph.containsKey(u)) {
                graph.put(u, new ArrayList<>());
            }
            graph.get(u).add(new int[]{v, w});
        }
        return graph;
    }

    public int networkDelayTime2(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            if (!graph.containsKey(time[0])) {
                graph.put(time[0], new ArrayList<>());
            }
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }

        Queue<int[]> Q = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        Q.offer(new int[]{k, 0});

        Map<Integer, Integer> dist = new HashMap<>();

        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            int u = cur[0], w = cur[1];
            if (dist.containsKey(u)) continue;
            dist.put(u, w);
            if (!graph.containsKey(u)) continue;
            for (int[] nei : graph.get(u)) {
                Q.offer(new int[]{nei[0], nei[1]});
            }
        }

        if (dist.size() != n) return -1;

        int ans = 0;
        for (int val : dist.values()) ans += val;
        return ans;
    }

    @Test
    public void test1() {
        int[][] times = {
                {1, 2, 1},
                {2, 3, 2},
                {1, 3, 4}
        };
        int n = networkDelayTime(times, 3, 1);
        assertThat(n, is(3));
    }
}
