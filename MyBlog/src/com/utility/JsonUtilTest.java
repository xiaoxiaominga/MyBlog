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
	 * ���Խ�ʵ����󼯺�ת����json�������
	 */
	@Test
	public void testGet() {
       Article article=new Article("mtf", "����һ��test case", "2015-01-02 00:00:00", "�ռ�", 20, "/images", 0);       		
	   Article article2=new Article("ymq", "����һ��test case", "2015-02-09 01:00:00", "����", 32, "/images1", 0);
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
		Article article=new Article("mtf", "����һ��test case", "2015-01-02 00:00:00", "�ռ�", 20, "/images", 0);       		
		Article article2=new Article("ymq", "����һ��test case", "2015-02-09 01:00:00", "����", 32, "/images1", 0);
		List<Entity> list=new ArrayList<Entity>();
		list.add(article);
		list.add(article2);
		JSONStringer jsonStringer= JsonUtil.DataToJsonObject(article);
		String actual=jsonStringer.toString();
		assertEquals("", actual);
    }
}
