package test;

import java.util.*;

public class work2 {
	
	static String blank(int num) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < num; i++) {
			result.append(" ");
		}
		return result.toString();
	}
	
    static ArrayList<String> fullJustify(String[] words, int L) {
		
    	ArrayList<String> result = new ArrayList<>();
		int nowFistWord = 0;
		int nowLastWord = 0;
		int nowLenth    = 0;
		int totalSpace  = 0;
		int eachSpace	= 0;
		int remainSpace = 0;
		
		if (L <= 0) {
			result.add("");
			return result;
		}
		
		if (words.length < 1) {
			result.add("");
			return result;
		}
		
		nowLenth = words[nowFistWord].length();
		
		while(nowFistWord < words.length) {
			
			// the last word is count, there is no more word
			if (nowLastWord + 1 == words.length) {
				
				totalSpace += L - nowLenth;
				
				// only one word
				if(nowLastWord==nowFistWord){
					result.add(words[nowFistWord] + blank(totalSpace));
					return result;
				}
				
				else {
					// initial the buffer
					StringBuffer unit = new StringBuffer();
					
					for (int j = nowFistWord; j < nowLastWord; j++) {
						unit.append(words[j]);
						unit.append(" ");
					}
					
					unit.append(words[nowLastWord]);
					result.add(unit.toString() + blank(L - unit.length()));
					
					return result;
				}
			}
			
			// the next word can not be added
			if (nowLenth + 1 + words[nowLastWord+1].length() > L) {
				
				totalSpace += L - nowLenth;
				
				// the situation of just one word
				if(nowLastWord==nowFistWord){
					
					result.add(words[nowFistWord] + blank(totalSpace));
					
					nowFistWord = nowLastWord + 1;
					nowLastWord = nowFistWord;
					totalSpace  = 0;
					nowLenth    = words[nowFistWord].length();
					
					if(nowFistWord==words.length-1){
						result.add(words[nowFistWord] + blank(L-nowLenth));
						return result;
					}
				}
				else {
					// initial the buffer
					StringBuffer unit = new StringBuffer();
					
					remainSpace = totalSpace%(nowLastWord-nowFistWord);
					eachSpace = totalSpace/(nowLastWord-nowFistWord);
					
					for (int j = nowFistWord; j < nowLastWord; j++) {
						unit.append(words[j]);
						unit.append(blank(eachSpace));
						if (remainSpace > 0) {
							unit.append(" ");
							remainSpace--;
						}
					}
					unit.append(words[nowLastWord]);
					// 3
					result.add(unit.toString());
					//System.out.println("3 add " + unit);
					
					// reset the attributes
					nowFistWord = nowLastWord + 1;
					nowLastWord = nowFistWord;
					totalSpace = 0;
					nowLenth = words[nowFistWord].length();
				}
			}
			// the next word can be add
			else {
				nowLastWord = nowLastWord + 1;
				totalSpace += 1;
				nowLenth += 1 + words[nowLastWord].length();
			}
		}
		
    	return result;
    }
    
    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int size = in.nextInt();
		String[] words = new String[size];
		in.nextLine();
		for (int i = 0; i < words.length; i++) {
			words[i] = in.nextLine();
		}
		
		ArrayList<String> result = work2.fullJustify(words, 12);
		for (String unit : result) {
			System.out.println(unit);
		}
		
		in.close();
	}
}
