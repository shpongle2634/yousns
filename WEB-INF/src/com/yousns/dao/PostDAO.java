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

import com.yousns.utils.Keygen;
import com.yousns.vo.CommentVO;
import com.yousns.vo.PostVO;

import oracle.jdbc.internal.OracleTypes;

/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-11-25
 */

public class PostDAO extends DBConnect{

	// 게시물 리스트
	public ArrayList<PostVO> getlist(String userKey){
		System.out.println("리스트");
		ArrayList<PostVO> list = new ArrayList<>();
		Connection conn=null;
		CallableStatement cstmt=null; //게시물, 댓글, 대댓글
		ResultSet rs =null;//게시물, 댓글, 대댓글

		try {
			PostVO vo =null;
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call newspeed_post(?,?)}"); //게시물

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


	//게시물 로드
	public PostVO getPost(String postId){
		PostVO vo = null;
		Connection conn=null;
		CallableStatement cstmt=null, comment_cstmt=null, cmt_cmt_cstmt=null;
		ResultSet rs =null, commentset=null, comment_commentset=null;
		System.out.println(postId);
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call read_post(?,?)}"); //게시글
			comment_cstmt=conn.prepareCall("call read_post_comment(?,?)"); //댓글
			cmt_cmt_cstmt= conn.prepareCall("call read_post_cmt_of_cmt(?,?)");//대댓글
			cstmt.setString (1, postId);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			comment_cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cmt_cmt_cstmt.registerOutParameter(2,OracleTypes.CURSOR);

			cstmt.execute();
			rs=(ResultSet) cstmt.getObject(2); //Resultset(튜플리스트)를 받아옴
			if(rs.next()){
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
				//1.댓글 로드
				
				ArrayList<CommentVO> comments=new ArrayList<>();
				comment_cstmt.setString(1, vo.getPostKey());
				comment_cstmt.execute();
				commentset = (ResultSet) comment_cstmt.getObject(2);
				while(commentset.next()){
					CommentVO cvo=new CommentVO();
					cvo.setPostKey(vo.getPostKey());
					cvo.setCommentKey(commentset.getString("CommentKey"));
					cvo.setUserKey(rs.getString("UserKey"));
					cvo.setUserName(commentset.getString("Name"));
					cvo.setContent(commentset.getString("Content"));
					cvo.setWritten(commentset.getDate("Written"));
					cvo.setLike_cnt(commentset.getInt("Like_cnt"));
					cvo.setComment_cnt(commentset.getInt("Comment_cnt"));

					//2.대댓글 로드
					cmt_cmt_cstmt.setString(1, commentset.getString("CommentKey"));

					cmt_cmt_cstmt.execute();
					comment_commentset=(ResultSet) cmt_cmt_cstmt.getObject(2);
					ArrayList<CommentVO> ofComments=new ArrayList<>();
					CommentVO ccvo=null;
					while(comment_commentset.next()){
						//대댓글 생성
						ccvo=new CommentVO();
						ccvo.setCommentKey(comment_commentset.getString("CommentKey"));
						ccvo.setUserKey(comment_commentset.getString("UserKey"));
						ccvo.setUserName(comment_commentset.getString("Name"));
						ccvo.setContent(comment_commentset.getString("Content"));
						ccvo.setWritten(comment_commentset.getDate("Written"));
						ccvo.setLike_cnt(comment_commentset.getInt("Like_cnt"));;
						ofComments.add(ccvo);
					}
					cvo.setOfComments(ofComments);
					comments.add(cvo);
				}
		
				comment_cstmt.close();
				vo.setComment(comments);
			}
			cmt_cmt_cstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}
		return vo;
	}

	//게시물 생성
	public boolean newPost(String userKey, String content,String pageKey,String groupKey, String utubelink, String tags, ArrayList<String> gallery){
		System.out.println(userKey + content +tags +pageKey+groupKey);
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_post(?,?,?,?,?,?)}"); //게시물
			String postkey =Keygen.generateKey();
			cstmt.setString(1, userKey);
			cstmt.setString(2, postkey);
			cstmt.setString(3, pageKey);
			cstmt.setString(4, groupKey);
			cstmt.setString(5, content);
			cstmt.setString(6, utubelink);

			cstmt.execute();//게시물 삽입
			cstmt.close();
			cstmt = conn.prepareCall("{call insert_gellery(?,?)}");//갤러리삽입
			cstmt.setString(2, postkey );
			for(String path:gallery){
				cstmt.setString(1, path);
				cstmt.execute();
			}
			cstmt.close();

			if(tags!=null){
				String[] tag=tags.split("#");
				for(String t : tag){
					System.out.println(t.length());
					if(t!="" && !t.isEmpty() && t.length()>0){
					cstmt = conn.prepareCall("{call insert_tag(?,?,?)}");
					cstmt.setString(1, postkey);
					cstmt.setString(2, Keygen.generateKey());
					cstmt.setString(3, t);
					cstmt.execute();
					}
				}
			}
			success=true;


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
	public boolean editPost(String postkey, String content, String utubelink,String tags, ArrayList<String> gallery){

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
				cstmt.setString(2, postkey);
				for(String path:gallery){
					cstmt.setString(1, path);
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
			cstmt.execute();
				success=true;
			
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
			cstmt = conn.prepareCall("{call report_post(?,?,?)}"); //게시물
			cstmt.setString(1, postKey);
			cstmt.setString(2, userKey);
			cstmt.setString(3, content);

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
		System.out.println(postKey +"  "+ userKey);
		Connection conn=null;
		CallableStatement cstmt=null; 
		boolean success = false;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_like_post(?,?)}"); //게시물
			cstmt.setString(1, userKey);
			cstmt.setString(2, postKey);
			cstmt.execute();
				success=true;
			
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
