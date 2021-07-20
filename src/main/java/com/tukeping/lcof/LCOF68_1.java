package com.tukeping.lcof;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/29
 **/
public class LCOF68_1 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val == q.val) return p;
        while (root != null) {
            if (root.val < q.val && root.val < p.val) root = root.right;
            if (root.val > q.val && root.val > p.val) root = root.left;
            else return root;
        }
        return null;
    }

    /**
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        TreeNode p = TreeNodeHelper.find(root, 2);
        TreeNode q = TreeNodeHelper.find(root, 8);
        TreeNode lca = lowestCommonAncestor(root, p, q);
        TreeNode expectLCA = TreeNodeHelper.find(root, 6);
        assertThat(lca, is(expectLCA));
    }

    /**
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     */
    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        TreeNode p = TreeNodeHelper.find(root, 2);
        TreeNode q = TreeNodeHelper.find(root, 4);
        TreeNode lca = lowestCommonAncestor(root, p, q);
        TreeNode expectLCA = TreeNodeHelper.find(root, 2);
        assertThat(lca, is(expectLCA));
    }
}
