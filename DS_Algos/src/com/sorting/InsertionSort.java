
package com.sorting;

import java.util.Stack;

public class InsertionSort {

    private static int arr[] = {
            2, 6, 4, 1, 8, 9, 3, 11, 5, 16
    };

    public static void insertionSort() {
        int key;
        int j;

        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            j = i;
            while (j > 0 && key < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = key;
        }
    }

    public static void main(String[] args) {
        insertionSort();
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);

    }

}
