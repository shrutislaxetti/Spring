package com.bridgelabz.junitdemo1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.bridgelabz.junitdemo1.Calculator;

public class TestCalculator  {
 Calculator calculator = null;
 
 @Before
 public void setUp() {
	 calculator = new Calculator(); 
 }
 @Ignore
 @Test
 public void testAdd() {
	assertEquals(5,calculator.add(2, 3) ); 
 }
 @Test
 public void testmax() {
     
	 assertEquals(6,calculator.max( new int[]{6,5,2}));
 }
}
