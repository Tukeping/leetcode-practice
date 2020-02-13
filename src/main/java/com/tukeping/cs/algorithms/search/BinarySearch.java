package com.tukeping.cs.algorithms.search;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 二分查找
 * ------
 * 实现一个有序数组的二分查找算法
 * 实现模糊二分查找算法（比如大于等于给定值的第一个元素）
 *
 * @author tukeping
 * @date 2020/1/2
 **/
public class BinarySearch {

    private static int bsearch(int[] a, int n, int value) {
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1; // (low + high) / 2
            if (a[mid] == value) { // value found
                return mid;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else { // a[mid] <= value
                low = mid + 1;
            }
        }

        return -1; // value not found
    }

    public static void main(String[] args) throws IOException {
        List<Integer> list = readNumbersFromLocalDisk("integer.txt");
        sortNumber(list);
        findNumber(list, 999999);
    }

    @Test
    public void test() {
        int[] a = new int[]{9, 7, 1, 2, 10, 3, 5, 4, 6, 8};
        int value = 5;
        int n = 10;
        Arrays.sort(a);
        int found = bsearch(a, n, value);
        System.out.println(found);
    }

    private static int bsearchRecursion(int[] a, int n, int value) {
        return bsearchRecursion0(a, 0, n - 1, value);
    }

    private static int bsearchRecursion0(int[] a, int low, int high, int value) {
        if (low <= high) {
            return -1;
        }

        int mid = (low + high) >>> 1;

        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchRecursion0(a, mid + 1, high, value);
        } else { // a[mid] >= value
            return bsearchRecursion0(a, low, mid - 1, value);
        }
    }

    private static void sortNumber(List<Integer> list) {
        Collections.sort(list);
        list.forEach(System.out::println);
    }

    private static void findNumber(List<Integer> list, Integer number) {
        int x = Collections.binarySearch(list, number);
        System.out.println(x);
    }

    private static List<Integer> readNumbersFromLocalDisk(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static void generateNumbers() throws IOException {
        Random random = new Random();

        // 100万
        int bound = 100_0000;

        List<Integer> list = Stream.generate(() -> random.nextInt(bound))
                .limit(bound)
                .collect(Collectors.toList());

        List<String> lines = list.stream().map(String::valueOf).collect(Collectors.toList());
        Files.write(Paths.get("integer.txt"), lines, StandardOpenOption.CREATE_NEW);
    }
}
