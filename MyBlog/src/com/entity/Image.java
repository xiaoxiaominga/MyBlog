package com.entity;

public class Image extends Entity {
    private int imageid;
    private String specification;
    private String type;
    private String url;
    private int del=0;//默认为没有删除的数据；
	public int getImageid() {
		return imageid;
	}
	public void setImageid(int imageid) {
		this.imageid = imageid;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public Image(int imageid, String specification, String type, String url,
			int del) {
		super();
		this.imageid = imageid;
		this.specification = specification;
		this.type = type;
		this.url = url;
		this.del = del;
	}
    
}
