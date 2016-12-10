<%@page import="com.yousns.vo.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<jsp:useBean id ="searchDAO" class="com.yousns.dao.SearchDAO"></jsp:useBean>

<%
String keyword = request.getParameter("keyword");
SearchVO vo=null;
if(keyword !=null){
	vo = searchDAO.search(keyword);
}

%>