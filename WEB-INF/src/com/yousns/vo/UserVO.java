package com.yousns.vo;

public class UserVO {
	private String UserKey;
	private String Email;
	private String Name;
	private String Pwd;
	private short Gender;
	
	
	public String getUserKey() {
		return UserKey;
	}
	public void setUserKey(String userKey) {
		UserKey = userKey;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public short getGender() {
		return Gender;
	}
	public void setGender(short gender) {
		Gender = gender;
	}
	

}
