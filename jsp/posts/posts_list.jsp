<%@page import="com.yousns.vo.FriendVO"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@page import="com.yousns.vo.GroupVO"%>
<%@page import="com.yousns.vo.PageVO"%>
<%@page import="com.yousns.vo.PostVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<%@include file="../header.jsp"%>

<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO"></jsp:useBean>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO"></jsp:useBean>
<jsp:useBean id="pageDAO" class="com.yousns.dao.PageDAO"></jsp:useBean>
<jsp:useBean id="groupDAO" class="com.yousns.dao.GroupDAO"></jsp:useBean>
<script src="//code.jquery.com/jquery.min.js"></script>
<script>
	$(function() {
		$("textarea.autosize").keyup(
				function() {
					$(this).css("height", "1px").css("height",
							(20 + $(this).prop("scrollHeight")) + "px");
				});
	});
</script>

<%
	String userkey = (String) session.getAttribute("userKey");
	String someone = (String) request.getAttribute("someonekey");
	String pagekey = (String) request.getAttribute("pagekey");
	String groupkey = (String) request.getAttribute("groupkey");
	PageVO pageinfo = null;
	GroupVO groupinfo = null;
	ArrayList<PostVO> posts = null;

	if (someone != null) {
		posts = userDAO.getMyPosts(someone);
		UserVO u = userDAO.myPage(someone);
		ArrayList<FriendVO> myflist = userDAO.getfriends(userkey);
		ArrayList<String> fklist=new ArrayList<String>();
		for(FriendVO v : myflist){
			fklist.add(v.getFriendKey());
		}
		u.setPwd(null);
%>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\page.png" width="30px" height="30px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;">
			<strong><%=u.getName()%></strong>
		</span>
		<%
			if (!fklist.contains(someone) && !someone.equals(userkey)) {
		%>
		<a
			href="/yousns/users/<%=session.getAttribute("userKey")%>/friends/<%=someone%>">
			친구신청 </a>
		<%
			}
		%>
	</div>
</div>
<%
	} else if (pagekey != null) {
		posts = pageDAO.getnewspeed(pagekey);
		pageinfo = pageDAO.read_page(pagekey);
%>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\page.png" width="30px" height="30px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;">
			<strong><%=pageinfo.getPageName()%></strong>
		</span>
		<p style="margin: 5px 5px 0px 5px; padding-bottom: 2px;"><%=pageinfo.getSubscribe_cnt()%>
			명 구독중
		</p>
		<a href="/yousns/pages/<%=pageinfo.getPageKey()%>/users/<%=userkey%>">
			<p style="margin: 5px 5px 0px 5px; padding-bottom: 2px;">구독하기</p>
		</a>
	</div>
</div>
<%
	} else if (groupkey != null) {
		posts = groupDAO.getnewspeed(groupkey);
		groupinfo = groupDAO.read_group(groupkey);
		ArrayList<FriendVO> mlist = groupDAO.list_member(groupkey);
%>
<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\page.png" width="30px" height="30px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;">
			<strong><%=groupinfo.getGroupName()%></strong>
		</span>
		<p style="margin: 5px 5px 0px 5px; padding-bottom: 2px;"><%=groupinfo.getMembers_cnt()%>
			명 가입
		</p>
		<a href="/yousns/groups/<%=groupinfo.getGroupKey()%>/users">구성원보기
		</a>
		<%
			if (groupinfo.getUserKey().equals(userkey)) {
		%>
		<a href="/yousns/groups/<%=groupinfo.getGroupKey()%>/delete">삭제하기</a>
		<a href="/yousns/groups/<%=groupinfo.getGroupKey()%>/requests">가입요청현황</a>
		<%
			} else {
					boolean isMember = false;
					for (FriendVO m : mlist) {
						if (m.getFriendKey().equals(userkey))
							isMember = true;
					}
					if (isMember) {
		%>
		<a href="/yousns/groups/<%=groupinfo.getGroupKey()%>/users/<%=userkey%>/delete">
			<p style="margin: 5px 5px 0px 5px; padding-bottom: 2px;">탈퇴하기</p>
		</a>
		<%
			} else {
		%><a href="/yousns/groups/<%=groupinfo.getGroupKey()%>/users/<%=userkey%>">
			<p style="margin: 5px 5px 0px 5px; padding-bottom: 2px;">가입하기</p>
		</a>
		<%}}%>
	</div>
</div>
<%
	
	} else {
		posts = postDAO.getlist(userkey);
	}
