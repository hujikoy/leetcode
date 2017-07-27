package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

class Cat {
	  private int catNumber;
	  Cat(int i) { catNumber = i; }
	  void print() {
	    System.out.println("Cat #" + catNumber);
	  }
	}

	class Dog {
	  private int dogNumber;
	  Dog(int i) { dogNumber = i; }
	  void print() {
	    System.out.println("Dog #" + dogNumber);
	  }
	}

public class work3 {

	public static void main(String[] args) {
//		InetAddress ip;
//		try {
//			ip = InetAddress.getLocalHost();
//			String localName = ip.getHostName();
//			String localIP = ip.getHostAddress();
//			System.out.println("Local Host: " + localName);
//			System.out.println("Local IP: " + localIP);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		ArrayList cats = new ArrayList();
	    for(int i = 0; i < 7; i++)
	      cats.add(new Cat(i));
	    Iterator e = cats.iterator();
	    while(e.hasNext())
	      ((Cat)e.next()).print();
	}
	
	static void fun() {
		try{
			int i = 1;
			int sum = i/0;
			System.out.println(sum);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter catch!");
		} finally {
			System.out.println("enter final!");
		}
	}
}
