package test;

import java.util.ArrayList;
import java.util.Scanner;

class pixel {
	int x;
	int y;
	public pixel(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static boolean checkAndMove(ArrayList<pixel> snake, char direction) {
		char d = direction;
		char bd = bDirect(snake.get(0), snake.get(1));
		
		pixel temp = new pixel(snake.get(0).x, snake.get(0).y);
		
		if (bd == d) {
			return false;
		}
		switch (d) {
			case 'L': temp.x--;break;
			case 'R': temp.x++;break;
			case 'U': temp.y++;break;
			case 'D': temp.y--;break;
			default:  break;
		}
		
		for (int i = 0; i < snake.size()-1; i++) {
			if (temp.x==snake.get(i).x && temp.y==snake.get(i).y) {
				return false;
			}
		}
		
		for (int i = snake.size()-1; i > 0; i--) {
			snake.get(i).x = snake.get(i-1).x;
			snake.get(i).y = snake.get(i-1).y;
		}
		snake.get(0).x = temp.x;
		snake.get(0).y = temp.y;
		
		return true;
	}
	
	public static char differ(char c) {
		switch (c) {
		case 'L': return 'R';
		case 'R': return 'L';
		case 'U': return 'D';
		case 'D': return 'U';
		default:  return 'R';
		}
	}
	
	public static char bDirect(pixel one, pixel two) {
		if (one.x > two.x) {
			return 'L';
		} else if (one.x < two.x) {
			return 'R';
		} else if (one.y > two.y) {
			return 'D';
		} else {
			return 'U';
		}
	}
	
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String move = sc.nextLine();
        
        ArrayList<pixel> snake = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
        	snake.add(new pixel(-i, 0));
		}
        
        for (int i = 0; i < move.length(); i++) {
			if (checkAndMove(snake, move.charAt(i))) {
				;
			} else {
				System.out.println("illegal");
				return;
			}
		}
        System.out.println("legal");
	}
}