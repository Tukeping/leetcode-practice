package com.tukeping.cp.leetcode.contest.biweekly.contest23;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/5
 **/
public class LeetCode5361 {

    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        double cc; // 圆中心到矩形中心 的长度
        double rx; // 圆半径+矩形斜线 的总长度
        double rw; // 圆半径+矩形宽 的总长度
        double rl; // 圆半径+矩形长 的总长度

        double recWidth = Math.abs(x2 - x1); // 矩形宽
        double recHeight = Math.abs(y2 - y1); // 矩形长
        double[] rc = new double[]{x1 + recWidth / 2.0, y1 + recHeight / 2.0}; // 矩形中心点坐标

        cc = sqrt(d(abs(x_center - rc[0])) + d(abs(y_center - rc[1])));
        rw = radius + recWidth / 2.0;
        rl = radius + recHeight / 2.0;
        rx = radius + sqrt(d(abs(rc[0] - x1)) + d(abs(rc[1] - y1)));

        return cc <= rw || cc <= rl || cc <= rx;
    }

    private double sqrt(double x) {
        return Math.sqrt(x);
    }

    private double abs(double x) {
        return Math.abs(x);
    }

    private double d(double x) {
        return x * x;
    }

    /**
     * 输入：radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
     * 输出：true
     * 解释：圆和矩形有公共点 (1,0)
     */
    @Test
    public void test1() {
        assertTrue(checkOverlap(1, 0, 0, 1, -1, 3, 1));
    }

    /**
     * 输入：radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
     * 输出：true
     */
    @Test
    public void test2() {
        assertTrue(checkOverlap(1, 0, 0, -1, 0, 0, 1));
    }

    /**
     * 输入：radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3
     * 输出：true
     */
    @Test
    public void test3() {
        assertTrue(checkOverlap(1, 1, 1, -3, -3, 3, 3));
    }

    /**
     * 输入：radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
     * 输出：false
     */
    @Test
    public void test4() {
        assertFalse(checkOverlap(1, 1, 1, 1, -3, 2, -1));
    }
}
