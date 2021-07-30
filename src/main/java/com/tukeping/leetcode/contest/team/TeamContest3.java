package com.tukeping.leetcode.contest.team;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/25
 **/
public class TeamContest3 {

    //        Debug debug = new Debug(true);

    public int minimalSteps2(String[] maze) {
        int n = maze.length;
        char[][] mat = new char[n][];
        for (int i = 0; i < n; i++) {
            mat[i] = maze[i].toCharArray();
        }

        int m = mat[0].length;
        List<int[]> triggers = new ArrayList<>();
        List<int[]> stones = new ArrayList<>();
        int[] start = null;
        int[] end = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 'M') {
                    triggers.add(new int[]{i, j});
                }
                if (mat[i][j] == 'O') {
                    stones.add(new int[]{i, j});
                }
                if (mat[i][j] == 'S') {
                    start = new int[]{i, j};
                }
                if (mat[i][j] == 'T') {
                    end = new int[]{i, j};
                }
            }
        }

        triggers.add(start);
        stones.add(end);
        int T = triggers.size();
        int S = stones.size();

        int[][] dist = new int[T][S];
//            int[][] ids = new int[n][m];
//            for (int i = 0; i < T; i++) {
//                int[] t = triggers.get(i);
//                ids[t[0]][t[1]] = i;
//            }
//            for (int i = 0; i < S; i++) {
//                int[] s = stones.get(i);
//                ids[s[0]][s[1]] = i;
//            }

        int[][] dirs = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        int inf = (int) 1e8;
        Deque<int[]> dq = new ArrayDeque<>(n * m);
        int[][] access = new int[n][m];
        for (int i = 0; i < T; i++) {
            dq.clear();
            for (int[] a : access) {
                Arrays.fill(a, -1);
            }
            int[] t = triggers.get(i);
            access[t[0]][t[1]] = 0;
            dq.addLast(t);
            while (!dq.isEmpty()) {
                int[] head = dq.removeFirst();
                for (int[] dir : dirs) {
                    int x = head[0] + dir[0];
                    int y = head[1] + dir[1];
                    if (x < 0 || x >= n || y < 0 || y >= m || mat[x][y] == '#' ||
                            access[x][y] != -1) {
                        continue;
                    }
                    access[x][y] = access[head[0]][head[1]] + 1;
                    dq.addLast(new int[]{x, y});
                }
            }

            for (int j = 0; j < S; j++) {
                int[] s = stones.get(j);
                if (access[s[0]][s[1]] == -1) {
                    dist[i][j] = inf;
                } else {
                    dist[i][j] = access[s[0]][s[1]];
                }
            }
        }

        int[][] move = new int[T][T];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < T; j++) {
                if (i == j) {
                    continue;
                }
                move[i][j] = inf;
                for (int k = 0; k < S - 1; k++) {
                    move[i][j] = Math.min(move[i][j], dist[i][k] + dist[j][k]);
                }
            }
        }

        int mask = (1 << (T - 1)) - 1;
        int[][] dp = new int[T][mask + 1];
        for (int i = 0; i < T; i++) {
            dp[i][0] = inf;
        }
        dp[T - 1][0] = 0;
        for (int i = 1; i <= mask; i++) {
            for (int j = 0; j < T; j++) {
                dp[j][i] = inf;
                if (bit(i, j) == 0) {
                    continue;
                }
                int remove = i ^ (1 << j);
                for (int k = 0; k < T; k++) {
                    dp[j][i] = Math.min(dp[j][i], dp[k][remove] + move[k][j]);
                }
            }
        }

        int ans = inf;
        if (T > 1) {
            for (int i = 0; i < T - 1; i++) {
                ans = Math.min(ans, dp[i][mask] + dist[i][S - 1]);
            }
        } else {
            ans = dist[0][S - 1];
        }

