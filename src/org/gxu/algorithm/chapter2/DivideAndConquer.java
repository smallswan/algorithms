package org.gxu.algorithm.chapter2;

import java.util.Random;

/**递归与分治策略
 * 分治法的设计思想是，将一个难以解决的大问题，分割成一些规模较小的相同的问题，
 * 以便分而治之，各个击破。
 * 直接或间接地调用自身的算法称为递归算法。
 * 用函数自身给出定义的函数称为递归函数。
 * 每个递归函数都必须有非递归定义的初始值，否则递归函数就无法计算。
 * */
public class DivideAndConquer {
	private static int title=0;
	private static int[][] board=new int[8][8];
	/**2.3二分搜索
	 * 在已经排好序的数组a，中查找x元素
	 * 最坏情况下使用O(logn)时间完成搜索任务
	 * @param a 按照从小到大排列好的数组
	 * @param x 被查找的元素
	 * @return 元素x所在的位置的下标
	 * */
	public static int binarySearch(int[] a,final int x ){
		int left=0;
		int right=a.length-1;
		//在a[0]<=a[1]<=...<=a[n-1]中查找元素x
		//找到x后返回其在数组中的位置，否则返回-1
		while(left<=right){
			int middle=(left+right)/2;
			if(x==a[middle]){
				return middle;
			}
			//在右半部搜索
			if(x>a[middle]){
				left=middle+1;
			}else{//在左半部搜索
				right=middle-1;
			}
			
		}
		
		return -1;
	}
	
