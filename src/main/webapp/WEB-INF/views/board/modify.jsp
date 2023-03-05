<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 페이지</title>
</head>
<body>
<h2>게시판 내용 수정</h2>
<form action="modify.do" method="post">
	title: <input name="title" value="${board.title}" /><br/>
	content: <input name="content" value="${board.content}" /><br/>
	writer: <input name="writer" value="${board.writer}" /><br/>
	<input type="hidden" name="seq" value="${board.seq }" /> 
	
	<input type="submit" value="수정완료" /> <a href="list.do">목록</a>
</form>

</body>
</html>