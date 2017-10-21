package com.bll;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.entity.Article;

public class ArticleGetTest {

	/**
	 * ʹ��artistid=1����������������
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
    	
    	Article article=new Article( "�ҵĵ�һƪ���Բ���", "˭��˭��˭", getNowDate(), "�㷨", 0, "testurl", 0);
    	boolean actual=articleGet.insertArticle(article);
    	assertEquals(expected, actual);
    }
    private String getNowDate()
    {
    	String dateString="";
    	java.util.Date date=new java.util.Date();
    	//���õ�ǰ����ʱ��ĸ�ʽ
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	dateString=simpleDateFormat.format(date);
    	return dateString;
    }
}
