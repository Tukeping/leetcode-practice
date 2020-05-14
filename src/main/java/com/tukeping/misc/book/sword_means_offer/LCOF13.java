package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF13 {

    private int m, n, k;
    private boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        visited = new boolean[m][n];
        return movingCount(0, 0, 0, 0);
    }

    private int movingCount(int i, int j, int si, int sj) {
        if (invalidCoord(i, j) || si + sj > k || visited[i][j]) return 0;
        visited[i][j] = true;
        int R = movingCount(i + 1, j, sumEveryNum(i + 1, si), sj);
        int B = movingCount(i, j + 1, si, sumEveryNum(j + 1, sj));
        return 1 + R + B;
    }

    private boolean invalidCoord(int i, int j) {
        return i < 0 || i >= m || j < 0 || j >= n;
    }

    private int sumEveryNum(int x, int sx) {
        return x % 10 == 0 ? sx - 8 : sx + 1;
    }

    public int movingCount2(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 0});
        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if (i < 0 || i >= m || j < 0 || j >= n || k < si + sj || visited[i][j]) continue;
            visited[i][j] = true;
            res++;
            queue.add(new int[]{i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj});
            queue.add(new int[]{i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8});
        }
        return res;
    }

//    private int m, n, k;
//    private boolean[][] visited;
//
//    public int movingCount1(int m, int n, int k) {
//        this.m = m;
//        this.n = n;
//        this.k = k;
//        this.visited = new boolean[m][n];
//        return dfs(0, 0, 0, 0);
//    }
//
//    public int dfs(int i, int j, int si, int sj) {
//        if (i < 0 || i >= m || j < 0 || j >= n || k < si + sj || visited[i][j]) return 0;
//        visited[i][j] = true;
//        int left = dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj);
//        int right = dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
//        return 1 + left + right;
//    }

    @Test
    public void test1() {
        int n = movingCount(16, 8, 4);
        assertThat(n, is(15));
    }

    @Test
    public void test2() {
        int n = movingCount(2, 3, 1);
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        int n = movingCount(3, 1, 0);
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int n = movingCount(38, 15, 9);
        assertThat(n, is(135));
    }
}
