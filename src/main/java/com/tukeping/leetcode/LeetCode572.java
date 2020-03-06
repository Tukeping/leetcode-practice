package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=572 lang=java
 *
 * [572] 另一个树的子树
 *
 * https://leetcode-cn.com/problems/subtree-of-another-tree/description/
 *
 * algorithms
 * Easy (42.34%)
 * Likes:    160
 * Dislikes: 0
 * Total Accepted:    14.5K
 * Total Submissions: 33.4K
 * Testcase Example:  '[3,4,5,1,2]\n[4,1,2]'
 *
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s
 * 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *
 * ⁠    3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \
 * ⁠1   2
 *
 *
 * 给定的树 t：
 *
 *
 * ⁠  4
 * ⁠ / \
 * ⁠1   2
 *
 *
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *
 * ⁠    3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \
 * ⁠1   2
 * ⁠   /
 * ⁠  0
 *
 *
 * 给定的树 t：
 *
 *
 * ⁠  4
 * ⁠ / \
 * ⁠1   2
 *
 *
 * 返回 false。
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tree
 *
 * ebay | facebook | google
 *
 * @author tukeping
 * @date 2020/3/5
 **/
public class LeetCode572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    private boolean traverse(TreeNode s, TreeNode t) {
        return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }

    private boolean equals(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
    }

    public boolean isSubtreePreOrder(TreeNode s, TreeNode t) {
        String t1Str = preorder(s, false);
        String t2Str = preorder(t, false);
        return t1Str.contains(t2Str);
    }

    private String preorder(TreeNode root, boolean isLeft) {
        if (root == null) return isLeft ? "#LN" : "#RN";
        return "#" + root.val + preorder(root.left, true) + preorder(root.right, false);
    }

    private String inorder(TreeNode root, boolean isLeft) {
        if (root == null) {
            return isLeft ? "#LN" : "#RN";
        }
        return inorder(root.left, true) + "#" + root.val + inorder(root.right, false);
    }

    private String postorder(TreeNode root, boolean isLeft) {
        if (root == null) return isLeft ? "#LN" : "#RN";
        return postorder(root.left, true) + postorder(root.right, false) + "#" + root.val;
    }

    /**
     * 示例 1:
     * 给定的树 s:
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 t:
     *    4
     *   / \
     *  1   2
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     */
    @Test
    public void test1() {
        TreeNode t1 = TreeNodeHelper.build(3, 4, 5, 1, 2);
        TreeNode t2 = TreeNodeHelper.build(4, 1, 2);
        boolean b = isSubtree(t1, t2);
        assertTrue(b);
    }

    /**
     * 示例 2:
     * 给定的树 s：
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     * 返回 false。
     */
    @Test
    public void test2() {
        TreeNode t1 = TreeNodeHelper.build(3, 4, 5, 1, 2, null, null, 0);
        TreeNode t2 = TreeNodeHelper.build(4, 1, 2);
        boolean b = isSubtree(t1, t2);
        assertFalse(b);
    }

    /**
     * 示例 2:
     * 给定的树 s：
     *      1
     *     / \
     *    2   3
     * 给定的树 t：
     *    2
     *   / \
     *  3
     * 返回 false。
     */
    @Test
    public void test3() {
        TreeNode t1 = TreeNodeHelper.build(1, 2, 3);
        TreeNode t2 = TreeNodeHelper.build(2, 3);
        boolean b = isSubtree(t1, t2);
        assertFalse(b);
    }
}
