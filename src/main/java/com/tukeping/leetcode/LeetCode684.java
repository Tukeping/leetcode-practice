package com.tukeping.leetcode;

import com.tukeping.tools.Arrays;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/4/29
 **/
public class LeetCode684 {

    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];

        for (int[] edge : edges) {
            int u = find(parent, edge[0]);
            int v = find(parent, edge[1]);
            if (u == v) return edge;
            parent[u] = v;
        }

        return new int[0];
    }

    private int find(int[] parent, int v) {
        return parent[v] == 0 ? v : find(parent, parent[v]);
    }

    public int[] findRedundantConnection2(int[][] edges) {
        DisjointSet ds = new DisjointSet(edges.length);

        for (int[] edge : edges) {
            if (!ds.merge(edge[0] - 1, edge[1] - 1))
                return edge;
        }

        return new int[0];
    }

    class DisjointSet {
        private int[] parent;
        private byte[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new byte[size];
        }

        public int find(int x) {
            if (parent[x] == 0) return x;
            return parent[x] = find(parent[x]); // Path compression by halving.
        }

        // Return false if x, y are connected.
        public boolean merge(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;

            // Make root of smaller rank point to root of larger rank.
            if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
            else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            return true;
        }
    }

    /**
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的无向图为:
     *   1
     *  / \
     * 2 - 3
     */
    @Test
    public void test1() {
        int[][] edges = {
                {1, 2}, {1, 3}, {2, 3}
        };
        int[] actual = findRedundantConnection(edges);
        int[] expect = {2, 3};
        Arrays.check(actual, expect);
    }

    /**
     * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
     * 输出: [1,4]
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     *     |   |
     *     4 - 3
     */
    @Test
    public void test2() {
        int[][] edges = {
                {1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}
        };
        int[] actual = findRedundantConnection(edges);
        int[] expect = {1, 4};
        Arrays.check(actual, expect);
    }
}
