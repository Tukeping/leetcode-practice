package com.tukeping.cs.algorithms.search;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 广度搜索
 *
 * 使用 '队列' 数据结构, 利用 '先进先出'
 *
 * procedure BFS(G, start_v) is
 *     let Q be a queue
 *     label start_v as discovered
 *     Q.enqueue(start_v)
 *     while Q is not empty do
 *         v := Q.dequeue()
 *         if v is the goal then
 *             return v
 *         for all edges from v to w in G.adjacentEdges(v) do
 *            if w is not labeled as discovered then
 *                label w as discovered
 *                w.parent := v
 *                Q.enqueue(w)
 *
 * @author tukeping
 * @date 2020/1/17
 **/
public class BreadthFirstSearch {

    class Graph {
        private int nodeSize;
        private LinkedList<Integer>[] nodes;

        public Graph(int nodeSize) {
            this.nodeSize = nodeSize;

            nodes = new LinkedList[nodeSize];

            for (int i = 0; i < nodeSize; i++) {
                nodes[i] = new LinkedList<>();
            }
        }

        public void addEdge(int v, int w) {
            nodes[v].add(w);
        }
    }

    public void bfs(Graph graph, int start) {
        boolean[] visited = new boolean[graph.nodeSize];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            start = queue.poll();
            System.out.print(start + " ");

            for (Integer current : graph.nodes[start]) {
                if (!visited[current]) {
                    visited[current] = true;
                    queue.add(current);
                }
            }
        }
    }

    @Test
    public void test() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");

        bfs(graph, 2);
    }
}
