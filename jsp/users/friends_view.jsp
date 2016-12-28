<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.FriendVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO"></jsp:useBean>

	
	
	<div class="wrapper">
		<div class="content2">
			<img src="\yousns\jsp\image\friend.png" width="12px" height="10px"
				style="padding-bottom: 0px;"><span
				style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>친구</strong></span>
		</div>
	</div>
<%

	String userKey = (String) session.getAttribute("userKey");

	
	ArrayList<FriendVO> flist =null;
	flist = userDAO.getfriends(userKey);

	

	if(flist !=null){
		for(FriendVO f : flist){
			
		%>
		<div class="wrapper">
		<div class="content3">
			<div class="left-friend">
				<div class="friendform">
					<div class="friendphoto">
						<img src="\yousns\jsp\image\person.png" width="80px" height="80px" style="padding-top:10px;">
					</div>
					<div class="friendname" style='display:table-cell;vertical-align:middle'>
					<a href="/yousns/users/<%=f.getFriendKey()%>/posts"><strong><%=f.getFriendName()%></strong></a></div>
						<%if(f.getFlag() ==0){%>
						<a href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=f.getFriendKey()%>/delete">
						<div class="friendbtn"><img src="\yousns\jsp\image\delete.png" width="50px" height="35px"></div>
						</a>
						<% }else if(f.getFlag() ==1){%>
						<a href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=f.getFriendKey()%>/delete">
						<div class="friendbtn"><img src="\yousns\jsp\image\cancel.png" width="50px" height="35px"></div>
						</a>
						<% }else if(f.getFlag() ==2){%>
						<a href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=f.getFriendKey()%>/approve">
						<div class="friendbtn"><img src="\yousns\jsp\image\accept.png" width="50px" height="35px"></div>
						</a>
						<%} %>
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