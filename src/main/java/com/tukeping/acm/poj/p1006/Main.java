package com.tukeping.acm.poj.p1006;

import java.util.Scanner;

/**
 * Created by tukeping on 14-7-3.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p,e,i,d;
        int lcm = 23*28*33;
        for(int k=1;;k++){
            p = sc.nextInt();
            e = sc.nextInt();
            i = sc.nextInt();
            d = sc.nextInt();
            if(p == -1 && e == -1 && i == -1 && d == -1)
                break;
            int n = (5544*p+14421*e+1288*i-d+lcm)%lcm;
            System.out.println("Case "+k+": the next triple peak occurs in "+(n==0 ? lcm : n)+" days.");
        }
    }
}
