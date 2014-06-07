package org.gxu.algorithm.test;

import java.util.Random;

public class TestRandom {
	public static int randomizedPartition( int p, int r) {
		Random rand=new Random();
		//产生[p,r]区间的任意一个整数i
		int i=r-rand.nextInt(r-p+1);//或者 int i=p+rand.nextInt(r-p+1);
		
		return i;
	}
	public static void main(String[] args) {
		for(int i=0;i<50;i++){
			System.out.println(randomizedPartition( 5, 10));
		}
	}

}
