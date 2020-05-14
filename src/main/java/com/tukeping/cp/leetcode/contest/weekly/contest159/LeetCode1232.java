package com.tukeping.cp.leetcode.contest.weekly.contest159;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/14
 **/
public class LeetCode1232 {

    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;
        if (len == 2)
            return true;

        double b = Math.abs(coordinates[0][0] - coordinates[1][0]);
        double c = Math.abs(coordinates[0][1] - coordinates[1][1]);
        double a = Math.sqrt(b * b + c * c);
        double cos = (a * a + b * b - c * c) / (2 * a * b);
        int cosInt = (int) (cos * 10000);

        for (int i = 2; i < len; i++) {
            b = Math.abs(coordinates[i][0] - coordinates[0][0]);
            c = Math.abs(coordinates[i][1] - coordinates[0][1]);
            a = Math.sqrt(b * b + c * c);
            double cos0 = (a * a + b * b - c * c) / (2 * a * b);
            int cos0Int = (int) (cos0 * 10000);
            if (cosInt != cos0Int) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
     * 输出：true
     */
    @Test
    public void test1() {
        int[][] coord = {
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 7}
        };
        assertTrue(checkStraightLine(coord));
    }

    /**
     * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
     * 输出：false
     */
    @Test
    public void test2() {
        int[][] coord = {
                {1, 1},
                {2, 2},
                {3, 4},
                {4, 5},
                {5, 6},
                {7, 7}
        };
        assertFalse(checkStraightLine(coord));
    }
}
