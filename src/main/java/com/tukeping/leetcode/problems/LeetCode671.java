package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author tukeping
 * @date 2021/7/27
 **/
public class LeetCode671 {

    Set<Integer> set = new HashSet<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public int findSecondMinimumValueV3(TreeNode root) {
        dfsV2(root);
        if (pq.isEmpty() || pq.size() == 1) return -1;
        else {
            return pq.poll();
        }
    }

    private void dfsV2(TreeNode root) {
        if (root == null) return;
        dfsV2(root.left);
        if (!set.contains(root.val)) {
            pq.add(root.val);
            set.add(root.val);
            if (pq.size() > 2) {
                set.remove(pq.remove());
            }
        }
        dfsV2(root.right);
    }

    List<Integer> list = new ArrayList<>();

    public int findSecondMinimumValue(TreeNode root) {
        dfs(root);
        if (list.isEmpty()) return -1;
        else {
            list.sort((o1, o2) -> o1 - o2);
            for (int val : list) {
                System.out.println("val = " + val);
            }
            int minValue = list.get(0);
            for (int val : list) {
                if (val != minValue) {
                    return val;
                }
            }
            return -1;
        }
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}
