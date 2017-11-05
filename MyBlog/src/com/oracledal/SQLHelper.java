package com.oracledal;
//连接字符串引擎；
import java.sql.Connection;
import java.sql.DriverManager;//
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import sun.util.logging.resources.logging;

import com.sun.rowset.CachedRowSetImpl;
import com.utility.Log;
/**
 * @author 马腾飞
 *对数据哭进行操作的操作类
 */
public class SQLHelper {
    //创建一个数据库连接---不能使用这种公用的类，因为会有一定延迟
 	static Connection connection=null;
    //创建一个预编译请求
	static PreparedStatement prst=null;
	//创建一个结果集用来储存结果
	static ResultSet resultSet=null;
	//创建一个结果集的离线结果集，因为在返回查询到的数据集时是需要rs关闭为了减少资源的浪费，但是其他地方却不能再使用了
	static CachedRowSetImpl rowSetImpl=null;
	/*
	 *1. ResultSet.TYPE_FORWARD_ONLY   (默认方式，略) 
   2. ResultSet.TYPE_SCROLL_INSENSITIVE  双向滚动，但不及时更新，就是如果数据库里的数据修改过，并不在ResultSet中反应出来。 
   3. ResultSet.TYPE_SCROLL_SENSITIVE  双向滚动，并及时跟踪数据库里的更新,以便更改ResultSet中的数据。 
   4. ResultSet.CONCUR_READ_ONLY  只读取ResultSet 
   5. ResultSet.CONCUR_UPDATABLE  用ResultSet更新数据库 
	 * */

	/**
	 * @param sqlText 即将执行查询的sql语句
	 * @param parms 预编译sql语句的参数
	 * @return 返回sql获取的离线缓存的结果
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
				//填充离线的结果集
				rowSetImpl.populate(resultSet);
				return rowSetImpl;		
			}
			else {
				return null;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.writeToError("错误原因："+e.getMessage().toString()+"\r\n错误场景：在oracledal包中SQLHelper类中的ExcuteResultSet(String,Object[])方法\r\n"+
					"SQL代码："+sqlText+"\r\n"+"参数："+getParms(parms));
			return null;
		}finally{
			ReleaseAll();
		}
		
	}
	/**
	 * @param sqlText 执行数据库操作的sql语句,只针对insert,update,delete操作
	 * @param parms  执行这些操作时所需要的预编译之后的参数
	 * @return  返回执行结果之后返回的行数
	 */
 	public static int ExecuteNonQuery(String sqlText,Object[] parms)
	{
 		
		try{
	    getConnection();
	    
	    //预编译sql语句执行的效率更加高效
	    PreparedStatement preparedStatement=connection.prepareStatement(sqlText);
		for(int i=1;i<=parms.length;i++)
		preparedStatement.setObject(i, parms[i-1]);
		//执行更新操作的语句执行
		int count=preparedStatement.executeUpdate();
		preparedStatement.close();
		return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.writeToError("错误原因："+e.getMessage().toString()+"\r\n错误场景：在oracledal包中SQLHelper类中的ExcuteNonQuery(String)方法\r\n"+
			"SQL代码："+sqlText+"\r\n"+"参数："+getParms(parms));
			return -1;
		}
	    finally{
	    	ReleaseAll();
	    }
	}
 	
	/**
	 * @param sqlText 即将要进行执行的sql语句
	 * @param parms sql语句的参数
	 * @return
	 */
	public static Object ExecuteScalar(String sqlText,Object[] parms)
	{
		Connection connection=null;
		try{
			Object resultString=null;
			connection=getConnection();//创建数据库的连接对象
			PreparedStatement preparedStatement=connection.prepareStatement(sqlText);
			for(int i=1;i<=parms.length;i++)
			{
				//将参数设置话
				preparedStatement.setObject(i, parms[i-1]);
			}
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				resultString= resultSet.getObject(1);//获取第一行第一列的值
			}
			return resultString;
		}catch(Exception e)
		{
			Log.writeToError("错误原因："+e.getMessage().toString()+"\r\n错误场景：在oracledal包中SQLHelper类中的ExcuteScalar(String,String[])方法\r\n"+
					"SQL代码："+sqlText+"\r\n"+"参数："+getParms(parms));
			return null;
		}finally{
			ReleaseAll();
		}
	}
	/**
	 * @return 返回创建的连接对象
	 */
	private static Connection getConnection()
	{
		try{
			if(connection==null)
		    connection=null;
			Class.forName("oracle.jdbc.driver.OracleDriver");//加载驱动程序
			//从xml中获取连接字符串信息
			String config=Config.getConnectionString();
			String[] parms=config.split(";");
			//使用驱动，用户名，密码来获取连接
			connection=DriverManager.getConnection(parms[0], parms[1], parms[2]);
			
		}catch(SQLException e)
		{
			Log.writeToError("。。。错误原因："+e.getMessage().toString()+"\r\n...错误场景：在oracledal包中SQLHelper类中的GetConnection()方法");
		    return null;
		}
		catch(ClassNotFoundException e){
			Log.writeToError("。。。错误原因："+e.getMessage().toString()+"\r\n...错误场景：在oracledal包中SQLHelper类中的GetConnection()方法");
		    return null;
		}
		return connection;
	}
	/**
	 *释放连接数据库，预编译，结果集对象 
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
     * @param parms sql的预编译参数
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
