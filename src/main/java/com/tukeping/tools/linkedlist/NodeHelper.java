package com.tukeping.tools.linkedlist;

import com.tukeping.leetcode.structures.linkedlist.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class NodeHelper {

    public static Node build(Integer... a) {
        return build0(a);
    }

    public static Node build0(Integer[] a) {
        if (a == null || a.length == 0) return null;

        Map<Integer, Node> nodeMap = new HashMap<>();
        int nodeIdx = 0;
        Node head = new Node(a[0]);
        nodeMap.put(nodeIdx++, head);
        Node cur = head;
        for (int i = 2; i < a.length; i = i + 2) {
            cur.next = new Node(a[i]);
            nodeMap.put(nodeIdx++, cur.next);
            cur = cur.next;
        }
        for (int i = 1, idx = 0; i < a.length; i = i + 2) {
            cur = nodeMap.get(idx++);
            cur.random = a[i] == null ? null : nodeMap.get(a[i]);
        }
        return head;
    }
}
