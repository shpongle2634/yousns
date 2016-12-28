<%@page import="com.yousns.vo.GroupVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<!-- body -->
<jsp:useBean id="groupDAO" class="com.yousns.dao.GroupDAO"></jsp:useBean>
<div class="wrapper">
	<div class="content2">
		<img src="jsp\image\group.png" width="30px" height="30px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>그룹</strong></span>
	</div>
</div>


<div class="wrapper">
	<div class="content">
		<%
			String userkey = (String) session.getAttribute("userKey");
			ArrayList<GroupVO> grouplist = groupDAO.grouplist(userkey);
		%>
		<div class="d2">
			<div class="comment_report">
				그룹 생성 <br> <br>
				<form action="groups" method="post">
					<input type="text" placeholder="생성할 그룹 이름 입력" name="groupName">
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
			if (grouplist != null) {
				for (GroupVO v : grouplist) {
		%>

		<div class="groupform" style="background: #fff;">
			<div class="groupphoto">
				<img src="jsp\image\person.png" width="80px" height="80px"
					style="padding-top: 10px; margin-left: 10px;">
			</div>
			<div class="groupname">
				<a href="/yousns/groups/<%=v.getGroupKey()%>"><strong><%=v.getGroupName()%></strong></a>
			</div>
			<div class="groupbtn">
				<%
					if (v.getUserKey().equals(userkey)) {
				%>
				<a href="groups/<%=v.getGroupKey()%>/delete"><img
					src="\yousns\jsp\image\groupdelete.png" width="50px" height="35px"></a>
				<%
					} else {
				%>
				<a href="groups/<%=v.getGroupKey()%>/users/<%=userkey%>"><img
					src="\yousns\jsp\image\groupcancel.png" width="50px" height="35px"></a>
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
	</body>
	</html>