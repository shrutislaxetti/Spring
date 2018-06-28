package com.bridgelabz.annotation;

import java.lang.reflect.Method;

class MyAnnotationTest {
	@SimpleAnnotation1(count=20)
	@SimpleAnnotation(name = "test1", desc = "testing annotations")
	public void myTestMethod() {
		try {
			Method mth;
			Class<?> object = this.getClass();
			mth = object.getMethod("myTestMethod");
			SimpleAnnotation myAnno = mth.getAnnotation(SimpleAnnotation.class);
			System.out.println("key: " + myAnno.name());
			System.out.println("value: " + myAnno.desc());
		
			SimpleAnnotation1 an1=mth.getAnnotation(SimpleAnnotation1.class);
			System.out.println("count"+an1.count());
			
		} catch (SecurityException | NoSuchMethodException e) {

			e.printStackTrace();
		}
	}

	public static void main(String a[]) {

		MyAnnotationTest mat = new MyAnnotationTest();
		mat.myTestMethod();
	}
}
