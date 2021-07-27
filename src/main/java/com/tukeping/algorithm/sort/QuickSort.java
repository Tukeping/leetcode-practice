package com.tukeping.algorithm.sort;

/**
 * Created by tukeping on 14-7-13.
 *
 * 排序算法参考网站：http://en.wikipedia.org/wiki/Sorting_algorithm
 * 思路参考网站：http://www.ruanyifeng.com/blog/2011/04/quicksort_in_javascript.html
 *
 * 时间复杂度：
 * n*log(n) <= QuickSort() <= n*n
 *
 * 概述~快速排序步骤：
 * 1、拿一个数作为基准值
 * 2、除了基准值的数之外的所有数与基准值比较，大的放在右边数组中，小的放在左边数组中
 * 3、递归重复1和2步骤，最后拼接数组并输出
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] testData = new int[]{10,1,5,6,7,9,8,4,2,3};
        System.out.println("QuickSort before data:");
        for(int d:testData){
            System.out.print(d + " ");
        }
        System.out.println("\nQuickSort after data:");
        int[] data = quickSort.quick(testData);
        for(int d:data){
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public int[] quick(int[] data){
        if(data.length <= 1){
            return data;
        }
        //拿第一个元素作为基准值
        int pivot = data[0];
        int i,r=0,l=0;
        int[] left = new int[data.length];
        int[] right = new int[data.length];
        for(i=1;i<data.length;i++){
            if(data[i] > pivot){
                right[r++] = data[i];
            }else{
                left[l++] = data[i];
            }
        }
        int[] left0 = new int[l];
        int[] right0 = new int[r];
        System.arraycopy(left,0,left0,0,l);
        System.arraycopy(right,0,right0,0,r);
        //递归左边数组和右边数组
        left = quick(left0);
        right = quick(right0);
        int[] arr = new int[data.length];
        //left+pivot+right 开始拼接数组
        System.arraycopy(left,0,arr,0,left.length);
        arr[l] = pivot;
        System.arraycopy(right,0,arr,l+1,right.length);
        return arr;
    }
}