%>




<!— 게시글 작성 —>
<div class="wrapper">
	<div class="content">
		<div class="photospace">
			<img alt="person" src="/yousns/jsp/image/person.png" width="100%"
				height="100%">
		</div>
		<div class="stringspace">
			<a href="/yousns/users/<%=userkey%>/posts"><span style="margin: 0 0 5% 1%;"><%=(String) session.getAttribute("userName")%>
				<br> </span></a>
		</div>
		<form action="/yousns/posts" method="POST"
			style="width: 90%; margin-left: 9%; margin-top: 3.3012%;">
			<input type="hidden" name="pageKey"
				value="<%=pageinfo != null ? pageinfo.getPageKey() : ""%>"> <input
				type="hidden" name="groupKey"
				value="<%=groupinfo != null ? groupinfo.getGroupKey() : ""%>"> <input
				class="autosize" type="text" name="utubeLink"
				placeholder="  유투브 링크 입력" style="margin-bottom: 1%; width: 80%;">
			<textarea class="autosize" rows="5" name="content"
				placeholder="무슨 동영상을 공유하고 싶은가요?"
				style="border-bottom: 1px solid #d2d2d2; width: 80%;"></textarea>
			<button type="submit">
				<img alt="submit" src="\yousns\jsp\image\post.png" width="62px"
					height="45px">
			</button>
		</form>
	</div>
</div>

<%
	if (posts != null) {
		for (PostVO vo : posts) {
%>
<div class="wrapper">
	<div class="content">
		<div class="photospace">
			<img alt="person" src="/yousns/jsp/image/person.png" width="100%"
				height="100%">
		</div>
		<div class="stringspace">
			<a href="/yousns/users/<%=vo.getUserKey()%>/posts"><span style="margin: 0 0 5% 3%;"> <%=vo.getName()%> <br>
			</span></a>
			<%if(vo.getGroupkey() !=null) {%>
			<a href="/yousns/groups/<%=vo.getGroupkey()%>"><span style="margin: 0 0 5% 3%;"> <%=vo.getGroupname()%> <br>
			</span></a>
			<%}else if(vo.getPagekey() !=null){%>
			<a href="/yousns/pages/<%=vo.getPagekey()%>"><span style="margin: 0 0 5% 3%;"> <%=vo.getPagename()%> <br>
			</span></a>
			
			<%}%>
		</div>
		<div class="datespace" style="margin-left: 9%;"><%=vo.getDate()%> 
		<%if(vo.getUserKey().equals(userkey)){ %>
		<span><a href="/yousns/posts/<%=vo.getPostKey()%>/delete">삭제하기</a></span>
		<%} %></div>
		<iframe width="73%" height="480px" src="<%=vo.getUtubeLink()%>"
			frameborder="0" allowfullscreen style="margin: 6% 10% 0 2.04%;">
		</iframe>
		<textarea class="autosize"
			style="border: 0px solid #d2d2d2; width: 73%; margin-left: 0%;"><%=vo.getContent()%></textarea>
		<div class="postbtn" >
			<a href="posts/<%=vo.getPostKey()%>/likes"
				style="text-align: right; width: auto; height: 40px;">
				<button type="submit" style="background-color: white">
					<img alt="like" src="\yousns\jsp\image\like.png" width="50px"
						height="50px"><span style="font-size: 70%; color: #F15B42;">
						<strong> <%=vo.getLike_cnt()%></strong>
					</span>
				</button>
			</a>

			<a href="posts/<%=vo.getPostKey()%>">
				<button type="submit" style="background-color: white">
					<img alt="comment" src="\yousns\jsp\image\comment.png" width="50px"
						height="50px"><span style="font-size: 70%; color: #F15B42;">
						<strong> <%=vo.getComment_cnt()%></strong>
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
	}
%>

</body>
</html>

