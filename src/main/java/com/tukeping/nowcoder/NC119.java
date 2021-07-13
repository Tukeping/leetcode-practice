package com.tukeping.nowcoder;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author tukeping
 * @date 2021/7/5
 **/
public class NC119 {

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (k > input.length) {
            return ret;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : input) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        while (!minHeap.isEmpty()) {
            ret.add(minHeap.poll());
        }
        Collections.reverse(ret);
        return ret;
    }

    @Test
    public void test() {
        ArrayList<Integer> ret = GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
        int[] actual = ListHelper.asArray(ret);
        ListHelper.assertThat(actual, new int[]{1, 2, 3, 4});
    }
}
