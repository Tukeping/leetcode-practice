package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层次遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/description/
 *
 * algorithms
 * Medium (60.00%)
 * Likes:    374
 * Dislikes: 0
 * Total Accepted:    77.5K
 * Total Submissions: 127.4K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 * 返回其层次遍历结果：
 * [
 * ⁠ [3],
 * ⁠ [9,20],
 * ⁠ [15,7]
 * ]
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.ListHelper;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * tree | breadth-first-search
 *
 * amazon | apple | bloomberg | facebook | linkedin | microsoft
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode102 {

    List<List<Integer>> retV2 = new ArrayList<>();

    public List<List<Integer>> levelOrderV3(TreeNode root) {
        dfsV2(root, 0);
        return retV2;
    }

    private void dfsV2(TreeNode root, int level) {
        if (root == null) return;

        List<Integer> result;
        if (retV2.size() == level) {
            result = new ArrayList<>();
            retV2.add(result);
        } else {
            result = retV2.get(level);
        }
        result.add(root.val);

        dfsV2(root.left, level + 1);
        dfsV2(root.right, level + 1);
    }

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> levelOrderV2(TreeNode root) {
        dfs(root, 0);
        return ret;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) return;
        List<Integer> data;
        if (ret.size() == level) {
            data = new ArrayList<>();
            ret.add(data);
        } else {
            data = ret.get(level);
        }
        data.add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, 0, res);
        return res;
    }

    private void levelOrder(TreeNode root, int level, List<List<Integer>> levelList) {
        if (levelList.size() == level) {
            levelList.add(new ArrayList<>());
        }
        levelList.get(level).add(root.val);
        if (root.left != null)
            levelOrder(root.left, level + 1, levelList);
        if (root.right != null)
            levelOrder(root.right, level + 1, levelList);
    }

    private List<List<Integer>> bfs(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(Collections.singletonList(root.val));

        if (!hasLeafNode(root))
            return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        traverse(queue, res, new LinkedList<>(), 0, 2, 0);

        return res;
    }

    private void traverse(LinkedList<TreeNode> queue, List<List<Integer>> res,
                          List<Integer> levelList, int cur, int level, int missNodeNum) {
        if (queue.isEmpty()) return;

        TreeNode root = queue.removeFirst();

        if ((cur + missNodeNum) >= level) {
            levelList = new LinkedList<>();
            level = level * 2;
            cur = 0;
            missNodeNum = missNodeNum * 2;
        }

        if (root.left != null) {
            levelList.add(root.left.val);
            if (hasLeafNode(root.left)) {
                queue.addLast(root.left);
                cur = cur + 1;
            } else {
                missNodeNum++;
            }
        } else {
            missNodeNum++;
        }

        if (root.right != null) {
            levelList.add(root.right.val);
            if (hasLeafNode(root.right)) {
                queue.addLast(root.right);
                cur = cur + 1;
            } else {
                missNodeNum++;
            }
        } else {
            missNodeNum++;
        }

        if (!levelList.isEmpty() && (cur + missNodeNum) >= level)
            res.add(levelList);

        traverse(queue, res, levelList, cur, level, missNodeNum);
    }

    private boolean hasLeafNode(TreeNode root) {
        return root.left != null || root.right != null;
    }

    private int levelN(int level) {
        return (int) (Math.log(level) / Math.log(2));
    }

    /**
     * 给定二叉树: [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(3, 9, 20, null, null, 15, 7);
        List<List<Integer>> res = levelOrder(root);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {3},
                {9, 20},
                {15, 7}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }

    @Test
    public void test2() {
        List<List<Integer>> res = levelOrder(null);
        assertThat(res, is(Collections.emptyList()));
    }

    @Test
    public void test3() {
        TreeNode root = TreeNodeHelper.build(1, 2);
        List<List<Integer>> res = levelOrder(root);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {1},
                {2}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }

    @Test
    public void test4() {
        TreeNode root = TreeNodeHelper.build(1, 2, 3, 4, null, null, 5);
        List<List<Integer>> res = levelOrder(root);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {1},
                {2, 3},
                {4, 5}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }

    @Test
    public void test5() {
        TreeNode root = TreeNodeHelper.build(1, 2, null, 3, null, 4, null, 5);
        List<List<Integer>> res = levelOrder(root);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {1},
                {2},
                {3},
                {4},
                {5}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }

    @Test
    public void test6() {
        TreeNode root = TreeNodeHelper.build(0, 2, 4, 1, null, 3, -1, 5, 1, null, 6, null, 8);
        List<List<Integer>> res = levelOrder(root);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {0},
                {2, 4},
                {1, 3, -1},
                {5, 1, 6, 8}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }
}
