package org.gxu.algorithm.test;

import java.util.Arrays;

import org.gxu.algorithm.chapter2.DivideAndConquer;

public class TestDivideAndConquer {
	public static void main(String[] args) {
		int index;
		int[] arr={3,23,26,29,30,32,36,38,40,45,55};
		index=DivideAndConquer.binarySearch(arr, 2);
		System.out.println(index);
		index=DivideAndConquer.binarySearch(arr, 40);
		System.out.println(index);
		DivideAndConquer.binarySearch(arr, 31, 0, arr.length-1);
		DivideAndConquer.binarySearch(arr, 36, 0, arr.length-1);
		
		int[] numbers={23,12,34,22,11,45,32,77,66};
		int[] x=DivideAndConquer.quickSort(numbers, 0, numbers.length-1);
		System.out.println(Arrays.toString(x));
		
		DivideAndConquer.chessBoard(0, 0, 0, 3, 8);
		DivideAndConquer.showBoard();
		
		int[] nums={32,23,45,22,34,55,78,11,18,25};
		DivideAndConquer.mergeSort(nums, nums.length);
		System.out.println(Arrays.toString(nums));
		
		int i=DivideAndConquer.randomizedSelect(nums, 0, nums.length-1,10);
		System.out.println(i);
	}

}
