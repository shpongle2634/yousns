package com.yousns.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yousns.utils.Keygen;
import com.yousns.vo.FriendVO;
import com.yousns.vo.GroupVO;
import com.yousns.vo.PostVO;

import oracle.jdbc.internal.OracleTypes;


public class GroupDAO extends DBConnect {

	//그룹 리스트
	public ArrayList<GroupVO> grouplist(String userkey){
		ArrayList<GroupVO> glist = null;
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs =null;

		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call list_group(?,?)}");
			cstmt.setString(1, userkey);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			cstmt.execute();
			rs= (ResultSet) cstmt.getObject(2);
			glist=new ArrayList<>();
			while(rs.next()){
				GroupVO vo = new GroupVO();
				vo.setGroupKey(rs.getString("groupkey"));
				vo.setGroupName(rs.getString("groupName"));
				vo.setMembers_cnt(rs.getInt("members_cnt"));
				vo.setUserKey(rs.getString("userkey"));
				glist.add(vo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt,rs);
		}


		return glist;
	}
	//그룹 생성
	public boolean newgroup(String userkey, String groupname){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs =null;

		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call create_group(?,?,?)}");
			cstmt.setString(1, Keygen.generateKey());
			cstmt.setString(2, groupname);
			cstmt.setString(3, userkey);

			cstmt.execute();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt,rs);
		}

		return success;
	}

	//그룹 삭제
	public boolean deletegroup( String groupkey){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 
		//			System.out.println(groupkey);
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call delete_group(?)}");
			cstmt.setString(1, groupkey);
			cstmt.execute();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt);
		}

		return success;
	}
	//그룹 가입 요청
	public boolean join_group(String userkey, String groupkey){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 

		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call join_group(?,?)}");
			cstmt.setString(1, groupkey);
			cstmt.setString(2, userkey);
			cstmt.execute();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt);
		}
		return success;
	}

	//그룹 뉴스피드
	public ArrayList<PostVO> getnewspeed(String groupkey){
		ArrayList<PostVO> list = new ArrayList<>();
		Connection conn=null;
		CallableStatement cstmt=null; //게시물, 댓글, 대댓글
		ResultSet rs =null;//게시물, 댓글, 대댓글

		try {
			PostVO vo =null;
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call newspeed_group(?,?)}"); //게시물

			cstmt.setString(1, groupkey);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			cstmt.execute();
			rs=(ResultSet) cstmt.getObject(2); //Resultset(튜플리스트)를 받아옴
			while(rs.next()){					
				vo = new PostVO(); //게시물을 담아서

				vo.setPostKey(rs.getString("PostKey"));
				vo.setContent(rs.getString("Content"));
				vo.setUserKey(rs.getString("UserKey"));
				vo.setPagekey(rs.getString("PageKey"));
				vo.setPagename(rs.getString("PageName"));
				vo.setGroupkey(rs.getString("GroupKey"));
				vo.setGroupname(rs.getString("GroupName"));
				vo.setName(rs.getString("Name"));
				vo.setUtubeLink(rs.getString("UtubeLink"));
				vo.setDate(rs.getDate("Written"));
				vo.setLike_cnt(rs.getInt("Like_cnt"));
				vo.setComment_cnt(rs.getInt("Comment_cnt"));
				System.out.println("게시물 로드");
				list.add(vo);//리스트에 추가
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}

		return list; //결과 리턴.
	}


	//그룹 로드
	public GroupVO read_group(String groupkey){
		GroupVO vo = null;
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs =null;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call read_group(?,?)}"); 

			cstmt.setString(1, groupkey);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			cstmt.execute();
			rs=(ResultSet) cstmt.getObject(2); //Resultset(튜플리스트)를 받아옴
			if(rs.next()){					
				vo = new GroupVO(); //게시물을 담아서
				vo.setGroupKey(rs.getString("groupkey"));
				vo.setGroupName(rs.getString("groupname"));
				vo.setMembers_cnt(rs.getInt("members_cnt"));
				vo.setUserKey(rs.getString("userkey"));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}

		return vo;

	}

	//그룹 가입승인
	public boolean approve_member(String userkey, String groupkey){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 

		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call approve_member(?,?)}");
			cstmt.setString(1, userkey);
			cstmt.setString(2, groupkey);
			cstmt.execute();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	//그룹가입 거절/삭제
	public boolean reject_member(String userkey, String groupkey){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 
		System.out.println(userkey + groupkey);
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call reject_member(?,?)}");
			cstmt.setString(1, userkey);
			cstmt.setString(2, groupkey);
			cstmt.execute();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt);
		}
		return success;
	}
	//가입자 리스트
	public ArrayList<FriendVO> list_member(String groupkey){
		ArrayList<FriendVO> mlist= null;
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs=null;
		try {
			FriendVO vo =null;
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call list_member(?,?)}");
			cstmt.setString(1, groupkey);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
			rs= (ResultSet) cstmt.getObject(2);
			mlist=new ArrayList<>();
			while(rs.next()){
				vo =new FriendVO();
				vo.setFriendKey(rs.getString("userkey"));
				vo.setFriendName(rs.getString("name"));
				System.out.println(vo.getFriendName());
				mlist.add(vo);	
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt,rs);
		}
		return mlist;
	}	

	//가입 요청자 리스트
	public ArrayList<FriendVO> list_member_request(String groupkey){
		ArrayList<FriendVO> mlist= null;

		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs=null;
		try {
			FriendVO vo =null;
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call list_member_request(?,?)}");
			cstmt.setString(1, groupkey);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
			rs= (ResultSet) cstmt.getObject(2);
			mlist=new ArrayList<>();
			while(rs.next()){
				vo =new FriendVO();
				vo.setFriendKey(rs.getString("userkey"));
				vo.setFriendName(rs.getString("name"));
				mlist.add(vo);
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt,rs);
		}
		return mlist;
	}	

	//그룹 탈퇴
	public boolean delete_member(String userkey, String groupkey){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 

		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call delete_member(?,?)}");
			cstmt.setString(1, groupkey);
			cstmt.setString(2, userkey);
			cstmt.execute();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,cstmt);
		}
		return success;
	}	
	
}
