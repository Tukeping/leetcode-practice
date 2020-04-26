package com.tukeping.book.sword_means_offer;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/3/30
 **/
public class LCOF12 {

    private char[][] board;
    private String word;
    private int wordLen;
    private int m, n;
    private int[][] coord = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private boolean[][] marked;

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) return false;

        this.board = board;
        this.word = word;
        this.wordLen = word.length();
        this.m = board.length;
        this.n = board[0].length;
        this.marked = new boolean[m][n];

        for (int x = 0; x < this.m; x++) {
            for (int y = 0; y < this.n; y++) {
                if (findWord(x, y, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findWord(int x, int y, int matchIdx) {
        if (matchIdx == wordLen - 1) {
            return board[x][y] == word.charAt(matchIdx);
        }

        if (board[x][y] == word.charAt(matchIdx)) {
            marked[x][y] = true;
            for (int d = 0; d < 4; d++) {
                int sx = x + coord[d][0];
                int sy = y + coord[d][1];
                if (inBoard(sx, sy) && !marked[sx][sy]) {
                    if (findWord(sx, sy, matchIdx + 1)) {
                        return true;
                    }
                }
            }
            marked[x][y] = false;
        }
        return false;
    }

    private boolean inBoard(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    /**
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true.
     * 给定 word = "SEE", 返回 true.
     * 给定 word = "ABCB", 返回 false.
     */
    @Test
    public void test1() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertTrue(exist(board, "ABCCED"));
    }

    @Test
    public void test2() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertTrue(exist(board, "SEE"));
    }

    @Test
    public void test3() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertFalse(exist(board, "ABCB"));
    }

    /**
     * 输入：board = [["a","b"],["c","d"]], word = "abcd"
     * 输出：false
     */
    @Test
    public void test4() {
        char[][] board = {
                {'a', 'b'},
                {'c', 'd'}
        };
        assertFalse(exist(board, "abcd"));
    }

    @Test
    public void test5() {
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
        };
        assertTrue(exist(board, "AAB"));
    }
}
