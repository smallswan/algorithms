package org.gxu.algorithm.chapter3;
/**动态规划
 * 算法适用于求最优解问题，通常有4个步骤设计
 * (1)找出最优解的性质，并刻画出其结构
 * (2)递归地定义最优值
 * (3)以自底向上的方式计算出最优值
 * (4)根据计算最优值时得到的信息，构造最优解
 * */
public class DynamicProgramming {
	
	/**3.1 矩阵连乘问题(矩阵连乘积最优计算次序问题)
	 * (1)分析最优解的结构
	 * 设矩阵连乘A[i]A[i+1]...A[j]，简记为A[i:j]。
	 * 设计算A[i:j]，i<=j<=n，所需的最少计算次数为m[i][j]，
	 * 则原问题的最优值为m[1][n].
	 * (2)建立递归关系
	 * m[i][j]可以递归地定义为：
	 * m[i][j]=0                                      i=j
	 *         min{m[i][k]+m[k+1][j]+p[i-1]p[k]p[j]}  i<j。
	 * m[i][j]的断开位置k记为s[i][j],在计算出最优值m[i][j]后，可以递归地有s[i][j]构造出相应的最优解。
	 * 
	 * (3)
	 * @param p 矩阵的维度(矩阵Ai的维度为p[i-1,i],i=1,2,...,n)
	 * @param n 矩阵的个数 
	 * @param m 最优值数组 
	 * @param s 最优断开位置数组
	 * 
	 * */
	public static void matrixChain(int[] p,int n,int[][] m,int[][] s){
		//初始化m[i][i]=0(当i=j时，A[i:j]=A[i]为单个矩阵，无需计算)
		for(int i=1;i<=n;i++){
			m[i][i]=0;
		}
		//依次计算m[i][i+1],i=1,2,...,n-1(矩阵链长长度为2)；
		//m[i][i+2],i=1,2,...,n-2(矩阵链长为3)...
		//在计算m[i][j]时，只用到已经计算出的m[i][k]和m[k+1][j]
		//r控制矩阵链的长度
		for(int r=2;r<=n;r++){
			//遍历m[i][j]
			for(int i=1;i<=n-r+1;i++){
				int j=i+r-1;
				m[i][j]=m[i+1][j]+p[i-1]*p[i]*p[j];
				//设最优断开的位置初始值为i
				s[i][j]=i;
				//寻找最优断开位置
				for(int k=i+1;k<j;k++){
					//根据已求出的m[i][k]、m[k+1][j],计算m[i][j],并求出断开的位置s[i][j]
					int t=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
					if(t<m[i][j]){
						m[i][j]=t;
						s[i][j]=k;
					}
				}
				
			}
		}
		
	}
	
	/**(4)构造最优解
	 * 输出A[1:n]的最优解
	 * @param i 矩阵标号i
	 * @param j 矩阵标号j
	 * @param s 最优断开位置s
	 * */
	public static void traceback(int i,int j,int[][] s){
		if(i==j){
			return;
		}
		traceback(i,s[i][j],s);
		traceback(s[i][j]+1,j,s);
		//矩阵连乘A[i:j],断开位置为s[i][j]
		/*System.out.print("Multiply A"+i+","+s[i][j]);
		System.out.println("and A"+(s[i][j]+1)+","+j);*/
		System.out.println("矩阵连乘A["+i+":"+j+"],断开位置"+s[i][j]);
		
	}

	/**3.3 最长公共子序列问题
	 * (给定两个序列X={x[1],x[2],...,x[m]}和Y={y[1],y[2],...,y[n]},找出序列X和序列Y的最长公共子序列)
	 * (1)分三种情况
	 * (2)c[i][j]存储序列Xi和Yj的最长公共子序列的长度
	 * 递归关系如下：
	 * c[i][j]=
	 * 0                                           i=0,j=0
	 * c[i-1][j-1]+1(子问题1)                      i,j>0;x[i]=y[j]
	 * max{c[i][j-1](子问题3),c[i-1][j](子问题2)}  i,j>0'x[i]=y[j]
	 * (3)计算最优值
	 * 
	 * @param m 序列X的长度
	 * @param n 序列Y的长度
	 * @param x 序列X
	 * @param y 序列Y
	 * @param c 存储序列Xi和Yj的最长公共子序列的长度
	 * @param b 记录c[i][j]是由哪个子问题解出来的
	 * 
	 * */
	public static void LCSLength(int m,int n,char[] x,char[] y,int[][] c,int[][] b){
		int i,j;
		//如果序列Y为空序列
		for(i=1;i<=m;i++){
			c[i][0]=0;
		}
		//如果序列X为空序列
		for(j=1;j<=n;j++){
			c[0][j]=0;
		}
		//遍历序列X和序列Y
		for(i=1;i<=m;i++){
			for(j=1;j<=n;j++){
				if(x[i]==y[j]){
					c[i][j]=c[i-1][j-1]+1;
					//子问题1
					b[i][j]=1;
				}else if(c[i-1][j]>=c[i][j-1]){
					c[i][j]=c[i-1][j];
					//子问题2
					b[i][j]=2;
				}else{
					c[i][j]=c[i][j-1];
					//子问题3
					b[i][j]=3;
				}
			}
		}
		
		
	}

	/**(4)构造最长公共子序列
	 * 算法的计算时间为O(m+n)
	 * @param i 序列X的长度
	 * @param j 序列Y的长度
	 * @param x 序列X(最长公共子序列必定为子集)
	 * @param b 记录c[i][j]是由哪个子问题解出来的
	 * */
	public static void LCS(int i,int j,char[] x,int[][] b){
		if(i==0||j==0)return;
		if(b[i][j]==1){
			LCS(i-1,j-1,x,b);
			System.out.print(x[i]);
		}else if(b[i][j]==2){
			LCS(i-1,j,x,b);
		}else{
			LCS(i,j-1,x,b);
		}
		
	}
	
	/**3.5 凸多边形最优三角剖分问题
	 * 给定凸多边形P={v[0],v[1],...,v[n-1]},以及定义由凸多边形的边和弦组成的三角形上的权函数w。
	 * 要求确定该凸多边形的三角剖分，使得该三角剖分所对应的权，即三角剖分中诸三角形上权之和为最小。  
	 * 
	 * 计算凸(n+1)边形P={v[0],v[1],...,v[n]}的最优三角剖分的动态规划算法minWeightTriangulation()
	 * 占用O(n^2)空间，耗时O(n^3)。
	 * @param n 
	 * @param t t[i][j]，1<=i<j<=n为凸子多边形{v[i-1],v[i],...,v[j]}的最优三角剖分所对应的权函数的值，即其最优值
	 * 省略权函数w
	 * 
	 * */
	public static void minWeightTriangulation(int n,int[][] t,int[][] s){
		for(int i=1;i<=n;i++){
			t[i][i]=0;
		}
		for(int r=2;r<=n;r++){
			for(int i=1;i<=n-r+1;i++){
				int j=i+r-1;
				t[i][j]=t[i+1][j];
				s[i][j]=i;
				for(int k=i+1;k<i+r-1;k++){
					int u=t[i][k]+t[k+1][j];
					if(u<t[i][j]){
						t[i][j]=u;
						s[i][j]=k;
					}
				}
			}
		}
	}
	
	/**3.10 0-1背包问题的动态规划算法knapsack
	 * 
	 * */
	public static void knapsack(int v,int w,int c,int n,int[][] m){
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
