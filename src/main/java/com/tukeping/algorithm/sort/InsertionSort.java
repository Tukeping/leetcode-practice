package com.tukeping.algorithm.sort;

/**
 * Created by tukeping on 14-7-15.
 */
public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] data = new int[]{13,14,94,33,82,25,59,94,65,23,45,27,73,25,39,10};
        System.out.println("InsertionSort");
        System.out.println("----------------------------------------------");
        System.out.println("Data Before sort:");
        int i;
        for(i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
        System.out.println("\nData After sort:");
        data = insertionSort.insertion(data);
        for(i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
    }

    public int[] insertion(int[] data){
        int i,j,tmp,move=0;
        for(i=1;i<data.length;i++){
            tmp = data[i];
            for(j=i-1;j>=0;j--){
                if(tmp < data[j]){
                    data[j+1] = data[j];
                    move++;
                }else{
                    data[i-move] = tmp;
                    move = 0;
                    break;
                }
            }
            if(j == -1 && move != 0){
                data[i-move] = tmp;
                move = 0;
            }
        }
        return data;
    }
}
