package com.tukeping.cp.codejam.year2020.round_1a.pattern_matching;

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
            String[] p = new String[N];
            for (int i = 0; i < N; i++) {
                p[i] = in.next();
            }

            String ans = solve(p, N);

            System.out.println(String.format("Case #%d: %s", x, ans));
        }

        System.exit(0);
    }

    private static String solve(String[] p, int n) {


        return "";
    }
}
