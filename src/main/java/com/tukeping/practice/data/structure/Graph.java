package com.tukeping.practice.data.structure;

import com.tukeping.algs4.p13_stacks_and_queues.Bag;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/8/5
 **/
public class Graph {

    private final int V;
    private final Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return 0;
    }

    public int E() {
        return 0;
    }

    public String toString() {
        return null;
    }

    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    public double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    public int numOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        return count / 2;
    }

    @Test
    public void test() {
        Graph G = new Graph(10);
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                System.out.println(v + "-" + w);
            }
        }
    }
}
