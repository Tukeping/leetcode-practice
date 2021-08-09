package com.tukeping.practice.algorithms.search;

import com.tukeping.algs4.p42_directed_graphs.Topological;
import com.tukeping.algs4.p44_shortest_path.DirectedEdge;
import com.tukeping.algs4.p44_shortest_path.EdgeWeightedDigraph;

/**
 * @author tukeping
 * @date 2021/8/6
 **/
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological topological = new Topological(G);
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v)) {

            }
        }
    }
}
