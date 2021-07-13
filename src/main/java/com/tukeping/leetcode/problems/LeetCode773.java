package com.tukeping.leetcode.problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author tukeping
 * @date 2020/4/29
 **/
public class LeetCode773 {

    public int slidingPuzzle(int[][] board) {
        int[] ten = {100_000, 10_000, 1000, 100, 10, 1};
        int solution = 1234505;
        int[][] moves = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {3, 1, 5}, {2, 4}};

        int unordered = 0;
        int state = 0;
        int z = 0;
        for (int i = 0; i < 6; i++) {
            int s = board[i / 3][i % 3];
            state = 10 * state + s;
            if (s == 0) {
                z = i;
            } else {
                for (int j = 0; j < i; j++) {
                    int o = board[j / 3][j % 3];
                    if (o > s) {
                        unordered++;
                    }
                }
            }
        }
        if (unordered % 2 == 1) {
            return -1;
        }

        state = 10 * state + z;

        LinkedList<Integer> Q = new LinkedList<>();
        Q.offer(state);

        Set<Integer> visited = new HashSet<>();
        visited.add(state);

        int dist = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            while (size-- > 0) {
                int nState = Q.poll();
                if (nState == solution) return dist;
                int zero = nState % 10;
                for (int move : moves[zero]) {
                    int fullState = nState / 10;
                    int atPos = (fullState / ten[move]) % 10;
                    int number = (fullState - atPos * ten[move]) + atPos * ten[zero];
                    int newState = number * 10 + move;
                    if (visited.add(newState)) {
                        Q.offer(newState);
                    }
                }
            }
            dist++;
        }
        return -1;
    }

    public int slidingPuzzle2(int[][] board) {
        int n = board.length, m = board[0].length;

        String solution = "123450";
        String state = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                state += board[i][j];
            }
        }

        int[][] moves = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {3, 5, 1}, {2, 4}};

        HashSet<String> visited = new HashSet<>();
        visited.add(state);

        Queue<String> Q = new LinkedList<>();
        Q.add(state);

        int dist = 0;
        while (!Q.isEmpty()) {
            int size = Q.size();
            while (size-- > 0) {
                String nState = Q.poll();
                if (nState.equals(solution)) return dist;
                int zero = nState.indexOf('0');
                for (int move : moves[zero]) {
                    String nextState = swap(nState, zero, move);
                    if (visited.contains(nextState)) continue;
                    Q.offer(nextState);
                    visited.add(nextState);
                }
            }
            dist++;
        }
        return -1;
    }

    private String swap(String str, int i, int j) {
        StringBuilder ans = new StringBuilder(str);
        ans.setCharAt(i, str.charAt(j));
        ans.setCharAt(j, str.charAt(i));
        return ans.toString();
    }
}
