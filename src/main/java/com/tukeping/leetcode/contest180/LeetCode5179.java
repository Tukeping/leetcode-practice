package com.tukeping.leetcode.contest180;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/*
 * 5179. 将二叉搜索树变平衡
 *
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 *
 * 如果一颗二叉搜索树中，任何节点两棵子树的高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 *
 * 如果有多种构造方法，请你返回任意一种。
 *
 * 示例：
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 *
 * 提示：
 *
 * 树节点的数目在 1 到 10^4 之间。
 * 树节点的值互不相同，且在 1 到 10^5 之间。
 */

/**
 * @author tukeping
 * @date 2020/3/15
 **/
public class LeetCode5179 {

    private List<Integer> values;

    private void dfs(TreeNode node) {
        if (node == null)
            return;

        dfs(node.left);
        values.add(node.val);
        dfs(node.right);
    }

    private TreeNode build_tree(int start, int end) {
        if (start >= end)
            return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(values.get(mid));
        root.left = build_tree(start, mid);
        root.right = build_tree(mid + 1, end);
        return root;
    }

    public TreeNode balanceBST(TreeNode root) {
        values = new ArrayList<>();
        dfs(root);
        return build_tree(0, values.size());
    }

    public TreeNode balanceBST1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        int[] data = asArray(res);
        return buildBalanceBST(data, 0, data.length - 1);
    }

    private TreeNode buildBalanceBST(int[] data, int start, int end) {
        if (start > end) return null;

        int mid = (end - start) / 2 + start;

        TreeNode root = new TreeNode(data[mid]);

        root.left = buildBalanceBST(data, start, mid - 1);
        root.right = buildBalanceBST(data, mid + 1, end);

        return root;
    }

    public static int[] asArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        int idx = 0;
        for (Integer n : list) {
            arr[idx++] = n;
        }
        return arr;
    }

    /** Left -> Root -> Right **/
    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 输入：root = [1,null,2,null,3,null,4,null,null]
     * 输出：[2,1,3,null,null,null,4]
     * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(1, null, 2, null, 3, null, 4, null, null);
        TreeNode balanceBST = balanceBST(root);
        assertTrue(TreeNodeHelper.isBalancedBST(balanceBST));
    }
}
