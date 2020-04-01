package com.tukeping.leetcode.lcof;

/*
 * 面试题35. 复杂链表的复制
 *
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 示例 1：
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 *
 * 提示：
 *
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 */

import com.tukeping.leetcode.structures.linkedlist.Node;
import com.tukeping.tools.linkedlist.NodeHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF35 {

    private Map<Node, Node> visited = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) return head;
        if (visited.containsKey(head)) return visited.get(head);

        Node clone = new Node(head.val);
        visited.put(head, clone);

        clone.next = copyRandomList(head.next);
        clone.random = copyRandomList(head.random);

        return clone;
    }

    /**
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     */
    @Test
    public void test1() {
        Node head = NodeHelper.build(7, null, 13, 0, 11, 4, 10, 2, 1, 0);
        Node clone = copyRandomList(head);
        assertThat(head.val, is(clone.val));
        assertThat(head.next.val, is(clone.next.val));
        if (head.random == null) {
            assertNull(clone.random);
        } else {
            assertThat(head.random, is(not(clone.random)));
        }
        assertThat(head, is(not(clone)));
    }

    /**
     * 输入：head = [[1,1],[2,1]]
     * 输出：[[1,1],[2,1]]
     */
    @Test
    public void test2() {
        Node head = NodeHelper.build(1, 1, 2, 1);
        Node clone = copyRandomList(head);
        assertThat(head.val, is(clone.val));
        assertThat(head.next.val, is(clone.next.val));
        if (head.random == null) {
            assertNull(clone.random);
        } else {
            assertThat(head.random, is(not(clone.random)));
        }
        assertThat(head, is(not(clone)));
    }

    /**
     * 输入：head = [[3,null],[3,0],[3,null]]
     * 输出：[[3,null],[3,0],[3,null]]
     */
    @Test
    public void test3() {
        Node head = NodeHelper.build(3, null, 3, 0, 3, null);
        Node clone = copyRandomList(head);
        assertThat(head.val, is(clone.val));
        assertThat(head.next.val, is(clone.next.val));
        if (head.random == null) {
            assertNull(clone.random);
        } else {
            assertThat(head.random, is(not(clone.random)));
        }
        assertThat(head, is(not(clone)));
    }

    /**
     * 输入：head = []
     * 输出：[]
     * 解释：给定的链表为空（空指针），因此返回 null。
     */
    @Test
    public void test4() {
        Node head = NodeHelper.build();
        Node clone = copyRandomList(head);
        assertNull(head);
        assertNull(clone);
    }
}
