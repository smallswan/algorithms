package org.gxu.algorithm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**敏感词过滤算法 
 * 参考：http://blog.csdn.net/lyflower/article/details/5890132
 * */
public class DeterministicFiniteAutomaton {
	//敏感词汇树
    private static Node rootNode = new Node('R');   
    //下面的代码只运行一次
    static{
    	//敏感词汇  应该可以从数据库中获取
    	String[] sentiveWords = {"DFA", "恶心", "DA","TMD","AV","鸡巴"};  
		createTree(sentiveWords);
	}
    
    /**在指定字符串中查找敏感词汇
     * @param content 需要进行敏感词汇过滤的字符串
     * @return List 返回敏感词汇的列表
     * */
    public static List<String> searchWords(String content){
    	char[] chars = content.toCharArray();
    	List<String> word = new ArrayList<String>();
    	List<String> words = new ArrayList<String>();
    	int a = 0;  
        Node node = rootNode;   
        while(a<chars.length) {   
            node = findNode(node,chars[a]);   
            if(node == null) {   
                node = rootNode;   
                a = a - word.size();   
                word.clear();   
            } else if(node.flag == 1) {   
                word.add(String.valueOf(chars[a]));   
                StringBuffer sb = new StringBuffer();   
                for(String str : word) {   
                    sb.append(str);   
                }   
                words.add(sb.toString());   
                a = a - word.size() + 1;   
                word.clear();   
                node = rootNode;   
            } else {   
                word.add(String.valueOf(chars[a]));   
            }   
            a++;   
        }
        return words;
    }
    /**使用敏感词汇构建tree
     * @param words 敏感词汇数组
     * */   
    private static void createTree(String[] words) {   
        for(String str : words) {   
            char[] chars = str.toCharArray();   
            if(chars.length > 0)   
                insertNode(rootNode, chars, 0);   
        }   
    }   
    /**递归地将敏感词汇的字符数组插入敏感词汇树
     * @param node 插入字符的的节点
     * @param cs   敏感词字符数组
     * @param index 敏感词字符数组的下标
     * */
    private static void insertNode(Node node, char[] cs, int index) {   
        Node n = findNode(node, cs[index]);   
        if(n == null) {   
            n = new Node(cs[index]);   
            node.nodes.add(n);   
        }   
           
        if(index == (cs.length-1))   
            n.flag = 1;   
               
        index++;   
        if(index<cs.length)   
            insertNode(n, cs, index);   
    }   
    /**在node节点中查找是否有等于c的节点
     * @param node 查找节点
     * @param c    需要查找的字符
     * */   
    private static Node findNode(Node node, char c) {   
        List<Node> nodes = node.nodes;   
        Node rn = null;   
        for(Node n : nodes) {   
            if(n.c==c) {   
                rn = n;   
                break;   
            }   
        }   
        return rn;   
    }   
       
    
       
    private static class Node {   
        public char c;   
        public int flag; //1：表示终结，0：延续   
        public List<Node> nodes = new ArrayList<Node>();   
           
        public Node(char c) {   
            this.c = c;   
            this.flag = 0;   
        }   
           
        public Node(char c, int flag) {   
            this.c = c;   
            this.flag = flag;   
        }   
    }   

}
