<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>鈴木研究室の入退室記録</title>
</head>
<body>
<h1>鈴木研究室の入退室記録</h1>
<form action="/lob/Login" method="post"> <%--属性で指定した先をリクエストすると同時に、データを送信する --%>
ユーザー名：<input type="text" name="name"><br>
パスワード：<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>