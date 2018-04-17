var accountStatus = false;
var rePasswordStatus = false;
var reg = /^[0-9a-zA-Z]+$/;
function checkAccount(regform){
	var account = $("#account").val().replace(/\s|\xA0/g, "");
	$.ajax({
		url : "checkAccountExist?account=" + account, 
		type : "POST",
		 dataType : "json", 
		 contentType : 'application/x-www-form-urlencoded;charset=UTF-8', 
		 success : function(data) {
			 if(account!=""){
				 if(data){
					 document.getElementById("errorMsg").style.color = "red";
					 document.getElementById("errorMsg").innerHTML = "* 账号已被占用";
					 document.getElementById("password").disabled=true;
					 document.getElementById("rePassword").disabled=true;
					 document.getElementById("login-submit").disabled=true;
					 return accountStatus=false;
				 }else{
					 if(rePasswordStatus==true){
						 document.getElementById("login-submit").disabled=false;
					 }
					 if(!reg.test(account)){
						 document.getElementById("errorMsg").style.color = "red";
						 document.getElementById("errorMsg").innerHTML = "* 账号只能为字母、数字";
						 document.getElementById("password").disabled=true;
						 document.getElementById("rePassword").disabled=true;
						 document.getElementById("login-submit").disabled=true;
						 return accountStatus=false;
					}else{
							document.getElementById("errorMsg").style.color = "green";
						 document.getElementById("errorMsg").innerHTML = "* 账号可以使用";
						 document.getElementById("password").disabled=false;
						 document.getElementById("rePassword").disabled=false;
						 return accountStatus=true;
					}
				 }
			 }else{
				 document.getElementById("errorMsg").style.color = "red";
				 document.getElementById("errorMsg").innerHTML = "* 账号不能为空"; 
				 document.getElementById("login-submit").disabled=true;
				 return accountStatus=false;
			 }
		 }
	})
}
/*function checkUserName(regform) {
	alert(55);
	var userName = $('#userName').val().replace(/\s|\xA0/g, "");
	if (userName != "") {
		$.ajax({
			url : "checkUserNameExist?userName=" + userName,
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data) {
				if (data) {
					document.getElementById("userNameMsg").style.color = "red";
					document.getElementById("userNameMsg").innerHTML = "* 用户名已被占用";
				} else {
					document.getElementById("userNameMsg").style.color = "green";
					document.getElementById("userNameMsg").innerHTML = "* 用户名可以使用";
				}
			}
		})
	} else {
		document.getElementById("userNameMsg").style.color = "red";
		document.getElementById("userNameMsg").innerHTML = "* 用户名不能为空";
	}
}

function checkEmail(regform) {
	var reg = new RegExp(
			"^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	var email = $('#email').val().replace(/\s|\xA0/g, "");
	if (email != "") {
		if (!reg.test(email)) {
			document.getElementById("emailMsg").style.color = "red";
			document.getElementById("emailMsg").innerHTML = "* 邮箱格式不正确";
		} else {
			$.ajax({
				url : "checkEmailExist?email=" + email,
				type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(data) {
					if (data) {
						document.getElementById("emailMsg").style.color = "red";
						document.getElementById("emailMsg").innerHTML = "* 邮箱已被占用";
					} else {
						document.getElementById("emailMsg").style.color = "green";
						document.getElementById("emailMsg").innerHTML = "* 邮箱可以使用";
					}
				}
			})
		}
	} else {
		document.getElementById("emailMsg").style.color = "red";
		document.getElementById("emailMsg").innerHTML = "* 邮箱不能为空";
	}
}*/
function checkPassword(regform) {
	var password = $("#password").val().replace(/\s|\xA0/g, "");
	var rePassword = $("#rePassword").val().replace(/\s|\xA0/g, "");
	if(password!=""){
		if(rePassword!=""){
			if (password != rePassword) {
				document.getElementById("errorMsg").style.color = "red";
				document.getElementById("errorMsg").innerHTML = "* 两次输入密码不一致";
				document.getElementById("login-submit").disabled=true;
				return rePasswordStatus=false;
			} else {
				if(!reg.test(password)){
					document.getElementById("errorMsg").style.color = "red";
					document.getElementById("errorMsg").innerHTML = "* 密码只能为字母、数字";
					document.getElementById("login-submit").disabled=true;
					return rePasswordStatus=false;
				}else{
					document.getElementById("errorMsg").style.color = "green";
					document.getElementById("errorMsg").innerHTML = "* 密码可用";
					if(accountStatus==true){
						document.getElementById("login-submit").disabled=false;
					}
					return rePasswordStatus=true;
				}
			}
		}
	}else{
		document.getElementById("errorMsg").style.color = "red";
		document.getElementById("errorMsg").innerHTML = "* 密码不能为空"; 
		document.getElementById("login-submit").disabled=true;
		return rePasswordStatus=false;
	}
	
}