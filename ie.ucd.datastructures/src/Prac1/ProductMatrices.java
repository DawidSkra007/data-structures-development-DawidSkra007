package Prac1;

import java.util.Arrays;
import java.util.Random;

public class ProductMatrices {
    public static int m = 5, n = 5;

    public static void main(String[] args) {
        int min = 0, max = 50;

        int mat1[][] = new int[m][n];
        int mat2[][] = new int[m][n];

        Random rnd = new Random();
        for(int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                mat1[i][j] = rnd.nextInt(max - min + 1) + min;
                mat2[i][j] = rnd.nextInt(max - min + 1) + min;
            }
        }
        int[][] prod = matProd(mat1,mat2);
        System.out.println("array 1 : " + Arrays.deepToString(mat1));
        System.out.println("array 2 : " + Arrays.deepToString(mat2));
        System.out.println("Multiplied arrays: " + Arrays.deepToString(prod));
    }

    public static int[][] matProd(int[][] mat1,int[][] mat2) {
        int prod[][] = new int[m][n];

        for (int i = 0; i < m;i++) {
            for (int j = 0; j < n;j++) {
                for (int k = 0; k < m;k++) {
                    prod[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return prod;
    }
}
