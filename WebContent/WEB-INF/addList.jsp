<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>

<%
	List<GuestbookVo> gbList= (List<GuestbookVo>)request.getAttribute("gl");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<!-- add section -->
	<form action="/guestbook2/gbc" method="get">
		<table border= "1" width= "500px">
			<tr>
				<td>이름</td>
				<td><input type= "text" name="name" value= ""></td>
				<td>비밀번호</td>
				<td><input type= "password" name="password" value= ""></td>
			</tr>
			<tr>
				<td colspan= "4">
					<textarea cols="65" rows="5" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan= "4">
					<input type="hidden" name="action" value="add">
					<button type="submit">글작성</button>
				</td>
			</tr>
		</table>
	</form>
	<br>
	
	
	<!-- list section -->
	<% 
	for(GuestbookVo vo: gbList) {
		int no= vo.getNo();
	%>
		<table border= "1" width= "500px">
			<tr>
				<td><%=vo.getNo()%></td>
				<td><%=vo.getName()%></td>
				<td><%=vo.getRegDate()%></td>
				<td><a href="/guestbook2/gbc?act=deleteForm&no=<%=no%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan= "4">
					<%=vo.getContent()%>
				</td>
			</tr>
		</table>
		<br>
	<%	
	}
	%>
	
</body>
</html>