package com.tukeping.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/6/3
 **/
public class LeetCode1001 {

    HashSet<String> lampSet = new HashSet<>();
    HashMap<Integer, HashSet<String>> rowMap = new HashMap<>();
    HashMap<Integer, HashSet<String>> colMap = new HashMap<>();
    HashMap<Integer, HashSet<String>> diagMap1 = new HashMap<>();
    HashMap<Integer, HashSet<String>> diagMap2 = new HashMap<>();

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        for (int[] lamp : lamps) {
            String lampIdx = lampKey(lamp[0], lamp[1]);
            lampSet.add(lampIdx);
            addLamp(rowMap, lamp[0], lampIdx);
            addLamp(colMap, lamp[1], lampIdx);
            addLamp(diagMap1, diag1Key(lamp[0], lamp[1]), lampIdx);
            addLamp(diagMap2, diag2Key(lamp[0], lamp[1]), lampIdx);
        }

        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {0, 1}, {1, -1}, {0, -1}, {-1, -1}};

        int[] ans = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            boolean light = rowMap.containsKey(q[0])
                    || colMap.containsKey(q[1])
                    || diagMap1.containsKey(diag1Key(q[0], q[1]))
                    || diagMap2.containsKey(diag2Key(q[0], q[1]));

            ans[idx++] = light ? 1 : 0;

            String key = lampKey(q[0], q[1]);
            if (lampSet.contains(key)) {
                remove(q[0], q[1], key);
                lampSet.remove(key);
            }

            for (int[] d : dir) {
                int sx = q[0] + d[0], sy = q[1] + d[1];
                if (!inGrid(sx, sy, n)) continue;
                key = lampKey(sx, sy);
                if (lampSet.contains(key)) {
                    remove(sx, sy, key);
                    lampSet.remove(key);
                }
            }
        }
        return ans;
    }

    private void remove(int x, int y, String key) {
        removeLamp(rowMap, x, key);
        removeLamp(colMap, y, key);
        removeLamp(diagMap1, diag1Key(x, y), key);
        removeLamp(diagMap2, diag2Key(x, y), key);
    }

    private boolean inGrid(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private void addLamp(HashMap<Integer, HashSet<String>> map, Integer key, String lampIdx) {
        if (!map.containsKey(key)) {
            map.put(key, new HashSet<>());
        }
        map.get(key).add(lampIdx);
    }

    private void removeLamp(HashMap<Integer, HashSet<String>> map, Integer key, String lampIdx) {
        if (!map.containsKey(key)) return;

        HashSet<String> lamp = map.get(key);
        if (lamp.size() == 1) {
            map.remove(key);
        } else {
            lamp.remove(lampIdx);
        }
    }

    private String lampKey(int x, int y) {
        return x + "_" + y;
    }

    private Integer diag1Key(int x, int y) {
        return x + y;
    }

    private Integer diag2Key(int x, int y) {
        return Math.abs(x - y);
    }

    /**
     * 输入：N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
     * 输出：[1,0]
     * 解释：
     * 在执行第一次查询之前，我们位于 [0, 0] 和 [4, 4] 灯是亮着的。
     * 表示哪些单元格亮起的网格如下所示，其中 [0, 0] 位于左上角：
     * 1 1 1 1 1
     * 1 1 0 0 1
     * 1 0 1 0 1
     * 1 0 0 1 1
     * 1 1 1 1 1
     * 然后，由于单元格 [1, 1] 亮着，第一次查询返回 1。在此查询后，位于 [0，0] 处的灯将关闭，网格现在如下所示：
     * 1 0 0 0 1
     * 0 1 0 0 1
     * 0 0 1 0 1
     * 0 0 0 1 1
     * 1 1 1 1 1
     * 在执行第二次查询之前，我们只有 [4, 4] 处的灯亮着。现在，[1, 0] 处的查询返回 0，因为该单元格不再亮着。
     */
    @Test
    public void test1() {
        int[][] lamps = {{0, 0}, {4, 4}};
        int[][] queries = {{1, 1}, {1, 0}};
        int[] ret = gridIllumination(5, lamps, queries);
        int[] expect = {1, 0};
        assertThat(ret, is(expect));
    }

    @Test
    public void test2() {
        int[][] lamps = {{0, 0}, {0, 4}};
        int[][] queries = {{0, 4}, {0, 1}, {1, 4}};
        int[] ret = gridIllumination(5, lamps, queries);
        int[] expect = {1, 1, 0};
        assertThat(ret, is(expect));
    }

    @Test
    public void test3() {
        int[][] lamps = {{0, 0}, {0, 1}, {0, 4}};
        int[][] queries = {{0, 0}, {0, 1}, {0, 2}};
        int[] ret = gridIllumination(5, lamps, queries);
        int[] expect = {1, 1, 1};
        assertThat(ret, is(expect));
    }
}
