<%-- リスト10-17の状態 --%>
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
			<br>
	</form>
	
	<% if(errorMsg != null) {%>
	<p><%= errorMsg %></p>
	<% } %>

	<br>
	<table border = "1">
		<tr>
			<th>　名前　</th>
			<th>　入室時間　</th>
			<th>　退室時間　</th>
		</tr>
		
		<% for(int i = 0; i < qList.size(); i++){%>
			<tr>
				<th>　　<%=qList.get(i).getUserName()%>　　</th>
				<th>　　<%=qList.get(i).getInTime()%>　　</th>
				<th>　　<%=qList.get(i).getOutTime()%>　　</th>
			<tr>
		<% } %>
		
		<% for(Access access : accessList){%>
			<tr>
				<th>　　<%=access.getUserName()%>　　</th>
				<th>　　<%=access.getInTime()%>　　</th>
				<th>　　<%=access.getOutTime()%>　　</th>
			<tr>
		<% } %>
		
				
	</table>
			
	<p>
		<br><br><a href="/lob/Logout">ログアウト</a>
	</p>
</body>
</html>