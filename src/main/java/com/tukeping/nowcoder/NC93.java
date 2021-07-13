package com.tukeping.nowcoder;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tukeping
 * @date 2021/7/3
 **/
public class NC93 {

    int cap;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        this.cap = k;
        this.map = new HashMap<>();

        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;

        List<Integer> ret = new ArrayList<>();

        for (int[] operator : operators) {
            int op = operator[0];
            if (op == 1) {
                int key = operator[1];
                int val = operator[2];
                set(key, val);
            } else if (op == 2) {
                int key = operator[1];
                int val = get(key);
                ret.add(val);
            } else {
                throw new RuntimeException("not support op:" + op);
            }
        }
        return ret.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void set(int key, int val) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, val);
            map.put(key, node);
            addNode(node);
            if (map.size() > cap) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
        } else {
            node.val = val;
            moveToHead(node);
        }
    }

    private int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            moveToHead(node);
            return node.val;
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    @Test
    public void test1() {
        int[][] operators = new int[][]{
                {1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}
        };
        int[] ret = LRU(operators, 3);
        ListHelper.assertThat(ret, new int[]{1, -1});
    }
}
