package com.bridgelabz.prepostannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrePostMain {
public static void main(String[] args) {
	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	   Book book=(Book) context.getBean("book");
	   book.toString();
	   ((ConfigurableApplicationContext)context).close();
	}
}

