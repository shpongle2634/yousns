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
 * Last updated : 2016-11-25
 */

public class PostDAO extends DBConnect{
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

	// 게시물 리스트
	public ArrayList<PostVO> getlist(int pagenum, int itemnum, String userKey){

		ArrayList<PostVO> list = new ArrayList<>();
		Connection conn=null;
		CallableStatement cstmt=null; //게시물, 댓글, 대댓글
		ResultSet rs =null;//게시물, 댓글, 대댓글

		try {
			PostVO vo =null;
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call PostList(?,?,?)}"); //게시물

			cstmt.setInt(1, pagenum);
			cstmt.setInt(2, itemnum);
			cstmt.setString(3, userKey);

			if(cstmt.execute()){
				rs=cstmt.getResultSet(); //Resultset(튜플리스트)를 받아옴
				while(rs.next()){					
					vo = new PostVO(); //게시물을 담아서
					vo.setPostKey(rs.getInt("PostKey"));
					vo.setText(rs.getString("Content"));
					vo.setUserKey(rs.getString("UserKey"));
					vo.setName(rs.getString("Name"));
					vo.setUtubeLink(rs.getString("UtubeLink"));
					vo.setDate(rs.getDate("Written"));
					vo.setLikes(rs.getInt("Likes"));
					list.add(vo);//리스트에 추가
				}
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


	//게시물 로드
	public PostVO getPost(int postId){
		PostVO vo = null;
		Connection conn=null;
		CallableStatement cstmt=null, comment_cstmt=null, comment_comment_cstmt=null;
		ResultSet rs =null, commentset=null, comment_commentset=null;

		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call getPost(?)}"); //게시글
			comment_cstmt=conn.prepareCall("call GetComments(?)"); //댓글
			comment_comment_cstmt= conn.prepareCall("call GetCommentComments(?)");//대댓글
			cstmt.setInt(1, postId);

			if(cstmt.execute()){
				rs=cstmt.getResultSet(); //Resultset(튜플리스트)를 받아옴
				if(rs.next()){
					vo = new PostVO(); //게시물을 담아서
					vo.setPostKey(rs.getInt("PostKey"));
					vo.setText(rs.getString("Content"));
					vo.setUserKey(rs.getString("UserKey"));
					vo.setName(rs.getString("Name"));
					vo.setUtubeLink(rs.getString("UtubeLink"));
					vo.setDate(rs.getDate("Written"));
					vo.setLikes(rs.getInt("Likes"));
					//1.댓글 로드
					comment_cstmt.setInt(1, vo.getPostKey());
					if(comment_cstmt.execute()){
						commentset = comment_cstmt.getResultSet();
						while(commentset.next()){
							CommentVO cvo=new CommentVO();
							cvo.setPostKey(vo.getPostKey());
							cvo.setCommentKey(commentset.getString("CommentKey"));
							cvo.setContent(commentset.getString("Content"));
							cvo.setWritten(commentset.getDate("Written"));
							cvo.setLikes(commentset.getInt("Likes"));
							//2.대댓글 로드
							comment_comment_cstmt.setString(1, commentset.getString("CommentKey"));
							if(comment_comment_cstmt.execute()){
								comment_commentset=comment_comment_cstmt.getResultSet();
								ArrayList<CommentVO> ofComments=new ArrayList<>();
								CommentVO ccvo=null;
								while(comment_commentset.next()){
									//대댓글 생성
									ccvo=new CommentVO();
									ccvo.setCommentKey(comment_commentset.getString("CommentKey"));
									ccvo.setContent(comment_commentset.getString("Content"));
									ccvo.setWritten(comment_commentset.getDate("Written"));
									ccvo.setLikes(comment_commentset.getInt("Likes"));
									ofComments.add(ccvo);
								}
								cvo.setOfComments(ofComments);
								comment_comment_cstmt.close();
							}

						}
						comment_cstmt.close();
					}

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}
		return vo;
	}

	//게시물 생성
	public boolean newPost(String userKey, String content, String utubelink, ArrayList<String> gallery){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_post(?,?,?,?)}"); //게시물
			String key =generateKey();
			cstmt.setString(1, key);
			cstmt.setString(2, userKey);
			cstmt.setString(3, content);
			cstmt.setString(4, utubelink);

			if(cstmt.execute()){
				cstmt.close();
				cstmt = conn.prepareCall("{call insert_gellery(?,?)}");
				cstmt.setString(1, key);
				for(String path:gallery){
					cstmt.setString(2, path);
					cstmt.execute();
				}
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


	//게시물 수정
	public boolean editPost(String postkey, String content, String utubelink, ArrayList<String> gallery){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call update_post(?,?,?)}"); //게시물
			cstmt.setString(1, postkey);
			cstmt.setString(2, content);
			cstmt.setString(3, utubelink);

			if(cstmt.execute()){
				cstmt.close();

				cstmt = conn.prepareCall("{call delete_gellery(?)}");//갤러리 일단 삭제
				cstmt.setString(1, postkey);
				cstmt.execute();
				cstmt.close();

				cstmt = conn.prepareCall("{call insert_gellery(?,?)}");//새 갤러리 추가.
				cstmt.setString(1, postkey);
				for(String path:gallery){
					cstmt.setString(2, path);
					cstmt.execute();
				}
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

	//게시물 삭제
	public boolean deletePost(String postKey){

		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call delete_post(?)}"); //게시물
			cstmt.setString(1, postKey);
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

	//게시물 신고
	public boolean reportPost(String postKey, String userKey, String content, short reportflag){
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call report_post(?,?,?,?)}"); //게시물
			cstmt.setString(1, postKey);
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
	public boolean likePost(String postKey, String userKey){
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call like_post(?,?)}"); //게시물
			cstmt.setString(1, postKey);
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
