package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC8 {

    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

    /**
     *
     * @param root TreeNode类
     * @param sum int整型
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, new ArrayList<Integer>(), 0, sum);
        return ret;
    }

    private void dfs(TreeNode root, List<Integer> path, int cur, int target) {
        if (root == null) return;

        path.add(root.val);
        cur += root.val;
        if (root.left == null && root.right == null && cur == target) {
            ret.add(new ArrayList<>(path));
        }

        dfs(root.left, path, cur, target);
        dfs(root.right, path, cur, target);

        path.remove(path.size() - 1);
    }
}
