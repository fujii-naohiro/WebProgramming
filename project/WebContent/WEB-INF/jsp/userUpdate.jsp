<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style>
a.red { color : #f00}
#header {background-color: #708090;}
input.form-control { width: 700px; }
</style>
</head>
<body>

<div class="container">
	<div id="header">
		<div align="right">
			<h5><p class="text-white">${userInfo.name}&nbsp;さん&emsp;&emsp;&emsp; <a class="red" href="LogoutServlet"><u>ログアウト</u></a></p></h5>
		</div>
	</div>
	<br></br>
	<p class="h1" align="center"><strong>ユーザ情報更新</strong></p>
	<br></br>

	<c:if test="${errMsg != null}">
		<div class="alert alert-danger" role="alert">
			${errMsg}
		</div>
	</c:if>



	<form action="UserUpdateServlet?id=${user.id}" method="post">
		<div class="form-group row">
		    <label for="staticEmail" class="col-sm-2 col-form-label"><strong>ログインID</strong></label>
		    <div class="col-sm-10">
		      <input type="text" name="loginId"readonly class="form-control-plaintext" id="staticEmail" value="${userU.loginId}">
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
		      <input type="text" name="name" class="form-control" id="inputName" value="${userU.name}">
  		  	</div>
  		</div>
  		<div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label"><strong>生年月日</strong></label>
		    <div class="col-sm-10">
		      <input type="date" name="birthDate" class="form-control" id="inputBirthDate" value="${userU.birthDate}">
  		  	</div>
  		</div>
  		<br></br>
  		<div align="center">
  			<button type="submit" class="btn btn-secondary btn-lg">　　更新　　</button>
  		</div>

  	</form>
  	<br></br>
  	<div align="left">
		<p><a href="UserListServlet"><u>戻る</u></a></p>
	</div>
</div>

</body>
</html>