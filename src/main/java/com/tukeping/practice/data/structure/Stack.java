package com.tukeping.practice.data.structure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2021/7/31
 **/
public class Stack {

    class LinkedStack {

        private class Node {
            String item;
            Node next;
        }

        private Node first;

        public void push(String item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public String pop() {
            String item = first.item;
            first = first.next;
            return item;
        }

        public boolean isEmpty() {
            return first == null;
        }
    }

    class FixedArrayStack {

        private int size;
        private String[] s;

        public FixedArrayStack(int cap) {
            s = new String[cap];
        }

        public void push(String item) {
            s[size++] = item;
        }

        public String pop() {
//            return s[--size];
            String item = s[--size];
            s[size] = null; // for GC
            return item;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    class ResizingArrayStack {

        private String[] s;
        private int size;

        public ResizingArrayStack() {
            s = new String[1];
        }

        public void push(String item) {
            if (size == s.length) resize(2 * s.length);
            s[size++] = item;
        }

        private void resize(int cap) {
            String[] copy = new String[cap];
            for (int i = 0; i < size; i++) {
                copy[i] = s[i];
            }
            s = copy;
        }

        public String pop() {
            String item = s[--size];
            s[size] = null;
            if (size > 0 && size == s.length / 4) resize(s.length / 2);
            return item;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    @Test
    public void test() {
//        LinkedListStack stack = new LinkedListStack();
        FixedArrayStack stack = new FixedArrayStack(10);
        stack.push("to");
        stack.push("be");
        stack.push("or");
        stack.push("not");
        stack.push("to");
        Assert.assertEquals(stack.pop(), "to");
        stack.push("be");
        Assert.assertEquals(stack.pop(), "be");
        Assert.assertEquals(stack.pop(), "not");
        stack.push("that");
        Assert.assertEquals(stack.pop(), "that");
        Assert.assertEquals(stack.pop(), "or");
        Assert.assertEquals(stack.pop(), "be");
        stack.push("is");
    }
}
