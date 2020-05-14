package com.tukeping.cp.codejam.year2020.round_0.nesting_depth;

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
            StringBuilder y = new StringBuilder();
            String S = in.next();
            S += "0";

            int debt = 0;
            for (char c : S.toCharArray()) {
                int newDebt = c - '0';
                int curDebt = debt - newDebt;
                int debtDue = curDebt < 0 ? -curDebt : 0;
                debt += debtDue;
                while (debtDue-- > 0) {
                    y.append("(");
                }
                int rep = Math.max(curDebt, 0);
                debt -= rep;
                while (rep-- > 0) {
                    y.append(")");
                }
                y.append(newDebt);
            }

            System.out.println(String.format("Case #%d: %s", x, y.substring(0, y.length() - 1)));
        }

        System.exit(0);
    }
}
