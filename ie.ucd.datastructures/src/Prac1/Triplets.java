package Prac1;

import java.util.Arrays;

public class Triplets {
    public static void main(String[] args) {
        int[] arr = {-1,12,4,7,3,2,1,2,0,1,5};
        int x = 5;
        int[][] ans = getTriples(arr,x);
        System.out.println(Arrays.deepToString(ans));
    }

    public static int[][] getTriples(int[] arr,int target) {
        int[][] trip = new int[arr.length][3];
        int c = 0;

        for (int i = 0; i < arr.length - 2;i++ ) {
            for (int j = i + 1;j < arr.length - 1;j++) {
                for (int p = j + 1; p < arr.length;p++) {

                    if (arr[i] + arr[j] + arr[p] == target) {
                        trip[c][0] = arr[i]; trip[c][1] = arr[j]; trip[c][2] = arr[p];
                        c++;
                    }
                }
            }

        }
        return trip;
    }

}