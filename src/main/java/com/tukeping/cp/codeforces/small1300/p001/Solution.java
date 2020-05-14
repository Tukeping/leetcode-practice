package com.tukeping.cp.codeforces.small1300.p001;

import java.util.Scanner;

/**
 * @author tukeping
 * @date 2020/4/30
 **/
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        int x = 0, y = 0, z = 0;
        for (int i = 1; i <= T; i++) {
            x += in.nextInt();
            y += in.nextInt();
            z += in.nextInt();
        }

        String ans = (x == 0 && y == 0 && z == 0) ? "YES" : "NO";
        System.out.println(ans);

        System.exit(0);
    }
}
