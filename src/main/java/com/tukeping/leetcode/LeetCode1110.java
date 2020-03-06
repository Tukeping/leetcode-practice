package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=1110 lang=java
 *
 * [1110] 删点成林
 *
 * https://leetcode-cn.com/problems/delete-nodes-and-return-forest/description/
 *
 * algorithms
 * Medium (54.93%)
 * Likes:    21
 * Dislikes: 0
 * Total Accepted:    2.1K
 * Total Submissions: 3.7K
 * Testcase Example:  '[1,2,3,4,5,6,7]\n[3,5]'
 *
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 *
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 *
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 *
 * 提示：
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/5
 **/
public class LeetCode1110 {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<Integer> data = new ArrayList<>();
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        bfs(Q, data);

        for (int delNode : to_delete) {
            for (int i = 0; i < data.size(); i++) {
                if (Integer.valueOf(delNode).equals(data.get(i))) {
                    data.set(i, null);
                    break;
                }
            }
        }

        List<TreeNode> res = new ArrayList<>();
        TreeNode tree = new TreeNode(data.get(0));
        Q = new LinkedList<>();
        Q.add(tree);
        buildTreeBfs(data, 1, Q, res);

        return res;
    }

    private void buildTreeBfs(List<Integer> data, int idx, Queue<TreeNode> Q, List<TreeNode> res) {
        if (Q.isEmpty()) return;

        TreeNode root = Q.poll();

        if (idx < data.size() - 1) {
            TreeNode left = data.get(idx) == null ? null : new TreeNode(data.get(idx));
            root.left = left;
            Q.add(left);
            idx++;
            TreeNode right = data.get(idx) == null ? null : new TreeNode(data.get(idx));
            root.right = right;
            Q.add(right);
        }

        buildTreeBfs(data, idx, Q, res);
    }

    private void preorder(TreeNode root, List<Integer> data) {
        if (root == null) return;

        data.add(root.val);
        preorder(root.left, data);
        preorder(root.right, data);
    }

    private void bfs(Queue<TreeNode> Q, List<Integer> data) {
        if (Q.isEmpty()) return;

        TreeNode root = Q.poll();

        data.add(root.val);

        if (root.left != null) Q.add(root.left);
        if (root.right != null) Q.add(root.right);

        bfs(Q, data);
    }

    /**
     * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
     * 输出：[[1,2,null,4],[6],[7]]
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(1, 2, 3, 4, 5, 6, 7);
        List<TreeNode> forest = delNodes(root, new int[]{3, 5});

        TreeNode t1 = TreeNodeHelper.build(1, 2, null, 4);
        TreeNode t2 = TreeNodeHelper.build(6);
        TreeNode t3 = TreeNodeHelper.build(7);

        assertThat(forest, containsInAnyOrder(t1, t2, t3));
    }
}
