<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー新規登録</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style>
p.center{text-align:center}
a.red { color : #f00}
#header {background-color: #708090;}
#container{width: 800px; margin: 0 auto;}
input.form-control { width: 500px; }
</style>
</head>
<body>
<div class="container">
	<div id="header">
		<div align="right">
			<h5><p class="text-white">${userInfo.name}&nbsp;さん&emsp;&emsp;&emsp;<a class="red" href="LogoutServlet"><u>ログアウト</u></a></p></h5>
		</div>
	</div>
	<p class="h1" align="center"><strong>ユーザー新規登録</strong></p>
	<br></br>

	<c:if test="${errMsg != null}">
		<div class="alert alert-danger" role="alert">
			${errMsg}
		</div>
	</c:if>

	<form action="UserCreateServlet" method="Post">
		<div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label"><strong>ログインID</strong></label>
		    <div class="col-sm-10">
		      <input type="text" name="loginId" class="form-control" id="inputLoginId" placeholder="ログインID">
  		  	</div>
  		</div>
  		<div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label"><strong>パスワード</strong></label>
		    <div class="col-sm-10">
		      <input type="password" name="password" class="form-control" id="inputPassword" placeholder="パスワード">
  		  	</div>
  		</div>
  		<div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label"><strong>パスワード（確認）</strong></label>
		    <div class="col-sm-10">
		      <input type="password" name="passwordCheck" class="form-control" id="inputPasswordCheck" placeholder="パスワード（確認）">
  		  	</div>
  		</div>
  		<div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label"><strong>ユーザ名</strong></label>
		    <div class="col-sm-10">
		      <input type="text" name="name" class="form-control" id="inputname" placeholder="ユーザ名">
  		  	</div>
  		</div>
  		<div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label"><strong>生年月日</strong></label>
		    <div class="col-sm-10">
		      <input type="date" name="birthDate" class="form-control" id="inputBirthDate" placeholder="生年月日">
  		  	</div>
  		</div>
  		<p class="center"><button type="submit" class="btn btn-secondary"><h6>　　登録　　</h6></button></p>
 		<br></br>
	</form>
	  	<br></br>
	  	<div align="left">
			<p><a href="UserListServlet"><u>戻る</u></a></p>
		</div>


</div>
</body>
</html>