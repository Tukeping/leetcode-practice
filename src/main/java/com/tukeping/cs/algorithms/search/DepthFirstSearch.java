package com.tukeping.cs.algorithms.search;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

import static org.junit.Assert.assertFalse;

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

    /**
     * 深度遍历 && 中序遍历: left -> root -> right
     */

    public boolean dfsLoop(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long inorder = -Long.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }

        return true;
    }

    private long inorder = -Long.MAX_VALUE;

    public boolean dfsRecursion(TreeNode root) {
        if (root == null)
            return true;

        if (!dfsRecursion(root.left))
            return false;

        // If next element in inorder traversal
        // is smaller than the previous one
        // that's not BST.
        if (root.val <= inorder)
            return false;

        inorder = root.val;
        return dfsRecursion(root.right);
    }

    @Test
    public void test7() {
        TreeNode root = TreeNodeHelper.build(10, 5, 15, null, null, 6, 20);
        boolean b = dfsLoop(root);
        assertFalse(b);
    }

    @Test
    public void test8() {
        TreeNode root = TreeNodeHelper.build(3, null, 30, 10, null, null, 15, null, 45);
        boolean b = dfsRecursion(root);
        assertFalse(b);
    }

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
