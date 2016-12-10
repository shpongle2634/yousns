<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");
	String MyIntroduce = request.getParameter("MyIntroduce");
	String Address = request.getParameter("Address");
	String Phone = request.getParameter("Phone");
	String Job = request.getParameter("Job");
	String Married = request.getParameter("Married");

	boolean success = userDAO.updateDetails(userKey, MyIntroduce, Address, Phone, Job,
			Short.parseShort(Married));

	if (success) {
%>
<script type="text/javascript"> alert('상세정보 등록완료.'); 
				location.href='/users/<%=userKey%>/details';
</script>
<%
	} else {
%>
<script type="text/javascript"> alert('상세정보 등록 실패'); 
				location.href='/users/<%=userKey%>
	/details';
</script>
<%
	}
%>