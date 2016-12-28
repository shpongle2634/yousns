<%@page import="com.yousns.vo.PageVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<!-- body -->

<jsp:useBean id="pageDAO" class="com.yousns.dao.PageDAO"></jsp:useBean>

<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\page.png" width="30px" height="30px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>페이지</strong></span>
	</div>
</div>


<div class="wrapper">
	<div class="content">
		<%
			String userkey = (String) session.getAttribute("userKey");
			ArrayList<PageVO> pagelist = pageDAO.page_list(userkey);
		%>
		<div class="d2">
			<div class="comment_report">
				페이지 이름 <br> <br>
				<form action="pages" method="post">
					<input type="text" placeholder="생성할 페이지 이름 입력" name="pageName">
					<button type="submit"></button>
				</form>
			</div>
			<br> <br>
		</div>

	</div>
</div>

<div class="wrapper">
	<div class="content3">
		<%
			if (pagelist != null) {
				for (PageVO v : pagelist) {		
		%>
		<div class="pageform" style="background: #fff;">
			<div class="pagephoto">
				<img src="\yousns\jsp\image\person.png" width="80px" height="80px"
					style="padding-top: 10px; margin-left: 10px;">
			</div>
			<div class="pagename"
				style='display: table-cell; vertical-align: middle'>
				<a href="/yousns/pages/<%=v.getPageKey()%>"><strong><%=v.getPageName()%></strong></a>
			</div>
			<div class="pagebtn">
				<%
					if (v.getUserKey().equals(userkey)) {
				%>
				<a href="pages/<%=v.getPageKey()%>/delete"><img src="\yousns\jsp\image\pagedelete.png" width="50px"
					height="35px"></a>
				<%
					} else {
				%>
				<a href="pages/<%=v.getPageKey()%>/users/<%=userkey%>"><img src="\yousns\jsp\image\pagecancel.png" width="50px"
					height="35px"></a>
				<%
					}
				%>
			</div>
		</div>
		<%
			}
			}
		%>
	</div>
</div>
</body>
</html>