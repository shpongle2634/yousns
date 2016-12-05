package com.yousns.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yousns.vo.PostVO;

public class PostDAO extends DBConnect{

	
	// 게시물 리스트
	public ArrayList<PostVO> getlist(int pagenum, int itemnum, String userKey){
		
		ArrayList<PostVO> list = new ArrayList<>();
		Connection conn=null;
		CallableStatement cstmt=null;
		ResultSet rs =null;
		
		try {
			PostVO vo =null;
			conn = super.getConnection();
			cstmt = conn.prepareCall("{call PostList(?,?,?)}"); //function 을 호출할것
			cstmt.setInt(1, pagenum);
			cstmt.setInt(2, itemnum);
			cstmt.setString(3, userKey);
			
			if(cstmt.execute()){
				rs=cstmt.getResultSet(); //Resultset(튜플리스트)를 받아옴
				while(rs.next()){
					
					vo = new PostVO(); //게시물을 담아서
					vo.setPostKey(rs.getString("PostKey"));
					vo.setText(rs.getString("Content"));
					vo.setUserKey(rs.getString("UserKey"));
					vo.setName(rs.getString("Name"));
//					vo.setPhotoPath(rs.getString("PhotoPath"));
					vo.setUtubeLink(rs.getString("UtubeLink"));
					vo.setDate(rs.getDate("written"));
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
}
