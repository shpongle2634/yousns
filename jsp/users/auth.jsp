<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope= "page"/>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
// 	UserVO vo = userDAO.login(id, password);
	UserVO vo= new UserVO();
	vo.setName("서태훈");
	vo.setUserKey(userDAO.generateKey());

	if(vo!=null){
		String token =userDAO.generateToken();
		session.setAttribute("token", token);
		session.setAttribute("userName", vo.getName());
		session.setAttribute("userKey", vo.getUserKey());
		response.sendRedirect("/yousns/posts");
	}
	else{
		%>
			<script type="text/javascript"> alert('사용자 정보가 일치하지 않습니다.'); 
				location.href='http://localhost:8080/yousns';
			</script>
		<%
	}
%>