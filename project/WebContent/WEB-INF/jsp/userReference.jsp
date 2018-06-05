<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー情報詳細参照</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style>
a.red { color : #f00}
#container{width: 700px; margin: 0 auto;}
#header {background-color: #708090;}
</style>
</head>
<body>

<div id="container">
	<div id="header">
		<div align="right">
			<h5><p class="text-white">${userInfo.name}&nbsp;さん&emsp;&emsp;&emsp;<a class="red" href="LogoutServlet"><u>ログアウト</u></a></p></h5>
		</div>
	</div>
	<br></br>
	<h1><p align="center"><strong>ユーザ情報詳細参照</strong></p></h1>
	<br></br>
	<form>
	  <div class="form-group row">
	    <label for="staticEmail" class="col-sm-2 col-form-label"><strong>ログインID</strong></label>
	    <div class="col-sm-10">
	      <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${userD.loginId}">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="staticEmail" class="col-sm-2 col-form-label"><strong>ユーザ名</strong></label>
	    <div class="col-sm-10">
	      <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${userD.name}">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="staticEmail" class="col-sm-2 col-form-label"><strong>生年月日</strong></label>
	    <div class="col-sm-10">
	      <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${userD.birthDate}">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="staticEmail" class="col-sm-2 col-form-label"><strong>登録日時</strong></label>
	    <div class="col-sm-10">
	      <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${userD.createDate}">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="staticEmail" class="col-sm-2 col-form-label"><strong>更新日時</strong></label>
	    <div class="col-sm-10">
	      <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="${userD.updateDate}">
	    </div>
	  </div>
	 </form>
	 <br></br>
	 <div align="left">
		<a href="UserListServlet"><u>戻る</u></a>
	 </div>
</div>

</body>
</html>