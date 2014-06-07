package org.gxu.algorithm.test;

import org.gxu.algorithm.common.Matrix;

public class MatrixTester {
	public static void main(String args[]) {
		int m1[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Matrix a = new Matrix(m1);
		int m2[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Matrix b = new Matrix(m2);
		System.out.print("Matrix a:\n" + a);
		System.out.print("Matrix b:\n" + b);
		System.out.println("Matrix a  multi Matrix b");
		a.multi(b);
		System.out.println("Matrix a add Matrix b");
		a.add(b);
		System.out.print("Matrix a become:\n" + a);
		System.out.println("Matrix a dec Matrix b");
		a.dec(b);
		System.out.print("Matrix a become:\n" + a);
		System.out.println("The transpose of matrix a：\n" + a.transpose());

	}

}
