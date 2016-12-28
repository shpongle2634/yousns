<%@page import="com.yousns.vo.FriendVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<jsp:useBean id="groupDAO" class="com.yousns.dao.GroupDAO"></jsp:useBean>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\group.png" width="30px" height="18px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>그룹원</strong></span>
	</div>
</div>
<%

	String userKey = (String) session.getAttribute("userKey");
	String groupkey =(String) request.getAttribute("groupKey");
	
	ArrayList<FriendVO> flist =null;
	flist = groupDAO.list_member_request(groupkey);	

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
					<div class="friendname" style='display:table-cell;vertical-align:middle'><strong><%=f.getFriendName()%></strong></div>
						<div class="friendbtn" style="width:auto">
						<a href="/yousns/groups/<%=groupkey%>/users/<%=f.getFriendKey()%>/approve">
						<img src="\yousns\jsp\image\accept.png" width="50px" height="35px"></a>
						<a href="/yousns/groups/<%=groupkey%>/users/<%=f.getFriendKey()%>/reject">
						<img src="\yousns\jsp\image\reject.png" width="50px" height="35px">
						</a></div>
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