<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");
	String MyIntroduce = request.getParameter("myintroduce")!=null?request.getParameter("myintroduce"):null;
	String Address = request.getParameter("address")!=null?request.getParameter("address"):null;
	String Phone = request.getParameter("phone")!=null?request.getParameter("phone"):null;
	String School= request.getParameter("school")!=null?request.getParameter("school"):null;
	String Job = request.getParameter("job")!=null?request.getParameter("job"):null;
	String Married =request.getParameter("married")!=null?request.getParameter("married"):null;
	Short m=0;
	if(Married!=null)
	m=Short.parseShort(Married);
	boolean success = userDAO.updateDetails(userKey, MyIntroduce, Address, Phone, Job, School, (short)m);

	if (success) {
%>
<script type="text/javascript"> alert('상세정보 등록완료.'); 
				location.href='/yousns/users/<%=userKey%>/details';
</script>
<%
	} else {
%>
<script type="text/javascript"> alert('상세정보 등록 실패'); 
				location.href='/yousns/users/<%=userKey%>/details';
</script>
<%
	}
%>