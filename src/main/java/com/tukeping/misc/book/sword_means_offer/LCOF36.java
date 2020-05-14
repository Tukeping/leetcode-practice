package com.tukeping.misc.book.sword_means_offer;

/*
 * 面试题36. 二叉搜索树与双向链表
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
 * 还需要返回链表中的第一个节点的指针。
 */

import com.tukeping.leetcode.structures.tree.Node;
import com.tukeping.tools.tree.NodeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF36 {

    private Node pre, head, tail;

    private void process(Node root) {
        if (root == null) return;

        process(root.left);

        root.left = pre;
        if (pre == null) head = root;
        else pre.right = root;
        pre = root;
        tail = root;

        process(root.right);
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        process(root);

        tail.right = head;
        head.left = tail;

        return head;
    }

    private List<Node> nodes = new ArrayList<>();

    public Node treeToDoublyList2(Node root) {
        inorder(root);

        Node dummy = new Node(0);
        dummy.left = nodes.get(0);
        Node head = dummy.left;
        int len = nodes.size();

        for (int i = 0; i < len; i++) {
            head.left = nodes.get((i + len + 1) % len);
            head.right = nodes.get((i + len - 1) % len);
            head = head.left;
        }
        return dummy.left;
    }

    private void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        nodes.add(root);
        inorder(root.right);
    }

    //@Test
    public void test1() {
        Node root = NodeHelper.build(4, 2, 5, 1, 3, null, null);
        Node doublyList = treeToDoublyList(root);
        Node expect = NodeHelper.build(1, 2, 5, 3, 1, null, null, 4, 2, null, null, 5, 3, null, null, 1, 4);
        NodeHelper.check(doublyList, expect);
    }
}
