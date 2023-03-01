<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Access,java.util.List,java.util.ArrayList"%>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");

String errorMsg = (String) request.getAttribute("errorMsg");

List<Access> qList = (List<Access>) application.getAttribute("qList");

List<Access> accessList = (List<Access>) application.getAttribute("accessList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>鈴木研究室の入退室記録</title>
</head>
<body>
	<h1>入退室記録</h1>
	<p>
		<%=loginUser.getName()%>さん、ログイン中
	</p>

	<form action="/lob/Main" method="post">
		入室<input type="radio" name="time" value="入室"> 
		退室<input type="radio" name="time" value="退室"><br> 
			<input type="submit" value="送信">
	</form>
	
	<% if(errorMsg != null) {%>
	<p><%= errorMsg %></p>
	<% } %>

	<h2>　　名前　　：　　入室時間　　：　　退室時間　　</h2>
		
	<% for(Access access : accessList){%>
			<h3>　　<%=access.getUserName()%>　　|　　<%=access.getInTime()%>　　|　　<%=access.getOutTime()%>　　</h3>
	<% } %>
	
	<% for(int i = 0; i < qList.size(); i++){ %>
		<h3>　　<%=qList.get(i).getUserName()%>　　|　　<%=qList.get(i).getInTime()%>　　|　　<%=qList.get(i).getOutTime()%>　　</h3>
	<% }%>
			
	<p>
		<br><br><a href="/lob/Logout">ログアウト</a>
	</p>
</body>
</html>
