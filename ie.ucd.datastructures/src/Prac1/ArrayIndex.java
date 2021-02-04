package Prac1;

public class ArrayIndex {
    public static void main(String[] args) {
        int[] arr = {90,61,32,435,46,12,44,1,6,23,77,32};
        int x = 90;
        int indexOf = getIndexOf(arr,x);
        System.out.println("Index of " + x + " is: " + indexOf);
    }

    private static int getIndexOf(int[] arr, int x) {
        int index = 0;
        for (int i = 0; i < arr.length;i++) {

            if (arr[i] == x) {
                index = i;
                break;
            }
        }
        return index;
    }

}
