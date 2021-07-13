package com.tukeping.nowcoder;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author tukeping
 * @date 2021/7/4
 **/
public class NC76 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        // queue -> FIFO
        // stack -> FILO
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    @Test
    public void test() {
        //["PSH1","PSH2","PSH3","POP","POP","PSH4","POP","PSH5","POP","POP"]
        List<Integer> ret = new ArrayList<>();
        push(1);
        push(2);
        push(3);
        ret.add(pop());
        ret.add(pop());
        push(4);
        ret.add(pop());
        push(5);
        ret.add(pop());
        ret.add(pop());
        int[] actual = ret.stream().mapToInt(Integer::intValue).toArray();
        int[] expect = new int[]{1, 2, 3, 4, 5};
        ListHelper.assertThat(actual, expect);
    }
}
