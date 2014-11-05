package com.binarysearch;
public class BinarySearch {

	private int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

	public int binarySearch(int num) {
		int first = 0;
		int last = arr.length - 1;
		int middle = (first + last) / 2;
		while (first <= last) {
			if (num > arr[middle]) {
				first = middle + 1;
			} else if (num < arr[middle]) {
				last = middle - 1;
			} else if (num == arr[middle]) {
				return middle;
			}

			middle = (first + last) / 2;
		}

		return -1;

	}

	public int binarySearchRecursion(int num, int first, int last) {
		if (first > last) {
			return -1;
		}
		int middle = (first + last) / 2;

		if (num > arr[middle]) {
			return binarySearchRecursion(num, middle + 1, last);
		} else if (num < arr[middle]) {
			return binarySearchRecursion(num, first, middle - 1);
		} else if (num == arr[middle]) {
			return middle;
		}
		return -1;

	}

	public static void main(String[] args) {
		BinarySearch b = new BinarySearch();
		System.out.println(b.binarySearch(86));
		System.out.println(b.binarySearchRecursion(99, 0, 13));
	}

}
