package com.tukeping.leetcode.problems;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * depth-first-search | union-find | graph
 *
 * @author tukeping
 * @date 2020/3/26
 **/
public class LeetCode999 {

    public int numRookCaptures(char[][] board) {
        int n = 8;
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    i = n;
                    break;
                }
            }
        }
        int[][] coord = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0, attack = 4, cnt = 0;
        while (attack > 0) {
            int sx = x + coord[d][0], sy = y + coord[d][1];
            while (0 <= sx && sx < n && 0 <= sy && sy < n) {
                if (board[sx][sy] == 'p') {
                    cnt++;
                    break;
                } else if (board[sx][sy] == 'B') {
                    break;
                }
                sx += coord[d][0];
                sy += coord[d][1];
            }
            d = (d + 1) % 4;
            attack--;
        }
        return cnt;
    }

    /**
     * 输入：
     *  [
     *      [".",".",".",".",".",".",".","."],
     *      [".",".",".","p",".",".",".","."],
     *      [".",".",".","R",".",".",".","p"],
     *      [".",".",".",".",".",".",".","."],
     *      [".",".",".",".",".",".",".","."],
     *      [".",".",".","p",".",".",".","."],
     *      [".",".",".",".",".",".",".","."],
     *      [".",".",".",".",".",".",".","."]
     *  ]
     * 输出：3
     * 解释：
     * 在本例中，车能够捕获所有的卒。
     */
    @Test
    public void test1() {
        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
        int n = numRookCaptures(board);
        assertThat(n, is(3));
    }

    /**
     * 输入：
     *  [
     *      [".",".",".",".",".",".",".","."],
     *      [".","p","p","p","p","p",".","."],
     *      [".","p","p","B","p","p",".","."],
     *      [".","p","B","R","B","p",".","."],
     *      [".","p","p","B","p","p",".","."],
     *      [".","p","p","p","p","p",".","."],
     *      [".",".",".",".",".",".",".","."],
     *      [".",".",".",".",".",".",".","."]
     *  ]
     * 输出：0
     * 解释：
     * 象阻止了车捕获任何卒。
     */
    @Test
    public void test2() {
        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
        int n = numRookCaptures(board);
        assertThat(n, is(0));
    }

    /**
     * 输入：
     *  [
     *      [".",".",".",".",".",".",".","."],
     *      [".",".",".","p",".",".",".","."],
     *      [".",".",".","p",".",".",".","."],
     *      ["p","p",".","R",".","p","B","."],
     *      [".",".",".",".",".",".",".","."],
     *      [".",".",".","B",".",".",".","."],
     *      [".",".",".","p",".",".",".","."],
     *      [".",".",".",".",".",".",".","."]
     *  ]
     * 输出：3
     * 解释：
     * 车可以捕获位置 b5，d6 和 f5 的卒。
     */
    @Test
    public void test3() {
        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'p', 'p', '.', 'R', '.', 'p', 'B', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
        int n = numRookCaptures(board);
        assertThat(n, is(3));
    }
}
