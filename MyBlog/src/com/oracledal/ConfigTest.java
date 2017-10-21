package com.oracledal;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest {

	
	@Test
	public void testGetConnectionString() {
		String Result="127.0.0.1/XE;admin;123456";
		String testResult=Config.getConnectionString();
		assertEquals(Result, testResult);
	}

}
