package com.tukeping.acm.poj.p1005;

import java.util.Scanner;

/**
 * Created by tukeping on 14-7-3.
 * 向上取整:Math.ceil()   //只要有小数都+1
 * 向下取整:Math.floor()  //不取小数
 * 四舍五入:Math.round()  //四舍五入
 */
public class Main {
    public static void main(String[] args) {
        int year;
        float x,y,r;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=1;i<=n;i++){
            x = Math.abs(sc.nextFloat());
            y = sc.nextFloat();
            r = (float)Math.sqrt((double)x*x+y*y);
            year = (int)Math.ceil((double)r*r*Math.PI/2/50);
            System.out.println("Property "+i+": This property will begin eroding in year "+year+".");
        }
        System.out.println("END OF OUTPUT.");
    }
}
