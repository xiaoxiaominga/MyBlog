package com.entity;

import java.sql.Date;

/**
 * @author 马腾飞
 *访问者信息
 */
public class Visitor {
     private int visitorid;
     private String ip;
     private String hostname;
     private Date date;//sql类型的日期格式。
     private int del=0;
	public int getVisitorid() {
		return visitorid;
	}
	public void setVisitorid(int visitorid) {
		this.visitorid = visitorid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public Visitor(int visitorid, String ip, String hostname, Date date, int del) {
		super();
		this.visitorid = visitorid;
		this.ip = ip;
		this.hostname = hostname;
		this.date = date;
		this.del = del;
	}
     
}
