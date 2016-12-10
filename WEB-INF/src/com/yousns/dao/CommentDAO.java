package com.yousns.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.yousns.vo.CommentVO;
import com.yousns.vo.PostVO;

/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-12-10
 */

public class CommentDAO extends DBConnect{
	public static String generateKey(){
		String key = null;
		Date current = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);   
		key =f.format(current);
		Random rand =new Random();
		key+=Integer.toString(rand.nextInt(9));
		key+=Integer.toString(rand.nextInt(9));
		key+=Integer.toString(rand.nextInt(9));
		return key;

	}
	//댓글 생성
	public boolean newComment(String postKey, String userKey, String content){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_comment(?,?,?,?)}"); 
			String commentkey =generateKey();
			cstmt.setString(1, commentkey);
			cstmt.setString(2, postKey);
			cstmt.setString(3, userKey);
			cstmt.setString(4, content);

			if(cstmt.execute()){
				success=true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success; //결과 리턴.
	}
	//대댓글 생성
	public boolean newCommentOfComment(String commentKey, String userKey, String content){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_comment_comment(?,?,?,?)}");
			String newCommentKey= generateKey();
			cstmt.setString(1, newCommentKey);
			cstmt.setString(2, commentKey);
			cstmt.setString(3, userKey);
			cstmt.setString(4, content);
			if(cstmt.execute()){
				success=true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success; //결과 리턴.
	}

	//댓글 수정
	public boolean updateComment(String commentKey, String content){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call update_comment(?,?)}"); 
			cstmt.setString(1, commentKey);
			cstmt.setString(2, content);
			if(cstmt.execute()){
				success=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success; //결과 리턴.
	}

	//댓글 삭제
	public boolean deleteComment(String commentKey){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call delete_Comment(?)}"); 
			cstmt.setString(1, commentKey);
			if(cstmt.execute()){
				success=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success; //결과 리턴.
	}

	//댓글 신고
	public boolean reportComment(String commentKey, String userKey, String content, short reportflag){
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call report_comment(?,?,?,?)}"); 
			cstmt.setString(1, commentKey);
			cstmt.setString(2, userKey);
			cstmt.setString(3, content);
			cstmt.setShort(4, reportflag);

			if(cstmt.execute()){
				success=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success; //결과 리턴.
	}

	//좋아요
	public boolean likeComment(String commentKey, String userKey){
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call like_Comment(?,?)}"); 
			cstmt.setString(1, commentKey);
			cstmt.setString(2, userKey);
			if(cstmt.execute()){
				success=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success; //결과 리턴.
	}
}
