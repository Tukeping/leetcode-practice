package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.ListHelper;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC45 {

    List<Integer> nums;

    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders(TreeNode root) {
        int[][] ret = new int[3][];

        nums = new ArrayList<>();
        preOrder(root);
        ret[0] = toTwoDimArray();

        nums = new ArrayList<>();
        midOrder(root);
        ret[1] = toTwoDimArray();

        nums = new ArrayList<>();
        postOrder(root);
        ret[2] = toTwoDimArray();

        return ret;
    }

    private int[] toTwoDimArray() {
        return nums.stream().mapToInt(Integer::intValue).toArray();
    }

    private void preOrder(TreeNode root) {
        if (root == null) return;
        nums.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void midOrder(TreeNode root) {
        if (root == null) return;
        midOrder(root.left);
        nums.add(root.val);
        midOrder(root.right);
    }

    private void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        nums.add(root.val);
    }

    @Test
    public void test() {
        int[][] ret = threeOrders(TreeNodeHelper.build(1, 2, 3));
        ListHelper.assertThatTwoDim(ret, new int[][]{
                {1, 2, 3}, {2, 1, 3}, {2, 3, 1}
        });
    }
}
