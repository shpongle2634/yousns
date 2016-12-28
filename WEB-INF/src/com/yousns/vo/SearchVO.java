package com.yousns.vo;

import java.util.ArrayList;

public class SearchVO {
	ArrayList<FriendVO> userlist =new ArrayList<>();
	ArrayList<FriendVO> friendlist=new ArrayList<>();
	ArrayList<PostVO> postlist =new ArrayList<>();
	ArrayList<GroupVO> grouplist =new ArrayList<>();
	ArrayList<PageVO> pagelist =new ArrayList<>();
	
	public ArrayList<FriendVO> getUserlist() {
		return userlist;
	}
	public void setUserlist(ArrayList<FriendVO> userlist) {
		this.userlist = userlist;
	}
	public ArrayList<PostVO> getPostlist() {
		return postlist;
	}
	public void setPostlist(ArrayList<PostVO> postlist) {
		this.postlist = postlist;
	}
	public ArrayList<GroupVO> getGrouplist() {
		return grouplist;
	}
	public void setGrouplist(ArrayList<GroupVO> grouplist) {
		this.grouplist = grouplist;
	}
	public ArrayList<PageVO> getPagelist() {
		return pagelist;
	}
	public void setPagelist(ArrayList<PageVO> pagelist) {
		this.pagelist = pagelist;
	}
	public ArrayList<FriendVO> getFriendlist() {
		return friendlist;
	}
	public void setFriendlist(ArrayList<FriendVO> friendlist) {
		this.friendlist = friendlist;
	}
	
	
	
	
}
