package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=36 lang=java
 *
 * [36] 有效的数独
 *
 * https://leetcode-cn.com/problems/valid-sudoku/description/
 *
 * algorithms
 * Medium (58.75%)
 * Likes:    286
 * Dislikes: 0
 * Total Accepted:    61.2K
 * Total Submissions: 104.2K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 *
 *
 *
 * 上图是一个部分填充的有效的数独。
 *
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 示例 1:
 *
 * 输入:
 * [
 * ⁠ ["5","3",".",".","7",".",".",".","."],
 * ⁠ ["6",".",".","1","9","5",".",".","."],
 * ⁠ [".","9","8",".",".",".",".","6","."],
 * ⁠ ["8",".",".",".","6",".",".",".","3"],
 * ⁠ ["4",".",".","8",".","3",".",".","1"],
 * ⁠ ["7",".",".",".","2",".",".",".","6"],
 * ⁠ [".","6",".",".",".",".","2","8","."],
 * ⁠ [".",".",".","4","1","9",".",".","5"],
 * ⁠ [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 *
 *
 * 示例 2:
 *
 * 输入:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 * ⁠    但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *
 * 说明:
 *
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * hash-table
 *
 * apple | snapchat | uber
 *
 * @author tukeping
 * @date 2020/3/21
 **/
public class LeetCode36 {

    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> row = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            Set<Integer> set = new HashSet<>();
            row.put(i, set);
        }

        Map<Integer, Set<Integer>> col = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            Set<Integer> set = new HashSet<>();
            col.put(i, set);
        }

        Map<String, Set<Integer>> rect = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String coord = i + "" + j;
                rect.put(coord, new HashSet<>());
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cell = board[i][j];
                if (cell != '.') {
                    int cellNum = (int) (cell - '0');

                    if (row.get(i).contains(cellNum)) return false;
                    else row.get(i).add(cellNum);

                    if (col.get(j).contains(cellNum)) return false;
                    else col.get(j).add(cellNum);

                    String coord = i / 3 + "" + j / 3;
                    if (rect.get(coord).contains(cellNum)) return false;
                    else rect.get(coord).add(cellNum);
                }
            }
        }
        return true;
    }

    public boolean isValidSudokuV2(char[][] board) {
        int n = 9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char cur = board[i][j];
                if (cur != '.') {
                    // 横线上不能有重复数字
                    for (int k = 0; k < n; k++) {
                        if (k == j) continue;
                        if (board[i][k] == cur) {
                            return false;
                        }
                    }

                    // 竖线上不能有重复数字
                    for (int k = 0; k < n; k++) {
                        if (k == i) continue;
                        if (board[k][j] == cur) {
                            return false;
                        }
                    }

                    // 3*3方格内不能有重复数字
                    int rowStart = i / 3 * 3;
                    int rowEnd = i / 3 * 3 + 3;
                    int columnStart = j / 3 * 3;
                    int columnEnd = j / 3 * 3 + 3;
                    for (int x = rowStart; x < rowEnd; x++) {
                        for (int y = columnStart; y < columnEnd; y++) {
                            if (x == i && y == j) continue;
                            if (board[x][y] == cur) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
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
        assertTrue(isValidSudoku(matrix));
    }

    @Test
    public void test2() {
        char[][] matrix = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        assertFalse(isValidSudoku(matrix));
    }
}
