package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/28
 **/
public class LeetCode847 {

    public int shortestPathLength5(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[1 << n][n];
        for (int[] row : dist) Arrays.fill(row, n * n);
        for (int i = 0; i < n; i++) dist[1 << i][i] = 0;

        for (int cover = 0; cover < (1 << n); cover++) {
            boolean repeat = true;
            while (repeat) {
                repeat = false;
                for (int head = 0; head < n; head++) {
                    int d = dist[cover][head];
                    for (int next : graph[head]) {
                        int cover2 = cover | (1 << next);
                        if (d + 1 < dist[cover2][next]) {
                            dist[cover2][next] = d + 1;
                            if (cover == cover2) repeat = true;
                        }
                    }
                }
            }
        }

        int ans = n * n;
        for (int val : dist[(1 << n) - 1])
            ans = Math.min(val, ans);
        return ans;
    }

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;

        Queue<int[]> Q = new LinkedList<>();
        for (int i = 0; i < n; i++) Q.offer(new int[]{1 << i, i});

        int allCover = (1 << n) - 1;
        boolean[][] visited = new boolean[1 << n][n];

        int dist = 0;
        while (!Q.isEmpty()) {
            for (int i = Q.size(); i > 0; i--) {
                int[] cur = Q.poll();
                int cover = cur[0], head = cur[1];

                if (cover == allCover) return dist;

                for (int next : graph[head]) {
                    int newCover = cover | (1 << next);
                    if (visited[newCover][next]) continue;
                    visited[newCover][next] = true;

                    Q.offer(new int[]{newCover, next});
                }
            }
            dist++;
        }
        return -1;
    }

    class State {
        int cover;
        int head;

        public State(int cover, int head) {
            this.cover = cover;
            this.head = head;
        }
    }

    public int shortestPathLength4(int[][] graph) {
        int N = graph.length;
        Queue<State> Q = new LinkedList<>();
        int[][] dist = new int[1 << N][N];
        for (int[] row : dist) Arrays.fill(row, N * N);

        for (int x = 0; x < N; ++x) {
            Q.offer(new State(1 << x, x));
            dist[1 << x][x] = 0;
        }

        while (!Q.isEmpty()) {
            State node = Q.poll();
            int d = dist[node.cover][node.head];
            if (node.cover == (1 << N) - 1) return d;

            for (int child : graph[node.head]) {
                int cover2 = node.cover | (1 << child);
                if (d + 1 < dist[cover2][child]) {
                    dist[cover2][child] = d + 1;
                    Q.offer(new State(cover2, child));

                }
            }
        }

        return -1;
    }

    public int shortestPathLength3(int[][] graph) {
        int N = graph.length;
        int dist[][] = new int[1 << N][N];
        for (int[] row : dist) Arrays.fill(row, N * N);
        for (int x = 0; x < N; ++x) dist[1 << x][x] = 0;

        for (int cover = 0; cover < 1 << N; ++cover) {
            boolean repeat = true;
            while (repeat) {
                repeat = false;
                for (int head = 0; head < N; ++head) {
                    int d = dist[cover][head];
                    for (int next : graph[head]) {
                        int cover2 = cover | (1 << next);
                        if (d + 1 < dist[cover2][next]) {
                            dist[cover2][next] = d + 1;
                            if (cover == cover2) repeat = true;
                        }
                    }
                }
            }
        }

        int ans = N * N;
        for (int cand : dist[(1 << N) - 1])
            ans = Math.min(cand, ans);
        return ans;
    }

    public int shortestPathLength2(int[][] graph) {
        int n = graph.length;

        Queue<Tuple> Q = new LinkedList<>();
        Set<Tuple> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int tmp = (1 << i);
            set.add(new Tuple(tmp, i, 0));
            Q.add(new Tuple(tmp, i, 1));
        }

        int finishState = (1 << n) - 1;

        while (!Q.isEmpty()) {
            Tuple cur = Q.poll();
            if (cur.bitMask == finishState) {
                return cur.cost - 1;
            } else {
                int[] neighbors = graph[cur.cur];

                for (int v : neighbors) {
                    int bitMask = cur.bitMask;
                    bitMask = bitMask | (1 << v);

                    Tuple t = new Tuple(bitMask, v, 0);
                    if (!set.contains(t)) {
                        Q.add(new Tuple(bitMask, v, cur.cost + 1));
                        set.add(t);
                    }
                }
            }
        }
        return -1;
    }

    class Tuple {
        int bitMask;
        int cur;
        int cost;

        public Tuple(int bitMask, int n, int c) {
            this.bitMask = bitMask;
            this.cur = n;
            this.cost = c;
        }

        @Override
        public boolean equals(Object o) {
            Tuple p = (Tuple) o;
            return bitMask == p.bitMask &&
                    cur == p.cur &&
                    cost == p.cost;
        }

        @Override
        public int hashCode() {
            return 1331 * bitMask + 7193 * cur + 727 * cost;
        }
    }

    /**
     * 输入：[[1,2,3],[0],[0],[0]]
     * 输出：4
     * 解释：一个可能的路径为 [1,0,2,0,3]
     */
    @Test
    public void test1() {
        int[][] grid = {
                {1, 2, 3}, {0}, {0}, {0}
        };
        int n = shortestPathLength(grid);
        assertThat(n, is(4));
    }

    /**
     * 输入：[[1],[0,2,4],[1,3,4],[2],[1,2]]
     * 输出：4
     * 解释：一个可能的路径为 [0,1,4,2,3]
     */
    @Test
    public void test2() {
        int[][] grid = {
                {1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}
        };
        int n = shortestPathLength(grid);
        assertThat(n, is(4));
    }
}
