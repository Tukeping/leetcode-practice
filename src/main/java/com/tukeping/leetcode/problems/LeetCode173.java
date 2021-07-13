package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=173 lang=java
 *
 * [173] 二叉搜索树迭代器
 *
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/description/
 *
 * algorithms
 * Medium (71.73%)
 * Likes:    150
 * Dislikes: 0
 * Total Accepted:    16.1K
 * Total Submissions: 22.3K
 * Testcase Example:  '["BSTIterator","next","next","hasNext","next","hasNext","next","hasNext","next","hasNext"]\n' +
  '[[[7,3,15,null,null,9,20]],[null],[null],[null],[null],[null],[null],[null],[null],[null]]'
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 *
 *
 * 示例：
 *
 *
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *
 * 提示：
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * stack | tree | design
 *
 * facebook | google | linkedin | microsoft
 *
 * @author tukeping
 * @date 2020/4/2
 **/
public class LeetCode173 {

    /**
     * 中序遍历: Left -> Root -> Right
     * BST 二叉搜索树: Left < Root < Right
     */
    class BSTIterator {
        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            inorderLeft(root);
        }

        private void inorderLeft(TreeNode root) {
            if (root == null) return;
            stack.push(root);
            inorderLeft(root.left);
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            if (node.right != null) {
                inorderLeft(node.right);
            }
            return node.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    /**
     * 中序遍历: Left -> Root -> Right
     * BST 二叉搜索树: Left < Root < Right
     */
    class BSTIterator2 {
        private int[] elements;
        private int size;
        private int curIdx = 0;

        public BSTIterator2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            inorder(root, res);
            this.size = res.size();
            elements = new int[size];
            for (int i = 0; i < size; i++) {
                elements[i] = res.get(i);
            }
        }

        private void inorder(TreeNode root, List<Integer> res) {
            if (root == null) return;
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }

        /** @return the next smallest number */
        public int next() {
            return elements[curIdx++];
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return curIdx != size;
        }
    }

    /**
     * BSTIterator iterator = new BSTIterator(root);
     * iterator.next();    // 返回 3
     * iterator.next();    // 返回 7
     * iterator.hasNext(); // 返回 true
     * iterator.next();    // 返回 9
     * iterator.hasNext(); // 返回 true
     * iterator.next();    // 返回 15
     * iterator.hasNext(); // 返回 true
     * iterator.next();    // 返回 20
     * iterator.hasNext(); // 返回 false
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(7, 3, 15, null, null, 9, 20);
        BSTIterator iterator = new BSTIterator(root);
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(7));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(9));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(15));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(20));
        assertFalse(iterator.hasNext());
    }
}
