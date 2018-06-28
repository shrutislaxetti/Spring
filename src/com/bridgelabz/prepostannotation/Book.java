package com.bridgelabz.prepostannotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Book {
	
	private String bookName;

	public Book(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookName() {
		return bookName;
	}

	@PostConstruct
	public void init() {
		System.out.println("---init method--");
		if (bookName != null) {
			System.out.println("bookName :" + bookName);
		}
	}

	
	@PreDestroy
	public void destroy() {
		System.out.println("-destroy method-");
	}
}
