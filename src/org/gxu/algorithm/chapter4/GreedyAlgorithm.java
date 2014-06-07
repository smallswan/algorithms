package org.gxu.algorithm.chapter4;

import java.util.Arrays;

public class GreedyAlgorithm {
	public final static int MAXINT=Integer.MAX_VALUE;
	
	/**4.1 活动安排问题(从给定的活动集合中选出最大的相容活动子集合)
	 * 活动i与当前集合A中所有活动相容的充分必要条件是其开始时间s[i]不早于最近加入集合A中的活动j的技术时间，
	 * 即s[i]>=f[j]。
	 * @param start  活动开始时间表
	 * @param finish 活动结束时间表(非递减排序)
	 * */
	public static boolean[] Activities(int[] start,int[] finish){
		//活动是否被选入安排
		boolean[] activities=new boolean[start.length];
		//活动1最早开始，所以马上被选中
		activities[1]=true;
		//最近加入集合A中的活动j
		int j=1;
		
		for(int i=2;i<start.length;i++){
			if(start[i]>finish[j]){
				activities[i]=true;
				j=i;
			}else{
				activities[i]=false;
			}
		}
		return activities;
	}

	/**4.3 最优装载（在装载体积不受限制的情况下，将尽可能多的集装箱装上轮船）
	 * 采用重量最轻者优先装的贪心选择策略策略
	 * @param weights   集装箱重量
	 * @param capacity  轮船载重量
	 * */
	public static int[] loading(int[] weights,int capacity){
		//n=集装箱个数+1
		int n=weights.length;
		//解向量
		int[] x=new int[n];
		//存储重量由轻到重排序后的集装箱的序号
		int[] t=sort(weights);
		System.out.println(Arrays.toString(t));
		//x[i]=0,表明集装箱i未装入轮船，x[i]=1则相反
		for(int i=1;i<n;i++){
			x[i]=0;
		}
		//采用重量最轻者优先装的贪心选择策略策略
		for(int i=1;i<n&&weights[t[i]]<=capacity;i++){
			x[t[i]]=1;
			capacity-=weights[t[i]];
		}
		return x;
	}
	/**将集装箱重量按照由轻到重排序，并返回排好序的集装箱编号
	 * 这里使用冒泡排序算法，可以使用更加高效的排序算法！！！
	 * */
	private static int[] sort(int[] weights){   
		int n=weights.length;
		int[] t=new int[n];
		for(int i=0;i<n;i++){
			t[i]=i;
		}
		int temp;
		//对重量进行冒泡排序,并将原来的序号记下来存入t中
		for(int i=0;i<n-1;i++){
			for(int j=1;j<n-i;j++){
				if(weights[t[j-1]]>weights[t[j]]){
					temp=t[j-1];
					t[j-1]=t[j];
					t[j]=temp;
				}
			}
		}
		return t;
	}
	
	
	
	/**4.5 单源最短路径——dijkstra算法
	 * 
	 * @param n 顶点数
	 * @param v 源点
	 * @param dist 最短路径长度
	 * @param prev 前驱节点
	 * @param c 各顶点之间的距离
	 * */
	public static void dijkstra(int n,int v,int[] dist,int[] prev,int[][] c){
		//节点i是否属于集合s
		boolean[] s=new boolean[n+1];
		//初始化路径，和前驱节点
		for(int i=1;i<=n;i++){
			dist[i]=c[v][i];
			s[i]=false;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			if(dist[i]==MAXINT){
				prev[i]=0;
			}else{
				prev[i]=v;
			}
		}
		//初始化源点
		dist[v]=0;
		s[v]=true;
		//计算源点到其它节点的最短路径长度
		for(int i=1;i<n;i++){
			int temp=MAXINT;
			int u=v;
			//寻找顶点u
			for(int j=1;j<=n;j++){
				if(!s[j]&&dist[j]<temp){
					u=j;
					temp=dist[j];
				}
			}
			//将顶点u添加到集合s
			s[u]=true;
			//计算经过顶点u的最短最短路径
			for(int j=1;j<=n;j++){
				if(!s[j]&&c[u][j]<MAXINT){
					int newdist=dist[u]+c[u][j];
					if(newdist<dist[j]){
						dist[j]=newdist;
						prev[j]=u;
					}
				}
			}
			
			
		}
		
	}

	/**4.6 最小生成树Perm算法
	 * 设G=(V,E)是连同带权图，V={1,2,...,n}.
	 * 构造G的最小生成树的Prim算法的基本思想是：首先置S={1},然后，只要S是V的真子集，
	 * 就做如下贪心选择：选取满足条件i属于S，j属于V-S，且c[i][j]最小的边，并将顶点j添加
	 * 到S中。这个过程一直进行到S=V时为止。
	 * @param n 图的顶点数
	 * @param c 图中各边的权值
	 * */
	public static void perm(int n,int[][] c){
		//closest[j]是j在S中的邻接顶点
		int[] closest=new int[n+1];
		//lowcost[j]的值是c[j]closest[j]
		int[] lowcost=new int[n+1];
		//顶点j是否属于集合S
		boolean[] s=new boolean[n+1];
		
		//初始化
		s[1]=true;
		for(int i=2;i<=n;i++){
			lowcost[i]=c[1][i];
			closest[i]=1;
			s[i]=false;
		}
		
		for(int i=1;i<n;i++){
			//初始化最小的邻接顶点及最小的c[i][j]
			int min=MAXINT;
			int j=1;
			//找出最小的权值
			for(int k=2;k<=n;k++){
				if(lowcost[k]<min&&!s[k]){
					min=lowcost[k];
					j=k;
				}
			}
			//将当前最小的权值对应的顶点加入集合S
			System.out.println("节点"+j+"在S中的邻接顶点为"+closest[j]);
			s[j]=true;
			//修改顶点的邻接顶点和权值
			for(int k=2;k<=n;k++){
				if(c[j][k]<lowcost[k]&&!s[k]){
					lowcost[k]=c[j][k];
					closest[k]=j;
				}
			}
			
		}
		//打印closest,lowcost两个数组
		System.out.println("邻接顶点："+Arrays.toString(closest));
		System.out.println("顶点与其邻接顶点的距离："+Arrays.toString(lowcost));
		
	}
	
	
	
}
