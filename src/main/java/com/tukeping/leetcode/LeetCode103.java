package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层次遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (52.76%)
 * Likes:    152
 * Dislikes: 0
 * Total Accepted:    32.4K
 * Total Submissions: 60.4K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 *
 * 返回锯齿形层次遍历如下：
 *
 * [
 * ⁠ [3],
 * ⁠ [20,9],
 * ⁠ [15,7]
 * ]
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.ListHelper;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode103 {

    /**
     * 33/33 cases passed (1 ms)
     * Your runtime beats 98.72 % of java submissions
     * Your memory usage beats 5.05 % of java submissions (38.3 MB)
     */

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        return bfsLoop(root, res);
    }

    private List<List<Integer>> bfsLoop(TreeNode root, List<List<Integer>> res) {
        Deque<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        int d = 0;
        while (!Q.isEmpty()) {
            List<Integer> resLine = new LinkedList<>();
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = Q.poll();
                if (node == null) continue;

                if (d % 2 == 0) resLine.add(node.val);
                else resLine.add(0, node.val);

                if (node.left != null) Q.add(node.left);
                if (node.right != null) Q.add(node.right);
            }
            res.add(resLine);
            d++;
        }
        return res;
    }

    /**
     * 给定二叉树 [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(3, 9, 20, null, null, 15, 7);
        int[][] actual = ListHelper.asTwoDimArray(zigzagLevelOrder(root));
        int[][] expect = {
                {3},
                {20, 9},
                {15, 7}
        };
        ListHelper.check(actual, expect);
    }
}
