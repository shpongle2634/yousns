package com.yousns.vo;
/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-12-09
 */
public class FriendVO {
	private String FriendKey;
	private String FriendName;
	private short flag;
	public String getFriendName() {
		return FriendName;
	}
	public void setFriendName(String friendname) {
		FriendName = friendname;
	}
	public short getFlag() {
		return flag;
	}
	public void setFlag(short flag) {
		this.flag = flag;
	}
	public String getFriendKey() {
		return FriendKey;
	}
	public void setFriendKey(String friendKey) {
		FriendKey = friendKey;
	}
}
