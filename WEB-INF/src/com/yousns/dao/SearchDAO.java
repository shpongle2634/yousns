package com.yousns.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yousns.vo.FriendVO;
import com.yousns.vo.GroupVO;
import com.yousns.vo.PageVO;
import com.yousns.vo.PostVO;
import com.yousns.vo.SearchVO;

import oracle.jdbc.OracleTypes;

public class SearchDAO extends DBConnect {

	public SearchVO search(String keyword, String userkey){
		SearchVO result= new SearchVO();
		Connection conn =null;
		CallableStatement cstmt=null;
		ResultSet users= null,pages= null,groups= null,posts= null,friends=null;
		System.out.println(keyword + userkey);
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call search(?,?,?,?,?,?,?)}");
			cstmt.setString(1, keyword);
			cstmt.setString(2, userkey);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
			cstmt.registerOutParameter(4, OracleTypes.CURSOR);
			cstmt.registerOutParameter(5, OracleTypes.CURSOR);
			cstmt.registerOutParameter(6, OracleTypes.CURSOR);
			cstmt.registerOutParameter(7, OracleTypes.CURSOR);
			cstmt.execute();
			users=(ResultSet) cstmt.getObject(3);
			friends=(ResultSet) cstmt.getObject(4);
			pages=(ResultSet) cstmt.getObject(5);
			groups=(ResultSet) cstmt.getObject(6);
			posts=(ResultSet) cstmt.getObject(7);
			
			result.setUserlist(getusers(users));
			result.setFriendlist(getfriends(friends));
			result.setPagelist(getpages(pages));
			result.setGrouplist(getgroups(groups));
			result.setPostlist(getposts(posts));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt);
				try {
					if(users!=null) users.close();
					if(friends!=null) friends.close();
					if(pages!=null) pages.close();
					if(groups!=null) groups.close();
					if(posts!=null) posts.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	private ArrayList<FriendVO> getusers(ResultSet users) throws SQLException{
		ArrayList<FriendVO> ulist=null;
		ulist=new ArrayList<>();
		while(users.next()){
			FriendVO vo= new FriendVO();
			vo.setFriendKey(users.getString("userkey"));
			vo.setFriendName(users.getString("name"));
			ulist.add(vo);
		}
		return ulist;
	}
	private ArrayList<FriendVO> getfriends(ResultSet friends) throws SQLException{
		ArrayList<FriendVO> ulist=null;
		ulist=new ArrayList<>();
		while(friends.next()){
			FriendVO vo= new FriendVO();
			vo.setFriendKey(friends.getString("friend"));
			vo.setFriendName(friends.getString("name"));
			vo.setFlag(friends.getShort("friend_flag"));
			ulist.add(vo);
		}
		return ulist;
	}
	private ArrayList<PageVO> getpages(ResultSet pages) throws SQLException{
		ArrayList<PageVO> plist=null;
		plist=new ArrayList<>();
		while(pages.next()){
			PageVO vo= new PageVO();
			vo.setPageKey(pages.getString("pagekey"));
			vo.setPageName(pages.getString("pagename"));
			vo.setSubscribe_cnt(pages.getInt("subscribe_cnt"));
			vo.setUserKey(pages.getString("userkey"));
			plist.add(vo);
		}
		return plist;
	}private ArrayList<GroupVO> getgroups(ResultSet groups) throws SQLException{
		ArrayList<GroupVO> glist=null;
		glist=new ArrayList<>();
		while(groups.next()){
			GroupVO vo= new GroupVO();
			vo.setGroupKey(groups.getString("groupkey"));
			vo.setGroupName(groups.getString("groupname"));
			vo.setMembers_cnt(groups.getInt("members_cnt"));
			vo.setUserKey(groups.getString("userkey"));
			glist.add(vo);
		}
		return glist;
	}
	private ArrayList<PostVO> getposts(ResultSet posts) throws SQLException{
		ArrayList<PostVO> plist=null;
		plist=new ArrayList<>();
		while(posts.next()){
			PostVO vo = new PostVO(); //게시물을 담아서
			
			vo.setPostKey(posts.getString("PostKey"));
			vo.setContent(posts.getString("Content"));
			vo.setUserKey(posts.getString("UserKey"));
			vo.setPagekey(posts.getString("PageKey"));
			vo.setPagename(posts.getString("PageName"));
			vo.setGroupkey(posts.getString("GroupKey"));
			vo.setGroupname(posts.getString("GroupName"));
			vo.setName(posts.getString("Name"));
			vo.setUtubeLink(posts.getString("UtubeLink"));
			vo.setDate(posts.getDate("Written"));
			vo.setLike_cnt(posts.getInt("Like_cnt"));
			vo.setComment_cnt(posts.getInt("Comment_cnt"));
			System.out.println("게시물 로드");
			plist.add(vo);//리스트에 추가
		}
		return plist;
	}
}
