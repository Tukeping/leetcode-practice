package com.tukeping.cp.codejam.year2020.round_0.vestigium;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author tukeping
 * @date 2020/4/30
 **/
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = in.nextInt();
            int[][] grid = new int[N][N];

            HashSet<Integer> set;

            int k = 0, r = 0, c = 0;
            for (int i = 0; i < N; i++) {
                boolean cnt = false;
                set = new HashSet<>(N);
                for (int j = 0; j < N; j++) {
                    grid[i][j] = in.nextInt();
                    if (i == j) k += grid[i][j];

                    if (!cnt && set.contains(grid[i][j])) {
                        r++;
                        cnt = true;
                    } else set.add(grid[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                boolean cnt = false;
                set = new HashSet<>(N);
                for (int j = 0; j < N; j++) {
                    if (!cnt && set.contains(grid[j][i])) {
                        c++;
                        cnt = true;
                    } else set.add(grid[j][i]);
                }
            }

            System.out.println(String.format("Case #%d: %d %d %d", x, k, r, c));
        }

        System.exit(0);
    }
}
