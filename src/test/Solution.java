package test;
import java.lang.Math;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
	
	public int maxProfit(int[] prices) {
        /*
		if (prices == null) {
			return 0;
		} else if (prices.length == 1) {
			return 0;
		}
        
        int profit = 0;
        int buyprice = -1;
        int sellprice = -1;
        
        for (int i = 0; i < prices.length-1; i++) {
			if(prices[i+1] > prices[i]) {
				// next price is higher
				if (buyprice < 0) {
					// if we have not buy one
					buyprice = prices[i];
				}
				sellprice = prices[i+1];
			} else {
				// stock price decreases
				if (buyprice >= 0) {
					// if we hold, just sell it
					profit += sellprice - buyprice;
					buyprice = sellprice = -1;
				}
			}
		}
        
        if (buyprice >= 0) {
        	// if we hold, just sell it
			profit += sellprice - buyprice;
			buyprice = sellprice = -1;
		}
		*/
		
		if(prices == null) {
			return 0;
		}
		
		int maxProfit = 0;
		int profit = 0;
		
		for (int i = 1; i < prices.length; i++) {
			profit += prices[i] - prices[i-1];
			profit =  profit > 0 ? profit : 0; 
			maxProfit = profit > maxProfit ? profit : maxProfit;
		}
        
        return maxProfit;
    }
	
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int depth = 1;
		
		depth = findDepth(root.left, root.right);
        return depth;
    }
	
	int findDepth(TreeNode leftNode, TreeNode rightNode) {
		if(leftNode == null && rightNode == null) {
			return 1;
		}
		else if(leftNode == null) {
			return 1 + findDepth(rightNode.left, rightNode.right);
		}
		else if (rightNode == null) {
			return 1 + findDepth(leftNode.left, leftNode.right);
		}
		else {
			int maxDepth = 1;
			int leftDepth = findDepth(leftNode.left, leftNode.right);
			int rightDepth = findDepth(rightNode.left, rightNode.right);
			maxDepth += leftDepth > rightDepth ? leftDepth : rightDepth;
			return maxDepth;
		}
	}
	
	public static int reverse(int x) {
		StringBuffer retStr = new StringBuffer();
		String getNum = Integer.toString(Math.abs(x));
		if(x < 0) {
			retStr.append("-");
		}
		
		int lastIndex = getNum.length() - 1;
		for (int i = 0; i < getNum.length(); i++) {
			retStr.append(getNum.charAt(lastIndex-i));
		}
		
		long checkNum = Long.parseLong(retStr.toString());
		if (checkNum < 0x80000000 || checkNum > 0x79999999) {
			return 0;
		} else {
			return (int)checkNum;
		}
	}
	
	public static boolean isPalindrome(int x) {
        if (x < 0) {
			return false;
		}
        
        String Number = Integer.toString(x);
        for (int i = 0; i < Number.length()/2; i++) {
			if (Number.charAt(i) != Number.charAt(Number.length()-1-i)) {
				return false;
			}
		}
        return true;
    }
	
	public static void rotate(int[][] matrix) {
        if (matrix == null) {
        	return;
        }
        
        int[][] new_matrix = new int[matrix.length][matrix.length];
        
        for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				new_matrix[j][matrix.length - i - 1] = matrix[i][j];
			}
		}
        
        for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = new_matrix[i][j];
			}
		}
    }
	
	public static int uniquePaths(int m, int n) {
        if (m==0 || n==0) {
			return 0;
		}
		int[][] matrix = new int[m][n];
        
		matrix[0][0] = 1;
		
		int left = 0;
		int above = 0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				above = i>0 ? matrix[i-1][j] : 0;
				left  = j>0 ? matrix[i][j-1] : 0;
				matrix[i][j] += above + left;
			}
		}
		
		return matrix[m-1][n-1];
    }

	public int climbStairs(int n) {
		if (n == 0) {
			return 0;
		}
		/* 这段代码不好，因为不需要建立一个数组空间
        int[] array = new int[n+1];
        array[0] = array[1] = 1;
        for (int i = 2; i < array.length; i++) {
			array[i] = array[i-2] + array[i-1];
		}
        return array[array.length-1];*/
		
		// 改进
		int prev = 0; // 前2个
		int last = 1; // 前1个
		int ret  = 0;
		for (int i = 0; i < n; i++) {
			ret += prev + last;
			prev = last;
			last = ret;
		}
		return ret;
		
    }
	
	public static int numTrees(int n) {
        if (n==0) {
			return 0;
		}
        
		int[] array = new int[n+1];
        array[0] = 1;
        
        // calculate the number of tress
        int sum = 0;
        
        for (int i = 1; i < array.length; i++) {
			//  i from 1 to n
        	for (int j = 1; j <= i; j++) {
				sum += array[j-1] * array[i-j];
			}
        	array[i] = sum;
        	sum = 0;
		}
		
        return array[n];
    }
	
	public static String intToRoman(int num) {
        // change int to roman nunbers
		int temp = num;
		int bit = 0;
		String ret = "";
		
		String[] unit = {"I", "V", "X", "L", "C", "D", "M", "M", "M"};
		
		for (int i = 0; i < 4; i++) {
			int bit10 = pow(10, 3-i);
			bit = temp / bit10;
			System.out.println("bit " + (i+1) + " = " + bit);
			temp = temp - bit * bit10;
			ret += romanBit(unit[2*(3-i)], unit[2*(3-i)+1], unit[2*(3-i)+2], bit);
		}
		return ret;
    }
	
	static int pow(int number, int times) {
		int sum = 1;
		for (int i = 0; i < times; i++) {
			sum = sum * number;
		}
		return sum;
	}
	
	public static String romanBit(String smaller, String medium, String bigger, int bit) {
		if (bit == 0) {
			return "";
		} else if (bit == 4) {
			return smaller + medium;
		} else if (bit == 9) {
			return smaller + bigger;
		} else {
			String ret="";
			if (bit > 4) {
				ret += medium;
			}
			for (int i = 0; i < bit%5; i++) {
				ret += smaller;
			}
			return ret;
		}
	}
	
	// 一个数组当中，只有一个数出现1次，其余出现2次
    public static int singleNumber(int[] A) {
//        if(A == null) {
//            return 0;
//        }
//        
//        int ret = A[0];
//        for(int i=1; i<A.length; i++) {
//			ret = ret ^ A[i];
//        }
//        return ret;
    	int one = 0, two = 0, three = 0, tmp;
    	int i = A.length-1;
        while (i>=0) {
            // 获取当前要统计的数字
            tmp = A[i]; 
            System.out.println("tmp:   " + toBinary(tmp));
            // 之前已经出现2次1的位且当前数字中又为1，则更新为出现3次
            three = two & tmp; 
            System.out.println("three: " + toBinary(three));
            // 清空上一步操作中出现3次1的位
            two &= ~three;  // 在two中清空
            System.out.println("two:   " + toBinary(two));
            tmp &= ~three;  // 当前数字也要清空，以防重复统计
            System.out.println("tmp:   " + toBinary(tmp));
            // 之前已经出现1次1的位且当前数字中又为1，则更新为出现2次
            two |= one & tmp;
            System.out.println("two:   " + toBinary(two));
            // 之前没有出现且当前数字中为1，更新为出现1次
            // 或者之前出现次数为1次，且当前数字中没有出现，仍旧为1次
            one ^= tmp;
            System.out.println("one:   " + toBinary(one));
            i--;
        }
        return one;
    }
    
    static String toBinary(int number) {
    	String binary = Integer.toBinaryString(number);
    	String ret = "";
    	for (int i = 0; i < (4 - binary.length()); i++) {
			ret += "0";
		}
    	return ret + binary;
    }
    
    public boolean hasCycle(ListNode head) {
        /*
    	ListNode temp = head;
    	while(temp != null) {
    		if (temp.val == 0xfffffffe) {
				return true;
			} else {
				temp.val = 0xfffffffe;
				temp = temp.next;
			}
    	}
    	*/
    	ListNode fast, slow;
    	fast = slow = head;
    	while(fast!=null && fast.next!=null) {
    		if (fast == slow) {
				return true;
			}
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	return false;
    }
    
    // Sort colors
    public void sortColors(int[] A) {
        if (A == null) {
            return;
        }
        int p1, p2;
        p1 = p2 = -1;
        for (int i = 0; i < A.length; i++) {
        	System.out.println("p1: " + p1 + ", p2: " + p2 + ", A[" + i + "]: " + A[i]);
        	int temp = -1;
        	switch (A[i]) {
            case 0:
                temp = rotate(A, i, p2);
                p2 = p2 != -1 ? p2+1 : p2;
                temp = rotate(A, temp, p1);
                p1 = p1 != -1 ? p1+1 : p1;
                break;
            case 1:
            	temp = rotate(A, i, p2);
                p1 = p1 != -1 ? p1 : temp; 
                p2 = p2 != -1 ? p2+1 : p2;
                break;
            case 2:
                p2 = p2 != -1 ? p2 : i;
                break;
            default:
                break;
            }
        }
    }
    
    int rotate(int[] A, int i, int pointer) {
        System.out.println("rotate: " + i + ", " + pointer);
        if (pointer==-1) {
            return i;
        }
        
        int temp = A[i];
        A[i] = A[pointer];
        A[pointer] = temp;
        return pointer;
    }
    
    public boolean isBalanced(TreeNode root) {
    	int Height = getHeight(root);
    	if (Height == -1) {
			return false;
		} else {
			return true;
		}
    }
    
    public int getHeight(TreeNode node) {
    	if(node == null) {
    		return 0;
    	} else {
    		int left = getHeight(node.left);
    		int right = getHeight(node.right);
    		if (left == -1 || right == -1) {
				return -1;
			} else if (left-right > 1 || right-left > 1) {
				return -1;
			} else {
				return 1 + (left > right ? left : right);
			}
    	}
    }
    
    public int romanToInt(String s) {
        HashMap<Character, Integer> romaMap = new HashMap<Character, Integer>();
        romaMap.put('I', 1);
        romaMap.put('V', 5);
        romaMap.put('X', 10);
        romaMap.put('L', 50);
        romaMap.put('C', 100);
        romaMap.put('D', 500);
        romaMap.put('M', 1000);
        
        int ret = 0;
        int tempAdder = 0;
        int nowAdder = 0;
        for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(s.length()-i-1);
			tempAdder = romaMap.get(c);
			if (tempAdder>=nowAdder) {
				nowAdder=tempAdder;
				ret+=nowAdder;
			} else {
				ret-=tempAdder;
			}
		}
        return ret;
    }
    
    // Given a sorted array and a target value, return the index if the target is found. 
    // If not, return the index where it would be if it were inserted in order.
    public int searchInsert(int[] A, int target) {
        if (A == null) {
			return 0;
		}
        
//        // normal but may take more time( O(n) )
//        for (int i = 0; i < A.length; i++) {
//			if (target == A[i] || target < A[i]) {
//				return i;
//			}
//		}
//        return A.length;
        
        // mid-search which take O(log(n))
        int left, right, mid;
        left  = 0;
        right = A.length-1;
        mid   = ( left + right ) / 2;
        
        while(left <= right){
        	if (A[right] < target) {
    			return right+1;
    		} else if (A[left] > target) {
    			return left;
    		}
        	
        	mid = ( left + right ) / 2;
        	if (A[mid] == target) {
				return mid;
			} else if (A[mid] < target) {
				left = mid+1;
			} else {
				right = mid-1;
			}
        }
        
        return left;
        
    }
    
    public int singleNumber3(int[] A) {
    	if (A == null)
    		return 0;
    	
    	int one, two, three;
    	one = two = three = 0;
    	
    	for (int i = 0; i <  A.length; i++) {
			int temp = A[i];
			int bit_twice   = one & temp;
			int bit_thirdth = two & temp;
			int bit_fourth  = three & temp;
			int bit_once    = temp - bit_twice - bit_thirdth;
			
			one = one - bit_twice + bit_once;
			two = two - bit_thirdth + bit_twice;
			three = three - bit_fourth + bit_thirdth;
			
//			System.out.println("tmp:         " + toBinary(temp));
//			System.out.println("bit_once:    " + toBinary(bit_once));
//			System.out.println("bit_twice:   " + toBinary(bit_twice));
//			System.out.println("bit_thirdth: " + toBinary(bit_thirdth));
//			System.out.println("bit_fourth:  " + toBinary(bit_fourth));
//			
//			System.out.println("one:         " + toBinary(one));
//			System.out.println("two:         " + toBinary(two));
//			System.out.println("three:       " + toBinary(three));
			
			
		}
        return one;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode head, temp1, temp2, temp;
    	temp1 = l1;
    	temp2 = l2;
    	head = new ListNode(0);
    	temp = head;
    	
		while( temp1!=null && temp2!=null ) {
			if (temp1.val <= temp2.val) {
    			temp.next = temp1;
    			temp1 = temp1.next;
    		} else {
    			temp.next = temp2;
    			temp2 = temp2.next;
    		}
			temp = temp.next;
		}
		
		temp.next = temp1 != null ? temp1 : temp2;
    	
        return head.next;
    }
    
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        
        ArrayList<Integer> a = new ArrayList<Integer>();
        if (root == null) {
			return a;
		}
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while( !s.isEmpty() ) {
        	TreeNode temp = s.pop();
        	a.add(temp.val);
        	if (temp.right!=null) {
				s.push(temp.right);
			}
        	if (temp.left!=null) {
        		s.push(temp.left);
			}
        }
        return a;
    }
    
    public static void main(String[] args) {
		Solution s = new Solution();
		int[] A = {1, 7, 7, 7};
		System.out.println(s.singleNumber3(A));
	}

}
