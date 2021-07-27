package com.tukeping.leetcode.problems;

import com.tukeping.leetcode.structures.tree.Node;
import com.tukeping.tools.tree.NodeHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2021/7/26
 **/
public class LeetCode116 {

    List<List<Node>> layer = new ArrayList<>();

    public Node connect(Node root) {
        dfs(root, 0);
        return root;
    }

    private void dfs(Node root, int deep) {
        if (root == null) return;

        if (layer.size() == deep) {
            List<Node> list = new ArrayList<>();
            list.add(root);
            layer.add(list);
        } else {
            List<Node> list = layer.get(deep);
            Node last = list.get(list.size() - 1);
            last.next = root;
            list.add(root);
        }

        dfs(root.left, deep + 1);
        dfs(root.right, deep + 1);
    }

    @Test
    public void test() {
        Node actual = connect(NodeHelper.build(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(actual.left.next.val, 3);
        Assert.assertEquals(actual.left.left.next.val, 5);
        Assert.assertEquals(actual.left.left.next.next.val, 6);
        Assert.assertEquals(actual.left.left.next.next.next.val, 7);
    }
}
