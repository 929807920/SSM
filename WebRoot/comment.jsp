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
<title>动态</title>
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="src/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
<link href="src/style/css/login.css" rel="stylesheet">
<script src="src/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>
</head>
<body>
	<div class="container">
		<div class="panel panel-info">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-comment"
							style="margin-right: 5px"></span>动态
					</div>
					<div class="panel-body">
						<div class="input-group" style="width: 100%">
							<form id="feedform" class="feedform" name="feedform"
								method="post">
								<textarea type="text" name="content" value=""
									class="form-control" id="content" placeholder="说点什么..." required></textarea>
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
	</div>
	<script src="src/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
