<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>

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
<div class="wrapper">
	<div class="content2">
		<img src="image\page.png" width="30px" height="30px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 5px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>내
				글보기</strong></span>
	</div>
</div>

<!— 게시글 작성 —>
<div class="wrapper">
	<div class="content">
		<div class="photospace">
			<img alt="person" src="image/person.png" width="100%" height="100%">
		</div>
		<div class="stringspace">
			<span style="margin: 0 0 5% 1%;">이용준${name} <br>
			</span>
		</div>
		<form method="post"
			style="width: 90%; margin-left: 9%; margin-top: 3.3012%;">
			<input class="autosize" type="text" name="utubeLink"
				placeholder="  유투브 링크 입력" style="margin-bottom: 1%; width: 80%;">
			<textarea class="autosize" rows="5"
				placeholder="
			
무슨 동영상을 공유하고 싶은가요?"
				style="border-bottom: 1px solid #d2d2d2; width: 80%;"></textarea>
			<img alt="submit" src="image\post.png" width="62px" height="45px">
		</form>
	</div>
</div>

<!— 이미 게시된 게시글 —>
<div class="wrapper">
	<div class="content">
		<div class="photospace">
			<img alt="person" src="image/person.png" width="100%" height="100%">
		</div>
		<div class="stringspace">
			<span style="margin: 0 0 5% 3%;">이용준${name} <br>
			</span>
		</div>
		<div class="datespace" style="margin-left: 9%;">2016-12-20
			10:20:32</div>
		<iframe width="73%" height="580px"
			src="https://www.youtube.com/embed/REAEnNIUwqQ" frameborder="0"
			allowfullscreen style="margin: 6% 10% 0 2.04%;"> </iframe>
		<textarea class="autosize"
			style="border: 0px solid #d2d2d2; width: 73%; margin-left: 0%;">소프트웨어의 중요성!!</textarea>
		<div class="postbtn">
			<img alt="like" src="image\like.png" width="50px" height="50px"><span
				style="font-size: 70%; color: #F15B42;"> <strong>30${likeNum}</strong></span><img
				alt="comment" src="image\comment.png" width="50px" height="50px"><span
				style="font-size: 70%; color: #F15B42;"><strong>20${commentCnt}</strong></span><img
				alt="report" src="image\report.png" width="50px" height="50px">
		</div>

	</div>
</div>
<!— 이미 게시된 게시글 —>
<div class="wrapper">
	<div class="content">
		<div class="photospace">
			<img alt="person" src="image/person.png" width="100%" height="100%">
		</div>
		<div class="stringspace">
			<span style="margin: 0 0 5% 3%;">이용준${name} <br>
			</span>
		</div>
		<div class="datespace" style="margin-left: 9%;">2016-12-20
			10:20:32</div>
		<iframe width="73%" height="580px"
			src="https://www.youtube.com/embed/REAEnNIUwqQ" frameborder="0"
			allowfullscreen style="margin: 6% 10% 0 2.04%;"> </iframe>
		<textarea class="autosize"
			style="border: 0px solid #d2d2d2; width: 73%; margin-left: 0%;">소프트웨어의 중요성!!</textarea>
		<div class="postbtn">
			<img alt="like" src="image\like.png" width="50px" height="50px"><span
				style="font-size: 70%; color: #F15B42;"> <strong>30${likeNum}</strong></span><img
				alt="comment" src="image\comment.png" width="50px" height="50px"><span
				style="font-size: 70%; color: #F15B42;"><strong>20${commentCnt}</strong></span><img
				alt="report" src="image\report.png" width="50px" height="50px">
		</div>

	</div>
</div>
</body>
</html>

