<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザ一覧</title>
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
	<h1 align="center"><strong>ユーザ一覧</strong></h1>
	<br></br>
	<div align="right">
		<a href="UserCreateServlet"><u>新規登録</u></a>&emsp;&emsp;
	</div>
	<form action="UserListServlet" method="post" class="form-horizontal">
		<div class="form-group row">
			   <label for="inputPassword" class="col-sm-2 col-form-label"><strong>ログインID</strong></label>
			    <div class="col-sm-10">
			      <input type="text" name="loginId" class="form-control" id="loginId" placeholder="ログインID">
		  	  	</div>
		</div>
		<div class="form-group row">
		    <label for="inputPassword" class="col-sm-2 col-form-label"><strong>ユーザ名</strong></label>
		    <div class="col-sm-10">
			    <input type="text" name="name" class="form-control" id="name" placeholder="ユーザ名">
		  	</div>
		</div>
		<strong>生年月日</strong>　　　　　　　　<input type="date" name="date1">　　〜　　<input type="date" name="date2">
	  	<br></br>
	  	<div align="right">
	  		<button type="submit" class="btn btn-secondary btn-lg">　　検索　　</button>
	  	</div>
  	</form>
  	<br /><hr/>
	<table class="table table-bordered">
	  <thead>
	    <tr bgcolor="#d3d3d3">
	      <th scope="col">ログインID</th>
	      <th scope="col">ユーザ名</th>
	      <th scope="col">生年月日</th>
	      <th scope="col"></th>
	    </tr>
	  </thead>
	  <tbody>
	  	<!-- userの数だけ繰り返す -->
		<c:forEach var="user" items="${userList}">
		    <tr>
		      <td>${user.loginId}</td>
		      <td>${user.name}</td>
		      <td>${user.birthDate}</td>
			      <td  width="350">
						  <a class="btn btn-primary" href="UserDetailServlet?id=${user.id}">　詳細　</a>&emsp;
					      <c:if test="${userInfo.loginId == user.loginId || userInfo.loginId == 'admin'}" >
					          <a class="btn btn-success" href="UserUpdateServlet?id=${user.id}">　更新　</a>&emsp;
				          </c:if>
				          <c:if test="${userInfo.loginId == 'admin'}">
					          <a class="btn btn-danger" href ="UserDeleteServlet?id=${user.id}">　削除　</a>
				          </c:if>
			      </td>
		    </tr>
		</c:forEach>
	  </tbody>
	</table>
</div>

</body>
</html>