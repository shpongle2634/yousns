package testDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yousns.vo.StudentVo;

public class StudentDAO extends DBconnect {

	
	public StudentVo getStudent(String userID, String userPass){
		String mySQL = "select s_name,s_pwd,s_id,s_addr from student where s_id =? and s_pwd=?";
		Connection conn = null;
		PreparedStatement psmt= null;
		ResultSet rs = null;
		StudentVo vo= null;
		try {
			conn=super.getConnection();
			psmt=conn.prepareStatement(mySQL);
			psmt.setString(1, userID); //물음표 치환
			psmt.setString(2, userPass); //물음표 치환 '' 필요없
			
	    	rs = psmt.executeQuery();
	    	if(rs.next()){
	    		vo = new StudentVo();
	    		vo.setS_id(rs.getString("s_id"));
	    		vo.setS_pwd("s_pwd");
	    		vo.setS_addr(rs.getString("s_addr"));
	    		vo.setS_name(rs.getString("s_name"));
	    	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn,psmt,rs);
		}
		
		return vo;
	}
}
