<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>YouStar</title>
<link  href="\yousns\jsp\style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<style>
#wrap {
	display: table;
}

#cell {
	display: table-cell;
	vertical-align: middle;
}
</style>
</head>
<body>
	<div class="d4">
		<form action="/yousns/search" method="POST">
			<input type="text" placeholder="검색어 입력" name="keyword">
			<button type="submit"></button>
		</form>
	</div>


<!-- Modal -->
	<div class="modal fade" id="reportComment" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<form method="post" action="/">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">댓글 신고</h4>
					</div>
					<div class="modal-body">
						<textarea class="autosize"
							style="border: 1px solid #d2d2d2; width: 100%;" name="content"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default" data-dismiss="modal">전송</button>
					</div>
				</form>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="reportPost" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<form method="post" action="/">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">게시글 신고</h4>
					</div>
					<div class="modal-body">
						<textarea class="autosize"
							style="border: 1px solid #d2d2d2; width: 100%;" name="content"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default" data-dismiss="modal">전송</button>
					</div>
				</form>
			</div>
		</div>
	</div>



	<div class="sidebar">
		<h1 class="blog-title">
			<a href="/yousns/posts" title="You Star" rel="home"><img
				src="\yousns\jsp\image\logo.png" width="180px"></a>
		</h1>

		<a class="nav-toggle hidden" title="Click to view the navigation"
			href="#">
			<div class="bars">
				<div class="bar"></div>
				<div class="bar"></div>
				<div class="bar"></div>
				<div class="clear"></div>
			</div>
			<p>
				<span class="menu">Menu</span> <span class="close">Close</span>
			</p>
		</a>
		<ul class="main-menu">
			<li
				class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-66 current_page_item menu-item-68"><a
				href="/yousns/posts">HOME</a></li>
			<li
				class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-163"><a
				href="/yousns/users/<%=session.getAttribute("userKey")%>/friends">FRIEND</a>
				<ul class="sub-menu">
				</ul></li>
			<li
				class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-37"><a
				href="/yousns/posts">POST</a></li>
			<li
				class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-43"><a
				href="/yousns/pages">PAGE</a>
				<ul class="sub-menu">
					<li
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-86"><a
						href="/yousns/users/<%=session.getAttribute("userKey")%>">MYPAGE</a></li>
					<li
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-86"><a
						href="/yousns/users/<%=session.getAttribute("userKey")%>/details">DETAILS</a></li>
				</ul></li>
			<li
				class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-45"><a
				href="/yousns/groups">GROUP</a>
			<li
				class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-45"><a
				href="/yousns/users/auth/out">LOGOUT</a>	
			
		</ul>
		<div class="widgets"></div>
		<div class="clear"></div>
	</div>
	<!-- /sidebar -->