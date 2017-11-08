package com.utility;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

import com.entity.Entity;
/**
 * @author 马腾飞
 * 处理json数据，封装json数据或者解析json数据
 */
public class JsonUtil {
	List<String> keys=new ArrayList<String>();
   public static Map<String, Object> JsonObjectToData(JSONObject object)
   {
	   
	   Map<String, Object> jsonMap=object.toMap();
	   return jsonMap;
   }
   public static List<Object> JsonArrayToData(JSONArray array)
   {
	   List<Object> list=new ArrayList<Object>();
	   list=array.toList();
	   return list;
   }
   public static JSONObject DataTOJson(String data)
   {
	   JSONObject jsonObject=new JSONObject(data);
	   return jsonObject;
   }
   /**
 * @param data,实体对象集合 
 * @return 返回json的对象-------将传入的实体对象转换成json数据存储下来
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
 */
public static JSONStringer DataToJsonObject(Entity data)
   {
	String valueString="";
	try{
	JSONStringer jsonObject=new JSONStringer();
	
		   Class class1=(Class)data.getClass();
		   Field[] fields=class1.getFields();
		   for(int i=0;i<fields.length;i++)
		   {
			   Field field=fields[i];
			   field.setAccessible(true);//设置属性可以访问
			   Object val=field.get(data);
			   
			   String nameString=field.getName();
			   jsonObject.key(nameString);
			   jsonObject.value(val);
			   valueString+=nameString+"|"+valueString.toString()+"##";
		   }
	       System.out.print(valueString+"##");
	   	   return jsonObject;
	}catch (Exception e) {
		// TODO: handle exception
		Log.writeToError("DataTOJsonObect->"+e.getMessage()+"->"+valueString);
		return null;
	}
   }
   /**
 * @param dataList
 * @return 将实体集合的转换成json数组对象（过时）
 * @throws IllegalArgumentException
 * @throws IllegalAccessException
 */
public static JSONArray DataToJsonArray(List<Entity> dataList) throws IllegalArgumentException, IllegalAccessException
   {
	   JSONArray jsonArray=new JSONArray();
	   for(Entity list:dataList)
	   {
		   Class class1=(Class)list.getClass();//获取实体类的实例
		   Field[] fields=class1.getDeclaredFields();
		   JSONStringer jsonStringer=new JSONStringer();
		   for(int i=0;i<fields.length;i++)
		   {
			   Field fd=fields[i];
			   fd.setAccessible(true);
			   Object val=fd.get(list);
			   String keyString=fd.getName();//获取属性的名称
			   jsonStringer.key(keyString);
			   jsonStringer.value(val);
			   //jsonArray.
		   }
	   }
	   return jsonArray;
   }
   public static String listToArray(List<Entity> list)
   {
	   JSONArray array=new JSONArray(list);
	   return array.toString();
   }
}
