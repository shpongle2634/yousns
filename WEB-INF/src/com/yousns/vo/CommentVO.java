package com.yousns.vo;

import java.sql.Date;
import java.util.ArrayList;

public class CommentVO {
	
	private String CommentKey;
	private String PostKey;
	private String userKey;
	private String userName;
	private String Content;
	private int Like_cnt=0;
	private int comment_cnt=0;

	private Date Written;
	private ArrayList<CommentVO> ofComments;
	
	public String getCommentKey() {
		return CommentKey;
	}
	public void setCommentKey(String commentKey) {
		CommentKey = commentKey;
	}
	public String getPostKey() {
		return PostKey;
	}
	public void setPostKey(String postKey) {
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
	public int getLike_cnt() {
		return Like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		Like_cnt = like_cnt;
	}
	public int getComment_cnt() {
		return comment_cnt;
	}
	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}
	
}
