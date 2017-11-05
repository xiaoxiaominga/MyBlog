package com.oracledal;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * @author 马腾飞
 *DOM方式解析webconfig.xml中的内容；
 */
public class Config {
     private static String connectionString="";
     public static String getConnectionString()
     {
    	 if(connectionString==null||connectionString.equals(""))
    	 {
    		 connectionString=getXmlString_Connection();
    	 }
    	 return connectionString;
     }
     private static String getXmlString_Connection()
     {
    	 //创建一个文档构建工厂的实例
    	 DocumentBuilderFactory dbfBuilderFactory=DocumentBuilderFactory.newInstance();
         try{
        	 //创建一个documentbuilder对象
        	 DocumentBuilder documentBuilder=dbfBuilderFactory.newDocumentBuilder();
        	 /*
        	  * 通过documentbuilder对象的parser方法加载xml文件到当前项目中
        	  * 注意得导入document对象时，需要导入org.w3c.dom.Document
        	  * */
        	 Document document=documentBuilder.parse("webconfig.xml");
        	 //获取xml中的根节点集合
        	 NodeList connectionList=document.getElementsByTagName("connectionStrings");
        	 //可以通过nodelist的getLength()方法获得做外层节点的个数
        	 //获取到所有的连接字符串（默认取第一个）
        	 String[] connectStrings=new String[connectionList.getLength()];
        	 for(int i=0;i<connectionList.getLength();i++)
        	 {
        		 connectStrings[i]="";
        		 //获取子节点connectionString的value值
        		 Node connectionNode=connectionList.item(i);
        		 //获取子节点中的所有属性值
        		 NodeList child=connectionNode.getChildNodes();
        		 for(int j=1;j<child.getLength();j++)
        		 {
        			 Node attr=child.item(j);
        			 if(attr.getNodeType()==Node.ELEMENT_NODE)
        			 connectStrings[i]+=attr.getTextContent();
        		     
        		 }
        		 
        	 }
        	 return connectStrings[0];//默认返回第一个节点
        	 
         }catch(ParserConfigurationException e)
         {
        	 e.printStackTrace();
         }catch(SAXException e)
         {
        	 e.printStackTrace();
         }catch (IOException e) {
			// TODO: handle exception
        	 e.printStackTrace();
		}
         return "";
     }
}
 
