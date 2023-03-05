<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 확인</title>
</head>
<body>
<h2>게시판 정보 삭제 페이지</h2>
<form action="delete.do" method="post">
	<input type="hidden" name="seq" value="${board.seq}" /> 
	<input type="submit" name="" value="정말로 삭제하겠습니까 ?" />
	<p><a href="list.do">돌아가기</a></p>
</form>
</body>
</html>