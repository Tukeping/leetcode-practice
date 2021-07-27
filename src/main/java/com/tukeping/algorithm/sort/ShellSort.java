package com.tukeping.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tukeping on 14-7-15.
 */
public class ShellSort {
    public static void main(String[] args) {
        ShellSort shellsort = new ShellSort();
        int[] data = new int[]{13,14,94,33,82,25,59,94,65,23,45,27,73,25,39,10};
        System.out.println("ShellSort");
        System.out.println("----------------------------------------------");
        System.out.println("Data Before sort:");
        int i;
        for(i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
        List<Integer> list = shellsort.step(data.length);
        System.out.println("\nStep Arrays:"+list.toString());
        System.out.println("\nData After sort:");
        data = shellsort.shell(data);
        for(i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
        System.out.println();
    }

    public int[] shell(int[] data){
        int i,j,p,len;
        int[] arr;
        InsertionSort insertionSort = new InsertionSort();
        List stepList = step(data.length);
        for(i=stepList.size()-1;i>=0;i--){
            p = Integer.valueOf(stepList.get(i).toString());
            if(p == 1) break;
            arr = new int[p];
            for(j=0;j<data.length;j=j+p){
                len = j + p > data.length ? data.length - j : p;
                System.arraycopy(data,j,arr,0,len);
                arr = insertionSort.insertion(arr);
                System.arraycopy(arr,0,data,j,len);
            }
        }
        return insertionSort.insertion(data);
    }

    private List<Integer> step(int max){
        int p,count=1,a=0,b=0;
        List<Integer> stepList = new ArrayList<Integer>();
        while(true){
            if(count%2 != 0){ //奇
                p = (int)(9 * (Math.pow(4,a) - Math.pow(2,a)) + 1);
                a++;
            }else{ //偶
                p = (int)(Math.pow(2,b+2) * (Math.pow(2,b+2) - 3) + 1);
                b++;
            }
            if(p>=max) break;
            stepList.add(p);
            count++;
        }
        return stepList;
    }

}
