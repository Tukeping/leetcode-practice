package com.tukeping.tools;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class DPHelper {

    public static void print(int[] f) {
        for (int i = 0; i < f.length; i++) {
            System.out.println("f[" + i + "] = " + f[i]);
        }
    }

    public static void print(int[][] f) {
        for (int i = 0; i < f.length; i++) {
            System.out.print("[n:" + i + "]");
            for (int j = 0; j < f[i].length; j++) {
                System.out.print(" " + f[i][j]);
            }
            System.out.println();
        }
    }

    public static void print(int[][] f, String A, String B) {
        int n = A.length(), m = B.length();

        System.out.print("    ");
        for (int i = 1; i <= m; i++) {
            System.out.print(B.charAt(i - 1) + " ");
        }
        System.out.println();

        System.out.print("  ");
        for (int i = 0; i <= m; i++) {
            System.out.print(f[0][i] + " ");
        }
        System.out.println();

        for (int i = 1; i <= n; i++) {
            System.out.print(A.charAt(i - 1) + " ");
            System.out.print(f[i][0] + " ");
            for (int j = 1; j <= m; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
