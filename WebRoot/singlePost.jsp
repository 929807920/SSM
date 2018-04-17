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

<title>文章详情</title>

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="src/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.css"
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
<link href="src/style/css/singlePost.css" rel="stylesheet">

</head>
<%
	User user = (User) request.getSession().getAttribute("user");
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
					<li><a href="createPost"><span
							class="glyphicon glyphicon-pencil"></span>&nbsp;写博客</a></li>
					<%
						if (null == user) {
					%>
					<li><a href="login.jsp"><span
							class="glyphicon glyphicon-log-in" aria-hidden="true"
							style="padding-right: 5px;"></span>登录</a></li>
					<li><a href="registration.jsp"><span
							class="glyphicon glyphicon-plus" aria-hidden="true"></span>注册</a></li>
					<%
						} else {
					%>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span class="glyphicon glyphicon-th"></span>
							<%
								if (user.getUserName() != null) {
							%>${user.getUserName()}<%
								} else {
							%>${user.getAccount()}<%
								}
							%></a>
						<ul class="dropdown-menu">
							<li><a href="center"><span
									class="glyphicon glyphicon-user" aria-hidden="true"
									style="padding-right: 5px;"></span>个人中心</a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-list-alt" aria-hidden="true"
									style="padding-right: 5px;"></span>我的文章</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-star"
									aria-hidden="true" style="padding-right: 5px;"></span>收藏夹</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="logout"><span
									class="glyphicon glyphicon-log-out" aria-hidden="true"
									style="padding-right: 5px;"></span>注销</a></li>
						</ul></li>
					<%
						}
					%>
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
				<table class="table table-hover">
					<tr>
						<span id='title'><font color=black size="3">${p.title}</font></span>
						<span id='typeName' style="float: right;">文章类型：<font color="green" size="3">${p.typeName}</font></span>
					</tr>
				</table>
				<div>
					<p style="float: right;">
						<!-- 收藏 -->
						<a id="collect" type="button"></a>
						<a href='#'><span class='glyphicon glyphicon-eye-open'
						aria-hidden='true'>${p.view}</span><span id="view"></span></a>
						<span class='glyphicon glyphicon-calendar' aria-hidden='true' style="margin-left:2px"></span>
						<span id='update_at'>${p.update_at}</span>
					</p>
					<a><span class='glyphicon glyphicon-user' aria-hidden='true'></span>
						<span id="userName" style="margin-left:1px">${p.userName}</span></a>
					<a style="margin-left:2px"><span class='glyphicon glyphicon-tags' aria-hidden='true'></span>
						<span style="margin-left:2px">${p.tagName}</span></a>	
					
				</div>
				<hr>
				<div>
					<p class="#" id="content">${p.content}</p>
				</div>
				<div id="editBtn" style="float: right">
					<a id='edit' href="editPost?id=${p.id}" type='button'
						class='btn btn-primary'>修改</a> <a id='edit'
						href="deletePost?id=${p.id}" type='button' class='btn btn-danger'
						style='margin-right: 5px'>刪除</a>
				</div>
				<!-- 分享 -->
				<section class="share" style="float: right">
					<h4>分享此博文</h4>
					<div class="bdsharebuttonbox bdshare-button-style1-24"
						data-bd-bind="1523541645853">
						<a href="#" class="bds_more" data-cmd="more"></a><a href="#"
							class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a
							href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a
							href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网"></a><a
							href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
					</div>
					<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"24"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
				</section>
			</div>
			<!--/.col-xs-12.col-sm-9-->
			<input type="hidden" id="userId" value="${p.userId}">
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				col-xs-6 col-sm-3</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<hr>
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
	<script>
	$(document).ready(function() { 
		var postId = ${p.id};
		$.ajax({
			url : 'getCollect',
			type : 'POST',
			contenType : "application/json;charset-utf-8",
			data : {'postId':postId},
			success:function(data) {
				html="";
				if(data[1]==1){
					html+="<span class='glyphicon glyphicon-heart' aria-hidden='true'>"+data[3]+"</span>";
				}else{
					html+="<span class='glyphicon glyphicon-heart-empty' aria-hidden='true'>"+data[3]+"</span>";
				}
				$("#collect").html(html);
			},
			error:function() {
				alert(0);
			},
		});
		
		<%if (user != null) {%>
		var postUserId = $("#userId").val();
		if(postUserId==${user.id}){
			$('#editBtn').attr("hidden",false); 
		}else{
			$('#editBtn').attr("hidden",true); 
		}
		<%} else {%>
		$('#editBtn').attr("hidden",true); 
		<%}%>
	});
	$('#collect').click(function() {
		var postId = ${p.id};
		<%if (user != null) {%>
		$.ajax({
			url : 'collect',
			type : 'POST',
			contenType : "application/json;charset-utf-8",
			data : {
				'postId':postId,
				'userId':${user.getId()},	
			},
			success:function(data) {
				html="";
				if(data[1]==1){
					html+="<span class='glyphicon glyphicon-heart' aria-hidden='true'>"+data[3]+"</span>";
				}else{
					html+="<span class='glyphicon glyphicon-heart-empty' aria-hidden='true'>"+data[3]+"</span>";
				}
				$("#collect").html(html);
			},
			error:function() {
				alert(0);
			},
		});
		<%} else {%>
			window.location.href="login.jsp";
		<%}%>
	});
	</script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="src/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.js"></script>
</body>
</html>
