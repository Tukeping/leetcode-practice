package com.tukeping.practice.algorithms.search;

import com.tukeping.algs4.p15_uion_find.UF;
import com.tukeping.algs4.p24_priority_queue.MinPQ;
import com.tukeping.algs4.p43_minimum_spanning_tree.Edge;
import com.tukeping.algs4.p43_minimum_spanning_tree.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/8/6
 **/
public class KruskalMST {

    private Queue<Edge> mst = new LinkedList<>();

    public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.offer(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
