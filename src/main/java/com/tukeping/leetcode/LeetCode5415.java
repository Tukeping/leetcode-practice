package com.tukeping.leetcode;

/**
 * @author tukeping
 * @date 2020/5/17
 **/
public class LeetCode5415 {

//    public int numPoints(int[][] points, int r) {
//        boolean debug = true;
//
//        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
//        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
//
//        for (int[] p : points) {
//            if (p[0] < minX) minX = p[0];
//            if (p[1] < minY) minY = p[1];
//            if (p[0] > maxX) maxX = p[0];
//            if (p[1] > maxY) maxY = p[1];
//        }
//
//        if (debug) {
//            System.out.println("minX = " + minX + ", minY = " + minY + ", maxX = " + maxX + ", maxY = " + maxY);
//        }
//
//        int[] offset = new int[2];
//        if (minX < 0) {
//            offset[0] = -minX;
//            minX = 0;
//        }
//        if (minY < 0) {
//            offset[1] = -minY;
//            minY = 0;
//        }
//
//        if (debug) {
//            System.out.println("offset = [" + offset[0] + ", " + offset[1] + "]" + ", minX = " + minX + ", minY = " + minY);
//        }
//
//        for (int[] p : points) {
//            p[0] += offset[0];
//            p[1] += offset[1];
//        }
//
//        if (debug) {
//            for (int[] p : points) {
//                System.out.println(p[0] + ", " + p[1]);
//            }
//        }
//
//        int[][] f = new int[maxX][maxY];
//
//        double rd = (double) r;
//
//        int ans = 0;
//        for (int i = 0; i <= maxX; i++) {
//            for (int j = 0; j <= maxY; j++) {
//                int cnt = 0;
//                for (int[] p : points) {
//                    double dist = Math.pow(Math.pow(i - p[0], 2) + Math.pow(j - p[1], 2), 0.5);
//                    if (dist <= rd) {
//                        cnt++;
//                    }
//                }
//                ans = Math.max(ans, cnt);
//                if (debug) System.out.println("cnt = " + cnt + ", ans = " + ans + ", [" + i + "," + j + "]");
//            }
//        }
//        return ans;
//    }

    double prec = 1e-8;

    public int numPoints(int[][] points, int r) {
        int n = points.length;
        if (n == 1) {
            return 1;
        }

        int ans = count(points, points[0][0], points[0][1], r);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    continue;
                }
                double mx = (points[i][0] + points[j][0]) / 2.0;
                double my = (points[i][1] + points[j][1]) / 2.0;
                double dirX = points[i][0] - points[j][0];
                double dirY = points[i][1] - points[j][1];
                double sqr = Math.sqrt(dirX * dirX + dirY * dirY);
                dirX /= sqr;
                dirY /= sqr;
                double h = Math.sqrt(r * r - sqr / 2 * sqr / 2);
                double moveX = -dirY;
                double moveY = dirX;

                double centerX = mx + moveX * h;
                double centerY = my + moveY * h;
                int cnt = count(points, centerX, centerY, r);
                ans = Math.max(ans, cnt);
            }
        }

        return ans;
    }

    public int count(int[][] points, double x, double y, int r) {
        int ans = 0;
        for (int[] pt : points) {
            double dx = x - pt[0];
            double dy = y - pt[1];
            if (dx * dx + dy * dy <= r * r + prec) {
                ans++;
            }
        }
        return ans;
    }
}
