<%@page import="com.sunjinxu.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">
<title>个人中心</title>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="./src/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="./src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.css"
	rel="stylesheet">
<link rel="stylesheet" href="src/style/ImgCropping/css/cropper.min.css">
<link rel="stylesheet" href="src/style/ImgCropping/css/ImgCropping.css">



<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="./src/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>
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
							<li class="active"><a><span
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
					<% } %>
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
				<table width="100%">
					<tr>
						<td rowspan="3">
							<!-- <button id="replaceImg" class="l-btn">更换图片</button> -->
							<div id="replaceImg" class="l-btn" style="width: 90px; height: 90px; border: solid 1px #555; padding: 5px; margin-top: 10px;">
								<img id="finalImg" src="${user.getAvatar()}" width="100%">
							</div>
						</td>
						<td align="left"><h3><% if (user.getUserName() != null) { %>${user.getUserName()}<% } else { %>${user.getAccount()}<% } %></h3></td>
						<td align="right"><a type="button" data-toggle="modal" data-target="#myModal">修改个人资料</a></td>
					</tr>
					<tr>
						<td colspan="2">
							<span id="business" style="margin-right:3px">行业：<% if (user.getBusiness() != null){%> ${user.business } <% } else { %> <span style="color:red">未填写</span> <% } %></span>|
							<span id="email" style="margin-left:3px;margin-right:3px;">邮箱：<% if (user.getEmail() != null){%> ${user.email } <% } else { %> <span style="color:red">未填写</span> <% } %></span>|
							<span id="phone" style="margin-left:3px;margin-right:3px;">手机：<% if (user.getPhone() != null){%> ${user.phone } <% } else { %> <span style="color:red">未填写</span> <% } %></span><br>
							<span id="address" style="margin-right:3px;">地址：<% if (user.getAddress() != null){%> ${user.birthday } <% } else { %> <span style="color:red">未填写</span> <% } %></span>|
							<span id="sex" style="margin-left:3px;margin-right:3px;">性别：<% if (user.getSex() != null){%> ${user.sex } <% } else { %> <span style="color:red">未填写</span> <% } %></span>|
							<span id="birthday" style="margin-left:3px">生日： <% if (user.getBirthday() != null){%> ${user.birthday } <% } else { %> <span style="color:red">未填写</span> <% } %></span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span id="business" style="margin-right: 10px">文章(<a href="#">${user.postNum }</a>)</span>|
							<span id="email" style="margin-right: 10px">说说(<a href="#">${user.commentNum }</a>)</span>|
							<span id="comment"><a href="#">留言板</a></span>
						</td>
					</tr>				
				</table>
				<!-- 面板 -->
				<div>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#home"
							aria-controls="home" role="tab" data-toggle="tab">动态</a></li>
						<li role="presentation"><a href="#messages"
							aria-controls="messages" role="tab" data-toggle="tab">我的说说</a></li>
						<li role="presentation"><a href="#profile"
							aria-controls="profile" role="tab" data-toggle="tab">我的文章</a></li>
						<li role="presentation"><a href="#settings"
							aria-controls="settings" role="tab" data-toggle="tab">收藏列表</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content" style="margin-top: 10px;">
						<div role="tabpanel" class="tab-pane active" id="home"></div>
						<div role="tabpanel" class="tab-pane" id="profile">...</div>
						<div role="tabpanel" class="tab-pane" id="messages">...</div>
						<div role="tabpanel" class="tab-pane" id="settings">...</div>
					</div>

				</div>
			</div>

			<!--/.col-xs-12.col-sm-9-->
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				col-xs-6 col-sm-3</div>
			<!--/.sidebar-offcanvas-->
		</div>
	</div>
	<!--/.container-->

	<!-- Modal -->
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">修改个人资料</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="chgUserInfo" method="post"
						name="chgUserInfo">
						<div class="form-group">
							<label for="userName" class="col-sm-2 control-label">昵称</label>
							<div class="col-sm-10">
								<input type="text" name="userName" id="userName"
									value="${user.userName }" class="form-control" id="userName"
									oninput="checkUserName()">
							</div>
						</div>
						<div class="form-group">
							<label for="sex" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="sex" id="inlineRadio1" value="男">男
								</label> <label class="radio-inline"> <input type="radio"
									name="sex" id="inlineRadio2" value="女">女
								</label> <label class="radio-inline"> <input type="radio"
									name="sex" id="inlineRadio3" value="保密" checked>保密
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="birthday" class="col-sm-2 control-label">生日</label>
							<div class="col-sm-10">
								<span class="glyphicon glyphicon-calendar" aria-hidden="true"
									style="float: left; line-height: 2"></span> <input name="birthday"
									style="float: left; width: 30%" type="text" id="date"
									class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label for="userName" class="col-sm-2 control-label">地区</label>
							<div class="col-sm-10">
								<div>
									<select id="s_province" name="s_province" class="form-control" style="float: left; width: 25%;margin-right:5px"></select>  
								    <select id="s_city" name="s_city" class="form-control" style="float: left; width: 25%;margin-right:5px"></select>  
								    <select id="s_county" name="s_county" class="form-control" style="float: left; width: 25%;margin-right:5px"></select>
								    <script class="resources library" src="src/style/area/area.js" type="text/javascript"></script>
								    <script type="text/javascript">_init_area();</script>
								</div>
								<div id="show"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label">手机号</label>
							<div class="col-sm-10">
								<input type="text" name="phone" id="phone"
									value="${user.phone }" class="form-control" id="phone"
									oninput="checkPhone()">
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="email" name="email" id="email"
									value="${user.email }" class="form-control" id="email"
									oninput="checkEmail()">
							</div>
						</div>
						<div class="form-group">
							<label for="business" class="col-sm-2 control-label">行业</label>
							<div class="col-sm-10">
								<input type="text" name="business" id="business"
									value="${user.business }" class="form-control" id="business">
							</div>
						</div>
						<span id="errorMsg"></span>
						<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
					</form>
				</div>
				
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!--图片裁剪框 start-->
	<div style="display: none" class="tailoring-container">
		<div class="black-cloth" onclick="closeTailor(this)"></div>
		<div class="tailoring-content">
		<form action="uploadImg" method="post" enctype="multipart/form-data">
			<div class="tailoring-content-one">
				<label title="上传图片" for="chooseImg" class="l-btn choose-btn">
					<input type="file" accept="image/jpg,image/jpeg,image/png"
					name="file" id="chooseImg" class="hidden"
					onchange="selectImg(this)"> 选择图片
				</label>
				<div class="close-tailoring" onclick="closeTailor(this)">×</div>
			</div>
			<div class="tailoring-content-two">
				<div class="tailoring-box-parcel">
					<img id="tailoringImg">
				</div>
				<div class="preview-box-parcel">
					<p>图片预览：</p>
					<div class="square previewImg"></div>
					<div class="circular previewImg"></div>
				</div>
			</div>
			<div class="tailoring-content-three">
				<button class="l-btn cropper-reset-btn">复位</button>
				<button class="l-btn cropper-rotate-btn">旋转</button>
				<button class="l-btn cropper-scaleX-btn">换向</button>
				<button class="l-btn sureCut" id="sureCut" type="submit">确定更换</button>
			</div>
		</form>
		</div>
	</div>
	<!--图片裁剪框 end-->
	<script src="src/style/ImgCropping/js/cropper.min.js"></script>
	<script src="src/style/ImgCropping/js/cropper.js"></script>
	<!-- 分页 -->
	<script type="text/html" id="comments-list-script">
					<div class="box-content">
						{{each pageInfo.list as value}}
							<div class="media">
  								<div class="media-left media-middle">
    								<a href="#"><img class="media-object" src="{{value.userAvatar}}" alt="{{value.userName}}"></a>
  								</div>
  								<div class="media-body">
									<table width="100%">
										<tr>
											<td><a href="#">{{value.userName}}</a></td>
											<td style="float:right"><span class='glyphicon glyphicon-calendar' aria-hidden='true'></span>{{value.create_at}}
												<a href='#' class='heart'><span
													class='glyphicon glyphicon-heart-empty' aria-hidden='true'></span>{{value.praise}}</a>
												<a href='#' class='heart'><span
													class='glyphicon glyphicon-heart' aria-hidden='true'></span>{{value.praise}}</a>
												<a href='#' class='comment'><span
													class='glyphicon glyphicon-comment' aria-hidden='true'></span>评论</a></td>
										</tr>
										<tr><td colspan="2">{{value.content}}</td></tr>
									</table>
  								</div>
							</div>
						{{/each}}
						<div class="clear"></div>
					</div>
					<!--点击分页-->
					<center>
						<ul class="pagination turningPage">
							<li><a>首页</a></li>
							<!--上一页-->
							{{if pageInfo.hasPreviousPage==true}}
							<li><a href="index?page={{pageInfo.pageNum-1}}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
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
							<li><a href="index?page={{pageInfo.pageNum+1}}" aria-label="Next"> <span aria-hidden="true">»</span></a></li>
							{{/if}}
							<li><a href="index?page={{pageInfo.pages}}">尾页</a></li>
						</ul>
					</center>
						
					<!--文字信息-->
					<div style="text-align: center;">当前第 {{pageInfo.pageNum}} 页.总共
							{{pageInfo.pages}} 页.一共 {{pageInfo.total}} 条记录</div>
				</script>
	<script>
		window.jQuery
				|| document
						.write('<script src="https:\\cdn.bootcss.com\jquery\3.3.1\jquery.min.js"><\/script>')
	</script>
	<script src="src/style/laydate/laydate.js"></script>
	<script> laydate.render({ elem : '#date' }); </script>
	<script src="src/style/js/center.js"></script>
	<script
		src="./src/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script
		src="./src/bootstrap-3.3.7/docs/examples/offcanvas/offcanvas.js"></script>
	<script type="text/javascript">
		var Gid  = document.getElementById ;
		var showArea = function(){
			Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" + 	
			Gid('s_city').value + " - 县/区" + 
			Gid('s_county').value + "</h3>"
		Gid('s_county').setAttribute('onchange','showArea()')}
	</script>
</body>
</html>
