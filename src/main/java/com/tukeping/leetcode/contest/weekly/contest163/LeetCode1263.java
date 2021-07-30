package com.tukeping.leetcode.contest.weekly.contest163;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/28
 **/
public class LeetCode1263 {

    char[][] grid;
    int n, m;
    int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;

        int[] S = {-1, -1};
        int[] B = {-1, -1};
        int[] T = {-1, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'S') {
                    S = new int[]{i, j};
                } else if (grid[i][j] == 'B') {
                    B = new int[]{i, j};
                } else if (grid[i][j] == 'T') {
                    T = new int[]{i, j};
                }

                if (S[0] != -1 && B[0] != -1 && T[0] != -1) break;
            }
        }

        return bfs0(S, B, T, new boolean[n][m][n][m]);
    }

    private int bfs0(int[] S, int[] B, int[] T, boolean[][][][] visited) {
        Deque<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{0, S[0], S[1], B[0], B[1]});

        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            int moves = cur[0];
            int sx = cur[1], sy = cur[2];
            int bx = cur[3], by = cur[4];

            if (bx == T[0] && by == T[1]) return moves;
            if (visited[sx][sy][bx][by]) continue;
            visited[sx][sy][bx][by] = true;

            for (int[] r : d) {
                int x = sx + r[0], y = sy + r[1];
                if (invalid(x, y)) continue;

                int[] next;
                if (x == bx && y == by) {
                    int bxMove = bx + r[0], byMove = by + r[1];
                    if (invalid(bxMove, byMove)) continue;
                    next = new int[]{moves + 1, x, y, bxMove, byMove};
                    Q.addLast(next);
                } else {
                    next = new int[]{moves, x, y, bx, by};
                    Q.addFirst(next);
                }
            }
        }
        return -1;
    }

    private int bfs(int[] S, int[] B, int[] T, boolean[][][][] visited) {
        PriorityQueue<int[]> Q = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        Q.offer(new int[]{heuristic(B[0], B[1], T), 0, S[0], S[1], B[0], B[1]});

        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            int moves = cur[1];
            int sx = cur[2], sy = cur[3];
            int bx = cur[4], by = cur[5];

            if (bx == T[0] && by == T[1]) return moves;
            if (visited[sx][sy][bx][by]) continue;
            visited[sx][sy][bx][by] = true;

            for (int[] r : d) {
                int x = sx + r[0], y = sy + r[1];
                if (invalid(x, y)) continue;

                int[] next;
                if (x == bx && y == by) {
                    int bxMove = bx + r[0], byMove = by + r[1];
                    if (invalid(bxMove, byMove)) continue;
                    next = new int[]{heuristic(bxMove, byMove, T) + moves + 1, moves + 1, x, y, bxMove, byMove};
                } else {
                    next = new int[]{moves, moves, x, y, bx, by};
                }
                Q.offer(next);
            }
        }
        return -1;
    }

    private int heuristic(int bx, int by, int[] T) {
        return Math.abs(bx - T[0]) + Math.abs(by - T[1]);
    }

    private boolean invalid(int i, int j) {
        return !inGrid(i, j) || !canMove(i, j);
    }

    private boolean inGrid(int i, int j) {
        return 0 <= i & i < n && 0 <= j && j < m;
    }

    private boolean canMove(int i, int j) {
        return grid[i][j] != '#';
    }

    /**
     * 输入：grid = [["#","#","#","#","#","#"],
     *              ["#","T","#","#","#","#"],
     *              ["#",".",".","B",".","#"],
     *              ["#",".","#","#",".","#"],
     *              ["#",".",".",".","S","#"],
     *              ["#","#","#","#","#","#"]]
     * 输出：3
     * 解释：我们只需要返回推箱子的次数。
     */
    @Test
    public void test1() {
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}};
        int n = minPushBox(grid);
        assertThat(n, is(3));
    }

    /**
     * 输入：grid = [["#","#","#","#","#","#"],
     *              ["#","T","#","#","#","#"],
     *              ["#",".",".","B",".","#"],
     *              ["#","#","#","#",".","#"],
     *              ["#",".",".",".","S","#"],
     *              ["#","#","#","#","#","#"]]
     * 输出：-1
     */
    @Test
    public void test2() {
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '#', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}};
        int n = minPushBox(grid);
        assertThat(n, is(-1));
    }

    /**
     * 输入：grid = [["#","#","#","#","#","#"],
     *              ["#","T",".",".","#","#"],
     *              ["#",".","#","B",".","#"],
     *              ["#",".",".",".",".","#"],
     *              ["#",".",".",".","S","#"],
     *              ["#","#","#","#","#","#"]]
     * 输出：5
     * 解释：向下、向左、向左、向上再向上。
     */
    @Test
    public void test3() {
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '.', '.', '#', '#'},
                {'#', '.', '#', 'B', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}};
        int n = minPushBox(grid);
        assertThat(n, is(5));
    }

    /**
     * 输入：grid = [["#","#","#","#","#","#","#"],
     *              ["#","S","#",".","B","T","#"],
     *              ["#","#","#","#","#","#","#"]]
     * 输出：-1
     */
    @Test
    public void test4() {
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', '.', 'B', 'T', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}};
        int n = minPushBox(grid);
        assertThat(n, is(-1));
    }

    @Test
    public void test5() {
        char[][] grid = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '#', '#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#', '#', 'T', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '#', '.', '#', '.', '.', '#', '#', '#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '.', '#', '#', '.', '#'},
                {'#', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '#', '.', '#', '#', '.', '#'},
                {'#', '.', '#', '.', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', 'B', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', 'S', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        int n = minPushBox(grid);
        assertThat(n, is(29));
    }
}
