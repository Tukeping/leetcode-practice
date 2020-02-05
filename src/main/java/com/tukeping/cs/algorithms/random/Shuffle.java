package com.tukeping.cs.algorithms.random;

import com.tukeping.tools.Arrays;
import com.tukeping.tools.Strings;
import org.junit.Test;

import java.util.Random;

/**
 * 公平的洗牌算法
 *
 *  来源于: Knuth-Shuffle
 *
 * @author tukeping
 * @date 2020/1/19
 **/
public class Shuffle {

    public void shuffle(int[] cards) {
        Random random = new Random();
        for (int sIndex = cards.length; sIndex >= 1; sIndex--) {
            int pickSwapIndex = random.nextInt(sIndex) + 1; // [0 ~ sIndex) => [1 ~ sIndex]
            Arrays.swap(cards, sIndex - 1, pickSwapIndex - 1);
        }
    }

    @Test
    public void test() {
        int[] cards = new int[]{1, 2, 3, 4, 5};
        System.out.println(Strings.arrayToString(cards));
        shuffle(cards);
        System.out.println(Strings.arrayToString(cards));
    }
}
