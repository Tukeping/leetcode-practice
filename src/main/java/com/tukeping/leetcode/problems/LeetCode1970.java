package com.tukeping.leetcode.problems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/8/17
 **/
public class LeetCode1970 {

    public int latestDayToCross(int row, int col, int[][] cells) {
        int[] dirs = new int[]{-1, 0, 1, 0, -1};
        int left = 0, right = row * col;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            boolean[][] invalid = new boolean[row][col];
            for (int i = 0; i < mid; i++)
                invalid[cells[i][0] - 1][cells[i][1] - 1] = true;

            Queue<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < col; i++) {
                if (!invalid[0][i]) {
                    q.offer(new int[]{0, i});
                    invalid[0][i] = true;
                }
            }

            boolean found = false;
            while (!q.isEmpty()) {
                int[] node = q.poll();
                int x = node[0], y = node[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d], ny = y + dirs[d + 1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col && !invalid[nx][ny]) {
                        if (nx == row - 1) {
                            found = true;
                            break;
                        }
                        q.offer(new int[]{nx, ny});
                        invalid[nx][ny] = true;
                    }
                }
            }

            if (found) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public int latestDayToCrossUF(int row, int col, int[][] cells) {
        int n = row * col;
        UF uf = new UF(n + 2);
        boolean[][] valid = new boolean[row][col];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = cells[i][0] - 1, y = cells[i][1] - 1;
            valid[x][y] = true;
            int id = x * col + y;
            if (x - 1 >= 0 && valid[x - 1][y]) {
                uf.union(id, id - col);
            }
            if (x + 1 < row && valid[x + 1][y]) {
                uf.union(id, id + col);
            }
            if (y - 1 >= 0 && valid[x][y - 1]) {
                uf.union(id, id - 1);
            }
            if (y + 1 < col && valid[x][y + 1]) {
                uf.union(id, id + 1);
            }
            if (x == 0) {
                uf.union(id, n);
            }
            if (x == row - 1) {
                uf.union(id, n + 1);
            }
            if (uf.connected(n, n + 1)) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    class UF {
        private final int[] id;
        private final int[] sz;

        public UF(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        private int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }

        public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            if (i == j) return;
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }
    }
}
