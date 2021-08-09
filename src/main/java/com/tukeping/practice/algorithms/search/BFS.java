package com.tukeping.practice.algorithms.search;

import com.tukeping.algs4.p41_graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/8/5
 **/
public class BFS {

    private boolean[] marked;
    private int[] edgeTo;

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.offer(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }
}
