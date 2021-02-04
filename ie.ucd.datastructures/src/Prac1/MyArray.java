package Prac1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class MyArray<T> implements Iterable<T>{

    private T[] x;

    MyArray(T[] input) {
        x = input;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>();
    }

    class MyIterator<T> implements Iterator<T> {

        private int i = 0;
        @Override
        public boolean hasNext() {
            return i < x.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) x[i++];
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] arr = {10,12,14,433,23,5546,213,467,1353,75,90,13,4,74,867,232,7664,35,1};
        MyArray<Integer> arr1 = new MyArray<Integer>(arr);

        for(Integer i : arr1) {
            System.out.println(i);
        }
    }
}
