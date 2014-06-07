package org.gxu.algorithm.test;

import java.util.Arrays;

import org.gxu.algorithm.chapter2.Recursion;

public class TestRecursion {
	public static void main(String[] args) {
		int number;
		number=Recursion.factorial(10);
		System.out.println(number);
		
		number=Recursion.fibonacci(10);
		System.out.println(number);
		
		number=Recursion.ackerman(5, 2);
		System.out.println(number);
		number=Recursion.ackerman(2, 3);
		System.out.println(number);
		
		Object[] list={'D','C','B','A'};
		Recursion.perm(list, 0, list.length-1);
		
		number=Recursion.q(6, 6);
		System.out.println(number);
		
		Recursion.hanoi(3, 'A', 'B', 'C');
		
	}

}
