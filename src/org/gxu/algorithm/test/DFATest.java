package org.gxu.algorithm.test;

import java.util.Iterator;
import java.util.List;

import org.gxu.algorithm.util.DeterministicFiniteAutomaton;
/**敏感词汇过滤测试*/
public class DFATest {
	 public static void main(String[] args) {   
	    	
	        
	        String text="Hello DFAAV World DFA, HaHa! 恶心 TMD AV是什么鸡巴东东";
	        List<String> words=DeterministicFiniteAutomaton.searchWords(text);
	        
	        Iterator Iwords=words.iterator();
	        while(Iwords.hasNext()){
	        	String word=(String)Iwords.next();
	        	System.out.print(" "+word);
	        }
	    }   

}