	/**改写的二分搜索
	 * 当找不到元素x时，返回小于x的最大元素位置i和大于x的最小元素的位置j,
	 * 如果找到元素x，则i=j,即为x在数组中位置
	 * @param a 按照从小到大排列好的数组
	 * @param x 被查找的元素
	 * @return 1（找到元素的情况）或者0（没有找到元素的情况）
	 * */
	public static int binarySearch(int[] a,final int x,int left,int right){
		int i,j,middle;
		while(left<=right){
			middle=(left+right)/2;
			if(x==a[middle]){
				i=j=middle;
				System.out.println("i="+i+",j="+j);
				return 1;
			}
			if(x>a[middle]){
				left=middle+1;
			}else{
				right=middle-1;
			}		
		}
		
		i=right;
		j=left;	
		System.out.println("i="+i+",j="+j);
		return 0;
	}
	/**2.6 棋盘覆盖
	 * @param tr 棋盘左上角的方格的行号
	 * @param tc 棋盘左上角的方格的列号
	 * @param dr 特殊方格的行号
	 * @param dc 特殊方格的列号
	 * @param size 棋盘的规格
	 * 这个算法有错误。
	 * */
	public static void chessBoard(int tr,int tc,int dr,int dc,int size){
		if(size==1)return;
		//L型骨牌号
		int t=title++;
		//分割棋盘
		int s=size/2;
		
		//覆盖左上角的子棋盘
		if(dr<tr+s&&dc<tr+s){
			//特殊方格在此棋盘中
			chessBoard(tr,tc,dr,dc,s);		
		}else{//此棋盘中无特殊方格
			board[tr+s-1][tc+s-1]=t;
			//覆盖其余方格
			chessBoard(tr,tc,tr+s-1,tc+s-1,s);
		}
		
		//覆盖右上角的子棋盘
		if(dr<tr+s&&dc>=tc+s){
			//特殊方格在此棋盘中
			chessBoard(tr,tc+s,dr,dc,s);		
		}else{//此棋盘中无特殊方格
			board[tr+s-1][tc+s]=t;
			//覆盖其余方格
			chessBoard(tr,tc+s,tr+s-1,tc+s,s);
		}
		
		//覆盖左下角的子棋盘
		if(dr>=tr+s&&dc<tc+s){
			//特殊方格在此棋盘中
			chessBoard(tr+s,tc,dr,dc,s);		
		}else{//此棋盘中无特殊方格
			board[tr+s][tc+s-1]=t;
			//覆盖其余方格
			chessBoard(tr+s,tc,tr+s,tc+s-1,s);
		}
		
		//覆盖右下角的子棋盘
		if(dr>=tr+s&&dc>=tc+s){
			//特殊方格在此棋盘中
			chessBoard(tr+s,tc+s,dr,dc,s);		
		}else{//此棋盘中无特殊方格
			board[tr+s][tc+s]=t;
			//覆盖其余方格
			chessBoard(tr+s,tc+s,tr+s,tc+s,s);
		}
	}
	/**显示棋盘内容*/
	public static void showBoard(){
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				System.out.printf("%2d ", board[i][j]);
			}
			System.out.println();
		}
	}
	

	/**2.7 合并排序
	 * 算法仍存在设计上的错误：
	 * 1)当数组元素个数为奇数时，会出现数组越界异常
	 * 2)并不是每次都能正确排序
	 * @param a 需要排序的数组
	 * @param n 数组元素的个数
	 * */
	public static int[] mergeSort(int[] a,int n){
		int[] b=new int[n];
		int s=1;
		while(s<n){
			//合并到数组b
			mergePass(a,b,s,n);
			s+=s;
			//合并到数组a
			mergePass(b,a,s,n);
			s+=s;
		}
		return a;
	}

	/**合并排序好的相邻数组段，具体合并由merge方法完成*/
	private static void mergePass(int[] x, int[] y, int s, int n) {
		//合并大小为s的相邻子数组
		int i=0;
		while(i<=n-2*s){
			merge(x,y,i,i+s-1,i+2*s-1);
			i=i+2*s;
		}
		//剩余的元素少于2个
		if(i+s<n){
			merge(x,y,i,i+s+1,n-1);
		}else{
			for(int j=i;j<=n-1;j++){
				y[j]=x[j];
			}
		}
		
	}
	/**合并数组
	 * @param c      数组c
	 * @param d      数组d,将数组c合并的结果存放到数组d
	 * @param start  数组c的开始下标
	 * @param middle 数组c的结束下标
	 * @param right  数组d的结束下标
	 * */
	private static void merge(int[] c, int[] d, int start, int middle, int right) {
		//合并c[1:m]和c[m+1:r]到d[l:r]
		int i=start,j=middle+1,k=start;
		//依次将c[1:m]和c[m+1:r]的元素合并到d[l:r]
		while(i<=middle&&j<=right){
			if(c[i]<=c[j]){
				d[k++]=c[i++];
			}else{
				d[k++]=c[j++];
			}
		}
		//如果c[l:m]中最大的元素小于c[m+1:r]的某个元素
		if(i>middle){
			for(int q=j;q<=right;q++){
				d[k++]=c[q];
			}
		}else{
			for(int q=i;q<=middle;q++){
				d[k++]=c[q];
			}
		}
		
	}

	/**2.8 快速排序
	 * 运用递归和分治的思想设计算法
	 * */
	public static int[] quickSort(int[] arr,int p,int r ){
		if(p<r){
			//基准元素排序后的位置存入q
			int q=partition(arr,p,r);
			//对左半段进行排序
			quickSort(arr,p,q-1);
			//对右半段进行排序
			quickSort(arr,q+1,r);
		}
		return arr;
	}
	/**快速排序算法的关键——对数组进行划分
	 * 返回原数组下标为p的元素在排序后所在的位置的下标
	 * */
	private static int partition(int[] arr, int p, int r) {
		int i=p,j=r+1;
		//基准元素x,就选数组下标最小的元素作为基准元素
		int x=arr[p];
		//将<x的元素交换到左边的区域
		//将>x的元素交换到右边的区域
		int temp;
		while(true){
			while(arr[++i]<x&&i<r);
			while(arr[--j]>x);
			if(i>=j){
				break;
			}
			//如果i<j，表明i,j对应的元素不符合排序，需要交换位置
			temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
		}
		//将基准元素和其最终排在的位置上的元素交换位置
		arr[p]=arr[j];
		arr[j]=x;
		//返回基准元素的位置
		return j;
	}

	/**2.9 线性时间选择
	 * 从给定的n个元素中找出第k(1<=k<=n)小的元素
	 * 算法randomizedSelect()可以在O(n)的时间内找出n个输入元素中第k小的元素
	 * */
	
	public static int randomizedSelect(int[] a,int p,int r,int k){
		if(p==r){
			return a[p];
		}
		int i=randomizedPartition(a,p,r);
		int j=i-p+1;
		
		if(k<=j){
			return randomizedSelect(a, p, i, k);
		}else{
			return randomizedSelect(a, i+1, r, k-j);
		}
		
		
	}
	/**随机划分函数
	 * 随机从a[p:r]中选择一个元素作为基准元素
	 * */
	private static int randomizedPartition(int[] a, int p, int r) {
		Random rand=new Random();
		//产生[p,r]区间的任意一个整数i
		int i=r-rand.nextInt(r-p+1);
		int temp=a[i];
		a[i]=a[p];
		a[p]=temp;
		return partition(a,p,r);
	}
}


