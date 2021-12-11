<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width-device-width, initial-scale=1, user-scalable=no">
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css" />
<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.js"></script>
</head>
<body>
<table id="dateTable">
	<thead>
		<tr>
			<th colspan=2>관리자페이지입니다.</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>날짜</th>
			<td><input type="date" name="" id="setDate" /></td>
		</tr>
		<tr>
			<th>장소</th>
			<td><input type="text" name="" id="setArea" /></td>
		</tr>
		<tr>
			<td colspan=2><input type="button" class="inputArea" value="등록" id="enrollInfo"/></td>
		</tr>
		<tr>
			<td colspan=2><input type="button" class="inputArea" id="deleteList" value="지난 리스트 초기화" /></td>
		</tr>
		<tr>
			<td colspan=2><input type="button" class="inputArea" id="goToList" value="참가자 현황 보러가기" /></td>
		</tr>
	</tbody>
</table>

<script>
	$("#enrollInfo").click((e) => {
		if(confirm("등록하시겠습니까?")){
			enrollInfo();
		}
	});
	
	const enrollInfo = () => {
		$.ajax({
			url: "<%= request.getContextPath() %>/th/addSpot",
			method: "POST",
			data:{
				date : $("#setDate").val(),
				area : $("#setArea").val()
			},
			success(data){
				$("#setDate").val('');
				$("#setArea").val('');
				alert(data);
			},
			error:console.log
		});
	};
	
 	$("#goToList").click((e) => {
 		location.href='${pageContext.request.contextPath}/th/listPage';
 	});
 	
 	$("#deleteList").click((e) => {
 		if(confirm("참가자 목록을 초기화 하겠습니까?")){
 			deleteBfList();
 		}
 	});
 	
 	const deleteBfList = () => {
 		$.ajax({
 			url: "<%= request.getContextPath() %>/th/deleteList",
 			method: "POST",
 			success(data){
 				alert(data);
 			},
 			error:console.log
 		});
 	}
</script>

</body>
</html>