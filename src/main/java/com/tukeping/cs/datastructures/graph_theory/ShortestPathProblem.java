package com.tukeping.cs.datastructures.graph_theory;

import com.tukeping.cs.datastructures.graph_theory.ds.Edge;
import com.tukeping.cs.datastructures.graph_theory.ds.Graph;
import com.tukeping.cs.datastructures.graph_theory.ds.Vertex;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最短路径问题是图论研究中的一个经典算法问题，旨在寻找图（由结点和路径组成的）中两结点之间的最短路径。
 *
 * Dijkstra    O(V^2)  1959
 *
 *
 * @author tukeping
 * @date 2020/6/9
 **/
public class ShortestPathProblem {

    private int INF = 0x3f3f3f3f;

    public int dijkstra3(int[][] g) {
        int n = g.length;

        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = INF;
        dist[1] = 0;

        boolean[] st = new boolean[n + 1];

        for (int i = 0; i < n - 1; i++) {
            int t = -1;
            for (int j = 1; j <= n; j++)
                if (!st[j] && (t == -1 || dist[t] > dist[j]))
                    t = j;

            for (int j = 1; j <= n; j++)
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);

            st[t] = true;
        }

        if (dist[n] == INF) return -1;
        return dist[n];
    }

    @Test
    public void test3() {
        int[][] g = {
                {INF, 0,   5,   2,   INF, INF, INF, INF},
                {INF, 5,   0,   INF, 1,   6,   INF, INF},
                {INF, 2,   INF, 0,   6,   8,   INF, INF},
                {INF, INF, 1,   6,   0,   1,   2,   INF},
                {INF, INF, 6,   INF, 1,   0,   INF, 7},
                {INF, INF, INF, 8,   2,   INF, 0,   3},
                {INF, INF, INF, INF, INF, 7,   3,   0}
        };
        int n = dijkstra3(g);
        System.out.println(n);
    }

    public int[] dijkstra2(Graph graph, int startIndex) {
        int n = graph.vertexes.length;

        int[] dist = new int[n];
        int[] pre = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        boolean[] visit = new boolean[n];
        visit[0] = true;

        for (Edge edge : graph.adj[startIndex]) {
            dist[edge.index] = edge.weight;
            pre[edge.index] = 0;
        }

        for (int i = 1; i < n; i++) {
            int minDist = Integer.MAX_VALUE;
            int minDistIdx = -1;
            for (int j = 1; j < n; j++) {
                if (!visit[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minDistIdx = j;
                }
            }
            if (minDistIdx == -1) {
                break;
            }
            visit[minDistIdx] = true;
            for (Edge edge : graph.adj[minDistIdx]) {
                if (visit[edge.index]) {
                    continue;
                }
                int weight = edge.weight;
                int preDist = dist[edge.index];
                if (weight != Integer.MAX_VALUE && minDist + weight < preDist) {
                    dist[edge.index] = minDist + weight;
                    pre[edge.index] = minDistIdx;
                }
            }
        }
        return pre;
    }

    @Test
    public void test2() {
        Graph graph = new Graph(7);
        initGraph(graph);
        int[] pre = dijkstra2(graph, 0);
        printPre(graph.vertexes, pre, graph.vertexes.length - 1);
    }

    private void printPre(Vertex[] vertices, int[] pre, int i) {
        if (i > 0) {
            printPre(vertices, pre, pre[i]);
        }
        System.out.print(vertices[i].data);
        if (i != vertices.length - 1) System.out.print(" => ");
    }

    public Map<Integer, Integer> dijkstra(Graph graph, int startIndex) {
        int n = graph.vertexes.length;

        Map<Integer, Integer> distMap = new HashMap<>();
        for (int i = 1; i < n; i++) {
            distMap.put(i, Integer.MAX_VALUE);
        }

        for (Edge edge : graph.adj[startIndex]) {
            distMap.put(edge.index, edge.weight);
        }

        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        for (int i = 1; i < n; i++) {
            int minDist = Integer.MAX_VALUE;
            int minDistIdx = -1;
            for (int j = 1; j < n; j++) {
                if (!visited.contains(j) && distMap.get(j) < minDist) {
                    minDist = distMap.get(j);
                    minDistIdx = j;
                }
            }
            if (minDistIdx == -1) {
                break;
            }
            visited.add(minDistIdx);
            for (Edge edge : graph.adj[minDistIdx]) {
                if (visited.contains(edge.index)) {
                    continue;
                }
                int weight = edge.weight;
                int preDist = distMap.get(edge.index);
                if (weight != Integer.MAX_VALUE && minDist + weight < preDist) {
                    distMap.put(edge.index, minDist + weight);
                }
            }
        }
        return distMap;
    }

    @Test
    public void test1() {
        Graph graph = new Graph(7);
        initGraph(graph);
        Map<Integer, Integer> distMap = dijkstra(graph, 0);
        int dist = distMap.get(6);
        // 从A到G的最短距离是11 (路径: A-B-D-F-G)
        System.out.println(dist);
    }

    private void initGraph(Graph graph) {
        graph.vertexes[0] = new Vertex("A");
        graph.vertexes[1] = new Vertex("B");
        graph.vertexes[2] = new Vertex("C");
        graph.vertexes[3] = new Vertex("D");
        graph.vertexes[4] = new Vertex("E");
        graph.vertexes[5] = new Vertex("F");
        graph.vertexes[6] = new Vertex("G");

        graph.adj[0].add(new Edge(1, 5));
        graph.adj[0].add(new Edge(2, 2));
        graph.adj[1].add(new Edge(0, 5));
        graph.adj[1].add(new Edge(3, 1));
        graph.adj[1].add(new Edge(4, 6));
        graph.adj[2].add(new Edge(0, 2));
        graph.adj[2].add(new Edge(3, 6));
        graph.adj[2].add(new Edge(5, 8));
        graph.adj[3].add(new Edge(1, 1));
        graph.adj[3].add(new Edge(2, 6));
        graph.adj[3].add(new Edge(4, 1));
        graph.adj[3].add(new Edge(5, 2));
        graph.adj[4].add(new Edge(1, 6));
        graph.adj[4].add(new Edge(3, 1));
        graph.adj[4].add(new Edge(6, 7));
        graph.adj[5].add(new Edge(2, 8));
        graph.adj[5].add(new Edge(3, 2));
        graph.adj[5].add(new Edge(6, 3));
        graph.adj[6].add(new Edge(4, 7));
        graph.adj[6].add(new Edge(5, 3));
    }
}
