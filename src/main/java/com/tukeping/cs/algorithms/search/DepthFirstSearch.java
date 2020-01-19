package com.tukeping.cs.algorithms.search;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 深度搜索
 *
 * 使用 '栈' 数据结构, 利用 '先进后出'
 *
 * 递归:
 * procedure DFS(G, v) is
 *     label v as discovered
 *     for all directed edges from v to w that are in G.adjacentEdges(v) do
 *         if vertex w is not labeled as discovered then
 *             recursively call DFS(G, w)
 *
 * 非递归:
 * procedure DFS-iterative(G, v) is
 *     let S be a stack
 *     S.push(v)
 *     while S is not empty do
 *         v = S.pop()
 *         if v is not labeled as discovered then
 *             label v as discovered
 *             for all edges from v to w in G.adjacentEdges(v) do
 *                 S.push(w)
 *
 * @author tukeping
 * @date 2020/1/17
 **/
public class DepthFirstSearch {

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

    public void dfs(Graph graph, int start) {
        boolean[] visited = new boolean[graph.nodeSize];
        dfs0(graph, start, visited);
    }

    private void dfs0(Graph graph, int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");

        for (Integer current : graph.nodes[start]) {
            if (!visited[current]) {
                dfs0(graph, current, visited);
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

        System.out.println("Following is Depth First Traversal " +
                "(starting from vertex 2)");

        dfs(graph, 2);
    }
}
