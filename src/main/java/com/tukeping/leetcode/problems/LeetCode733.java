package com.tukeping.leetcode.problems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/7/26
 **/
public class LeetCode733 {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});

        int m = image.length;
        int n = image[0].length;
        int[] d = new int[]{1, 0, -1, 0, 1};

        boolean[][] visited = new boolean[m][n];
        while (!q.isEmpty()) {
            int[] cr = q.poll();
            int x = cr[0];
            int y = cr[1];
            if (visited[x][y]) continue;
            visited[x][y] = true;
            image[x][y] = newColor;
            for (int i = 0; i < 4; i++) {
                int sx = x + d[i];
                int sy = y + d[i + 1];
                if (sx >= 0 && sx < m && sy >= 0 && sy < n && image[sx][sy] == oldColor) {
                    image[sx][sy] = newColor;
                    q.offer(new int[]{sx, sy});
                }
            }
        }
        return image;
    }
}
