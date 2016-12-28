package com.yousns.vo;

import java.util.ArrayList;

public class PageVO {

	private String PageKey;
	private String PageName;
	private String UserKey;
	private int subscribe_cnt;
	private ArrayList<PostVO> posts;
	
	
	public String getPageKey() {
		return PageKey;
	}
	public void setPageKey(String pageKey) {
		PageKey = pageKey;
	}
	public String getPageName() {
		return PageName;
	}
	public void setPageName(String pageName) {
		PageName = pageName;
	}
	public String getUserKey() {
		return UserKey;
	}
	public void setUserKey(String userKey) {
		UserKey = userKey;
	}
	public ArrayList<PostVO> getPosts() {
		return posts;
	}
	public void setPosts(ArrayList<PostVO> posts) {
		this.posts = posts;
	}
	public int getSubscribe_cnt() {
		return subscribe_cnt;
	}
	public void setSubscribe_cnt(int subscribe_cnt) {
		this.subscribe_cnt = subscribe_cnt;
	}
	
	
}
