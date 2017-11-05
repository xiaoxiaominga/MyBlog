package com.bll;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.entity.Article;
import com.entity.Entity;
import com.utility.*;
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
    /**
     * @return �������ɵĵ�ǰϵͳʱ��
     */
    private String getNowDate()
    {
    	String dateString="";
    	java.util.Date date=new java.util.Date();
    	//���õ�ǰ����ʱ��ĸ�ʽ
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	dateString=simpleDateFormat.format(date);
    	return dateString;
    }
    @Test
    public void testGetAllArticles()
    {
    	ArticleGet get=new ArticleGet();
    	String actualString=get.getAllArticles();
        
         System.out.println(actualString);
         assertEquals("", actualString);
    }
    
}
