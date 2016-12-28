package com.yousns.vo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-12-09
 */
public class PostVO {
	private String PostKey;
	private String UserKey;
	private String groupkey;
	
	private String pagekey;
	private String groupname;
	private String pagename;
	
	private String Name;
	private String Content;
	private String UtubeLink;
	private String tags;
	private int Like_cnt=0;
	private int comment_cnt=0;
	private Date date;
	
	private ArrayList<CommentVO> comment;

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

	public String getGroupkey() {
		return groupkey;
	}

	public void setGroupkey(String groupkey) {
		this.groupkey = groupkey;
	}

	public String getPagekey() {
		return pagekey;
	}

	public void setPagekey(String pagekey) {
		this.pagekey = pagekey;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getUtubeLink() {
		return UtubeLink;
	}

	public void setUtubeLink(String utubeLink) {
		UtubeLink = utubeLink;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<CommentVO> getComment() {
		return comment;
	}

	public void setComment(ArrayList<CommentVO> comment) {
		this.comment = comment;
	}
	
	
	
}
