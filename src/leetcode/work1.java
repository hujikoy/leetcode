package test;

import java.util.Scanner;

class work1{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String checkStr = in.nextLine();
		String checkRule = in.nextLine();
		in.close();
		String[] Rules = checkRule.split("\\*");
		int numOfRule = 0;
		int finali, finalj;
		finali = finalj = 0;
		for (int i = 0, j=0; i < checkRule.length(); i++, j++) {
			finali=i+1;
			finalj=j+1;
			//System.out.println("i="+i+", j=" + j);
			if(i>=checkStr.length()) {
				System.out.println("0");
				//System.out.println("i>=checkStr.length()");
				return;
			}
			if(checkRule.charAt(i) == '?') {
				continue;
			}
			if(checkRule.charAt(i) == '*') {
				numOfRule++;
				if(numOfRule>=Rules.length){
					System.out.println("1");
					return;
				}
				if(Rules[numOfRule].equals("")) {
					System.out.println("1");
					return;
				}
				int newIndex = checkStr.indexOf(Rules[numOfRule], j);
				//System.out.println("Rules[numOfRule]="+Rules[numOfRule]+", newIndex=" + newIndex);
				//System.out.println("Rules[numOfRule].length() = "+Rules[numOfRule].length());
				if (newIndex==-1) {
					System.out.println("0");
					//System.out.println("newIndex==-1");
					return;
				} else {
					//System.out.println("Before, i="+i+", j=" + j);
					i=i + Rules[numOfRule].length();
					j = newIndex + Rules[numOfRule].length() - 1;
					//System.out.println("After, i="+i+", j=" + j);
					continue;
				}
			}
			if(checkRule.charAt(i) != checkStr.charAt(j)) {
				System.out.println("0");
				//System.out.println("i="+i+", j=" + j);
				return;
			}
		}
		if(finali==checkRule.length() && finalj==checkStr.length()) {
			System.out.println("1");
		} else {
			System.out.println("0");
		}
	}
}