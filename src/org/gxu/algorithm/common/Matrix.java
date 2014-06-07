package org.gxu.algorithm.common;

public class Matrix {
	//行
	private int rows;
	//列
	private int cols;
	//存储矩阵的值
	private int values[][];

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	/**无参构造函数*/
	public Matrix(){
		
	}
	/**构造一个rows行，cols列的矩阵*/
	public Matrix(int rows, int cols) {
		this.rows=rows;
		this.cols=cols;
		this.values = new int[rows][cols];
	}
	/**使用二维数组matrix初始化矩阵*/
	public Matrix(int matrix[][]) {
		this(matrix.length, matrix[0].length);
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				this.values[i][j] = matrix[i][j];
	}
	@Override
	/**重写toString()方法*/
	public String toString() {
		String str = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				str += "  " + values[i][j];
			str += "\n";
		}
		return str;
	}

	/**矩阵相加*/ 
	public void add(Matrix b) {
		if(this.getRows()!=b.getRows()||this.getCols()!=b.getCols()){
			throw new RuntimeException("矩阵结构不相同，不能相加");
		}
		for (int i = 0; i < this.values.length; i++)
			for (int j = 0; j < this.values[i].length; j++)
				this.values[i][j] += b.values[i][j];
	}

	/** 矩阵想减*/
	public void dec(Matrix b) {
		if(this.getRows()!=b.getRows()||this.getCols()!=b.getCols()){
			throw new RuntimeException("矩阵结构不相同，不能相减");
		}
		for (int i = 0; i < this.values.length; i++)
			for (int j = 0; j < this.values[i].length; j++)
				this.values[i][j] -= b.values[i][j];
	}

	/**矩阵相乘*/ 
	public void multi(Matrix x) {
		if(this.getCols()!=x.getRows()){
			throw new RuntimeException("两个矩阵不能相乘");
		}
		int sum;
		int c[][] = new int[10][10];
		for (int i = 0; i < this.values.length; i++)
			for (int k = 0; k < x.values[i].length; k++) {
				sum = 0;
				for (int j = 0; j < x.values.length; j++)
					sum = this.values[i][j] * x.values[j][k] + sum;
				c[i][k] = sum;
			}
		for (int i = 0; i < this.values.length; i++) {
			for (int k = 0; k < x.values[i].length; k++)
				System.out.print(c[i][k] + "  ");
			System.out.print(" \n");
		}

	}

	/**矩阵转置*/ 
	public Matrix transpose() {
		Matrix trans = new Matrix(values[0].length, values.length);
		for (int i = 0; i < this.values.length; i++)
			for (int j = 0; j < this.values[i].length; j++)
				trans.values[j][i] = this.values[i][j];
		return trans;
	}

}
