package com.tukeping.practice.algorithms.search;

import com.tukeping.algs4.p24_priority_queue.MinPQ;
import com.tukeping.algs4.p43_minimum_spanning_tree.Edge;
import com.tukeping.algs4.p43_minimum_spanning_tree.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/8/6
 **/
public class LazyPrimMST {

    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        mst = new LinkedList<>();
        marked = new boolean[G.V()];
        visit(G, 0);

        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.offer(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> mst() {
        return mst;
    }
}
