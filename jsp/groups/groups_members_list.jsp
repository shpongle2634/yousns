<%@page import="com.yousns.vo.GroupVO"%>
<%@page import="com.yousns.vo.FriendVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<jsp:useBean id="groupDAO" class="com.yousns.dao.GroupDAO"></jsp:useBean>

<%
	String userKey = (String) session.getAttribute("userKey");
	String groupkey = (String) request.getAttribute("groupKey");
	GroupVO groupinfo = groupDAO.read_group(groupkey);
	ArrayList<FriendVO> flist = null;
	flist = groupDAO.list_member(groupkey);
%>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\group.png" width="30px" height="18px"
			style="padding-bottom: 0px;"> <a
			href="/yousns/groups/<%=groupinfo.getGroupKey()%>"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;">
				<strong><%=groupinfo.getGroupName()%></strong>
		</span></a>
	</div>
</div>
<%
	if (userKey.equals(groupinfo.getUserKey())) {
		for (FriendVO f : flist) {
%>
<div class="wrapper">
	<div class="content3">
		<div class="left-friend">
			<div class="friendform">
				<div class="friendphoto">
					<img src="\yousns\jsp\image\person.png" width="80px" height="80px"
						style="padding-top: 10px;">
				</div>
				<div class="friendname"
					style='display: table-cell; vertical-align: middle'>
					<a href= "/yousns/users/<%=f.getFriendKey()%>/posts"><strong><%=f.getFriendName()%></strong></a>
				</div>
				<%
					if (!f.getFriendKey().equals(groupinfo.getUserKey())) { //개설자 아닌 그룹 가입자
				%>
				<a
					href="/yousns/groups/<%=groupinfo.getGroupKey()%>/users/<%=f.getFriendKey()%>/delete">
					<div class="friendbtn">
						<img src="\yousns\jsp\image\ban.png" width="50px" height="35px">
					</div>
				</a> 
				<%
					}
				%>
			</div>
		</div>
	</div>
</div>
<%
	}
	} else {
		for (FriendVO f : flist) {
%>
<div class="wrapper">
	<div class="content3">
		<div class="left-friend">
			<div class="friendform">
				<div class="friendphoto">
					<img src="\yousns\jsp\image\person.png" width="80px" height="80px"
						style="padding-top: 10px;">
				</div>
				<div class="friendname"
					style='display: table-cell; vertical-align: middle'>
					<a href= "/yousns/users/<%=f.getFriendKey()%>/posts"><strong><%=f.getFriendName()%></strong> </a>
				</div>
			</div>
		</div>
	</div>
</div>
<%
	}
	}
%>

</body>
</html>