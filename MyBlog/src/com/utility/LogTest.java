package com.utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogTest {

	@Test
	public void testwriteToError() {
		boolean expected=true;
		boolean result=Log.writeToError("����һ��С����");
		assertEquals(expected,result);
	}
	@Test
	public void testwriteToOperator()
	{
		boolean expected=true;
		boolean actual=Log.writeToOperator("����һ������");
		assertEquals(expected, actual);
	}
    
}
