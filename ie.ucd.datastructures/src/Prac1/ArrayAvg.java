package Prac1;

public class ArrayAvg {

    public static void main(String[] args) {
        int[] array = {23,21,54,2,65,21,11,78,21,3,12};
        int total = 0;

        for (int i = 0; i < array.length;i++) {
            total += array[i];
        }
        total = (total / array.length);
        System.out.println("Average of array is: " + total);
    }
}
