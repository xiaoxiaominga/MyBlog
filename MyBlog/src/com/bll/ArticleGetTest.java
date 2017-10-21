package com.bll;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.entity.Article;

public class ArticleGetTest {

	/**
	 * 使用artistid=1的数据做测试用例
	 */
	@Test
	public void testGetArticle() {
		String title="zhongguomeng";
		ArticleGet get=new ArticleGet();
		Article article=get.getArticle(1);
		String actual=article.getTitle().trim();
		assertEquals(title, actual);
	}
    @Test
    public void testInsert()
    {
    	boolean expected=true;
    	ArticleGet articleGet=new ArticleGet();
    	
    	Article article=new Article( "我的第一篇测试博客", "谁是谁的谁", getNowDate(), "算法", 0, "testurl", 0);
    	boolean actual=articleGet.insertArticle(article);
    	assertEquals(expected, actual);
    }
    private String getNowDate()
    {
    	String dateString="";
    	java.util.Date date=new java.util.Date();
    	//设置当前日期时间的格式
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	dateString=simpleDateFormat.format(date);
    	return dateString;
    }
}
