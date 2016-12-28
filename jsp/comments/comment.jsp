<%@page import="com.yousns.vo.CommentVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO"></jsp:useBean>
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
	String postkey = (String) request.getAttribute("postkey");
	PostVO vo = postDAO.getPost(postkey);
	String userkey= (String) session.getAttribute("userKey");
	if (vo != null) {
%>




<div class="commentwrapper">
	<div class="content4">
		<div class="photospace">
			<img alt="person" src="/yousns/jsp/image/person.png" width="100%"
				height="100%">
		</div>
		<div class="stringspace" style="margin-bottom: 6%; padding-top: 2%;">
			<span style="margin: 0 0 5% 3%; padding-top: 10%;"><%=vo.getName()%><br>
			</span>
		</div>
		<div class="datespace" style="margin-left: 14%; padding-top: 2%;"><%=vo.getDate()%></div>
		<br> <br>
		<iframe width="90%" height="580px" src="<%=vo.getUtubeLink()%>"
			frameborder="0" allowfullscreen></iframe>
		<textarea
			style="width: 90%; height: auto; text-align: left; margin-left: 1%;"><%=vo.getContent()%></textarea>
		<!-- !!!@!@!@@!@!@! -->

		<div class="full" style="margin:0px;">
         <form method="get" action="<%=vo.getPostKey()%>/likes"
            style="float: left; width: 75%; height: 50px; text-align: right; padding:0px;">
            <button type="submit" style="background-color: white"><img alt="like" src="\yousns\jsp\image\like.png" width="50px" height="50px"><span
               style="font-size: 70%; color: #F15B42;"><strong><%=vo.getLike_cnt()%></strong></span>
               </button>
         </form>
         <div class="full" style="width: auto; float: left; text-align:right; margin:0px;">
            <img alt="comment" src="\yousns\jsp\image\comment.png" width="50px"
               height="50px"><span style="font-size: 70%; color: #F15B42;"><strong><%=vo.getComment_cnt()%></strong></span>
            <img alt="report" src="\yousns\jsp\image\report.png" width="50px" height="50px">
         </div>
      </div>


		<div class="commentform" style="height: auto;">
			<div class="commentphoto">
				<img src="\yousns\jsp\image\person.png" width="50px" height="50px">
			</div>
			<div class="comment2">
				<form style="width: 100%; height: 100%;" action="../comments"
					method="post">
					<input type="hidden" name="postKey" value="<%=postkey%>">
					<textarea class="autosize"
						style="border: 1px solid #d2d2d2; width: 90%;" name="content"></textarea>
					<button style="background-color: white" type="submit">
						<img alt="submit" src="\yousns\jsp\image\transfer2.png"
							width="62px" height="45px">
					</button>
				</form>
			</div>
		</div>

		<%
			ArrayList<CommentVO> cmtlist = vo.getComment();
				if (cmtlist != null) {
					for (CommentVO c : cmtlist) {
		%>


		<div class="commentform" style="height: auto;">
			<div class="commentphoto">
				<img src="\yousns\jsp\image\person.png" width="100px" height="100px">
			</div>

			<div class="commentname">
				<a href="#"><strong><%=c.getUserName()%></strong></a>
			</div>
			<div class="comment2">
				<textarea class="autosize" style="width: 90%;" readonly="readonly"><%=c.getContent()%></textarea>
			</div>
			<div class="commentleft">
				<form style="width:auto ;float:left;" action="../comments/<%=c.getCommentKey()%>/likes" method="get">
				<button type="submit" style="background-color: white"><img alt="like" src="\yousns\jsp\image\like.png" width="30px"
					height="30px"><span style="font-size: 70%; color: #F15B42;"><strong><%=c.getLike_cnt()%></strong></span>
				</button>
				</form>
				
				
				<img alt="comment" src="\yousns\jsp\image\comment.png" width="30px"
					height="30px"><span style="font-size: 70%; color: #F15B42;"><strong><%=c.getComment_cnt()%></strong></span>
				
				<button  style="background-color: white">
					<img alt="report" src="\yousns\jsp\image\report.png" width="50px"
						height="50px" data-toggle="modal" data-target="#reportComment">
				</button>
				<%=c.getWritten()%>
				<%if(userkey.equals(c.getUserKey())){%>
				<span><a href="/yousns/comments/<%=c.getCommentKey()%>/delete"> 삭제하기</a></span>
				<%}%>
			</div>


		
		<%
			ArrayList<CommentVO> ccmtlist = c.getOfComments();
						if (ccmtlist != null) {
							for (CommentVO cc : ccmtlist) {
		%>
		<!-- 대댓글 -->
		<div class="cmtcommentform" style="height: auto;">
			<div class="cmtcommentphoto">
				<img src="\yousns\jsp\image\person.png" width="100px" height="100px">
			</div>

			<div class="cmtcommentname">
				<a href="#"><strong><%=cc.getUserName()%></strong></a>
			</div>
			<div class="cmtcomment2">
				<textarea class="autosize" style="width: 90%;" readonly="readonly"><%=cc.getContent()%></textarea>
			</div>
			<div class="cmtcommentleft">
			<form style="padding:0;width:auto ;float:left;" action="../comments/<%=cc.getCommentKey()%>/likes" method="get">
				<button type="submit" style="background-color: white"><img alt="like" src="\yousns\jsp\image\like.png" width="30px"
					height="30px"><span style="font-size: 70%; color: #F15B42;"><strong><%=cc.getLike_cnt()%></strong></span>
				</button>
				</form>
				<img alt="comment" src="\yousns\jsp\image\comment.png" width="30px"
					height="30px"> <span style="font-size: 70%; color: #F15B42;"><strong><%=cc.getComment_cnt()%></strong></span>
				<button  style="background-color: white">
					<img alt="report" src="\yousns\jsp\image\report.png" width="50px"
						height="50px" data-toggle="modal" data-target="#reportComment">
				</button>
				<%=cc.getWritten()%>
				
				<%if(userkey.equals(cc.getUserKey())){%>
				<span><a href="/yousns/comments/<%=cc.getCommentKey()%>/delete"> 삭제하기</a></span>
				<%}%>
			</div>
		</div>
		<%
			}
		%>
		<!-- 대댓글 달기 -->
		<div class="cmtofcmtcommentform" style="height: auto;">
			<div class="commentphoto">
				<img src="\yousns\jsp\image\person.png" width="50px" height="50px">
			</div>
			<div class="comment2">
				<form style="width: 100%; height: 100%;"
					action="../comments/<%=c.getCommentKey()%>/comments" method="post">
					<input type="hidden" name="postkey"
						value="<%=postkey%>">
					<input type="hidden" name="commentKey"
						value="<%=c.getCommentKey()%>">
					<textarea class="autosize"
						style="border: 1px solid #d2d2d2; width: 90%;" name="content"></textarea>
					<button style="background-color: white;" type="submit">
						<img alt="submit" src="\yousns\jsp\image\transfer2.png"
							width="62px" height="45px">
					</button>
				</form>
			</div>
		</div>
		
		<%
			}%>
			</div>
			<%
					}

				}

			}
		%>

	</div>
	</body>
	</html>