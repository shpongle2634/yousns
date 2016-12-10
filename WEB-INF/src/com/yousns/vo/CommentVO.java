package com.yousns.vo;

import java.sql.Date;
import java.util.ArrayList;

public class CommentVO {
	
	private String CommentKey;
	private int PostKey;
	private String userKey;
	private String userName;
	private String Content;
	
	private int Likes=0;
	private Date Written;
	private ArrayList<CommentVO> ofComments;
	
	public String getCommentKey() {
		return CommentKey;
	}
	public void setCommentKey(String commentKey) {
		CommentKey = commentKey;
	}
	public int getPostKey() {
		return PostKey;
	}
	public void setPostKey(int postKey) {
		PostKey = postKey;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Date getWritten() {
		return Written;
	}
	public void setWritten(Date written) {
		Written = written;
	}
	public ArrayList<CommentVO> getOfComments() {
		return ofComments;
	}
	public void setOfComments(ArrayList<CommentVO> ofComments) {
		this.ofComments = ofComments;
	}
	public int getLikes() {
		return Likes;
	}
	public void setLikes(int likes) {
		Likes = likes;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
