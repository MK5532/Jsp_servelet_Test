<%@page import="org.comstudy.myweb.saram.model.SaramDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP 지시어 -->
<!-- JSP 선언문 : 자바 Class의 멤버 필드, 멤버 메서드 -->
<%!
// JSP 선언문 : 이곳에는 멤버 필드(전역변수)와 멤버 메서드(전역함수)를 선언할 수 있다.
private String username = "김범준";
public String hello(){
	return "Hello" + username + "World";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet 연습</title>
</head>
<body>
<h1>길동이의 홈페이지</h1>
<h3>페이지임</h3>

<!-- EL 표기법 -->
<p>${saram }</p>
<%
// 스크립트 릿 - _jspService() 함수 내용
out.println("<h2>"+hello()+"</h2>");
%>

<%
// 리스트를 출력한다.
// request에 list속성 명으로 저장된 목록을 가져온다.
ArrayList<SaramDTO> list = (ArrayList<SaramDTO>)request.getAttribute("list");
for(SaramDTO saram : list) {
	%>
   <p><a href="modify.do?seq=<%=saram.getSeq()%>"><%=saram %></a> | 
   <a href="delete.do?seq=<%=saram.getSeq()%>">삭제</a></p>
 <%
}
%>

<p><a href="input.do">사람등록</a></p>
</body>
</html>