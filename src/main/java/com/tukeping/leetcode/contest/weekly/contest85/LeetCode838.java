package com.tukeping.leetcode.contest.weekly.contest85;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode838 {

    public String pushDominoes(String dominoes) {
        char[] s = dominoes.toCharArray();
        int n = s.length;
        char pre = 0;
        int prepos = -1;
        for (int i = 0; i < n; i++) {
            if (s[i] == 'L') {
                if (pre == 'R') {
                    int K = (prepos + i + 1) / 2 - prepos;
                    for (int j = 0; j < K; j++) {
                        s[prepos + j] = 'R';
                        s[i - j] = 'L';
                    }
                } else {
                    for (int j = prepos + 1; j <= i; j++) {
                        s[j] = 'L';
                    }
                }
                prepos = i;
                pre = 'L';
            } else if (s[i] == 'R') {
                if (pre == 'R') {
                    for (int j = prepos; j < i; j++) {
                        s[j] = 'R';
                    }
                }
                pre = 'R';
                prepos = i;
            }
        }
        if (pre == 'R') {
            for (int i = prepos; i < n; i++) s[i] = 'R';
        }
        return new String(s);
    }

    class Node {
        char c;
        int idx;

        public Node(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }

    public String pushDominoes2(String dominoes) {
        int len = dominoes.length();
        Stack<Node> stack = new Stack<>();
        char[] chars = dominoes.toCharArray();
        for (int i = 0; i < len; i++) {
            char c = chars[i];
            if (c == 'L') {
                if (stack.isEmpty()) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (chars[k] == '.') {
                            chars[k] = 'L';
                        } else {
                            break;
                        }
                    }
                } else {
                    Node node = stack.peek();
                    if (node.c == 'R') {
                        stack.pop();
                        for (int p1 = node.idx + 1, p2 = i - 1; p1 <= p2; p1++, p2--) {
                            if (p1 == p2) {
                                chars[p1] = '.';
                            } else {
                                chars[p1] = 'R';
                                chars[p2] = 'L';
                            }
                        }
                    }
                }
            } else if (c == 'R') {
                if (!stack.isEmpty()) {
                    Node node = stack.peek();
                    if (node.c == 'R') {
                        stack.pop();
                        for (int k = i - 1; k >= node.idx + 1; k--) {
                            if (chars[k] == '.') {
                                chars[k] = 'R';
                            } else {
                                break;
                            }
                        }
                    }
                }
                stack.push(new Node('R', i));
            }
        }

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.c == 'R') {
                for (int i = node.idx + 1; i < len; i++) {
                    if (chars[i] == '.') {
                        chars[i] = 'R';
                    } else {
                        break;
                    }
                }
            }
        }
        return new String(chars);
    }

    /**
     * 输入：".L.R...LR..L.."
     * 输出："LL.RR.LLRRLL.."
     */
    @Test
    public void test1() {
        String ans = pushDominoes(".L.R...LR..L..");
        assertThat(ans, is("LL.RR.LLRRLL.."));
    }

    /**
     * 输入："RR.L"
     * 输出："RR.L"
     * 说明：第一张多米诺骨牌没有给第二张施加额外的力。
     */
    @Test
    public void test2() {
        String ans = pushDominoes("RR.L");
        assertThat(ans, is("RR.L"));
    }

    @Test
    public void test3() {
        String ans = pushDominoes("LL");
        assertThat(ans, is("LL"));
    }

    @Test
    public void test5() {
        String ans = pushDominoes("L.....RR.RL.....L.R.");
        assertThat(ans, is("L.....RRRRLLLLLLL.RR"));
    }
}
