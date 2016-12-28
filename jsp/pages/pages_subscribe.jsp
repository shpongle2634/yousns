<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<jsp:useBean id="pageDAO" class="com.yousns.dao.PageDAO" scope="page" />
<%
	String pagekey = (String)request.getAttribute("pageKey");
	String userkey= (String)session.getAttribute("userKey");
	
	boolean success = pageDAO.insert_subscribe(userkey, pagekey);
	if (success) {
%>
<script type="text/javascript">
	alert('구독/취소 성공');
	location.href = '/yousns/pages/<%=pagekey%>';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('구독실패');
	location.href = '/yousns/pages/<%=pagekey%>';
</script>
<%
	}
%>