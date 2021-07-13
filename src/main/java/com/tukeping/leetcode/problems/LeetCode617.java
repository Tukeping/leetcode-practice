package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=617 lang=java
 *
 * [617] 合并二叉树
 *
 * https://leetcode-cn.com/problems/merge-two-binary-trees/description/
 *
 * algorithms
 * Easy (74.26%)
 * Likes:    300
 * Dislikes: 0
 * Total Accepted:    34.5K
 * Total Submissions: 46.1K
 * Testcase Example:  '[1,3,2,5]\n[2,1,3,null,4,null,7]'
 *
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL
 * 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 *
 *
 * 输入:
 * Tree 1                     Tree 2
 * ⁠         1                         2
 * ⁠        / \                       / \
 * ⁠       3   2                     1   3
 * ⁠      /                           \   \
 * ⁠     5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 *
 *
 * 注意: 合并必须从两个树的根节点开始。
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.Stack;

/**
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode617 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) return null;
        if(t1 == null || t2 == null) return (t1 == null) ? t2 : t1;

        t1.val += t2.val;

        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }

    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        return dfsLoop(t1, t2);
    }

    private TreeNode dfsLoop(TreeNode t1, TreeNode t2) {
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});

        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();

            if (nodes[0] == null || nodes[1] == null) {
                continue;
            }

            nodes[0].val = nodes[0].val + nodes[1].val;

            if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            } else {
                stack.push(new TreeNode[]{nodes[0].left, nodes[1].left});
            }

            if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            } else {
                stack.push(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }

        return t1;
    }

    private TreeNode dfsRecursion(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val = t1.val + t2.val;
        t1.left = dfsRecursion(t1.left, t2.left);
        t1.right = dfsRecursion(t1.right, t2.right);
        return t1;
    }

    /**
     * 输入:
     * 	Tree 1                     Tree 2
     *           1                         2
     *          / \                       / \
     *         3   2                     1   3
     *        /                           \   \
     *       5                             4   7
     * 输出:
     * 合并后的树:
     * 	     3
     * 	    / \
     * 	   4   5
     * 	  / \   \
     * 	 5   4   7
     *
     * 注意: 合并必须从两个树的根节点开始。
     */
    @Test
    public void test1() {
        TreeNode tree1 = TreeNodeHelper.build(1, 3, 2, 5);
        TreeNode tree2 = TreeNodeHelper.build(2, 1, 3, null, 4, null, 7);
        TreeNode merged = mergeTrees(tree1, tree2);
        TreeNodeHelper.check(merged, 3, 4, 5, 5, 4, null, 7);
    }

    /**
     * 	Tree 1                     Tree 2
     *           1                         1
     *          / \                       / \
     *         2                             2
     *        /                               \
     *       3                                 3
     */
    @Test
    public void test2() {
        TreeNode tree1 = TreeNodeHelper.build(1, 2, null, 3);
        TreeNode tree2 = TreeNodeHelper.build(1, null, 2, null, 3);
        TreeNode merged = mergeTrees(tree1, tree2);
        TreeNodeHelper.check(merged, 2, 2, 2, 3, null, null, 3);
    }
}
