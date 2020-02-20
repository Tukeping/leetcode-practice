package com.tukeping.leetcode;

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

/**
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode617 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode master, slave;
        if (t1 == null) {
            master = t2;
            slave = t1;
        } else {
            master = t1;
            slave = t2;
        }
        if (master != null && slave != null) {
            master.val = master.val + slave.val;
        }
        dfs(master, slave);
        return master;
    }

    private void dfs(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null)
            return;

        if (tree1 != null && tree2 != null) {
            if (tree1.left != null && tree2.left != null) {
                tree1.left.val = tree1.left.val + tree2.left.val;
            } else if (tree1.left == null && tree2.left != null) {
                tree1.left = tree2.left;
            }
            if (tree1.right != null && tree2.right != null) {
                tree1.right.val = tree1.right.val + tree2.right.val;
            } else if (tree1.right == null && tree2.right != null) {
                tree1.right = tree2.right;
            }
        }

        TreeNode tree1Left = (tree1 == null) ? null : tree1.left;
        TreeNode tree1Right = (tree1 == null) ? null : tree1.right;
        TreeNode tree2Left = (tree2 == null) ? null : tree2.left;
        TreeNode tree2Right = (tree2 == null) ? null : tree2.right;

        dfs(tree1Left, tree2Left);
        dfs(tree1Right, tree2Right);
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
