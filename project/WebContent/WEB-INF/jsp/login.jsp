<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style>
p.center{text-align:center}
#container{width: 650px; margin: 0 auto;}
</style>
</head>
<body>

<div id="container">
	<br></br>

	<!-- header -->
	<p class="h1" align="center"><strong>ログイン画面</strong></p>
	<br></br>
	<br></br>

	<!-- body -->
	<div id="site-box">

	<c:if test="${errMsg != null}">
		<div class="alert alert-danger" role="alert">
			${errMsg}
		</div>
	</c:if>

		<form class="form-signin" action="LoginServlet" method="post">
			<div class="form-group row">
			    <label for="inputPassword" class="col-sm-2 col-form-label">ログインID</label>
			    <div class="col-sm-10">
			      <input type="text" name="loginId" id="inputLoginId" class="form-control" placeholder="ログインID" required autofocus>
	  		  	</div>
	  		</div>
	  		<br></br>
			<div class="form-group row">
			   	 <label for="inputPassword" class="col-sm-2 col-form-label">パスワード</label>
			   	 <div class="col-sm-10">
			      	<input type="password" name="password" id="inputPassword" class="form-control" placeholder="パスワード" required >
		  		 </div>
	  		</div>
	  		<br></br>
	  		<div align="center">
				<h6><button type="submit" class="btn btn-secondary btn-sigin">　　ログイン　　</button></h6>
			</div>
		</form>

	</div>


</div>

</body>
</html>