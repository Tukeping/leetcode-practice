package com.tukeping.cs.datastructures;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * 数组
 * ------
 * [x] 实现一个支持动态扩容的数组
 * [] 实现一个大小固定的有序数组，支持动态增删改操作
 * [] 实现两个有序数组合并为一个有序数组
 *
 * @author tukeping
 * @date 2020/1/7
 **/
public class ArrayPractice {

    @Test
    public void test1() {
        TuArray<Integer> array = new TuArray<>(2);
        array.add(10); // autoboxing
        array.add(20);
        array.add(30);
        array.add(40);
        array.remove(new Integer(20));
        assertThat(3, is(3));
        assertThat(30, is(array.get(1)));
    }

    @Test
    public void test2() {
        TuSortedArray<Integer> array = new TuSortedArray<>();
        array.add(40);
        array.add(30);
        array.add(20);
        array.add(0);
        array.add(10);

        array.forEach(System.out::println);

        assertThat(0, is(array.get(0)));
        assertThat(10, is(array.get(1)));
        assertThat(20, is(array.get(2)));
        assertThat(30, is(array.get(3)));
        assertThat(40, is(array.get(4)));
    }

    class TuSortedArray<E> {
        private final Object[] EMPTY = {};
        private final int INITIAL_CAPACITY = 10;
        private Object[] elementData;
        private int size;

        public TuSortedArray() {
            elementData = EMPTY;
        }

        public TuSortedArray(int initialCapacity) {
            elementData = new Object[initialCapacity];
        }

        public void forEach(Consumer<Object> fn) {
            for (int i = 0; i < size; i++) {
                fn.accept(elementData[i]);
            }
        }

        public void add(Object o) {
            ensureCapacity(size + 1);
            elementData[size++] = o;
            Arrays.sort(elementData); // O(logN)
        }

        @SuppressWarnings("unchecked")
        public E get(int index) {
            return (E) elementData[index];
        }

        private void ensureCapacity(int minCapacity) {
            minCapacity = calculateCapacity(minCapacity);
            if (minCapacity > elementData.length) {
                grow(minCapacity);
            }
        }

        private void grow(int newLength) {
            elementData = Arrays.copyOf(elementData, newLength);
        }

        private int calculateCapacity(int minCapacity) {
            if (elementData == EMPTY) {
                return Math.min(INITIAL_CAPACITY, minCapacity);
            }
            if (minCapacity > elementData.length) {
                minCapacity = minCapacity << 1;
            }
            return minCapacity;
        }
    }

    class TuArray<E> {
        private final Object[] EMPTY = {};
        private final int INITIAL_CAPACITY = 10;
        private Object[] elementData;
        private int size;

        public TuArray() {
            elementData = EMPTY;
        }

        public TuArray(int initialCapacity) {
            if (initialCapacity > 0) {
                elementData = new Object[initialCapacity];
            } else if (initialCapacity == 0) {
                elementData = EMPTY;
            } else {
                throw new IllegalArgumentException("initial capacity must not be negative");
            }
        }

        public void forEach(Consumer<Object> fn) {
            for (int i = 0; i < size; i++) {
                fn.accept(elementData[i]);
            }
        }

        public int size() {
            return size;
        }

        public void add(E e) {
            ensureCapacity(size + 1);
            elementData[size++] = e;
        }

        private void ensureCapacity(int minCapacity) {
            minCapacity = calculateCapacity(minCapacity);
            if (minCapacity > elementData.length) {
                grow(minCapacity);
            }
        }

        private void grow(int newLength) {
            elementData = Arrays.copyOf(elementData, newLength);
        }

        private int calculateCapacity(int minCapacity) {
            if (elementData == EMPTY) {
                return Math.min(INITIAL_CAPACITY, minCapacity);
            }
            if (minCapacity > elementData.length) {
                minCapacity = minCapacity << 1;
            }
            return minCapacity;
        }

        public void remove(Object o) {
            for (int i = 0; i < size; i++) {
                if (o == null) {
                    if (elementData[i] == null) {
                        remove(i);
                    }
                } else {
                    if (o.equals(elementData[i])) {
                        remove(i);
                    }
                }
            }
        }

        public void remove(int index) {
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            }
            elementData[--size] = null; // help gc collect
        }

        @SuppressWarnings("unchecked")
        public E get(int index) {
            return (E) elementData[index];
        }
    }
}
