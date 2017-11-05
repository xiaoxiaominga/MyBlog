package com.entity;

/**
 * @author 马腾飞
 *评论表实体
 */
public class Comment extends Entity {
    private int commentid;
    private int hostid;
    private String content;
    private int artistid;
    private int del=0;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getHostid() {
		return hostid;
	}
	public void setHostid(int hostid) {
		this.hostid = hostid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getArtistid() {
		return artistid;
	}
	public void setArtistid(int artistid) {
		this.artistid = artistid;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public Comment(int commentid, int hostid, String content, int artistid,
			int del) {
		super();
		this.commentid = commentid;
		this.hostid = hostid;
		this.content = content;
		this.artistid = artistid;
		this.del = del;
	}
	
}
