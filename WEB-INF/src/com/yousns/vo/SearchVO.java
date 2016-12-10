package com.yousns.vo;

import java.util.ArrayList;

public class SearchVO {
	ArrayList<UserVO> userlist =new ArrayList<>();
	ArrayList<PostVO> postlist =new ArrayList<>();
	ArrayList<GroupVO> grouplist =new ArrayList<>();
	ArrayList<PageVO> pagelist =new ArrayList<>();
	
	public ArrayList<UserVO> getUserlist() {
		return userlist;
	}
	public void setUserlist(UserVO user) {
		this.userlist.add(user);
	}
	public ArrayList<PostVO> getPostlist() {
		return postlist;
	}
	public void setPostlist(PostVO post) {
		this.postlist.add(post);
	}
	public ArrayList<GroupVO> getGrouplist() {
		return grouplist;
	}
	public void setGrouplist(GroupVO group) {
		this.grouplist.add(group);
	}
	public ArrayList<PageVO> getPagelist() {
		return pagelist;
	}
	public void setPagelist(PageVO page) {
		this.pagelist.add(page);
	}
	
	
}
