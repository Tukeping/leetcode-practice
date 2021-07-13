package com.tukeping.leetcode.problems;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/7/13
 **/
public class LeetCode210 {

    public int[] findOrder(int n, int[][] p) {
        List<List<Integer>> adjs = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adjs.add(new ArrayList<>());

        int[] indegree = new int[n];

        for (int[] edge : p) {
            adjs.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                toVisit.offer(i);
            }
        }

        int[] order = new int[n];
        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjs.get(from)) {
                indegree[to]--;
                if (indegree[to] == 0) {
                    toVisit.offer(to);
                }
            }
        }
        return visited == n ? order : new int[0];
    }

    @Test
    public void test() {
        int[][] p = new int[][]{
                {1, 0}
        };
        int[] actual = findOrder(2, p);
        ListHelper.assertThat(actual, new int[]{0, 1});
    }
}
