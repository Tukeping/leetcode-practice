package com.tukeping.practice.courses.advanced.p2;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 */
public class Code_05_MountainsAndFlame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(communications(arr));
        }
        in.close();
    }

    private static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    private static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {//找到最大值
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int value = arr[maxIndex];
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(value));
        while (index != maxIndex) {//从最大值开始遍历
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {
                int times = stack.pop().times;
                // C(2,k) + 2 * k
                res += getInternalSum(times) + 2 * times;
            }
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.pop().times++;
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index);
        }
        while (!stack.isEmpty()) {//遍历完成后，栈中仍有元素
            int times = stack.pop().times;
            res += getInternalSum(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;
                } else {
                    res += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return res;
    }
}
