package org.gxu.algorithm.util;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.gxu.algorithm.common.TreeNode;

public class DFAReplace {

	    /** 
	     * 根节点 
	     */  
	    private TreeNode rootNode = new TreeNode();  
	      
	    /** 
	     * 关键词缓存 
	     */  
	    private ByteBuffer keywordBuffer = ByteBuffer.allocate(1024);     
	      
	    /** 
	     * 关键词编码 
	     */  
	    private String charset = "utf-8";  
	  
	    /** 
	     * 创建DFA 
	     * @param keywordList 
	     * @throws UnsupportedEncodingException  
	     */  
	    public void createKeywordTree(List<String> keywordList) throws UnsupportedEncodingException{  
	        for (String keyword : keywordList) {  
	            if(keyword == null) continue;  
	            keyword = keyword.trim();  
	            byte[] bytes = keyword.getBytes(charset);  
	            TreeNode tempNode = rootNode;  
	            for (int i = 0; i < bytes.length; i++) {  
	                int index = bytes[i] & 0xff;   
	                TreeNode node = tempNode.getSubNode(index);  
	                if(node == null){  
	                    node = new TreeNode();  
	                    tempNode.setSubNode(index, node);  
	                }  
	                tempNode = node;  
	                if(i == bytes.length - 1){  
	                    tempNode.setKeywordEnd(true);    
	                }  
	            }  
	        }
	    }  
	      
	   
	    public String searchKeyword(String text) throws UnsupportedEncodingException{  
	        return searchKeyword(text.getBytes(charset));  
	    }  
	   
	    public String searchKeyword(byte[] bytes){  
	        StringBuilder words = new StringBuilder();  
	        if(bytes == null || bytes.length == 0){  
	            return words.toString();  
	        }  
	        TreeNode tempNode = rootNode;  
	        int rollback = 0;   
	        int position = 0; 
	        while (position < bytes.length) {  
	            int index = bytes[position] & 0xFF;  
	            keywordBuffer.put(bytes[position]); 
	            tempNode = tempNode.getSubNode(index);  
	            if(tempNode == null){
	                position = position - rollback;
	                rollback = 0;  
	                tempNode = rootNode;      
	                keywordBuffer.clear();  
	            }  
	            else if(tempNode.isKeywordEnd()){  
	                keywordBuffer.flip();  
	                for (int i = 0; i <= rollback; i++) {
	                		bytes[position-i] = 42;
					}
	                keywordBuffer.limit(keywordBuffer.capacity());  
	                rollback = 1;  
	            }else{   
	                rollback++; 
	            }  
	            position++;  
	        }  
	        String result = null;
	         try {
	        	 result  =  new String(bytes,"utf-8");  
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	    }  
	      
	    public void setCharset(String charset) {  
	        this.charset = charset;  
	    } 
	


}
