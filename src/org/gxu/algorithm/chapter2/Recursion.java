package org.gxu.algorithm.chapter2;
/**2.1 递归的概念*/
public class Recursion {
	//记录汉诺塔问题中移动盘子的次序
	static int time=0;
	
	/**2-1 阶乘（求整数n（n>=0）的阶乘）
	 * 
	 * */
	public static int factorial(int n){
		
		if(n==0||n==1){
			return 1;
		}if(n<0){
			throw new RuntimeException("整数必须大于或等于0");
		}
		return n*factorial(n-1);
	}
	
	/**2-2 Fibonacci数（斐波那契数）
	 * 数列应该从n=1开始，课本上从n=0开始，下面做了修改*/
	public static int fibonacci(int n){
		if(n==1||n==2){
			return 1;
		}
		return fibonacci(n-1)+fibonacci(n-2);
	}

	/**2-3 Ackerman函数(双递归函数)
	 * A(1,0)=2
	 * A(0,m)=1          m>=0;
	 * A(n,0)=n+2        n>=2;
	 * A(n,m)=A(A(n-1,m),m-1) n,m>=1; 
	 * 非递归方式：
	 * A(n,1)=2n (当n>=1)，A(n,1)=1 (当n=0);
	 * A(n,2)=2^n;
	 * ...
	 * 注意：
	 * 1、并非一切递归函数都能用非递归方式定义
	 * 2、由于Ackerman函数增长地非常快，容易导致栈内存溢出，请用小的数字测试
	 * 关于Ackerman函数更多请看百度百科：
	 * http://baike.baidu.com/view/7035175.htm?fr=aladdin
	 * */
	public static int ackerman(int n,int m){
		if(n==1&&m==0){
			return 2;
		}
		if(n==0&&m>=0){
			return 1;
		}
		if(n>=2&&m==0){
			return n+2;
		}
		return ackerman(ackerman(n-1,m),m-1);
	}

	/**2-4 全排列
	 * 设R={r1,r2,...rn}是要进行排序的n个元素，Ri=R-{ri},集合X中元素的全排列
	 * 记为Perm(X),(ri)Perm(X)表示在全排列Perm(X)的每一个排列前加上前缀ri得到的排列。
	 * R的全排列可以递归地定义为：
	 * 当n=1时，Perm(R)=(r),其中r是集合R中唯一的元素；
	 * 当n=2时，Perm(R)由(r1)Perm(R1),(r2)Perm(R2),...,(rn)Perm(Rn)构成
	 * @param list 进行排列的n个元素
	 * @param k    第一个元素的下标
	 * @param m    最后一个元素的下标
	 * */
	public static void perm( Object[] list,int k,int m){
		//产生list[k:m]的全排列
		if(k==m){
			//只剩下一个元素，输出排列
			for(int i=0;i<=m;i++){
				System.out.print(list[i]+" ");
			}
			System.out.println();
		}else{
			for(int i=k;i<=m;i++){
				Object temp=list[k];
				list[k]=list[i];
				list[i]=temp;
				
				perm(list,k+1,m);
				
				temp=list[k];
				list[k]=list[i];
				list[i]=temp;
				
			}
		}
		
	}

	/**2-5 整数划分问题(将正整数n表示成一系列正整数之和，
	 * n=n1+n2+...+nk(其中n1>=n2>=n3...>=nk,k>=1))
	 * 求正整数n的划分数,记为p(n)
	 * 将最大加数n1不大于m的划分个数记为q(n,m),
	 * 则正整数的划分p(n)=q(n,n)。
	 * @param n 进行整数划分的整数
	 * @param m 最大整数
	 * @return 返回总共有多少划分的情况
	 * */
	public static int q(int n,int m){
		if((n<1)||(m<1)){
			return 0;
		}
		if(n==1||m==1){
			return 1;
		}
		if(n<m){
			return q(n,n);
		}
		if(n==m){
			return q(n,m-1)+1;
		}
		//n>m>1
		return q(n,m-1)+q(n-m,m);
	}

	/**模拟汉诺塔
	 * 将A塔座上的n个圆盘移动到C塔座上
	 * @param n 盘子数
	 * @param A A塔（起始塔）
	 * @param B B塔（辅助塔）
	 * @param C C塔（目标塔）
	 * 
	 * */
	public static void hanoi(int n,char A,char B,char C){
		
		if(n==1){
			time++;
			System.out.println(time+":Move disk from "+A+" to "+C);
		}else{
			hanoi(n-1,A,C,B);
			time++;
			System.out.println(time+":Move disk from "+A+" to "+C);
			hanoi(n-1,B,A,C);
		}
	} 
	
}
