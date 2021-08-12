package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tukeping
 * @date 2021/8/11
 **/
public class LeetCode199 {

    List<Integer> ret = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>(); // level, num
    // 每一层只需要保存一个数字
    // 需要依靠 dfs 遍历时优先遍历 右子树
    int maxLevel = 0;

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return ret;

        dfs(root, 0);

        for (int i = 0; i <= maxLevel; i++) {
            ret.add(map.get(i));
        }
        return ret;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) return;

        if (!map.containsKey(level)) {
            map.put(level, root.val);
            maxLevel = Math.max(maxLevel, level);
        }

        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }
}
