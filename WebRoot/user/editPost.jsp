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

<title>修改文章</title>

<!-- Bootstrap core CSS -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="./src/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="./src/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="./src/style/ckeditor/ckeditor.js"></script>
</head>
<%
	User user = (User) request.getSession().getAttribute("user");
	if (null == user) {
		response.sendRedirect("login.jsp");
		return;
	}
%>
<body>
	<nav class="navbar navbar-fixed-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">我的博客</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="index">主页</a></li>
					<li><a href="#about">关于</a></li>
					<li><a href="#contact">联系</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-success">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</form>
					<li class="active"><a href="createPost"><span
							class="glyphicon glyphicon-pencil"></span>&nbsp;写博客</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> <span class="glyphicon glyphicon-user"></span>
							<%
								if (user.getUserName() != null) {
							%>${user.getUserName()}<%
								} else {
							%>${user.getAccount()}<%
								}
							%>
					</a>
						<ul class="dropdown-menu">
							<li><a href="center"><span class="glyphicon glyphicon-user"
									aria-hidden="true" style="padding-right: 5px;"></span>个人中心</a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-list-alt" aria-hidden="true" style="padding-right: 5px;"></span>我的文章</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-star"
									aria-hidden="true"></span style="padding-right: 5px;">收藏夹</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="logout"><span
									class="glyphicon glyphicon-log-out" aria-hidden="true" style="padding-right: 5px;"></span>注销</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- /.navbar -->

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<div class="col-xs-12 col-sm-9">
				<form class="createpost" action="updatePost?id=${p.id}" method="post" name="createpost">
					<div class="form-group">
						<label for="title">标题</label> 
						<input type="text" name="title" value="${p.title }" class="form-control" id="title" placeholder="文章标题" maxlength="25" required>
					</div>
					<div class="form-group">
						<label for="summary">简介</label> 
						<textarea class="form-control" name="summary" id="summary" required>${p.summary }</textarea>
					</div>
					<div class="form-group">
						<label for="content">正文</label>
						<textarea type="text" name="content" class="form-control" id="content" rows="20" required>${p.content }</textarea>
					</div>
					<div class="form-group">
						<label for="typeId">类型</label> 
						<select class="form-control" name="typeId" id="typeId" required hidden> </select>
					</div>
					<div class="form-group">
						<label for="tagName">标签</label> 
						<input type="text" name="tagName" value="${p.tagName }" class="form-control" id="tagName" maxlength="10" required>
					</div>
					<span id="errorMsg"></span>
					<button id="submit" type="submit" class="btn btn-primary" style="float:right">提交</button>
				</form>
			</div>
			<!--/.col-xs-12.col-sm-9-->

			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				col-xs-6 col-sm-3</div>
			<!--/.sidebar-offcanvas-->
		</div>
	</div>
	<!--/.container-->
	 <footer>FOOTER</footer>
	<script>
		CKEDITOR.replace('content');
	</script>
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
	<script src="./src/style/js/createPost.js"></script>
	<script>
	$(document).ready(function() {
		var num = ${p.typeId};   //获取input中输入的数字
		var numbers = $("#typeId").find("option"); //获取select下拉框的所有值
		for (var j = 1; j < numbers.length; j++) {
			if ($(numbers[j]).val() == num) {
				$(numbers[j]).attr("selected", "selected");
			}
		}
	});
	</script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="./src/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="./src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.js"></script>
</body>
</html>
