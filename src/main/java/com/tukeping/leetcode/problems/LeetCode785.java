package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/7/13
 **/
public class LeetCode785 {

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        if (n == 0) return true;

        int[] color = new int[n];
        Arrays.fill(color, 0);

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                q.add(i);
                color[i] = 1;
            }
            while (!q.isEmpty()) {
                int node = q.poll();
                for (int j : graph[node]) {
                    if (color[j] == 0) {
                        q.add(j);
                        color[j] = (color[node] == 2) ? 1 : 2;
                    } else if (color[node] == color[j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[][] graph = new int[][]{
                {1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}
        };
        boolean b = isBipartite(graph);
        System.out.println(b);
    }
}
