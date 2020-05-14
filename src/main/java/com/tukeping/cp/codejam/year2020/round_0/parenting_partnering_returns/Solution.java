package com.tukeping.cp.codejam.year2020.round_0.parenting_partnering_returns;

import java.util.Arrays;
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

            int[][] nums = new int[N][3];

            for (int i = 0; i < N; i++) {
                int S = in.nextInt();
                int E = in.nextInt();
                nums[i] = new int[]{S, E, i};
            }

            String ans = solve(nums, N);

            System.out.println(String.format("Case #%d: %s", x, ans));
        }

        System.exit(0);
    }

    private static String solve(int[][] a, int n) {
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);
        char[] ans = new char[n];
        int C = 0, J = 0;
        for (int i = 0; i < n; i++) {
            if (C <= a[i][0]) {
                C = a[i][1];
                ans[a[i][2]] = 'C';
            } else if (J <= a[i][0]) {
                J = a[i][1];
                ans[a[i][2]] = 'J';
            } else {
                return "IMPOSSIBLE";
            }
        }
        return new String(ans);
    }
}
