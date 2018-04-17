<%@page import="com.sunjinxu.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="icon" href="favicon.ico">
<title>博客主页</title>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="src/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/jquery.nanoscroller/0.6.7/css/nanoscroller.min.css">
<script
	src="src/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>
<link href="src/style/css/index.css" rel="stylesheet">
<script src="src/style/js/template.js"></script>
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
					<li class="active"><a href="index">主页</a></li>
					<li><a href="#about">关于</a></li>
					<li><a href="#contact">联系</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form navbar-left">
						<div class="form-group">
							<input id="searchTitle" type="text" class="form-control" placeholder="Search">
						</div>
						<button id="search" type="button" class="btn btn-success">
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
					<li class="dropdown"><a class="dropdown-toggle" id="userInfo"
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
							<li><a href="center"><span class="glyphicon glyphicon-user"
									aria-hidden="true" style="padding-right: 5px;"></span>个人中心</a></li>
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
				<!-- 巨幕 -->
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox" id="listbox">
						<div class="item active">
							<img src="src/images/carousel/1.jpg" alt="...">
							<div class="carousel-caption">写点东西</div>
						</div>
						<div class="item">
							<img src="src/images/carousel/2.jpg" alt="...">
							<div class="carousel-caption">写点东西</div>
						</div>
						<div class="item">
							<img src="src/images/carousel/3.jpg" alt="...">
							<div class="carousel-caption">写点东西</div>
						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				<hr>

				<div id="posts-list"></div>
				<script type="text/html" id="posts-list-script">
					<div class="box-content">
						{{each pageInfo.list as value}}
							<div class='row' id='postlist'>
								<div class='col-xs-6 col-md-3' id='colleft'>
									<a href='singlePost?id={{value.id}}' class='thumbnail'> <img
										src='{{value.imgPath}}' alt='...'>
									</a>
								</div>
								<div class='col-xs-6 col-md-9' id='colright'style='height:123.58px'>
									<a href='singlePost?id={{value.id}}' id='title'>{{value.title}}<label id='postId' hidden>{{value.id}}</label></a>
									<div id='summary' style='height:60px'>{{value.summary}}
										<a href='singlePost?id={{value.id}}' class='more'>详情</a>
									</div>
									<div class='detail'>
										<p class='update_at'>
											<span class='glyphicon glyphicon-calendar' aria-hidden='true'></span>{{value.update_at}}
										</p>
										<a href='#' class='auth'><span
											class='glyphicon glyphicon-user' aria-hidden='true'></span>{{value.userName}}</a>
										<a href='#' class='view'><span
											class='glyphicon glyphicon-eye-open' aria-hidden='true'></span>{{value.view}}</a>
										<a href='#' class='heart'><span
											class='glyphicon glyphicon-heart' aria-hidden='true'></span>{{value.collect}}</a>
									</div>
								</div>
							</div>
						{{/each}}
						<div class="clear"></div>
					</div>
					<!--点击分页-->
					<center>
						<ul class="pagination turningPage">
							<li value={{pageInfo.firstPage}}><a>首页</a></li>
								<!--上一页-->
								{{if pageInfo.hasPreviousPage==true}}
								<li value="{{pageInfo.prePage}}"><a aria-label="Previous"><span aria-hidden="true">«</span></a></li>
								{{/if}}
								<!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
								{{each pageInfo.navigatepageNums as value}}
									{{if value == pageInfo.pageNum}}
										<li class="active"><a href="#">{{value}}</a></li>
									{{/if}}
									{{if value != pageInfo.pageNum}}
										<li value={{value}}><a href="#">{{value}}</a></li>
									{{/if}}
								{{/each}}
								<!--下一页-->
								{{if pageInfo.hasNextPage==true}}
								<li value={{pageInfo.nextPage}}><a aria-label="Next"><span aria-hidden="true">»</span></a></li>
								{{/if}}
								{{if pageInfo.isLastPage==false}}
								<li value={{pageInfo.lastPage}}><a>尾页</a></li>
								{{/if}}
							</ul>
					</center>
						
						<!--文字信息-->
						<div style="text-align: center;">当前第 {{pageInfo.pageNum}} 页.总共
							{{pageInfo.pages}} 页.一共 {{pageInfo.total}} 条记录</div>
				</script>
			</div>
			<!--/.col-xs-12.col-sm-9-->
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				<div class="panel panel-info">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-comment"
							style="margin-right: 5px"></span>大家正在说 <span><a
							class="btn btn-xs" href="/feed" style="float: right">更多»</a></span>
					</div>
					<div class="panel-body">
						<div class="input-group" style="width: 100%">
							<form id="feedform" class="feedform" name="feedform"
								method="post">
								<textarea type="text" name="content" value=""
									class="form-control" id="content" required></textarea>
								<button id="button" class="btn btn-success btn-xs" type="submit"
									style="float: right;">发布</button>
							</form>
						</div>
						<!-- /input-group -->
						<div class="feed">
							<div id="about" class="nano">
								<div class="content" id="listComment"></div>
							</div>
						</div>
					</div>
					<div class="panel-footer">Panel footer</div>
				</div>
				<h3 style="margin-bottom:-15px">标签云</h3>
				<div id="tagCloud"></div>
			</div>
			<!--/.sidebar-offcanvas-->
		</div>
	</div>
	<!--/.container-->
	<footer class="footer">
		<div class="container">
			<p class="text-muted">Place sticky footer content here.</p>
		</div>
	</footer>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
	<script src="src/style/js/index.js"></script>
	<script src="src/style/js/tagCloud.js"></script>

	<!-- 留言板滚动条 -->
	<script
		src="https://cdn.bootcss.com/jquery.nanoscroller/0.6.7/javascripts/jquery.nanoscroller.min.js"></script>
	<script>
		$(".nano").nanoScroller();
		window.jQuery
				|| document
						.write('<script src="https:\\cdn.bootcss.com\jquery\3.3.1\jquery.min.js"><\/script>')
	</script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		window.template()
	</script>
	<script
		src="src/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.js"></script>
</body>
</html>
