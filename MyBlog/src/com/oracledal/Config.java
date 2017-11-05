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
 * @author ���ڷ�
 *DOM��ʽ����webconfig.xml�е����ݣ�
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
    	 //����һ���ĵ�����������ʵ��
    	 DocumentBuilderFactory dbfBuilderFactory=DocumentBuilderFactory.newInstance();
         try{
        	 //����һ��documentbuilder����
        	 DocumentBuilder documentBuilder=dbfBuilderFactory.newDocumentBuilder();
        	 /*
        	  * ͨ��documentbuilder�����parser��������xml�ļ�����ǰ��Ŀ��
        	  * ע��õ���document����ʱ����Ҫ����org.w3c.dom.Document
        	  * */
        	 Document document=documentBuilder.parse("webconfig.xml");
        	 //��ȡxml�еĸ��ڵ㼯��
        	 NodeList connectionList=document.getElementsByTagName("connectionStrings");
        	 //����ͨ��nodelist��getLength()������������ڵ�ĸ���
        	 //��ȡ�����е������ַ�����Ĭ��ȡ��һ����
        	 String[] connectStrings=new String[connectionList.getLength()];
        	 for(int i=0;i<connectionList.getLength();i++)
        	 {
        		 connectStrings[i]="";
        		 //��ȡ�ӽڵ�connectionString��valueֵ
        		 Node connectionNode=connectionList.item(i);
        		 //��ȡ�ӽڵ��е���������ֵ
        		 NodeList child=connectionNode.getChildNodes();
        		 for(int j=1;j<child.getLength();j++)
        		 {
        			 Node attr=child.item(j);
        			 if(attr.getNodeType()==Node.ELEMENT_NODE)
        			 connectStrings[i]+=attr.getTextContent();
        		     
        		 }
        		 
        	 }
        	 return connectStrings[0];//Ĭ�Ϸ��ص�һ���ڵ�
        	 
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
 
