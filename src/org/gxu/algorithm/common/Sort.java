package org.gxu.algorithm.common;

import java.util.ArrayList;

/**
 * Copyright (C), 2011-2012, GXU.
 * FileName: Sort.java 
 * @author teagle
 *
 * Description:SortTester.java中包含Sort类，Sort类中定义8种常见的排序方法
 * Date:2012-7-14
 * @version 1.0
 * Version: Function List:
 *  1、public void insertSort()，直接插入排序 
 *  2、public void shellSort()，希尔排序（最小增量排序）
 *  3、public void selectSort()，简单选择排序 
 *  4、public void bubbleSort()，冒泡排序 
 *  5、public void quickSort()，快速排序 
 *  6、public void mergingSort()，归并排序
 *  7、public void radixSort()，基数排序
 *  8、public void heapSort()，堆排序
 * 
 * History: 这是从网上搜集、并经过整理得到的的排序算法程序
 * 
 */
public class Sort {

	// 通过定义values变量，实现统一的数据结构奠定基础
	private int values[];

	// 统一构造函数
	public Sort(int array[]) {
		this.values = array;
	}

	// 统一的输出
	public void showValues() {
		for (int i = 0; i < values.length; i++)
			System.out.print(values[i] + " ");
	}

	/**
	 * 1、直接插入排序 public void insertSort()
	 * 基本思想：在要排序的一组数中，假设前面n-1(n>=2,n=1时，默认已经排好了)个数已经是排好顺序的，
	 * 现在要把第n个数插到前面的有序数中，使得这n个数 也是排好顺序的。如此反复循环，直到全部排好顺序。
	 */
	public void insertSort() {
		int temp = 0;
		for (int i = 1; i < values.length; i++) {
			int j = i - 1;
			temp = values[i];
			for (; j >= 0 && temp < values[j]; j--) {
				// 将大于temp的值整体后移一个单位
				values[j + 1] = values[j]; 
			}
			values[j + 1] = temp;
		}
		this.showValues();
	}

	/**
	 * 2、 希尔排序（最小增量排序）public void shellSort()
	 * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
	 * 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
	 */
	public void shellSort() {
		double d1 = values.length;
		int temp = 0;

		while (true) {
			d1 = Math.ceil(d1 / 2);
			int d = (int) d1;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < values.length; i += d) {
					int j = i - d;
					temp = values[i];

					for (; j >= 0 && temp < values[j]; j -= d) {
						values[j + d] = values[j];
					}
					values[j + d] = temp;
				}
			}

