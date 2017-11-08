package com.utility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.json.JSONArray;
import org.json.JSONStringer;

import com.entity.Article;
import com.entity.Entity;
public class JsonUtilTest {

	/**
	 * 测试将实体对象集合转换成json数组对象
	 */
	@Test
	public void testGet() {
       Article article=new Article("mtf", "这是一个test case", "2015-01-02 00:00:00", "日记", 20, "/images", 0);       		
	   Article article2=new Article("ymq", "这是一个test case", "2015-02-09 01:00:00", "心情", 32, "/images1", 0);
	   List<Entity> list=new ArrayList<Entity>();
	   list.add(article);
	   list.add(article2);
	   
	   String nameString=JsonUtil.listToArray(list);
	   System.out.print(nameString);
	   System.out.print(article.toString());
	   assertEquals("1", nameString);
	}
	@Test
    public void testJsonObject() throws IllegalArgumentException, IllegalAccessException
    {
		Article article=new Article("mtf", "这是一个test case", "2015-01-02 00:00:00", "日记", 20, "/images", 0);       		
		Article article2=new Article("ymq", "这是一个test case", "2015-02-09 01:00:00", "心情", 32, "/images1", 0);
		List<Entity> list=new ArrayList<Entity>();
		list.add(article);
		list.add(article2);
		JSONStringer jsonStringer= JsonUtil.DataToJsonObject(article);
		String actual=jsonStringer.toString();
		assertEquals("", actual);
    }
}
