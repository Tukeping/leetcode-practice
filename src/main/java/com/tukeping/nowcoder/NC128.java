package com.tukeping.nowcoder;

/**
 * @author tukeping
 * @date 2021/7/7
 **/
public class NC128 {

    /**
     * 将数组中每个位置上的累加起来就是总的水量
     * 首先求容器边界,
     * 然后使用双指针,
     * 分别从两边往中间扫描,
     * 当左边的高度小于右边的高度时,左指针++,
     * 如果此时当前位置的高度小于容器的边界高度,这个位置上方有水,进行水量累加。
     * 反之，则右指针向左扫描-1
     *
     * max water
     * @param arr int整型一维数组 the array
     * @return long长整型
     */
    public long maxWater(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int min = Math.min(arr[l], arr[r]);
        long area = 0;
        while (l < r) {
            if (arr[l] < arr[r]) {
                l++;
                if (arr[l] < min) {
                    area += min - arr[l];
                } else {
                    min = Math.min(arr[l], arr[r]);
                }
            } else {
                r--;
                if (arr[r] < min) {
                    area += min - arr[r];
                } else {
                    min = Math.min(arr[l], arr[r]);
                }
            }
        }
        return area;
    }
}
