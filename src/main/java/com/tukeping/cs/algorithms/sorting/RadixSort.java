package com.tukeping.cs.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序（英语：Radix sort）是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 * 基数排序的发明可以追溯到1887年赫尔曼·何乐礼在打孔卡片制表机（Tabulation Machine）上的贡献。
 *
 * 基数排序的方式可以采用 LSD（Least significant digital）或 MSD（Most significant digital），
 * LSD的排序方式由键值的最右边开始，而MSD则相反，由键值的最左边开始。
 *
 * @author tukeping
 * @date 2020/1/14
 **/
public class RadixSort {

    private int[] sort(int[] arr) {
        // 1. find max number and calc 'k'
        int k = (int) Math.ceil(Math.log(max(arr) + 1));

        // 2. put value to each 'bucket'
        // 取余 % 是舍去高位 ; 除法 / 是舍去低位
        // (val % (radix ^ i)) / (radix ^ (i-1))
        for (int i = 1; i < k+1; i++) {
            List<List<Integer>> bucket = new ArrayList<>();


        }


        // 3. reason of Non-In-Place sort, cover to origin array

        return arr;
    }

    private int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max) max = arr[i];
        return max;
    }
}
