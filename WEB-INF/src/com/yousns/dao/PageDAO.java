package com.yousns.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yousns.utils.Keygen;
import com.yousns.vo.PageVO;
import com.yousns.vo.PostVO;

import oracle.jdbc.internal.OracleTypes;

public class PageDAO extends DBConnect {

	//페이지 리스트
	public ArrayList<PageVO> page_list(String userkey){
		ArrayList<PageVO> plist = null;
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs =null;
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call read_list_page(?,?)}");
			cstmt.setString(1, userkey);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			cstmt.execute();
			rs= (ResultSet) cstmt.getObject(2);

			plist=new ArrayList<>();
			while(rs.next()){
				System.out.println("11");
				PageVO vo = new PageVO();
				vo.setPageKey(rs.getString("pageKey"));
				vo.setPageName(rs.getString("pageName"));
				vo.setSubscribe_cnt(rs.getInt("subscribe_cnt"));
				vo.setUserKey(rs.getString("userKey"));
				plist.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt,rs); //커넥션은 닫아줄것
		}
		
		return plist;
	}
	
	//페이지 생성
	public boolean newpage(String userkey, String pagename){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 
		ResultSet rs =null;
		
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_page(?,?,?)}");
			cstmt.setString(1, Keygen.generateKey());
			cstmt.setString(2, pagename);
			cstmt.setString(3, userkey);
			
			cstmt.execute();
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		
		return success;
	}
	
	//페이지 삭제
	public boolean deletepage( String pagekey){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 
		System.out.println(pagekey);
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call delete_page(?)}");
			cstmt.setString(1, pagekey);
			cstmt.execute();
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}
	//페이지 구독
	public boolean insert_subscribe(String userkey, String pagekey){
		boolean success= false;
		Connection conn=null;
		CallableStatement cstmt=null; 

		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call insert_subscribe(?,?)}");
			cstmt.setString(1, pagekey);
			cstmt.setString(2, userkey);
			cstmt.execute();
			success = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			super.close(conn,cstmt); //커넥션은 닫아줄것
		}
		return success;
	}
	
	//페이지 뉴스피드
		public ArrayList<PostVO> getnewspeed(String pagekey){
			System.out.println("리스트");
			ArrayList<PostVO> list = new ArrayList<>();
			Connection conn=null;
			CallableStatement cstmt=null; //게시물, 댓글, 대댓글
			ResultSet rs =null;//게시물, 댓글, 대댓글

			try {
				PostVO vo =null;
				conn = super.getConnection();
				cstmt = conn.prepareCall("{call newspeed_page(?,?)}"); //게시물

				cstmt.setString(1, pagekey);
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
	
	
	//페이지 로드
	public PageVO read_page(String pagekey){
		PageVO vo = null;
		Connection conn=null;
		CallableStatement cstmt=null; //게시물, 댓글, 대댓글
		ResultSet rs =null;//게시물, 댓글, 대댓글
		try {
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call read_page(?,?)}"); //게시물

			cstmt.setString(1, pagekey);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			cstmt.execute();
			rs=(ResultSet) cstmt.getObject(2); //Resultset(튜플리스트)를 받아옴
			if(rs.next()){					
				vo = new PageVO(); //게시물을 담아서
				vo.setPageKey(rs.getString("pagekey"));
				vo.setPageName(rs.getString("pagename"));
				vo.setSubscribe_cnt(rs.getInt("subscribe_cnt"));
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
		
	
}
