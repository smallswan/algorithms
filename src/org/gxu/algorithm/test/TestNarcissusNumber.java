package org.gxu.algorithm.test;

import org.gxu.algorithm.common.NarcissusNumber;

public class TestNarcissusNumber {
	//将主函数放在前面，有助于理解程序的功能，对程序有一个大概的了解，进而是实现细节，符合人类认识由浅入深的，逐步求精的规律
    public static void main(String[] args) {
        long time = System.nanoTime();//用于计时，精确度达到纳秒级别
         for (int i = 1; i < 40; i++) {//输出1到39位的水仙花数
        //int i=21;
        System.out.println(i+"位水仙花的结果:");
        NarcissusNumber narcissusNumber = new NarcissusNumber(i);
        narcissusNumber.show();
         }
        time = System.nanoTime() - time;//总耗时
        System.out.println("time:\t" + time / 1000000000.0 + "s");//将纳秒转换为秒
    }   

}
