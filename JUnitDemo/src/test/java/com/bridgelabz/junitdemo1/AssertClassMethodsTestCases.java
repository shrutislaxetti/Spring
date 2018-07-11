package com.bridgelabz.junitdemo1;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bridgelabz.junitdemo2.AssertClassMethods;

public class AssertClassMethodsTestCases {
	AssertClassMethods testCase = null;

	@BeforeClass
	public static void beforeClass() {
		System.out.println("before Class");

	}

	@Before
	public  void before() {
		testCase = new AssertClassMethods();
		
	}

	@Test
	public void testAdd() {
		assertEquals(10, testCase.add(5, 5));
	}

	@Test
	public void compareString() {
		assertTrue(testCase.compareStrings("yuga", "yuga"));
	}

	@Test
	public void compareStringWithMessage() {
		assertTrue("Your test is fail: ", testCase.compareStrings("Yuga", "Yuga"));
	}

	@Test
	public void compareStringsWithFalse() {
		assertFalse(testCase.compareStrings("yug", "yuga"));
	}

	@Test
	public void compareStringsWithFalseMessage() {
		assertFalse("Your test is fail: ", testCase.compareStrings("piya", "priya"));
	}

	@Test
	public void concatString() {
		assertEquals("HelloWorld", testCase.concatString("Hell", "World"));
		 //assertEquals("HelloWorld", testCase.concatString("Hii", "World"));
	}

	@Test
	public void addOneToArray() {
		int[] expected = new int[] { 3, 4 };
		assertArrayEquals(expected, testCase.oneToArray(new int[] { 2, 3 }));
	}

	@Test(timeout = 1)
	public void toCheckPerformance() {
		testCase.toCheckPerformance();
	}

	/*
	 * @Test(expected = ArithmeticException.class) public void checkException() { //
	 * testCase.checkException(0); testCase.checkException(1); }
	 */

	@After
	public void afterTestCase() {
		System.out.println("After each test case");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("After test class");
		System.out.println("closing connections");
	}
}