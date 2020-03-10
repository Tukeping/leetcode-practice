package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=236 lang=java
 *
 * [236] 二叉树的最近公共祖先
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (58.68%)
 * Likes:    397
 * Dislikes: 0
 * Total Accepted:    48.3K
 * Total Submissions: 80.3K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]\n5\n1'
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tree
 *
 * amazon | apple | facebook | linkedin | microsoft
 *
 * @author tukeping
 * @date 2020/3/6
 **/
public class LeetCode236 {

    private TreeNode lca;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestor(root, p.val, q.val);
        return lca;
    }

    private boolean lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || lca != null) return false;

        int mid = root.val == p || root.val == q ? 1 : 0;
        int left = lowestCommonAncestor(root.left, p, q) ? 1 : 0;
        int right = lowestCommonAncestor(root.right, p, q) ? 1 : 0;

        if (mid + left + right >= 2) lca = root;

        return (mid + left + right) > 0;
    }

    /**
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = TreeNodeHelper.find(root, 5);
        TreeNode q = TreeNodeHelper.find(root, 1);
        TreeNode lca = lowestCommonAncestor(root, p, q);
        assertThat(lca.val, is(3));
    }

    /**
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     */
    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = TreeNodeHelper.find(root, 5);
        TreeNode q = TreeNodeHelper.find(root, 4);
        TreeNode lca = lowestCommonAncestor(root, p, q);
        assertThat(lca.val, is(5));
    }
}
