package Prac1;

import java.util.ArrayList;

public class ArrayCommon {
    public static void main(String[] args) {
        String[] arr1 = {"nail","cure","act","cat","dog"};
        String[] arr2 = {"act","book","dog","laptop","glass"};

        System.out.println(getCommonElements(arr1,arr2));
    }

    private static ArrayList<String> getCommonElements(String[] arr1, String[] arr2) {
        ArrayList<String> common = new ArrayList<>();

        for (int i = 0;i < arr1.length;i++) {
            for (int j = 0;j < arr2.length;j++) {
                if (arr1[i].equals(arr2[j])) {
                    common.add(arr1[i]);
                }
            }
        }
        return common;
    }
}
