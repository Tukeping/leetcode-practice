package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author tukeping
 * @date 2021/7/28
 **/
public class LeetCode863 {

    Map<Integer, List<TreeNode>> adj = new HashMap<>();
    List<Integer> ret = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (k == 0) {
            ret.add(target.val);
            return ret;
        }
        buildGraph(null, root);
        bfs(target.val, k);
        return ret;
    }

    private void bfs(int root, int k) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{root, 0});
        Set<Integer> set = new HashSet<>();
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int val = node[0], dist = node[1];
            if (set.contains(val)) {
                continue;
            }
            set.add(val);
            List<TreeNode> nei = adj.get(val);
            if (nei == null) continue;
            for (TreeNode sub : nei) {
                if (dist + 1 == k && !set.contains(sub.val)) {
                    ret.add(sub.val);
                } else if (dist < k) {
                    q.add(new int[]{sub.val, dist + 1});
                }
            }
        }
    }

    private void buildGraph(TreeNode parent, TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = adj.computeIfAbsent(root.val, k -> new ArrayList<>());
        if (root.left != null) list.add(root.left);
        if (root.right != null) list.add(root.right);
        if (parent != null) list.add(parent);
        buildGraph(root, root.left);
        buildGraph(root, root.right);
    }
}
