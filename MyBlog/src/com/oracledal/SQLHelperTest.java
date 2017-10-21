package com.oracledal;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.hamcrest.core.Is;
import org.junit.Test;

public class SQLHelperTest {
    
	/**
	 * ����ִ���޸�����sql���
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
	 * ���Բ�ѯ������ĵ�һ�е�һ�е�ֵ
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
    		//����֮��������ʾ���Դ���
    		
    		
    		
    		//fail();
		}
    }
}
