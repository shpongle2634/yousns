package com.yousns.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.yousns.utils.Keygen;
import com.yousns.vo.DetailVO;
import com.yousns.vo.FriendVO;
import com.yousns.vo.PostVO;
import com.yousns.vo.UserVO;

import oracle.jdbc.internal.OracleTypes;
/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-12-09
 */
public class UserDAO extends DBConnect{

	public String generateToken() {
		String token = null;
		SecureRandom secureRandom;
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			secureRandom.setSeed(secureRandom.generateSeed(128));
			try {
				token= new String(digest.digest((secureRandom.nextLong() + "").getBytes()),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}


	//로그인
	public UserVO login(String id, String password){
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs =null;
		UserVO vo =null;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call auth_user(?,?,?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.setString(2, password);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
			cstmt.execute();

				rs=(ResultSet) cstmt.getObject(3); //Resultset(튜플리스트)를 받아옴
				if(rs.next()){					
					System.out.println("로그인 성공");
					vo = new UserVO(); //패스워드빼고 셋업해서 저장.
					vo.setUserKey(rs.getString("UserKey"));
					vo.setEmail(rs.getString("Email"));
					vo.setName(rs.getString("Name"));
					//					vo.setGender(rs.getShort("Gender"));
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}
		return vo;
	}

	//친구목록
	public ArrayList<FriendVO> getfriends(String id){
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs =null;
		ArrayList<FriendVO> list =new ArrayList<>();
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call friends_user(?,?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
				rs=(ResultSet) cstmt.getObject(2); //Resultset(튜플리스트)를 받아옴
				while(rs.next()){					
					FriendVO vo= new FriendVO(); //친구 생성 후 리스트등록
					vo.setFriendKey(rs.getString("Friend"));
					vo.setFriendName(rs.getString("name"));
					vo.setFlag(rs.getShort("Friend_Flag"));
					list.add(vo);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}
		return list;
	}


	//친구삭제
	public boolean deleteFriend(String id,String fid){
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success= false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call delete_user(?,?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.setString(2, fid);
			cstmt.execute();
				success=true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}


	//친구수락
	public boolean approveFriend(String id,String fid){
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success= false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call approve_user(?,?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.setString(2, fid);
			cstmt.execute();
				success=true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}

	//친구신청
	public boolean requestFriend(String id,String fid){
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success= false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call request_user(?,?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.setString(2, fid);
			cstmt.execute();
				success=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}

	//회원가입
	public boolean join(String Email,String Pwd,String Name,short Gender){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success= false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_user(?,?,?,?,?)}"); //게시물
			cstmt.setString(1, Keygen.generateKey());
			cstmt.setString(2, Email);
			cstmt.setString(3, Name);
			cstmt.setString(4, Pwd);
			cstmt.setShort(5, Gender);
			cstmt.execute();
				success=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}

	//회원정보수정
	public boolean updateUser(String userKey,String Pwd){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success= false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call update_user(?,?)}"); //게시물
			cstmt.setString(1, userKey);
			cstmt.setString(2, Pwd);

			cstmt.execute();
				success=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}

	//마이페이지
	public UserVO myPage(String id){
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs= null;
		UserVO vo = null;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call read_user(?,?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
				rs=(ResultSet) cstmt.getObject(2);
				if(rs.next()){
					vo=new UserVO();
					vo.setUserKey(rs.getString("UserKey"));
					vo.setEmail(rs.getString("Email"));
					vo.setName(rs.getString("Name"));
					vo.setGender(rs.getShort("Gender"));
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}
		return vo;
	}

	//상세정보 생성 /수정
	public boolean updateDetails(String userKey,String MyIntroduce, String Address, String Phone, String Job,String School, short Married){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success= false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call update_detail_user(?,?,?,?,?,?,?)}"); //게시물
			cstmt.setString(1, userKey);
			cstmt.setString(2, MyIntroduce);
			cstmt.setString(3, Address);
			cstmt.setString(4, Phone);
			cstmt.setString(5, Job);
			cstmt.setString(6, School);
			cstmt.setShort(7, Married);
			cstmt.execute();
				success=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}

	//상세정보 조회
	public DetailVO myDetails(String id){
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs= null;
		DetailVO vo = null;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call read_detail_user(?,?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
				rs=(ResultSet) cstmt.getObject(2);
				if(rs.next()){
					vo=new DetailVO();
					vo.setMyIntroduce(rs.getString("MyIntroduce"));
					vo.setAddress(rs.getString("Address"));
					vo.setJob(rs.getString("Job"));
					vo.setPhone(rs.getString("Phone"));
					vo.setSchool(rs.getString("School"));
					vo.setMarried(rs.getShort("Married"));
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}
		return vo;
	}

	//내 게시물 리스트
	public ArrayList<PostVO> getMyPosts(String userKey){

		ArrayList<PostVO> list = new ArrayList<>();
		Connection conn=null;
		CallableStatement cstmt=null; //게시물, 댓글, 대댓글
		ResultSet rs =null;//게시물, 댓글, 대댓글

		try {
			PostVO vo =null;
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call myPost_user(?,?)}"); //게시물
			cstmt.setString(1, userKey);
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

	//회원탈퇴
	public boolean remove_user(String id){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success= false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call remove_user(?)}"); //게시물
			cstmt.setString(1, id);
			cstmt.execute();
				success=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}


	//	public static void main(String[] args) {
	//
	//		UserDAO dao= new UserDAO();
	//		System.out.println(dao.generateKey());
	//
	//	}

}
