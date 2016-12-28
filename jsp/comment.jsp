<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="header.jsp"%>

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

<div class="commentwrapper">
	<div class="content4">
		<div class="photospace">
			<img alt="person" src="image/person.png" width="100%" height="100%">
		</div>
		<div class="stringspace" style="margin-bottom:6%; padding-top:2%;">
			<span style="margin: 0 0 5% 3%; padding-top:10%;">서태훈${name} <br>
			</span>
		</div>
		<div class="datespace" style="margin-left: 14%; padding-top:2%;">2016-12-20
			10:20:32</div>
		<br><br>	
			<iframe width="90%" height="580px" src="https://www.youtube.com/embed/v-KtCcAVe4Y" frameborder="0" allowfullscreen></iframe>
		<textarea style="width: 90%; height: auto; text-align: left; margin-left:1%;">https://www.youtube.com/embed/v-KtCcAVe4Y 동국대학교 산학협력 결실 맺다!!!!</textarea>
		<!-- !!!@!@!@@!@!@! -->
		<div class="full" style="padding-right: 7.4%;">
			<img alt="like" src="image\like.png" width="50px" height="50px"><span
				style="font-size: 70%; color: #F15B42;"><strong>20${likeNum}</strong></span>
			<img alt="comment" src="image\comment.png" width="50px" height="50px"><span
				style="font-size: 70%; color: #F15B42;"><strong>4${commentCnt}</strong></span>
			<img alt="report" src="image\report.png" width="50px" height="50px">
		</div>

		<div class="commentform" style="height: auto;">
			<div class="commentphoto">
				<img src="image\person.png" width="50px" height="50px">
			</div>
			<div class="comment2">
				<form style="width: 100%; height: 100%;">
					<textarea class="autosize"
						style="border: 1px solid #d2d2d2; width: 90%;"></textarea>
					<img alt="submit" src="image\transfer2.png" width="62px"
						height="45px">
				</form>
			</div>
		</div>



		<div class="commentform" style="height: auto;">
			<div class="commentphoto">
				<img src="image\person.png" width="100px" height="100px">
			</div>

			<div class="commentname">
				<a href="#"><strong>동국대 김우람</strong></a>
			</div>
			<div class="comment2">
				<textarea class="autosize" style="width: 90%;" readonly="readonly">와 정말 대단하네요!</textarea>
			</div>
			<div class="commentleft">
				<img alt="like" src="image\like.png" width="30px" height="30px"><span
					style="font-size: 70%; color: #F15B42;"><strong>13${commentCnt}</strong></span>
				<img alt="comment" src="image\comment.png" width="30px"
					height="30px"><span style="font-size: 70%; color: #F15B42;"><strong>3${commentCnt}</strong></span>
				<img alt="report" src="image\report.png" width="30px" height="30px">
				2016.12.10 12:04:30
			</div>
			<!-- 대댓글 -->
			<div class="cmtcommentform" style="height: auto;">
				<div class="cmtcommentphoto">
					<img src="image\person.png" width="100px" height="100px">
				</div>

				<div class="cmtcommentname">
					<a href="#"><strong>윤주형</strong></a>
				</div>
				<div class="cmtcomment2">
					<textarea class="autosize" style="width: 90%;" readonly="readonly">정말 짱이네요!</textarea>
				</div>
				<div class="cmtcommentleft">
					<img alt="like" src="image\like.png" width="30px" height="30px"><span
						style="font-size: 70%; color: #F15B42;"><strong>4${commentCnt}</strong></span>
					<img alt="comment" src="image\comment.png" width="30px"
						height="30px"> <span style="font-size: 70%; color: #F15B42;"><strong>1${commentCnt}</strong></span>
					<img alt="report" src="image\report.png" width="30px" height="30px">
					2016.12.10 14:04:30
				</div>
			</div>
			<!-- 대댓글 달기 -->
			<div class="cmtofcmtcommentform" style="height: auto;">
				<div class="commentphoto">
					<img src="image\person.png" width="50px" height="50px">
				</div>
				<div class="comment2">
					<form style="width: 100%; height: 100%;">
						<textarea class="autosize"
							style="border: 1px solid #d2d2d2; width: 90%;"></textarea>
						<img alt="submit" src="image\transfer2.png" width="62px"
							height="45px">
					</form>
				</div>
			</div>
		</div>

		<div class="commentform" style="height: auto;">
			<div class="commentphoto">
				<img src="image\person.png" width="100px" height="100px">
			</div>

			<div class="commentname">
				<a href="#"><strong>권순성</strong></a>
			</div>
			<div class="comment2">
				<textarea class="autosize" style="width: 90%;" readonly="readonly">룰루~~~ 자랑스럽습니당~~</textarea>
			</div>
			<div class="commentleft">
				<img alt="like" src="image\like.png" width="30px" height="30px"><span
					style="font-size: 70%; color: #F15B42;"><strong>20${commentCnt}</strong></span>
				<img alt="comment" src="image\comment.png" width="30px"
					height="30px"> <span style="font-size: 70%; color: #F15B42;"><strong>3${commentCnt}</strong></span><img
					alt="report" src="image\report.png" width="30px" height="30px">
				2016.12.10 12:04:30
			</div>
			<!-- 대댓글 달기 -->
			<div class="cmtofcmtcommentform" style="height: auto;">
				<div class="commentphoto">
					<img src="image\person.png" width="50px" height="50px">
				</div>
				<div class="comment2">
					<form style="width: 100%; height: 100%;">
						<textarea class="autosize"
							style="border: 1px solid #d2d2d2; width: 90%;"></textarea>
						<img alt="submit" src="image\transfer2.png" width="62px"
							height="45px">
					</form>
				</div>
			</div>

		</div>
	</div>
	</body>
	</html>