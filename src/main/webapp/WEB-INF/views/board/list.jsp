<%@page import="java.util.ArrayList"%>
<%@page import="org.comstudy.myweb.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet 연습</title>
</head>
<body>
<h1>길동이의 홈페이지</h1>
<h3>게시판 확인 페이지</h3>

<%
ArrayList<BoardDTO> boardList = (ArrayList<BoardDTO>)request.getAttribute("list");
for(BoardDTO board : boardList) {
	%>
   <p><a href="modify.do?seq=<%=board.getSeq()%>"><%=board %></a> 
	   | <a href="delete.do?seq=<%=board.getSeq()%>">삭제</a><p/><%
	}
%>
<p><a href="input.do">게시판 등록</a></p>

</body>
</html>