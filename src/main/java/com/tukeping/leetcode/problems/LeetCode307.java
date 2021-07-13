package com.tukeping.leetcode.problems;

import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/14
 **/
public class LeetCode307 {

    // 前缀和
    class NumArray {

        int[] tree;

        private void buildTree(int[] nums, int[] tree, int node, int start, int end) {
            System.out.println("node = " + node + ", start = " + start + ", end = " + end);
            if (start == end) {
                tree[node] = nums[start];
            } else {
                int mid = (start + end) / 2;
                int leftNode = 2 * node + 1;
                int rightNode = 2 * node + 2;
                buildTree(nums, tree, leftNode, start, mid);
                buildTree(nums, tree, rightNode, mid + 1, end);
                tree[node] = tree[leftNode] + tree[rightNode];
            }
        }

        public NumArray(int[] nums) {
            int n = nums.length;
            this.tree = new int[n * 2 + 1];

            buildTree(nums, tree, 0, 0, n - 1);

            for (int i = 0; i < this.tree.length; i++) {
                System.out.println("tree[" + i + "] = " + tree[i]);
            }
        }

        private void updateTree(int[] tree, int node, int start, int end, int idx, int val) {
            int mid = (start + end) / 2;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            if (idx >= start && idx <= mid) {
                updateTree(tree, leftNode, start, mid, idx, val);
            } else {
                updateTree(tree, rightNode, mid + 1, end, idx, val);
            }
            tree[node] = tree[leftNode] + tree[rightNode];
        }

        public void update(int index, int val) {
        }

        public int sumRange(int left, int right) {

            return 0;
        }
    }

    @Test
    public void test() {
        NumArray obj = new NumArray(new int[]{1, 3, 5, 7, 9, 11});
//		obj.sumRange(0, 2);
//		obj.update(1, 2);
//		obj.sumRange(0, 2);
    }
}
