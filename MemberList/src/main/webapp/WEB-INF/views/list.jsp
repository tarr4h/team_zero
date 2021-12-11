<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width-device-width, initial-scale=1, user-scalable=no">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/list.css" />
<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.js"></script>
</head>
<body>
 	<table id="adminArea">
	 	<tbody>
	 	</tbody>
 	</table>
 	<br />
 	<table id="memberList">
 		<thead>
 			<tr>
	 			<th>이름</th>
	 			<th>이동수단</th>
	 			<th>추가인원</th>
	 			<th>기타사항</th>
 			</tr>
 		</thead>
 		<tbody>
 		</tbody>
 	</table>
 	
 	<table id="countMember">
 		<thead>
	 		<tr>
	 			<th>참여인원</th>	
	 		</tr>
 		</thead>
 		<tbody>
 			<tr>
 				<td><span id="totalMember"></span>명</td>
 			</tr>
 		</tbody>
 	</table>
 	
 	
	<input type="button" value="참가 신청하기" id="showInsertArea" />
	<table id="insertTable" style="display:none;">
<!-- 		<thead>
			<tr>
				<th colspan=5>참가 신청하기</th>
			</tr>
		</thead> 	 -->
		<tbody>
			<tr>
				<th>이름</th>
				<td colspan=4>
				 	<input type="text" id="userName" class="inputArea"/>
				</td>
			</tr>
			<tr>
				<th>이동수단</th>
		 		<td colspan=4><input type="text" id="transportation" class="inputArea"/></td>
			</tr>
			<tr>
				<th>추가인원</th>
				<td colspan=4><input type="number" id="extraUser" class="inputArea" value="0"/></td>
			</tr>
			<tr>
				<th>기타사항</th>
				<td colspan=4><input type="text" id="userComment" class="inputArea"/></td>
			</tr>
			<tr>
				<td colspan=5>
					<input type="button" id="submitBtn" value="등록하기" />				
				</td>
			</tr>
		</tbody>
 	</table>
	
	<input type="button" value="참가 신청 취소" id="showDeleteArea" />
	<table id="deleteTable" style="display:none;">
 		<thead>
			<tr>
				<td colspan=2><span id="noti">* 본인 외 건드리지 마십시오 *</span></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>이름</th>
				<td><input type="text" class="inputArea" id="delUser" /></td>
			</tr>
			<tr>
				<td colspan=2><input type="button" id="deleteMember" class="inputArea" value="삭제하기" /></td>
			</tr>
		</tbody>
	</table>

<script>
	$("#showInsertArea").click((e) => {
		$("#insertTable").toggle();
	});
	
	$("#showDeleteArea").click((e) => {
		$("#deleteTable").toggle();
	});

	
	//list 가져오기 ajax
	$(() => {
		getGameInfo();
		getMember();
		getTotalMember();
	});
	
	// list 등록 ajax
	$("#submitBtn").click((e) => {
		if(!confirm("등록하시겠습니까?")){
			$("#userName").val('');
			$("#transportation").val('');
			$("#extraUser").val('');
			$("#userComment").val('');
			return false;
		};
		if($("#extraUser").val() < 0){
			alert("추가 인원이 있는 경우 1명 이상이어야 합니다.");
			$("#userName").val('');
			$("#transportation").val('');
			$("#extraUser").val('');
			$("#userComment").val('');
			return false;
		};
		$.ajax({
			url: "<%= request.getContextPath() %>/th/addList",
			method: "POST",
			data: {
				name : $("#userName").val(),
				transport : $("#transportation").val(),
				extraUser : $("#extraUser").val(),
				comment : $("#userComment").val()
			},
			success(data){
				alert(data);
				$("#insertTable").toggle();
				$("#userName").val('');
				$("#transportation").val('');
				$("#extraUser").val('');
				$("#userComment").val('');
				getMember();
				getTotalMember();
			},
			error:console.log
		});		
	});
	
	// list 삭제 ajax
	$("#deleteMember").click((e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/th/deleteMember",
			method: "POST",
			data:{
				delUser : $("#delUser").val()
			},
			success(data){
				if(confirm(data)){
					location.reload();
				};
			},
			error:console.log
		});
	});
	
	const getMember = () => {
		$("#memberList tbody").empty();
		$.ajax({
			url: "<%= request.getContextPath() %>/th/getList",
			success(data){
				$(data).each((i, e) => {
					let tr = `
						<tr>
							<td>\${e.name}</td>
							<td>\${e.transport}</td>
							<td>\${e.extraUser}</td>
							<td>\${e.comment}</td>
						<tr>
					`;
					$("#memberList tbody").append(tr);					
				});
			},
			error:console.log
		});
	};
	
	const getTotalMember = () => {
		$.ajax({
			url: "<%= request.getContextPath() %>/th/calculate",
			success(data){
				$("#totalMember").html(data.totalMember + data.totalExtra);
			},
			error:console.log
		});
	};
	
	// game정보 받아오기
	const getGameInfo = () => {
		$.ajax({
			url: "<%= request.getContextPath() %>/th/playarea",
			success(data){
				const info = `
					<tr>
						<th>
						\${data.date}
						</th>
					</tr>
					<tr>
						<th>
						\${data.area}
						</th>
					</tr>
				`;
				
				$("#adminArea tbody").append(info);
			},
			error: console.log
		});
	};
	
</script>
</body>
</html>