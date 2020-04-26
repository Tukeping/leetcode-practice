package com.tukeping.book.sword_means_offer;

import com.tukeping.leetcode.structures.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/4/1
 **/
public class LCOF32_2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, 0, res);
        return res;
    }

    private void levelOrder(TreeNode root, int level, List<List<Integer>> res) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        if (root.left != null)
            levelOrder(root.left, level + 1, res);
        if (root.right != null)
            levelOrder(root.right, level + 1, res);
    }
}
