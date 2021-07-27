package com.tukeping.acm.poj.p1004;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by tukeping on 14-7-3.
 */
public class Main {
    public static void main(String[] args) {
        float money = 0;
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<12;i++){
            money += sc.nextFloat();
        }
        DecimalFormat df = new DecimalFormat(".00");
        System.out.println("$"+df.format(money/12));
    }
}
