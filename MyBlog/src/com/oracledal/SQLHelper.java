package com.oracledal;
//�����ַ������棻
import java.sql.Connection;
import java.sql.DriverManager;//
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import sun.util.logging.resources.logging;

import com.sun.rowset.CachedRowSetImpl;
import com.utility.Log;
/**
 * @author ���ڷ�
 *�����ݿ޽��в����Ĳ�����
 */
public class SQLHelper {
    //����һ�����ݿ�����---����ʹ�����ֹ��õ��࣬��Ϊ����һ���ӳ�
 	static Connection connection=null;
    //����һ��Ԥ��������
	static PreparedStatement prst=null;
	//����һ�����������������
	static ResultSet resultSet=null;
	//����һ������������߽��������Ϊ�ڷ��ز�ѯ�������ݼ�ʱ����Ҫrs�ر�Ϊ�˼�����Դ���˷ѣ����������ط�ȴ������ʹ����
	static CachedRowSetImpl rowSetImpl=null;
	/*
	 *1. ResultSet.TYPE_FORWARD_ONLY   (Ĭ�Ϸ�ʽ����) 
   2. ResultSet.TYPE_SCROLL_INSENSITIVE  ˫�������������ʱ���£�����������ݿ���������޸Ĺ���������ResultSet�з�Ӧ������ 
   3. ResultSet.TYPE_SCROLL_SENSITIVE  ˫�����������ʱ�������ݿ���ĸ���,�Ա����ResultSet�е����ݡ� 
   4. ResultSet.CONCUR_READ_ONLY  ֻ��ȡResultSet 
   5. ResultSet.CONCUR_UPDATABLE  ��ResultSet�������ݿ� 
	 * */

	/**
	 * @param sqlText ����ִ�в�ѯ��sql���
	 * @param parms Ԥ����sql���Ĳ���
	 * @return ����sql��ȡ�����߻���Ľ��
	 */
	public static CachedRowSetImpl ExecuteResultSet(String sqlText,Object[] parms)
	{
		
		try{
			getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlText,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if(parms!=null)
			for(int i=1;i<=parms.length;i++)
			{
				preparedStatement.setObject(i, parms[i-1]);
			}
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				resultSet.previous();
				rowSetImpl=new CachedRowSetImpl();
				//������ߵĽ����
				rowSetImpl.populate(resultSet);
				return rowSetImpl;		
			}
			else {
				return null;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����SQLHelper���е�ExcuteResultSet(String,Object[])����\r\n"+
					"SQL���룺"+sqlText+"\r\n"+"������"+getParms(parms));
			return null;
		}finally{
			ReleaseAll();
		}
		
	}
	/**
	 * @param sqlText ִ�����ݿ������sql���,ֻ���insert,update,delete����
	 * @param parms  ִ����Щ����ʱ����Ҫ��Ԥ����֮��Ĳ���
	 * @return  ����ִ�н��֮�󷵻ص�����
	 */
 	public static int ExecuteNonQuery(String sqlText,Object[] parms)
	{
 		
		try{
	    getConnection();
	    
	    //Ԥ����sql���ִ�е�Ч�ʸ��Ӹ�Ч
	    PreparedStatement preparedStatement=connection.prepareStatement(sqlText);
		for(int i=1;i<=parms.length;i++)
		preparedStatement.setObject(i, parms[i-1]);
		//ִ�и��²��������ִ��
		int count=preparedStatement.executeUpdate();
		preparedStatement.close();
		return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����SQLHelper���е�ExcuteNonQuery(String)����\r\n"+
			"SQL���룺"+sqlText+"\r\n"+"������"+getParms(parms));
			return -1;
		}
	    finally{
	    	ReleaseAll();
	    }
	}
 	
	/**
	 * @param sqlText ����Ҫ����ִ�е�sql���
	 * @param parms sql���Ĳ���
	 * @return
	 */
	public static Object ExecuteScalar(String sqlText,Object[] parms)
	{
		Connection connection=null;
		try{
			Object resultString=null;
			connection=getConnection();//�������ݿ�����Ӷ���
			PreparedStatement preparedStatement=connection.prepareStatement(sqlText);
			for(int i=1;i<=parms.length;i++)
			{
				//���������û�
				preparedStatement.setObject(i, parms[i-1]);
			}
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				resultString= resultSet.getObject(1);//��ȡ��һ�е�һ�е�ֵ
			}
			return resultString;
		}catch(Exception e)
		{
			Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����SQLHelper���е�ExcuteScalar(String,String[])����\r\n"+
					"SQL���룺"+sqlText+"\r\n"+"������"+getParms(parms));
			return null;
		}finally{
			ReleaseAll();
		}
	}
	/**
	 * @return ���ش��������Ӷ���
	 */
	private static Connection getConnection()
	{
		try{
			if(connection==null)
		    connection=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");//������������
			//��xml�л�ȡ�����ַ�����Ϣ
			String config=Config.getConnectionString();
			String[] parms=config.split(";");
			//ʹ���������û�������������ȡ����
			connection=DriverManager.getConnection(parms[0], parms[1], parms[2]);
			
		}catch(SQLException e)
		{
			Log.writeToError("����������ԭ��"+e.getMessage().toString()+"\r\n...���󳡾�����oracledal����SQLHelper���е�GetConnection()����");
		    return null;
		}
		catch(ClassNotFoundException e){
			Log.writeToError("����������ԭ��"+e.getMessage().toString()+"\r\n...���󳡾�����oracledal����SQLHelper���е�GetConnection()����");
		    return null;
		}
		return connection;
	}
	/**
	 *�ͷ��������ݿ⣬Ԥ���룬��������� 
	 */
	private static void ReleaseAll()
	{
		try{
			if(connection!=null)
			{
				connection.close();
				
			}
			if(resultSet!=null)
			{
				resultSet.close();
			}
			if(prst!=null)
			{
				prst.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
	}
    /**
     * @param parms sql��Ԥ�������
     * @return
     */
    private static String getParms(Object[] parms)
    {
    	String parmString="";
    	for(int i=0;i<parms.length;i++)
    	{
    		parmString+=parms[i].toString()+",";
    	}
    	return parmString;
    }
}
