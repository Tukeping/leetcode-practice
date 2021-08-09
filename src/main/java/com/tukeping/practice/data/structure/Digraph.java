package com.tukeping.practice.data.structure;

import com.tukeping.algs4.p13_stacks_and_queues.Bag;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/8/5
 **/
public class Digraph {

    private final int V;
    private final Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
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

    public Digraph reverse() {
        return null;
    }

    public String toString() {
        return null;
    }

    @Test
    public void test() {
        Digraph G = new Digraph(10);
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                System.out.println(v + "->" + w);
            }
        }
    }
}
