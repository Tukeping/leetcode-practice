package com.tukeping.acm.poj.p3094;

import java.util.Scanner;

/**
 * Created by tukeping on 14-7-3.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        char[] latter;
        int sum = 0,i;
        while(!line.equals("#")){
            latter = line.toCharArray();
            for(i=1;i<=latter.length;i++){
                if(latter[i-1] == ' ')
                    continue;
                sum += i*(latter[i-1]-64);
            }
            System.out.println(sum);
            sum = 0;
            line = sc.nextLine();
        }
    }
}
