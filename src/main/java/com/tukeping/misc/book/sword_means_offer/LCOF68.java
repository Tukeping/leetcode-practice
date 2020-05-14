package com.tukeping.misc.book.sword_means_offer;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF68 {

    TreeNode lca;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findNode(root, p.val, q.val);
        return lca;
    }

    private boolean findNode(TreeNode root, int p, int q) {
        if(root == null || lca != null)
            return false;
        int node = root.val == p || root.val == q ? 1 : 0;
        int left = findNode(root.left, p, q) ? 1 : 0;
        int right = findNode(root.right, p, q) ? 1 : 0;
        if(node + left + right >= 2)
            lca = root;
        return (node + left + right) > 0;
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
