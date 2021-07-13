package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.ListHelper;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author tukeping
 * @date 2021/7/5
 **/
public class NC15 {

    ArrayList<ArrayList<Integer>> ret;

    /**
     *
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        this.ret = new ArrayList<>();
        dfs(root, 0);
        return ret;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        ArrayList<Integer> result;
        if (ret.size() <= level) {
            result = new ArrayList<>();
            ret.add(result);
        } else {
            result = ret.get(level);
        }
        result.add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    @Test
    public void test() {
        TreeNode root = TreeNodeHelper.build(1, 2);
        ArrayList<ArrayList<Integer>> result = levelOrder(root);
        int[][] actual = ListHelper.asTwoDimArray(result);
        int[][] expect = new int[][]{
                {1}, {2}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }

    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(1, 2, 3, 4, null, null, 5);
        ArrayList<ArrayList<Integer>> result = levelOrder(root);
        int[][] actual = ListHelper.asTwoDimArray(result);
        int[][] expect = new int[][]{
                {1}, {2, 3}, {4, 5}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }
}
