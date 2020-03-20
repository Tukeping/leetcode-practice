package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=297 lang=java
 *
 * [297] 二叉树的序列化与反序列化
 *
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/description/
 *
 * algorithms
 * Hard (45.44%)
 * Likes:    152
 * Dislikes: 0
 * Total Accepted:    17.1K
 * Total Submissions: 37.6K
 * Testcase Example:  '[1,2,3,null,null,4,5]'
 *
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 /
 * 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * ⁠    / \
 * ⁠   4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode
 * 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * tree | design
 *
 * amazon | bloomberg | facebook | google | linkedin | microsoft | uber |  yahoo
 *
 * @author tukeping
 * @date 2020/3/20
 **/
public class LeetCode297 {

    public class Codec2 {

        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "null,";
            } else {
                str += root.val + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode rdeserialize(List<String> list) {
            if ("null".equals(list.get(0))) {
                list.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
            list.remove(0);
            root.left = rdeserialize(list);
            root.right = rdeserialize(list);

            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] dataArr = data.split(",");
            List<String> dataList = new LinkedList<>(Arrays.asList(dataArr));
            return rdeserialize(dataList);
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> res = parseTree(root);
            while (!res.isEmpty() && res.get(res.size() - 1) == null) {
                res.remove(res.size() - 1);
            }
            return toString(res);
        }

        private String toString(List<Integer> list) {
            Iterator<Integer> it = list.iterator();
            if (!it.hasNext())
                return "[]";

            StringBuilder sb = new StringBuilder();
            sb.append('[');
            while (true) {
                Integer e = it.next();
                sb.append(e);
                if (!it.hasNext())
                    return sb.append(']').toString();
                sb.append(',');
            }
        }

        private List<Integer> parseTree(TreeNode root) {
            if (root == null) return Collections.emptyList();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            List<Integer> res = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    res.add(null);
                    continue;
                }
                res.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            return res;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty() || "[]".equals(data)) return null;

            data = data.replaceAll("[\\[|\\]]", "");
            Integer[] nums = Stream.of(data.split(","))
                    .map(num -> {
                        if ("null".equals(num)) return null;
                        else return Integer.valueOf(num);
                    })
                    .toArray(Integer[]::new);

            LinkedList<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(nums[0]);
            queue.addLast(root);

            buildTree(nums, 1, queue);

            return root;
        }

        private void buildTree(Integer[] nums, int idx, Queue<TreeNode> queue) {
            if (idx >= nums.length) return;
            if (queue.isEmpty()) return;

            TreeNode root = queue.peek();

            root.left = (nums[idx] == null) ? null : new TreeNode(nums[idx]);

            if (idx + 1 == nums.length) {
                root.right = null;
            } else {
                root.right = (nums[idx + 1] == null) ? null : new TreeNode(nums[idx + 1]);
            }

            queue.poll();

            if (root.left != null)
                queue.add(root.left);

            if (root.right != null)
                queue.add(root.right);

            buildTree(nums, idx + 2, queue);
        }
    }

    /**
     * 你可以将以下二叉树：
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     *
     * 序列化为 "[1,2,3,null,null,4,5]"
     */
    @Test
    public void test1() {
        Codec codec = new Codec();
        String tree = codec.serialize(TreeNodeHelper.build(1, 2, 3, null, null, 4, 5));
        assertThat(tree, anyOf(is("[1,2,3,null,null,4,5]"), is("1,2,3,null,null,4,5,null,null,null,null")));
        TreeNode root = codec.deserialize(tree);
        TreeNodeHelper.check(root, 1, 2, 3, null, null, 4, 5);
    }

    @Test
    public void test2() {
        Codec codec = new Codec();
        String tree = codec.serialize(null);
        assertThat(tree, anyOf(is("[]"), is("")));
        TreeNode root = codec.deserialize(tree);
        assertNull(root);
    }
}
