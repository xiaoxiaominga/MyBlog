package com.oracledal;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.hamcrest.core.Is;
import org.junit.Test;

public class SQLHelperTest {
    
	/**
	 * 测试执行修改语句的sql语句
	 */
	@Test
	public void testExecuteNonQuery() {
		int count=1;
		String sql="insert into visitor(ip,hostname,visiteddate) values(?,?,to_date(?,'yyyy-MM-dd'))";
		String[] strings={"192.168.137.1","haha","2015-01-03"};
		int actual=SQLHelper.ExecuteNonQuery(sql, strings);
	    assertEquals(count, actual);	
	}
	/**
	 * 测试查询出结果的第一行第一列的值
	 */
	@Test
	public void TestExcuteScalar()
	{
	    Object object=2;
	    String sqlTextString="select * from visitor where VISITORID=?";
	    String[] strings={"2"};
	    Object actual=SQLHelper.ExecuteScalar(sqlTextString, strings);
	    actual=Integer.parseInt(actual.toString());
	    assertEquals(object, actual);
	}
    @Test
    public void TestExcuteResult()
    {
    	try{
    	String sqlText="select * from visitor where visitorid=?";
    	String[] strings={"2"};
    	ResultSet set=SQLHelper.ExecuteResultSet(sqlText, strings);
    	String actual="1";
    	while(set.next())
    	{
    	    actual=set.getObject(1).toString();
    	}
    	String expected="2";
    	assertEquals(expected, actual);
    	}catch (Exception e) {
			// TODO: handle exception
    		//报错之后立马提示测试错无
    		
    		
    		
    		//fail();
		}
    }
}
