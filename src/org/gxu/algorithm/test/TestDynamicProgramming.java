package org.gxu.algorithm.test;

import org.gxu.algorithm.chapter3.DynamicProgramming;

public class TestDynamicProgramming {
	public static void main(String[] args) {
		//测试矩阵连乘问题
		int[] p={30,35,15,5,10,20,25};
		int[][] m=new int[p.length][p.length];
		int[][] s=new int[p.length][p.length];
		DynamicProgramming.matrixChain(p, 6, m, s);
		//输出m[i][j]
		for(int i=1;i<p.length;i++){
			for(int j=1;j<m[i].length;j++){
				System.out.printf("%5d ",m[i][j] );
			}
			System.out.println();
		}
		System.out.println();
		for(int i=1;i<7;i++){
			for(int j=1;j<7;j++){
				System.out.print(s[i][j]+" ");
			}
			System.out.println();
		}
		//矩阵连乘问题最优解
		DynamicProgramming.traceback(1, 6, s);
		
		//测试最长公共子序列
		char[] x={' ','A','B','C','B','D','A','B'};
		char[] y={' ','B','D','C','A','B','A','B'};
		int[][] c=new int[x.length][y.length];
		int[][] b=new int[x.length][y.length];
		DynamicProgramming.LCSLength(x.length-1, y.length-1, x, y, c, b);
		for(int i=1;i<=x.length-1;i++){
			for(int j=1;j<=y.length-1;j++){
				System.out.print(b[i][j]+" ");
			}
			System.out.println();
		}
		DynamicProgramming.LCS(x.length-1, y.length-1, x, b);
	}

}
