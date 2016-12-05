package com.yousns.vo;

import java.util.Date;

public class PostVO {
	private String PostKey;
	private String UserKey;
	private String Name;
	private String Content;
	private String UtubeLink;
//	private String PhotoPath;
	private Date date;
	
	public String getPostKey() {
		return PostKey;
	}
	public void setPostKey(String postKey) {
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
	
}
