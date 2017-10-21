package com.entity;

import java.sql.Date;

/**
 * @author ���ڷ�
 *�û���������±� ��ʵ����
 */
public class Article {
    private int articleid;
    private String title;
    //�������ݡ�
    private String content;
    private String date;//�������µ�ʱ�䡣
    private String type;
    private int count=0;
    private String imageurl;
    private int del=0;
    //���췽��2
    public Article(String title, String content, String date,
			String type, int count, String imageurl, int del)
    {
    	this.title = title;
		this.content = content;
		this.date = date;
		this.type = type;
		this.count = count;
		this.imageurl = imageurl;
		this.del = del;
    }
    //���췽��1
    public Article(int articleid, String title, String content, String date,
			String type, int count, String imageurl, int del) {
		super();
		this.articleid = articleid;
		this.title = title;
		this.content = content;
		this.date = date;
		this.type = type;
		this.count = count;
		this.imageurl = imageurl;
		this.del = del;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	
}
