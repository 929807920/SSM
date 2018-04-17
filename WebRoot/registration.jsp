<%@page import="com.sunjinxu.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>注册</title>

<!-- Bootstrap core CSS -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="src/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="src/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<link href="src/style/css/login.css" rel="stylesheet">
</head>
<%
	User user = (User) request.getSession().getAttribute("user");
%>
<body>
	<div class="container">

		<form class="form-horizontal" id="regform" action="insertUserByReg"
			method="post">
			<div class="form-group">
				<label for="inputAccount" class="col-sm-2 control-label">账号</label>
				<div class="col-sm-10">
					<input type="text" name="account" id="account" value=""
						class="form-control" id="inputAccount" placeholder="账号只能为字母、数字.."
						oninput="checkAccount()" maxlength="10" required autofocus>
				</div>
			</div>
<!-- 			<div class="form-group">
				<label for="userName" class="col-sm-2 control-label">昵称</label>
				<div class="col-sm-10">
					<input type="text" name="userName" id="userName" value=""
						class="form-control" id="userName" placeholder="请输入昵称.."
						oninput="checkUserName()" >
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">邮箱</label>
				<div class="col-sm-10">
					<input type="email" name="email" id="email" value=""
						class="form-control" id="email" placeholder="请输入邮箱.." oninput="checkEmail()">
				</div>
			</div>
 -->			<div class="form-group">
				<label for="inputPassword" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="password" name="password" id="password" value=""
						class="form-control" placeholder="密码只能为字母、数字.." maxlength="20"
						oninput="checkPassword()" required disabled="disabled">
				</div>
			</div>
			<div class="form-group">
				<label for="inputRePassword" class="col-sm-2 control-label">确认</label>
				<div class="col-sm-10">
					<input type="password" name="rePassword" id="rePassword" value=""
						class="form-control" placeholder="请输入确认密码.."
						oninput="checkPassword()" required disabled="disabled">
				</div>
			</div>
			<span id="errorMsg"></span>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="login-submit" type="submit" class="btn btn-primary" disabled="disabled">注册</button>
					<button type="reset" class="btn btn-warning">重置</button>
					<a href="login.jsp" style="float: right">去登录</a>
				</div>
			</div>
		</form>
	</div>
	<!--/.container-->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="https:\\cdn.bootcss.com\jquery\3.3.1\jquery.min.js"><\/script>')
	</script>

	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="src/style/js/registration.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="src/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script
		src="src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.js"></script>
</body>
</html>
