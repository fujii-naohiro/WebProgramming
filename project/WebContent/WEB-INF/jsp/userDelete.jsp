<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザ削除確認</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style>
a.red { color : #f00}
#site-box {width : 600px;}
#header {background-color: #708090;}
#container{width: 700px; margin: 0 auto;}
</style>
</head>
<body>

<div id="container">
	<div id="header">
		<div align="right">
			<h5><p class="text-white">${userInfo.name}&nbsp;さん&emsp;&emsp;&emsp; <a class="red" href="LogoutServlet"><u>ログアウト</u></a></p></h5>
		</div>
	</div>
	<form action="UserDeleteServlet?id=${userD.id}" method="post">
		<h1 align="center"><strong>ユーザー削除確認</strong></h1>
		<p>ログインID　：　${userD.loginId}</p>
			を本当に削除してよろしいでしょうか。
		<br></br>
		<br></br>
		<br></br>
		<div align="center">
	  		<button type="submit" class="btn btn-secondary btn-lg" name="submit" value="cancel">　キャンセル　</button>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	  		<button type="submit" class="btn btn-secondary btn-lg" name="submit" value="ok">　　　OK　　　</button>
		</div>
	</form>
</div>

</body>
</html>