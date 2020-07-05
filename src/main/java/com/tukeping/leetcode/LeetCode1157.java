package com.tukeping.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/26
 **/
public class LeetCode1157 {

    class MajorityChecker {

        class Node {
            int val = 0;
            int count = 0;
        }

        Map<Integer, List<Integer>> indices = new HashMap<>();
        List<Node> tree = new ArrayList<>();
        int size;

        public MajorityChecker(int[] arr) {
            this.size = arr.length;
            for(int i = 0; i < arr.length * 4; i++) tree.add(new Node());

            if (this.size > 0) {
                for (int i = 0; i < arr.length; i++) {
                    if (!indices.containsKey(arr[i])) {
                        indices.put(arr[i], new ArrayList<>());
                    }
                    indices.get(arr[i]).add(i);
                }
                buildTree(arr, 0, 0, arr.length - 1);
            }
        }

        public int query(int left, int right, int threshold) {
            if (size == 0) return -1;

            Node node = findMax(0, 0, size - 1, left, right);
            if (node.count == 0) return -1;

            List<Integer> idxArr = indices.get(node.val);
            if (idxArr.size() < threshold) return -1;

            int it1 = lower_bound(idxArr, 0, idxArr.size() - 1, left);
            int it2 = upper_bound(idxArr, 0, idxArr.size() - 1, right);
            int cnt = it2 - it1;
            return (cnt >= threshold) ? node.val : -1;
        }

        private int lower_bound(List<Integer> nums, int l, int r, int target) {
            while (l < r) {
                int mid = l + r >> 1;
                if (nums.get(mid) < target) l = mid + 1;
                else r = mid;
            }
            return l;
        }

        private int upper_bound(List<Integer> nums, int l, int r, int target) {
            while (l < r) {
                int mid = l + r >> 1;
                if (nums.get(mid) <= target) l = mid + 1;
                else r = mid;
            }
            return l;
        }

        private Node findMax(int root, int left, int right, int limitLeft, int limitRight) {
            Node node = tree.get(root);
            if (left >= limitLeft && right <= limitRight) {
                return node;
            }

            int mid = (left + right) / 2;
            int rl = root * 2 + 1, rr = rl + 1;
            if (mid < limitLeft) return findMax(rr, mid + 1, right, limitLeft, limitRight);
            if (mid + 1 > limitRight) return findMax(rl, left, mid, limitLeft, limitRight);

            Node t = new Node();
            return merge(t, findMax(rl, left, mid, limitLeft, limitRight), findMax(rr, mid + 1, right, limitLeft, limitRight));
        }

        private void buildTree(int[] arr, int root, int left, int right) {
            if (left == right) {
                tree.get(root).val = arr[left];
                tree.get(root).count = 1;
                return;
            }

            int mid = (right + left) / 2;
            int rl = root * 2 + 1, rr = rl + 1;
            buildTree(arr, rl, left, mid);
            buildTree(arr, rr, mid + 1, right);
            merge(tree.get(root), tree.get(rl), tree.get(rr));
        }

        private Node merge(Node root, Node a, Node b) {
            if (a.val == b.val) {
                root.val = a.val;
                root.count = a.count + b.count;
            } else if (a.count > b.count) {
                root.val = a.val;
                root.count = a.count - b.count;
            } else {
                root.val = b.val;
                root.count = b.count - a.count;
            }
            return root;
        }
    }

    @Test
    public void test1() {
        MajorityChecker majorityChecker = new MajorityChecker(new int[]{1, 1, 2, 2, 1, 1});
        assertThat(majorityChecker.query(0, 5, 4), is(1)); // 返回 1
        assertThat(majorityChecker.query(0, 3, 3), is(-1)); // 返回 -1
        assertThat(majorityChecker.query(2, 3, 2), is(2)); // 返回 2
    }
}
