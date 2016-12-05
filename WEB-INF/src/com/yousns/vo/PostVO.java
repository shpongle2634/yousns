package com.yousns.vo;

import java.util.Date;

public class PostVO {
	private String PostKey;
	private String UserKey;
	private String Text;
	private String UtubeLink;
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
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
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
	private Date date;
}
