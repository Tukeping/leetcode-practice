package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/9
 **/
public class LeetCode5827 {

    public boolean checkMove(char[][] board, int r, int c, char color) {
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : dirs) {
            int dx = r + dir[0];
            int dy = c + dir[1];
            int cnt = 0;
            while (isValid(dx, dy)) {
                if (board[dx][dy] == color) {
                    if (cnt >= 1) return true;
                    else break;
                }

                if (board[dx][dy] == '.') break;

                cnt++;
                dx += dir[0];
                dy += dir[1];
            }
        }
        return false;
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }
}
