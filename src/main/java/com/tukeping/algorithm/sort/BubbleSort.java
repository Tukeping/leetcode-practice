package com.tukeping.algorithm.sort;

/**
 * Created by tukeping on 14-7-15.
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] data = new int[]{13,14,94,33,82,25,59,94,65,23,45,27,73,25,39,10};
        System.out.println("BubbleSort");
        System.out.println("----------------------------------------------");
        System.out.println("Data Before sort:");
        int i;
        for(i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
        System.out.println("\nData After sort:");
        data = bubbleSort.bubble(data);
        for(i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
    }

    public int[] bubble(int[] data){
        int i,j,tmp;
        for(i=0;i<data.length;i++){
            for(j=i+1;j<data.length;j++){
                if(data[i] > data[j]){
                    tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
        return data;
    }
}
