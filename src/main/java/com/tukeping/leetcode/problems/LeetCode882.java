package com.tukeping.leetcode.problems;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author tukeping
 * @date 2021/7/13
 **/
public class LeetCode882 {

    public int reachableNodes(int[][] edges, int m, int n) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.computeIfAbsent(u, x -> new HashMap<>()).put(v, w);
            graph.computeIfAbsent(v, x -> new HashMap<>()).put(u, w);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        pq.offer(new int[]{m, 0});

        Map<Integer, Integer> visited = new HashMap<>(n);
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int dist = node[0];
            int curNode = node[1];
            if (visited.containsKey(curNode)) {
                continue;
            }
            visited.put(curNode, dist);

            if (graph.containsKey(curNode)) {
                for (Map.Entry<Integer, Integer> entry : graph.get(curNode).entrySet()) {
                    int nextNode = entry.getKey();
                    int nextDist = dist - entry.getValue() - 1;
                    if (visited.containsKey(nextNode) || nextDist < 0) {
                        continue;
                    }
                    pq.add(new int[]{nextDist, nextNode});
                }
            }
        }

        int ans = visited.size();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int uv = visited.getOrDefault(u, 0);
            int vu = visited.getOrDefault(v, 0);
            ans += Math.min(w, uv + vu);
        }
        return ans;
    }

    @Test
    public void test() {
        int[][] edges = new int[][]{
                {0, 1, 10}, {0, 2, 1}, {1, 2, 2}
        };
        int actual = reachableNodes(edges, 6, 3);
        Assert.assertEquals(actual, 13);
    }

    @Test
    public void test2() {
        int[][] edges = new int[][]{
                {1, 2, 4}, {1, 4, 5}, {1, 3, 1}, {2, 3, 4}, {3, 4, 5}
        };
        int actual = reachableNodes(edges, 17, 5);
        Assert.assertEquals(actual, 1);
    }
}
