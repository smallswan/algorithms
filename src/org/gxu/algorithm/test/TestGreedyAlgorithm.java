package org.gxu.algorithm.test;

import java.util.Arrays;

import org.gxu.algorithm.chapter4.GreedyAlgorithm;

public class TestGreedyAlgorithm {
	public final static int MAXINT=Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		//活动开始时间
		int[] start={0,1,3,0,5,3,5,6,8,8,2,12};
		//活动结束时间
		int[] finish={0,4,5,6,7,8,9,10,11,12,13,14};
		//注意传进来的参数已经按照活动结束时间排好序了
		boolean[] activities=GreedyAlgorithm.Activities(start, finish);
		
		for(int i=1;i<start.length;i++){
			if(activities[i]){
				System.out.print("activities"+i+" ");
			}
		}
		
		System.out.println();
		//集装箱重量,由于集装箱编号是从1开始的，所以添加一个重量0作为数组的第一个元素
		//也方便进行排序
		int[] weights={0,12,8,9,11,13,10,14,15};
		int[] x=GreedyAlgorithm.loading(weights, 50);
		System.out.println("解向量："+Arrays.toString(x));
		
		//测试最短路径
		int[][] c={{0,0,0 ,0,0,0},
				   {0,0,10,MAXINT,30,100},
				   {0,10,0,50,MAXINT,MAXINT},
				   {0,MAXINT,50,0,20,10},
				   {0,30,MAXINT,20,0,60},
				   {0,100,MAXINT,10,60,0}};	
		
		int[] dist=new int[6];
		int[] prev=new int[6];
		GreedyAlgorithm.dijkstra(5, 1, dist, prev, c);
		System.out.println(Arrays.toString(dist));
		System.out.println(Arrays.toString(prev));
		
		// 测试最小生成树
		int[][] cc = { { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 6, 1, 5, MAXINT, MAXINT },
				{ 0, 6, 0, 5, MAXINT, 3, MAXINT }, 
				{ 0, 1, 5, 0, 5, 6, 4 },
				{ 0, 5, MAXINT, 5, 0, MAXINT, 2 },
				{ 0, MAXINT, 3, 6, MAXINT, 0, 6 },
				{ 0, MAXINT, MAXINT, 4, 2, 6, 0 } };
		
		GreedyAlgorithm.perm(6, cc);
		  
	}
    
}
