package com.utility;

import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author 马腾飞
 *将必要的地方出现的提示信息写入日志文件
 */
public class Log {
	
	  
	
	
      public static boolean writeToError(String error)
      {
    	  try{
    		 
    		  
    	  Date date=new Date();
    	  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    	  String pathname="WebRoot/logs/"+df.format(date)+"-error.log";
    	  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String time=format.format(date);
		  error+="\t\t"+time+"\r\n";
    	  File file=new File(pathname);
    	  if(!file.exists())
    	  {
    		  file.createNewFile();
    	  }
    	  //定义写入流
    	  FileWriter fileWriter=new FileWriter(file,true);
    	  BufferedWriter writer=new BufferedWriter(fileWriter);
    	  writer.write(error);
    	  writer.close();
    	  return true;
    	  }catch (Exception e) {
			// TODO: handle exception
    		 
		}
    	  return false;
    	  
      }
      public static boolean writeToOperator(String operateString)
      {
    	  try{
     		 
    		  
        	  Date date=new Date();
        	  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        	  String pathname="WebRoot/logs/"+df.format(date)+"-operate.log";
        	  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		  String time=format.format(date);
    		  operateString+="\t\t"+time+"\r\n";
        	  File file=new File(pathname);
        	  if(!file.exists())
        	  {
        		  file.createNewFile();
        	  }
        	  FileWriter fileWriter=new FileWriter(file,true);
        	  BufferedWriter writer=new BufferedWriter(fileWriter);
        	  writer.write(operateString);
        	  writer.close();
        	  return true;
        	  }catch (Exception e) {
    			// TODO: handle exception
        		 
    		}
        	  return false;
      }
}
