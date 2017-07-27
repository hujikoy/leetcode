package test;

import java.util.*;


//请实现一个算法，确定一个字符串的所有字符是否全都不同。这里我们要求不允许使用额外的存储结构。
//给定一个string iniString，请返回一个bool值,True代表所有字符全都不同，False代表存在相同的字符。保证字符串中的字符为ASCII字符。字符串的长度小于等于3000。

public class Different {
    public static boolean checkDifferent(String iniString) {
        // write code here
    	if (iniString == null) {
			return true;
		}
    	
    	int lenth = iniString.length();
    	int[] a = new int[256];
    	
    	// initialize
    	for (int i = 0; i < a.length; i++) {
			a[i] = 0;
		}
    	
    	int position = 0;
    	for (int i = 0; i < lenth; i++) {
    		position = iniString.charAt(i);
    		System.out.println(position);
			if (a[position] != 0) {
				return false;
			} else {
				a[position] = 1;
			}
		}
    	return true;
    }
    
    public void connect(TreeLinkNode root) {
        if (root == null) {
        	return;
        } 
        // recurrency
        else if (root.left != null) {
        	root.left.next = root.right;
        	TreeLinkNode leftTemp, rightTemp;
        	leftTemp = root.left.right;
        	rightTemp = root.right.left;
        	while (leftTemp != null) {
				leftTemp.next = rightTemp;
				leftTemp = leftTemp.right;
				rightTemp = rightTemp.left;
			}
        	connect(root.left);
        	connect(root.right);
        	return;
        }
        TreeLinkNode head, temp;
        head = temp = root;
        while(head != null) {
        	while(temp != null) {
        		if (temp.left != null && temp.right != null) {
					temp.left.next = temp.right;
				}
        		if (temp.right != null && temp.next != null) {
        			// the next temp must have child
        			temp.right.next = temp.next.left;
        		}
        		temp = temp.next;
        	}
        	head = head.left;
        	temp = head;
        }
        return;
    }
    
    
    
    public static void main(String[] args) {
    	
	}
    
}