package com.tukeping.nowcoder;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2021/7/5
 **/
public class NC102 {

    TreeNode lca;

    /**
     *
     * @param root TreeNode类
     * @param o1 int整型
     * @param o2 int整型
     * @return int整型
     */
    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        dfs(root, o1, o2);
        return lca.val;
    }

    private boolean dfs(TreeNode root, int o1, int o2) {
        if (root == null) {
            return false;
        }

        int boss = (root.val == o1 || root.val == o2) ? 1 : 0;
        int left = dfs(root.left, o1, o2) ? 1 : 0;
        int right = dfs(root.right, o1, o2) ? 1 : 0;

        if ((boss + left + right) >= 2) {
            lca = root;
            return false;
        }

        return boss == 1 || left == 1 || right == 1;
    }

    @Test
    public void test3() {
        TreeNode root = TreeNodeHelper.build(12, 39, 20, 37, 7, 1, 5, 10, 31, 18, 23, null, 22, null, 26, 25, 19, 11, null, 14, 13, 3, 8, 40, null, 21, 36, null, 32, 35, 16, null, null, 27, 2, null, 17, 29, null, 4, null, null, null, null, null, 6, null, null, null, null, null, 38, 33, null, null, 9, 24, null, null, null, null, null, 30, null, null, null, null, 34, null, null, 28, null, 15);
        int lca = lowestCommonAncestor(root, 1, 15);
        assertThat(lca, is(12));
    }
}