			if (d == 1)
				break;
		}

		this.showValues();
	}

	/**
	 * 3.简单选择排序 public void selectSort()
	 * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；然后在剩下的数当中再找最小的与第二个位置的数交换，
	 * 如此循环到倒数第二个数和最后一个数比较为止。
	 */
	public void selectSort() {
		// position存放当前需要排序的数中最小的数的下标
		int position = 0;
		// temp存放当前最小的数
		int temp=0;
		for (int i = 0; i < values.length; i++) {
			//先假设最小的数就是需要排序的位置上的数
			position = i;
			temp = values[i];
			//从后续的元素中找出最小的数，并记下该数的下标
			for (int j = i + 1; j < values.length; j++) {
				if (values[j] < temp) {
					temp = values[j];
					position = j;
				}
			}
			//
			values[position] = values[i];
			values[i] = temp;
		}
		this.showValues();
	}

	/**
	 * 4.冒泡排序 public void bubbleSort()
	 * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
	 * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
	 */
	public void bubbleSort() {

		int temp = 0;
		//n个数需要进行n-1轮冒泡
		for (int i = 0; i < values.length - 1; i++) {
			for (int j = 0; j < values.length - 1 - i; j++) {
				//如果前一个数大于后一个数
				if (values[j] > values[j + 1]) {
					temp = values[j];
					values[j] = values[j + 1];
					values[j + 1] = temp;
				}
			}
		}

		this.showValues();
	}

	/**
	 * 5.快速排序 public void quickSort()
	 * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
	 * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
	 */

	public void quickSort() {
		quick(values);
		this.showValues();
	}

	private void quick(int array[]) {
		// 查看数组是否为空
		if (array.length > 0)
			quickSort_(array, 0, array.length - 1);
	}

	private void quickSort_(int[] list, int low, int high) {
		if (low < high) {
			// 将list数组进行一分为二
			int middle = getMiddle(list, low, high);
			// 对低字表进行递归排序
			quickSort_(list, low, middle - 1);
			// 对高字表进行递归排序
			quickSort_(list, middle + 1, high);
		}
	}

	private int getMiddle(int[] list, int low, int high) {
		// 数组的第一个作为中轴
		int tmp = list[low];
		while (low < high) {
			while (low < high && list[high] >= tmp)
				high--;
			// 比中轴小的记录移到低端
			list[low] = list[high];

			while (low < high && list[low] <= tmp)
				low++;
			// 比中轴大的记录移到高端
			list[high] = list[low];
		}
		// 中轴记录到尾
		list[low] = tmp;
		// 返回中轴的位置
		return low;
	}

	/**
	 * 6、归并排序 public void mergingSort()
	 * 基本排序：归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表
	 * ，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
	 */
	public void mergingSort() {
		sort(values, 0, values.length - 1);
		this.showValues();
	}

	private void sort(int[] data, int left, int right) {
		if (left < right) {
			// 找出中间索引
			int center = (left + right) / 2;
			// 对左边数组进行递归
			sort(data, left, center);
			// 对右边数组进行递归
			sort(data, center + 1, right);
			// 合并
			merge(data, left, center, right);
		}
	}

	private void merge(int[] data, int left, int center, int right) {
		//中间数组
		int[] tmpArr = new int[data.length];
		int mid = center + 1;
		// third记录中间数组的索引
		int third = left;
		int tmp = left;
		while (left <= center && mid <= right) {
			// 从两个数组中取出最小的放入中间数组
			if (data[left] <= data[mid])
				tmpArr[third++] = data[left++];
			else
				tmpArr[third++] = data[mid++];
		}

		// 剩余部分依次放入中间数组
		while (mid <= right)
			tmpArr[third++] = data[mid++];

		while (left <= center)
			tmpArr[third++] = data[left++];

		// 将中间数组中的内容复制回原数组
		while (tmp <= right)
			data[tmp] = tmpArr[tmp++];

	}

	/**
	 * 7、基数排序 public void radixSort()
	 * 基本思想：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
	 * 这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
	 */
	public void radixSort() {
		sort(values);
		this.showValues();
	}

	public void sort(int[] array) {
		// 首先确定排序的趟数;
		int max = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] > max)
				max = array[i];
		}

		int time = 0;
		// 判断位数;
		while (max > 0) {
			max /= 10;
			time++;
		}
		// 建立10个队列;
		// List<ArrayList> queue=new ArrayList<ArrayList>();
		ArrayList<ArrayList> queue = new ArrayList<ArrayList>();

		for (int i = 0; i < 10; i++) {
			ArrayList<Integer> queue1 = new ArrayList<Integer>();
			queue.add(queue1);
		}

		// 进行time次分配和收集;
		for (int i = 0; i < time; i++) {
			// 分配数组元素;
			for (int j = 0; j < array.length; j++) {
				// 得到数字的第time+1位数;
				int x = array[j] % (int) Math.pow(10, i + 1)
						/ (int) Math.pow(10, i);
				ArrayList<Integer> queue2 = queue.get(x);
				queue2.add(array[j]);
				queue.set(x, queue2);
			}

			int count = 0;// 元素计数器;
			// 收集队列元素;
			for (int k = 0; k < 10; k++) {
				while (queue.get(k).size() > 0) {
					ArrayList<Integer> queue3 = queue.get(k);
					array[count] = queue3.get(0);
					queue3.remove(0);
					count++;
				}
			}
		}
	}

	/**
	 * 8， 堆排序 public void heapSort() 基本思想：堆排序是一种树形选择排序，是对直接选择排序的有效改进。
	 * 堆的定义如下：具有n个元素的序列
	 * （h1,h2,...,hn),当且仅当满足（hi>=h2i,hi>=2i+1）或（hi<=h2i,hi<=2i+1）
	 * (i=1,2,...,n/2)时称之为堆。
	 * 在这里只讨论满足前者条件的堆。由堆的定义可以看出，堆顶元素（即第一个元素）必为最大项（大顶堆）。完全二叉树可以很直观地表示堆的结构
	 * 。堆顶为根，其它为左子树、右子树。
	 * 初始时把要排序的数的序列看作是一棵顺序存储的二叉树，调整它们的存储序，使之成为一个堆，这时堆的根节点的数最大。然后将根节点与堆的最后一个节点交换
	 * 。然后对前面(n-1)个数重新调整使之成为堆。
	 * 依此类推，直到只有两个节点的堆，并对它们作交换，最后得到有n个节点的有序序列。从算法描述来看，堆排序需要两个过程
	 * ，一是建立堆，二是堆顶与堆的最后一个元素交换位置。 所以堆排序有两个函数组成。一是建堆的渗透函数，二是反复调用渗透函数实现排序的函数。
	 */
	public void heapSort() {
		heap(values);
		this.showValues();
	}

	public void heap(int[] a) {
		int arrayLength = a.length;

		// 循环建堆
		for (int i = 0; i < arrayLength - 1; i++) {
			// 建堆
			buildMaxHeap(a, arrayLength - 1 - i);
			// 交换堆顶和最后一个元素
			swap(a, 0, arrayLength - 1 - i);
			// System.out.println(Arrays.toString(a));
		}
	}

	private void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	// 对data数组从0到lastIndex建大顶堆
	private void buildMaxHeap(int[] data, int lastIndex) {
		// 从lastIndex处节点（最后一个节点）的父节点开始
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k保存正在判断的节点
			int k = i;
			// 如果当前k节点的子节点存在

			while (k * 2 + 1 <= lastIndex) {
				// k节点的左子节点的索引
				int biggerIndex = 2 * k + 1;
				// 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
				if (biggerIndex < lastIndex) {
					// 若果右子节点的值较大
					if (data[biggerIndex] < data[biggerIndex + 1]) {
						// biggerIndex总是记录较大子节点的索引
						biggerIndex++;
					}
				}

				// 如果k节点的值小于其较大的子节点的值
				if (data[k] < data[biggerIndex]) {
					// 交换他们
					swap(data, k, biggerIndex);
					// 将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
					k = biggerIndex;
				} else
					break;

			}
		}
	}
}