//            debug.debug("mat", Arrays.deepToString(mat));
//            for (int[] t : triggers) {
//                debug.debug("t", t);
//            }
//            for (int[] s : stones) {
//                debug.debug("s", s);
//            }
//            debug.debug("dist", dist);
//            debug.debug("move", move);
//            debug.debug("dp", dp);

        if (ans >= inf) {
            return -1;
        }
        return ans;
    }

    int bit(int x, int i) {
        return (x >> i) & 1;
    }

    private int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private char[][] grid;
    private int n, m;
    private boolean[][] used;

    public int minimalSteps(String[] maze) {
        this.n = maze.length;
        if (n == 1) return -1;

        this.m = maze[0].length();

        this.grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = maze[i].toCharArray();
        }

        this.used = new boolean[n][m];

        int[] S = new int[]{-1, -1};
        int[] T = new int[]{-1, -1};

        int OCnt = 0;
        int MCnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = grid[i][j];

                if (!canReached(i, j)) {
                    if (c == 'M') return -1;
                    else continue;
                }

                if (c == 'S') {
                    S = new int[]{i, j};
                } else if (c == 'T') {
                    T = new int[]{i, j};
                } else if (c == 'O') {
                    OCnt++;
                } else if (c == 'M') {
                    MCnt++;
                }
            }
        }

        if (S[0] == -1 || T[0] == -1 || (MCnt > 0 && OCnt == 0)) return -1;

        int totalSteps = 0;
        if (MCnt == 0) {
            // S -> T
            int path = getMinPath(S, T, new boolean[n][m]);
            if (path == -1) return -1;
            totalSteps += path;
        } else {
            // S -> O
            // {
            //   O -> M
            //   M -> O
            //   ...
            // }
            // M -> T
            int[] lastPos = new int[]{S[0], S[1]};
            while (MCnt > 0) {
                int[] info = getMinPath(lastPos, 'O');
                int path = info[0];
                if (path == -1) return -1;
//                System.out.println(String.format("[%d, %d] => [%d, %d] : %d",
//                        lastPos[0], lastPos[1], info[1], info[2], path));
                lastPos = new int[]{info[1], info[2]};

                totalSteps += path;

                info = getMinPath(lastPos, 'M');
                path = info[0];
                if (path == -1) return -1;
//                System.out.println(String.format("[%d, %d] => [%d, %d] : %d",
//                        lastPos[0], lastPos[1], info[1], info[2], path));
                lastPos = new int[]{info[1], info[2]};

                totalSteps += path;

                used[lastPos[0]][lastPos[1]] = true;

                MCnt--;
            }

            int path = getMinPath(lastPos, T, new boolean[n][m]);
            if (path == -1) return -1;
            System.out.println(String.format("[%d, %d] => [%d, %d] : %d",
                    lastPos[0], lastPos[1], T[0], T[1], path));
            totalSteps += path;
        }

        return totalSteps;
    }

    private int[] getMinPath(int[] start, char target) {
        Queue<int[]> Q = new LinkedList<>();
        Q.add(start);

        int path = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            path++;
            while (size-- > 0) {
                int[] pos = Q.poll();
                if (pos == null) continue;

                for (int[] r : d) {
                    int si = pos[0] + r[0], sj = pos[1] + r[1];
                    if (!inGrid(si, sj)) continue;
                    if (!canWalk(si, sj)) continue;

                    if (grid[si][sj] == target) {
                        if (target == 'M' && !used[si][sj]) {
                            return new int[]{path, si, sj};
                        } else if (target != 'M') {
                            return new int[]{path, si, sj};
                        } else {
                            Q.add(new int[]{si, sj});
                        }
                    } else {
                        Q.add(new int[]{si, sj});
                    }
                }
            }
        }
        return new int[]{-1, -1, -1};
    }

    private int getMinPath(int[] start, int[] end, boolean[][] visted) {
        Queue<int[]> Q = new LinkedList<>();
        Q.add(start);
        visted[start[0]][start[1]] = true;

        int path = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            path++;
            while (size-- > 0) {
                int[] pos = Q.poll();
                if (pos == null) continue;

                for (int[] r : d) {
                    int si = pos[0] + r[0], sj = pos[1] + r[1];
                    if (!inGrid(si, sj)) continue;
                    if (!canWalk(si, sj)) continue;
                    if (visted[si][sj]) continue;

                    if (si == end[0] && sj == end[1]) {
                        return path;
                    } else {
                        Q.add(new int[]{si, sj});
                        visted[si][sj] = true;
                    }
                }
            }
        }
        return -1;
    }

    private boolean canReached(int i, int j) {
        for (int[] r : d) {
            int si = i + r[0], sj = j + r[1];
            if (inGrid(si, sj) && canWalk(si, sj)) {
                return true;
            }
        }
        return false;
    }

    private boolean canWalk(int i, int j) {
        char c = grid[i][j];
        return c == '.' || c == 'S' || c == 'T' || c == 'M' || c == 'O'; // c != '#'
    }

    private boolean inGrid(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }

    /**
     * 输入： ["S#O", "M..", "M.T"]
     * 输出：16
     * 解释：
     * 最优路线为：
     * S->O, cost = 4, 去搬石头
     * O->第二行的M, cost = 3, M机关触发
     * 第二行的M->O, cost = 3, 我们需要继续回去 O 搬石头。 O->第三行的M, cost = 4,
     * 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。
     */
    @Test
    public void test1() {
        String[] maze = {
                "S#O", "M..", "M.T"
        };
        int n = minimalSteps(maze);
        assertThat(n, is(16));
    }

    /**
     * 输入： ["S#O", "M.#", "M.T"]
     * 输出：-1
     * 解释：我们无法搬到石头触发机关
     */
    @Test
    public void test2() {
        String[] maze = {
                "S#O", "M.#", "M.T"
        };
        int n = minimalSteps(maze);
        assertThat(n, is(-1));
    }

    /**
     * 输入： ["S#O", "M.T", "M.."]
     * 输出：17
     * 解释：注意终点也是可以通行的。
     */
    @Test
    public void test3() {
        String[] maze = {
                "S#O", "M.T", "M.."
        };
        int n = minimalSteps(maze);
        assertThat(n, is(17));
    }

    @Test
    public void test4() {
        String[] maze = {
                "OOOOO", "OS#OO", "#O#TO", "M#OOO"
        };
        int n = minimalSteps(maze);
        assertThat(n, is(-1));
    }

    @Test
    public void test5() {
        String[] maze = {
                "MMMMM", "MS#MM", "MM#TO"
        };
        int n = minimalSteps(maze);
        assertThat(n, is(95));
    }

    @Test
    public void test6() {
        String[] maze = {
                "..#..", ".S#..", "..#T#"
        };
        int n = minimalSteps(maze);
        assertThat(n, is(-1));
    }
}
