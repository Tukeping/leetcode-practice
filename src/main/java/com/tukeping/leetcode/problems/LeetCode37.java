package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=37 lang=java
 *
 * [37] 解数独
 *
 * https://leetcode-cn.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (58.37%)
 * Likes:    314
 * Dislikes: 0
 * Total Accepted:    18.6K
 * Total Submissions: 31.3K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 *
 * 空白格用 '.' 表示。
 *
 *
 *
 * 一个数独。
 *
 *
 *
 * 答案被标成红色。
 *
 * Note:
 *
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table | backtracking
 *
 * snapchat | uber
 *
 * @author tukeping
 * @date 2020/3/21
 **/
public class LeetCode37 {

    // box size
    int n = 3;
    // row size
    int N = n * n;

    int[][] rows = new int[N][N + 1];
    int[][] columns = new int[N][N + 1];
    int[][] boxes = new int[N][N + 1];

    char[][] board;

    boolean sudokuSolved = false;

    public boolean couldPlace(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char) (d + '0');
    }

    public void removeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    public void placeNextNumbers(int row, int col) {
        // if we're in the last cell
        // that means we have the solution
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        }
        // if not yet
        else {
            // if we're in the end of the row
            // go to the next row
            if (col == N - 1) backtrack(row + 1, 0);
                // go to the next column
            else backtrack(row, col + 1);
        }
    }

    public void backtrack(int row, int col) {
        // if the cell is empty
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    // if sudoku is solved, there is no need to backtrack
                    // since the single unique solution is promised
                    if (!sudokuSolved) removeNumber(d, row, col);
                }
            }
        } else placeNextNumbers(row, col);
    }

    public void solveSudoku(char[][] board) {
        this.board = board;

        // init rows, columns and boxes
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrack(0, 0);
    }

    private List<Character> origin = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');

    public void solveSudoku2(char[][] board) {
        int n = 9;
        List<Character> option;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    option = new ArrayList<>(origin);

                    // 横线上不能有重复数字
                    for (int k = 0; k < n; k++) {
                        if (board[i][k] != '.') {
                            option.remove((Character) board[i][k]);
                        }
                    }

                    // 竖线上不能有重复数字
                    for (int k = 0; k < n; k++) {
                        if (board[k][j] != '.') {
                            option.remove((Character) board[k][j]);
                        }
                    }

                    // 3*3方格内不能有重复数字
                    int rs = i / 3 * 3, re = rs + 3, cs = j / 3 * 3, ce = cs + 3;
                    for (int x = rs; x < re; x++) {
                        for (int y = cs; y < ce; y++) {
                            if (board[x][y] != '.') {
                                option.remove((Character) board[x][y]);
                            }
                        }
                    }


                }
            }
        }
    }

    private void backtracking(char[][] board, int n, int row, int col, List<Integer> option, int start) {
        if(row == n - 1 && col == n - 1 && !option.isEmpty()) return;

        for (int i = start; i < option.size(); i++) {

        }

    }

    private void findNext(char[][] board, int n, int row, int column, List<Character> option) {
        if (option.size() == 1) return;
        findRowNext(board, n, row, column, option);
        if (option.size() == 1) return;
        findColumnNext(board, n, row, column, option);
        if (option.size() == 1) return;
        findMatrixNext(board, n, row, column, option);
    }

    private void findRowNext(char[][] board, int n, int row, int column, List<Character> option) {
        for (int col = column + 1; col < n; col++) {
            if (board[row][col] == '.') {
                filterOption(board, n, row, col, option);
                if (option.size() == 1) return;
            }
        }
    }

    private void findColumnNext(char[][] board, int n, int row, int column, List<Character> option) {
        for (int r = row + 1; r < n; r++) {
            if (board[r][column] == '.') {
                filterOption(board, n, r, column, option);
                if (option.size() == 1) return;
            }
        }
    }

    private void findMatrixNext(char[][] board, int n, int row, int column, List<Character> option) {
        int rs = row / 3 * 3, re = rs + 3, cs = column / 3 * 3, ce = cs + 3;
        for (int x = rs; x < re; x++) {
            for (int y = cs; y < ce; y++) {
                if (row == x && column == y) continue;
                if (board[x][y] == '.') {
                    filterOption(board, n, x, y, option);
                    if (option.size() == 1) return;
                }
            }
        }
    }

    private void filterOption(char[][] board, int n, int row, int column, List<Character> option) {
        List<Character> current = new ArrayList<>(origin);
        filterOptionByRow(board, n, row, current);
        filterOptionByColumn(board, n, column, current);
        filterOptionByMatrix(board, n, row, column, current);
        if (!current.containsAll(option)) option.removeAll(current);
    }

    private void filterOptionByRow(char[][] board, int n, int row, List<Character> option) {
        // 横线上不能有重复数字
        for (int k = 0; k < n; k++) {
            if (board[row][k] != '.') {
                option.remove((Character) board[row][k]);
            }
        }
    }

    private void filterOptionByColumn(char[][] board, int n, int column, List<Character> option) {
        // 竖线上不能有重复数字
        for (int k = 0; k < n; k++) {
            if (board[k][column] != '.') {
                option.remove((Character) board[k][column]);
            }
        }
    }

    private void filterOptionByMatrix(char[][] board, int n, int row, int column, List<Character> option) {
        // 3*3方格内不能有重复数字
        int rs = row / 3 * 3, re = rs + 3, cs = column / 3 * 3, ce = cs + 3;
        for (int x = rs; x < re; x++) {
            for (int y = cs; y < ce; y++) {
                if (board[x][y] != '.') {
                    option.remove((Character) board[x][y]);
                }
            }
        }
    }

    @Test
    public void test1() {
        char[][] matrix = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(matrix);
        char[][] expect = {
                {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
                {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
        };
        assertThat(matrix, is(expect));
    }
}
