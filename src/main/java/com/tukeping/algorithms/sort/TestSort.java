package com.tukeping.algorithms.sort;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/29
 **/
public class TestSort {

//    BubbleSort inst = new BubbleSort();
//    SelectionSort inst = new SelectionSort();
//    InsertionSort inst = new InsertionSort();
    QuickSort inst = new QuickSort();
//    ShellSort inst = new ShellSort();
//    MergeSort inst = new MergeSort();

    @Test
    public void test() {
        int n = 10;
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }

        int testCount = 50;
        for (int i = 0; i < testCount; i++) {
            int[] shuffled = inst.shuffle(nums);
            inst.sort(nums);
            ListHelper.printArray(shuffled, nums);
            ListHelper.assertSorted(nums);
        }
    }
}
