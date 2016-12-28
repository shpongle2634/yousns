<%@page import="com.yousns.vo.DetailVO"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<!-- body -->

<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO"></jsp:useBean>


<div class="wrapper">
	<div class="content2">
		<img src="\yousns\jsp\image\mypage.png" width="40px" height="40px"
			style="padding-bottom: 0px;"><span
			style="font-size: 20pt; margin: 0px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>
				상세 페이지</strong></span>
	</div>
</div>

<%
	String userkey = (String) session.getAttribute("userKey");
	DetailVO vo = userDAO.myDetails(userkey);
%>


<div class="wrapper">
	<div class="content3" style="background: #fff; padding-bottom: 0px">
		<form action="/yousns/users/<%=userkey%>/details" method="post"
			style="width: auto;">

			<div class="left-info">
				<div class="infoform">
					<div class="infophoto"
						style='display: table-cell; vertical-align: middle'>소개</div>
					<div class="infoname"
						style='display: table-cell; vertical-align: middle'>
						<strong> <input size="20" type="text"
							value="<%=vo.getMyIntroduce() != null ? vo.getMyIntroduce() : ""%>"
							name="myintroduce" style="width: 400px; border: 0px;">
						</strong>
					</div>
				</div>
			</div>
			<div class="left-info">
				<div class="infoform">

					<div class="infophoto">주소</div>
					<div class="infoname">
						<strong> <input size="20" type="text"
							value="<%=vo.getAddress() != null ? vo.getAddress() : ""%>"
							name="address" style="width: 400px; border: 0px;">
						</strong>
					</div>
				</div>
			</div>
			<div class="left-info">
				<div class="infoform">

					<div class="infophoto">학교</div>
					<div class="infoname"
						style='display: table-cell; vertical-align: middle'>
						<strong> <input size="20" type="text"
							value="<%=vo.getSchool() != null ? vo.getSchool() : ""%>" name="school"
							style="width: 400px; border: 0px;">
						</strong>
					</div>
				</div>
			</div>
			<div class="left-info">
				<div class="infoform">
					<div class="infophoto">직업</div>
					<div class="infoname"
						style='display: table-cell; vertical-align: middle'>
						<strong> <input size="20" type="text"
							value="<%=vo.getJob() != null ? vo.getJob() : ""%>" name="job"
							style="width: 400px; border: 0px;">
						</strong>
					</div>

				</div>
			</div>
			<div class="left-info">
				<div class="infoform">

					<div class="infophoto">전화번호</div>
					<div class="infoname">
						<strong> <input size="20" type="text"
							value="<%=vo.getPhone() != null ? vo.getPhone() : ""%>" name="phone"
							style="width: 400px; border: 0px;">
						</strong>
					</div>

				</div>
			</div>
			<div class="left-info">
				<div class="infoform">
					<div class="infophoto">결혼</div>

					<div class="infoname">
						<strong> <label>미혼</label><input size="20" type="radio"
							value="0" name="married" style="width: 80px; border: 0px;"
							checked> <label>기혼</label><input size="20" type="radio"
							value="1" name="married" style="width: 80px; border: 0px;">
						</strong>
					</div>

				</div>
			</div>
			<div class="infobtn">
				<button type="submit">
					<img src="\yousns\jsp\image\change.png" width="50px" height="35px">
				</button>
			</div>
		</form>
	</div>
</div>

</body>
</html>

