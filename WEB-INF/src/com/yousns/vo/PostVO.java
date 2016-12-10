package com.yousns.vo;

import java.util.Date;

/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-12-09
 */
public class PostVO {
	private int PostKey;
	private String UserKey;
	private String Name;
	private String Content;
	private String UtubeLink;
	private int Likes=0;
	private Date date;
	
	public int getPostKey() {
		return PostKey;
	}
	public void setPostKey(int postKey) {
		PostKey = postKey;
	}
	public String getUserKey() {
		return UserKey;
	}
	public void setUserKey(String userKey) {
		UserKey = userKey;
	}
	public String getContent() {
		return Content;
	}
	public void setText(String content) {
		Content = content;
	}
	public String getUtubeLink() {
		return UtubeLink;
	}
	public void setUtubeLink(String utubeLink) {
		UtubeLink = utubeLink;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getLikes() {
		return Likes;
	}
	public void setLikes(int likes) {
		Likes = likes;
	}
	
}
