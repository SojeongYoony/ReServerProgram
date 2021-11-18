<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>상세보기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		
		$('#fo').on('submit', function(event){
			
			if ($('#author').val() == '') {
				alert('작성자를 입력하세요');
				event.preventDefault();
				$('#author').focus();
				return false;
			} else if ($('#content').val() == '') {
				alert('내용을 입력하세요');
				event.preventDefault();
				$('#content').focus();
				return false;
			}
			return true;
		}) // end submit
	}); // end onload
	
</script>

	
</head>
<body>
	<%-- 받아올 dto 2가지 --%>
	<div>
		<h3>${boardDTO.no} 번 게시글</h3>
		<br>		<%-- session 저장이든 request 저장이든 똑같이 EL 사용 가능함 --%>
		작성자 : ${boardDTO.author}<br>
		작성일 : ${boardDTO.postdate}<br>
		작성IP: ${boardDTO.ip}<br>
		조회수 : ${boardDTO.hit}<br>
		제목 <br>
		<input value="${boardDTO.title}">
		내용 <br>
		<textarea rows="10" cols="25" name="content">${boardDTO.content}</textarea>
		<div>
			<input type="button" value="삭제하기" onclick="fnNoticeDelete()">		
			<input type="button" id="list_btn" value="목록보기" onclick="location.href='selectBoardList.do'">
		</div>
		<script>
		function fnNoticeDelete(){
			if(confirm('게시글을 삭제할까요?')) {
				location.href='deleteBoard.do?no=${boardDTO.no}';
				return true;
			}
		}
	</script>
		<hr>
		<%-- 댓글 작성할 수 있는 form 구역 --%>
		<form action="insertReply.do" method="post" id="fo">	
			<div>
			<textarea rows="2" cols="30" id="content" name="content" placeholder="댓글을 입력하세요"></textarea><br>
			</div>
			<input name="author" id="author" placeholder="작성자">&nbsp;<button>작성</button>
			<input type="hidden" name="board_no" value="${boardDTO.no}">
		</form>
		<%-- reply list가 보여질 구역 --%>
		<table border="1">
			<c:if test="${empty replyList}">
				<tr>
					<td>작성된 댓글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty replyList}">
				<c:forEach items="${replyList}" var="reply">
				<tr>
					<td>${reply.content}</td>
					<td>${reply.author}</td>
					<td>${reply.ip}</td>
					<td>${reply.postdate}</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
		
	</div>

</body>
</html>