<%@page import="com.yousns.vo.PostVO"%>
<%@page import="com.yousns.vo.PageVO"%>
<%@page import="com.yousns.vo.GroupVO"%>
<%@page import="com.yousns.vo.FriendVO"%>
<%@page import="com.yousns.vo.SearchVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="header.jsp"%>
<jsp:useBean id="searchDAO" class="com.yousns.dao.SearchDAO"></jsp:useBean>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\friend.png" width="12px" height="10px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>검색결과</strong></span>
	</div>
</div>
<%
	String keyword = (String) request.getAttribute("keyword");
	String userkey = (String) session.getAttribute("userKey");

	SearchVO vo = searchDAO.search(keyword, userkey);
%>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\friend.png" width="12px" height="10px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>내
				친구</strong></span>
	</div>
</div>
<%
	for (FriendVO f : vo.getFriendlist()) {//친구리스트
%>
<div class="wrapper">
	<div class="content3">
		<div class="left-friend">
			<div class="friendform">
				<div class="friendphoto">
					<img src="\yousns\jsp\image\person.png" width="80px" height="80px"
						style="padding-top: 10px;">
				</div>
				<a href="/yousns/users/<%=f.getFriendKey()%>/posts"><div class="friendname"
					style='display: table-cell; vertical-align: middle'>
					<strong><%=f.getFriendName()%></strong></a>
				</div>
				<%
					if (f.getFlag() == 0) {
				%>
				<a
					href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=f.getFriendKey()%>/delete">
					<div class="friendbtn">
						<img src="\yousns\jsp\image\delete.png" width="50px" height="35px">
					</div>
				</a>
				<%
					} else if (f.getFlag() == 1) {
				%>
				<a
					href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=f.getFriendKey()%>/delete">
					<div class="friendbtn">
						<img src="\yousns\jsp\image\cancel.png" width="50px" height="35px">
					</div>
				</a>
				<%
					} else if (f.getFlag() == 2) {
				%>
				<a
					href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=f.getFriendKey()%>/approve">
					<div class="friendbtn">
						<img src="\yousns\jsp\image\accept.png" width="50px" height="35px">
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
%>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\friend.png" width="12px" height="10px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>사용자</strong></span>
	</div>
</div>
<%
	for (FriendVO f : vo.getUserlist()) {
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
					<a href="/yousns/users/<%=f.getFriendKey()%>/posts"><strong><%=f.getFriendName()%></strong></a>
				</div>
				<a
					href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=f.getFriendKey()%>">
					<div class="friendbtn">
						<img src="\yousns\jsp\image\transfer.png" width="50px"
							height="35px">
					</div>
				</a>
			</div>
		</div>
	</div>
</div>
<%
	}
%>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\friend.png" width="12px" height="10px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>페이지</strong></span>
	</div>
</div>
<%
	for (PageVO v : vo.getPagelist()) {
%>
<div class="wrapper">
	<div class="content3">
		<div class="pageform" style="background: #fff;">
			<div class="pagephoto">
				<img src="\yousns\jsp\image\person.png" width="80px" height="80px"
					style="padding-top: 10px; margin-left: 10px;">
			</div>
			<div class="pagename"
				style='display: table-cell; vertical-align: middle'>
				<a href="/yousns/pages/<%=v.getPageKey()%>"><strong><%=v.getPageName()%></strong></a>
			</div>
		</div>
	</div>
</div>
<%
	}
%>

<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\friend.png" width="12px" height="10px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>그룹</strong></span>
	</div>
</div>
<%
	for (GroupVO v : vo.getGrouplist()) {
%>
<div class="wrapper">
	<div class="content3">
		<div class="groupform" style="background: #fff;">
			<div class="groupphoto">
				<img src="jsp\image\person.png" width="80px" height="80px"
					style="padding-top: 10px; margin-left: 10px;">
			</div>
			<div class="groupname">
				<a href="/yousns/groups/<%=v.getGroupKey()%>"><strong><%=v.getGroupName()%></strong></a>
			</div>
		</div>
	</div>
</div>
<%
	}
%>

<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\friend.png" width="12px" height="10px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>태그
				검색</strong></span>
	</div>
</div>
<%
	for (PostVO p : vo.getPostlist()) {
%>
<div class="wrapper">
	<div class="content">
		<div class="photospace">
			<img alt="person" src="/yousns/jsp/image/person.png" width="100%"
				height="100%">
		</div>
		<div class="stringspace">
			<span style="margin: 0 0 5% 3%;"> <%=p.getName()%> <br>
			</span>
		</div>
		<div class="datespace" style="margin-left: 9%;"><%=p.getDate()%></div>
		<iframe width="73%" height="480px" src="<%=p.getUtubeLink()%>"
			frameborder="0" allowfullscreen style="margin: 6% 10% 0 2.04%;">
		</iframe>
		<textarea class="autosize"
			style="border: 0px solid #d2d2d2; width: 73%; margin-left: 0%;"><%=p.getContent()%></textarea>
		<div class="postbtn">
			<form method="post" action="posts/<%=p.getPostKey()%>"
				style="float: left; text-align: right; width: 85%; height: 40px;">
				<button type="submit" style="background-color: white">
					<img alt="like" src="\yousns\jsp\image\like.png" width="50px"
						height="50px"><span style="font-size: 70%; color: #F15B42;">
						<strong> <%=p.getLike_cnt()%></strong>
					</span>
				</button>
			</form>

			<a href="posts/<%=p.getPostKey()%>">
				<button type="submit" style="background-color: white">
					<img alt="comment" src="\yousns\jsp\image\comment.png" width="50px"
						height="50px"><span style="font-size: 70%; color: #F15B42;">
						<strong> <%=p.getComment_cnt()%></strong>
					</span>
				</button>
			</a>

			<button style="background-color: white">
				<img alt="report" src="\yousns\jsp\image\report.png" width="50px"
					height="50px" data-toggle="modal" data-target="#reportPost">
			</button>

		</div>

	</div>
</div>
<%
	}
%>

