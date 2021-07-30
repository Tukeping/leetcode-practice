package com.tukeping.cs.datastructures.graph.ds;

import java.util.LinkedList;

/**
 * @author tukeping
 * @date 2020/6/9
 **/
public class Graph {
    public Vertex[] vertexes;
    public LinkedList<Edge> adj[];

    public Graph(int size) {
        this.vertexes = new Vertex[size];
        this.adj = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }
}
