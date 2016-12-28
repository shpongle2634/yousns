package com.yousns.vo;

import java.util.ArrayList;

public class GroupVO {
	private String groupKey;
	private String groupName;
	private String userKey;
	private int members_cnt;
	private ArrayList<PostVO> postlist;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public int getMembers_cnt() {
		return members_cnt;
	}
	public void setMembers_cnt(int members_cnt) {
		this.members_cnt = members_cnt;
	}
	public ArrayList<PostVO> getPostlist() {
		return postlist;
	}
	public void setPostlist(ArrayList<PostVO> postlist) {
		this.postlist = postlist;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	
}
