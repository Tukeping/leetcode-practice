package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=71 lang=java
 *
 * [71] 简化路径
 *
 * https://leetcode-cn.com/problems/simplify-path/description/
 *
 * algorithms
 * Medium (37.96%)
 * Likes:    100
 * Dislikes: 0
 * Total Accepted:    25.2K
 * Total Submissions: 65.1K
 * Testcase Example:  '"/home/"'
 *
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..）
 * 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 *
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 /
 * 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 *
 *
 * 示例 2：
 *
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 *
 *
 * 示例 3：
 *
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 *
 *
 * 示例 4：
 *
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 *
 *
 * 示例 5：
 *
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 *
 *
 * 示例 6：
 *
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 *
 */

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 栈 (stack)
 *
 * @author tukeping
 * @date 2020/2/10
 **/
public class LeetCode71 {

    /**
     * 254/254 cases passed (12 ms)
     * Your runtime beats 13.35 % of java submissions
     * Your memory usage beats 5.06 % of java submissions (40.4 MB)
     */
    public String simplifyPath(String path) {
        Stack<Character> stack = new Stack<>();
        char[] chars = path.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (stack.isEmpty()) {
                stack.push(c);
            } else if (c == '/') {
                char pre = stack.peek();
                if (pre == '/') {
                    // ignore c: '/'
                } else if (pre == '.') {
                    stack.pop(); // 1st pop
                    char pp = stack.peek(); // pre.pre
                    if (pp == '/') {
                        // this is ./ -> current folder -> pop ./
                    } else if (pp == '.') {
                        // this is ../ -> back to up folder
                        stack.pop(); // 2st pop
                        backUp(stack);
                    }
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }

            // finish operation
            if (i == chars.length - 1 && stack.size() > 1) {
                char last = stack.peek();
                if (last == '.') {
                    stack.pop(); // 1st pop
                    char preLast = stack.peek();
                    if (preLast == '.') {
                        stack.pop(); // 2st pop
                        backUp(stack);
                    }
                }
                last = stack.peek();
                if (last == '/' && stack.size() > 1) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            sb.append(c);
        }

        return sb.reverse().toString();
    }

    private void backUp(Stack<Character> stack) {
        int slashNum = 0;
        boolean isName = false;
        while (true) {
            if (stack.size() == 1) break;
            char x = stack.pop(); // 3st pop
            if (x == '.' && !isName) { // illegal like '...', remain it.
                stack.push('.');
                stack.push('.');
                stack.push('.');
                break;
            } else if (x == '/') {
                slashNum++;
                if (slashNum == 2) {
                    stack.push('/');
                    break;
                }
            } else {
                isName = true;
            }
        }
    }

    /**
     * 输入："/home/"
     * 输出："/home"
     * 解释：注意，最后一个目录名后面没有斜杠。
     */
    @Test
    public void test1() {
        String s = simplifyPath("/home/");
        assertThat(s, is("/home"));
    }

    /**
     * 输入："/../"
     * 输出："/"
     * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
     */
    @Test
    public void test2() {
        String s = simplifyPath("/../");
        assertThat(s, is("/"));
    }

    /**
     * 输入："/home//foo/"
     * 输出："/home/foo"
     * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
     */
    @Test
    public void test3() {
        String s = simplifyPath("/home//foo/");
        assertThat(s, is("/home/foo"));
    }

    /**
     * 输入："/a/./b/../../c/"
     * 输出："/c"
     */
    @Test
    public void test4() {
        String s = simplifyPath("/a/./b/../../c/");
        assertThat(s, is("/c"));
    }

    /**
     * 输入："/a/../../b/../c//.//"
     * 输出："/c"
     */
    @Test
    public void test5() {
        String s = simplifyPath("/a/../../b/../c//.//");
        assertThat(s, is("/c"));
    }

    /**
     * 输入："/a//b////c/d//././/.."
     * 输出："/a/b/c"
     */
    @Test
    public void test6() {
        String s = simplifyPath("/a//b////c/d//././/..");
        assertThat(s, is("/a/b/c"));
    }

    /**
     * 输入: "/..."
     * 输出: "/..."
     */
    @Test
    public void test7() {
        String s = simplifyPath("/...");
        assertThat(s, is("/..."));
    }

    /**
     * 输入: "/home/foo/.ssh/../.ssh2/authorized_keys/"
     * 输出: "/home/foo/.ssh2/authorized_keys"
     */
    @Test
    public void test8() {
        String s = simplifyPath("/home/foo/.ssh/../.ssh2/authorized_keys/");
        assertThat(s, is("/home/foo/.ssh2/authorized_keys"));
    }

    /**
     * 输入: "/."
     * 输出: "/"
     */
    @Test
    public void test9() {
        String s = simplifyPath("/.");
        assertThat(s, is("/"));
    }
}
