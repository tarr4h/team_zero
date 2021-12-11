<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width-device-width, initial-scale=1, user-scalable=no">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/main.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
 <table id="mainTable">
 	<thead>
 		<tr>
 			<th>Team Zero</th>
 		</tr>
 	</thead>
 	<tbody>
 		<tr>
 			<th>일정 확인</th>
 		</tr>
 		<tr>
 			<td>
 				<p>비밀번호를 입력하고 확인하세요</p>
 				<input type="password" name="password" id="password" />
			</td>
 		</tr>
 		<tr>
 			<td><input type="button" value="확인하기" id="loginBtn" onclick="openLink()"/></td>
 		</tr>
 	</tbody>
 </table>
 
 <input type="button" value="관리자전용" id="adminOnly"/>
 <table id="adminTable" style="display:none;">
 	<thead>
 		<tr>
 			<th>관리자 비밀번호를 입력하세요</th>
 		</tr>
 	</thead>
 	<tbody>
 		<tr>
 			<th><input type="password" id="adminPw" /><input type="button" value="login" onclick="adminLink();"/></th>
 		</tr>
 	</tbody>
 </table>
 
 <script>
 	const openLink = () => {
 		console.log("패스워드 유효성검사 시작");
 		$.ajax({
 			url: "<%= request.getContextPath() %>/th/password",
 			method: "GET",
 			data:{
 				password : $("#password").val()
 			},
 			success(data){
 				if(data == 1){
					location.href='${pageContext.request.contextPath}/th/listPage';
 				} else {
 					alert("잘못된 비밀번호");
 				}
 			},
 			error: console.log("패스워드 유효성 검사 중 에러 발생")
 		});
 	};
 	
 	$("#adminOnly").click((e) => {
 		$("#adminTable").toggle();
 	});
 	
 	const adminLink = () => {
 		$.ajax({
 			url: "<%= request.getContextPath() %>/th/adminPw",
 			data:{
 				password : $("#adminPw").val()
 			},
 			success(data){
 				if(data == 1){
					location.href='${pageContext.request.contextPath}/th/admin';
 				} else {
 					alert("잘못된 비밀번호");
 				}
 			},
 			error: console.log
 		});
 	};
 </script>
 
</body>

</html>