package com.utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogTest {

	@Test
	public void testwriteToError() {
		boolean expected=true;
		boolean result=Log.writeToError("这是一个小测试");
		assertEquals(expected,result);
	}
	@Test
	public void testwriteToOperator()
	{
		boolean expected=true;
		boolean actual=Log.writeToOperator("这是一个测试");
		assertEquals(expected, actual);
	}
    
}
