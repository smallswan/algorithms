package org.gxu.algorithm.common;


public class BinaryTree {
	public int value;
	public BinaryTree left;
	public BinaryTree right;
	
	/**建立一棵新的树*/
	public void makeTree(int i,BinaryTree left,BinaryTree right){
		this.value=i;
		this.left=left;
		this.right=right;
	}
	
	/**存储一个值，不会重复存储*/
	public void store(int value){
		if(value<this.value){
			if(left==null){
				left=new BinaryTree();
				left.value=value;
			}else{
				left.store(value);
			}
		}else if(value>this.value){
			if(right==null){
				right=new BinaryTree();
				right.value=value;
			}else{
				right.store(value);
			}
		}
	}
	
	/**查找二叉树中是否存在value*/
	public boolean find(int value){
		System.out.println("happen "+this.value);
		if(value==this.value){
			return true;
		}else if(value>this.value){
			if(right==null){
				return false;
			}
			return right.find(value);
		}else{
			if(left==null){
				return false;
			}
			return left.find(value);
		}
	}
	
	/**前序遍历*/
	public void preList(){
		System.out.print(this.value+",");
		if(left!=null){
			left.preList();
		}
		if(right!=null){
			right.preList();
		}
	}
	
	/**中序遍历*/
	public void middleList(){
		
		if(left!=null){
			left.preList();
		}
		System.out.print(this.value+",");
		if(right!=null){
			right.preList();
		}
	}
	
	/**后序遍历*/
	public void afterList(){
		
		if(left!=null){
			left.preList();
		}
		if(right!=null){
			right.preList();
		}
		System.out.print(this.value+",");
	}


}
