package org.gxu.algorithm.test;

import java.util.Arrays;
import java.util.Random;

import org.gxu.algorithm.common.Sort;

public class TestSort {
	public static void main(String[] args) {
		Random rand=new Random();

		int a[]=new int[1000];
		for(int i=0;i<1000;i++){
			a[i]=rand.nextInt(1000);
		}
		System.out.println(Arrays.toString(a));
		int b[] = new int[1000];
		int c[] = new int[1000];
		int d[] = new int[1000];
		int e[] = new int[1000];
		int f[] = new int[1000];
		int g[] = new int[1000];
		int h[] = new int[1000];
		
			
		System.arraycopy(a, 0, b, 0, 1000);
		System.arraycopy(a, 0, c, 0, 1000);
		System.arraycopy(a, 0, d, 0, 1000);
		System.arraycopy(a, 0, e, 0, 1000);
		
		System.arraycopy(a, 0, f, 0, 1000);
		System.arraycopy(a, 0, g, 0, 1000);
		System.arraycopy(a, 0, h, 0, 1000);
		
		Sort msa = new Sort(a);
		Sort msb = new Sort(b);
		Sort msc = new Sort(c);
		Sort msd = new Sort(d);
		Sort mse = new Sort(e);
		Sort msf = new Sort(f);
		Sort msg = new Sort(g);
		Sort msh = new Sort(h);

		long start = System.currentTimeMillis();
		System.out.print("\n ");
		msa.insertSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		System.out.print("\n ");
		msb.selectSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		System.out.print("\n ");
		msc.bubbleSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		System.out.print("\n ");
		msd.shellSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		System.out.print("\n ");
		mse.quickSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		System.out.print("\n ");
		msf.mergingSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		System.out.print("\n ");
		msg.radixSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		System.out.print("\n ");
		msh.heapSort();
		System.out.println("time:"+(System.currentTimeMillis() - start));
	}

}
