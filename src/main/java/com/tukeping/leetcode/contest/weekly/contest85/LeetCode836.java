package com.tukeping.leetcode.contest.weekly.contest85;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode836 {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2])
                && Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);
    }

    public boolean isRectangleOverlap3(int[] rec1, int[] rec2) {
        // rec2 is on top of rec1
        // rec2.y1 >= rec1.y2
        boolean isTop = rec2[1] >= rec1[3];
        // rec2 is on bottom of rec1
        // rec2.y2 <= rec1.y1
        boolean isBottom = rec2[3] <= rec1[1];
        // rec2 is on right of rec1
        // rec2.x1 >= rec1.x2
        boolean isRight = rec2[0] >= rec1[2];
        // rec2 is on left of rec1
        // rec2.x2 <= rec1.x1
        boolean isLeft = rec2[2] <= rec1[0];

        return !isTop && !isBottom && !isRight && !isLeft;
    }

    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        double rec1Width = abs(rec1[0] - rec1[2]) / 2.0; // 矩形1宽
        double rec1Height = abs(rec1[1] - rec1[3]) / 2.0; // 矩形1长
        double rec1Oblique = sqrt(d(rec1Width) + d(rec1Height)); // 矩形1斜线长度
        double rec2Width = abs(rec2[0] - rec2[2]) / 2.0; // 矩形2宽
        double rec2Height = abs(rec2[1] - rec2[3]) / 2.0; //矩形2长
        double rec2Oblique = sqrt(d(rec2Width) + d(rec2Height)); // 矩形2斜线长度
        double[] rec1Center = {rec1[0] + rec1Width, rec1[1] + rec1Height}; // 矩形1 中心点
        double[] rec2Center = {rec2[0] + rec2Width, rec2[1] + rec2Height}; // 矩形2 中心点
        // 矩形1中心点 到 矩形2中心点 的直线距离
        double cc = sqrt(d(abs(rec1Center[0] - rec2Center[0])) + d(abs(rec1Center[1] - rec2Center[1])));
        double recW2 = rec1Width + rec2Width; // 矩形1和矩形2 两个宽的长度
        double recH2 = rec1Height + rec2Height; // 矩形1和矩形2 两个长的长度
        double recWH = rec1Width + rec2Height; // 矩形1和矩形2 宽和长连接后的长度
        double recOO = rec1Oblique + rec2Oblique; // 矩形1和矩形2 斜线连接后的长度
        return cc < recW2 || cc < recH2 || cc < recWH || cc < recOO;
    }

    private double abs(double x) {
        return Math.abs(x);
    }

    private double sqrt(double x) {
        return Math.sqrt(x);
    }

    private double d(double x) {
        return x * x;
    }

    /**
     * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
     * 输出：true
     */
    @Test
    public void test1() {
        assertTrue(isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
    }

    /**
     * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
     * 输出：false
     */
    @Test
    public void test2() {
        assertFalse(isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
    }

    @Test
    public void test3() {
        assertTrue(isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 2, 3}));
    }

    @Test
    public void test4() {
        assertFalse(isRectangleOverlap(new int[]{-5, 8, 0, 8}, new int[]{-5, 4, 5, 5}));
    }

    @Test
    public void test5() {
        assertTrue(isRectangleOverlap(new int[]{2, 17, 6, 20}, new int[]{3, 8, 6, 20}));
    }
}
