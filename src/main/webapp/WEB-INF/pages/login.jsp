<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>登陆页面</title>
</head>
<body>
<div class="container">
	<div>
		用户名：<input type="text" id="email" name="email" />
		密码：<input type="text" id="pwd" name="pwd" />
		<input type="button" value="登陆" onclick="login()"/>
	</div>	
</div>
<script type="text/javascript" src="../../../resources/js/lib/jquery.js"></script>


<script type="text/javascript">
    $(document).ready(function () {
    });
	
    var login = function() {
    	var pwd = $('#pwd').val();
    	var email = $('#email').val();
    	$.ajax({
			type : "post",
			async: false,
			url :  "/login",
			data : {
				'pwd' : pwd,
				'email' : email
			},
			dataType : "text",
			success : function(result) {
				alert(result);
				console.log(result);
			}
		});
    	
    	
    	
    	
    	/* $.ajax({
            type:"POST",
            url:"testLogin.aspx",
            data:{Name:"sanmao",Password:"sanmaoword"},
            datatype: "html",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:function(){
            	$("#msg").html("logining");
           	},
            //成功返回之后调用的函数             
            success:function(data){
           		$("#msg").html(decodeURI(data));            
            }   ,
            //调用执行后调用的函数
            complete: function(XMLHttpRequest, textStatus){
               alert(XMLHttpRequest.responseText);
               alert(textStatus);
            },
            //调用出错执行的函数
            error: function(){
            }         
         }); */
    }
</script>
</body>
</html>

