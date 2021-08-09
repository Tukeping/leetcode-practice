package com.tukeping.practice.quiz;

import com.tukeping.algs4.stdlib.StdIn;
import com.tukeping.algs4.stdlib.StdOut;
import com.tukeping.practice.data.structure.ST;

/**
 * @author tukeping
 * @date 2021/8/3
 **/
public class FrequencyCounter {

    /**
     * cd target/classes
     * java com.tukeping.practice.quiz.FrequencyCounter 2 < tale.txt
     */
    public static void main(String[] args) {
        int minLen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
    }
}
