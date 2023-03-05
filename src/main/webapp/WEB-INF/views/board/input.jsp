<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판 정보 입력 페이지</h2>
<% 
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
String formattedDate = simpleDateFormat.format(new java.util.Date());
java.sql.Date writeDate = java.sql.Date.valueOf(formattedDate);
%>

<form action="input.do" method="post">
	title: <input name="title" value="강감찬의 게시글" /><br/>
	content: <input name="content" value="내용은 없음" /><br/>
	writer: <input name="writer" value="강감찬" /><br/>
	<input type="hidden" name="writeDate" value=<%=writeDate%> /><br/>
	
	<input type="submit" name="" value="입력하기" />
</form>

</body>
</html>