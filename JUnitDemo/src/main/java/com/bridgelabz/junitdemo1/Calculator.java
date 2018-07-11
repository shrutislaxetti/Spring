package com.bridgelabz.junitdemo1;

public class Calculator {

	public int add(int i, int j) {
		int k = i + j;
		return k;

	}
	
	public int max(int [] a) {
		int max=0;
		for(int i=0;i<a.length;i++) {
			if(max<a[i]) {
				max=a[i];
			}
		}
		return max;
	}
}
