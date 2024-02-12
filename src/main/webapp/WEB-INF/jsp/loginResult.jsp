<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>鈴木研究室の入退室記録</title>
</head>
</head>
<body>
	<h1>鈴木研究室の入退室記録</h1>
	<%
	if (loginUser != null) {
	%>
	<p>ログインに成功しました</p>
	<p>
		ようこそ<%=loginUser.getName()%>さん
	</p>
	<a href="/lob/Main">記録へ</a>
	<%
	} else {
	%>
	<p>ログインに失敗しました</p>
	<a href="/lob/">TOPへ</a>
	<%
	}
	%>
</body>
</html>