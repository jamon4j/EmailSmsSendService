<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/include.jsp"%>
<%@ include file="../include/main.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/css/login/login.css">
<script type="text/javascript" src="/js/login/login.js"></script>
</head>
<body>
	<div class="container">
		<h2 class="form-signin-heading" align="center">金山云信息发送平台</h2>
		<form id="form-signin" name="form-signin" action="/login"
			method="post">
			<div class="div-center">
				<div class="input-prepend control-group">
					<span class="add-on"><i class="icon-envelope"></i> </span> <input
						class="span2.5" type="text" placeholder="账号/Account" name="email"
						id="email" />
				</div>
				<div class="input-prepend control-group">
					<span class="add-on"><i class="icon-key"></i> </span> <input
						class="span2.5" type="password" placeholder="密码/Password"
						name="password" id="password" />
				</div>
			</div>
			<button class="btn-small btn-success span1" style="float:right;"
				type="button" name="login" id="login">登陆</button>
		</form>
	</div>
</body>
</html>
