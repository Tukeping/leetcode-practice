package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/3
 **/
public class LCOF45 {

    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        list.sort((s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder ans = new StringBuilder();
        for (String str : list) {
            ans.append(str);
        }
        return ans.toString();
    }

    @Test
    public void test1() {
        String s = minNumber(new int[]{10, 2});
        assertThat(s, is("102"));
    }

    @Test
    public void test2() {
        String s = minNumber(new int[]{3, 30, 34, 5, 9});
        assertThat(s, is("3033459"));
    }
}
