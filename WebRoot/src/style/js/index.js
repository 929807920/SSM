$(document).ready(function() {
	$.ajax(listComment);
	$.ajax({
		url : 'postListJson',
		dataType : 'json',
		data : {
			pageNum : 1
		},
		success : function(data) {
			var html = template('posts-list-script', data);
			document.getElementById('posts-list').innerHTML = html;	
		},
		error : function() {
		}
	})
});
$('#search').click(function() {
	$.ajax({
		url : 'searchPostList',
		dataType : 'json',
		data : {
			pageNum : 1,
			title:$('#searchTitle').val()
		},
		success : function(data) {
			var html = template('posts-list-script', data);
			document.getElementById('posts-list').innerHTML = html;	
		},
		error : function() {
		}
	})
});
$('#posts-list').on('click', '.turningPage li', function() {
	var $this = $(this);
		var pageNum = $this.val();
		$.ajax({
			url : 'postListJson',
			dataType : 'json',
			data : {
				pageNum : pageNum
			},
			success : function(data) {
				var html = template('posts-list-script', data);
				document.getElementById('posts-list').innerHTML = html;
			},
			error : function() {
				
			}
		});
	});
var listComment = {
		type : "post",
		url : "listComment",
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		async : true,
		success : function(data) {
			html="";
			for(var x in data){
					html+="<ul id='feed1' class='media-list nano-content' tabindex='0' style='right:-16px'>"+
					"<li class='media' data-key='30103'>"+	
					"<div class='media-left'>"+	
					"<a href='/user/42009' rel='author'><img class='media-object' src='"+data[x].userAvatar+"' alt='"+data[x].userName+"'></a>"+		
					"</div><div class='media-body'><div class='media-content'>"+		
					"<a href='/user/42009' rel='author'>"+data[x].userName+"</a>: "+data[x].content+
					"</div><div class='media-action'>"+
					"<span class='time'>"+data[x].create_at+"</span>"+
					"<span class='pull-right'>"+		
					"<a href='/feed/30103'><i class='fa fa-comment-o'></i>"+data[x].subCount+"</a>"+		
					"<a class='vote up' href='javascript:void(0);' title='' data-type='feed' data-id='30103' data-toggle='tooltip' data-original-title='顶'>"+				
					"<i class='fa fa-thumbs-o-up'></i>"+data[x].praise+"</a>"+				
					"</span></div></div></li></ul>";
			}
			document.getElementById("listComment").innerHTML = html;
		}				
}
$('#button').click(function() {
	var userInfo = $("#userInfo").val();
	if(userInfo!=null){
		$.ajax({
			url : 'insertComment',
			type : 'POST',
			contenType : "application/json;charset-utf-8",
			data : {'content':$("#content").val()},
			success:function(data) {
				if(data==1){
					$("#content").val("");
					$.ajax(listComment);
				}
			},
			error:function() {
				$("#content").val("");
			},
		});
		return false;
	}else {
		window.location.href="login.jsp";
	}
});
