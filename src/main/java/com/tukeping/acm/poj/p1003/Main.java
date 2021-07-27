package com.tukeping.acm.poj.p1003;

import java.util.Scanner;

/**
 * Created by tukeping on 14-7-3.
 */
public class Main {
    public static void main(String[] args) {
        int n;
        float cardLength = 0f;
        Scanner scanner = new Scanner(System.in);
        float num = scanner.nextFloat();
        while(num != 0.00){
            for(n=1;;n++){
                cardLength += (float)1/(n+1);
                if(cardLength >= num){
                    System.out.println(n+" card(s)");
                    break;
                }
            }
            num = scanner.nextFloat();
            cardLength = 0f;
        }
    }
}
