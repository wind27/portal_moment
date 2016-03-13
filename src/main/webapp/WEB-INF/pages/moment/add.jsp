<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>动态查询</title>
    
    <style type="text/css">
	p {
		border-bottom-style: ridge;
		margin-top: 10px;
		margin-bottom: 10px;
	}
	.article_div {
		margin-top: 10px;
		margin-bottom: 10px;
	}
</style>
<script type="text/javascript">

$(document).ready(function () {
	init();

});
var init = function() {
	$("#submit").click(function() {
		var title = $("#title").val();
		var desc = $("#desc").val();
		var content = $("#content").val();
		
		var data = {
			"title":title,
			"content":content,
			"desc":desc
		};
		alert(title+"*"+desc+"*"+content);
		$.ajax({
			  type: "POST",
			  url: ctx + "/moment/add",
			  data: data,
			  dataType: "json",
			  success: function(result) {
				  if(result.meta.code == 1000) {
					  alert("添加成功");
				  } else {
					  alert("添加失败");
				  }
			  }
		});
	}); 
}

/* 	
	$('#content').xheditor({tools:'mfull', height:300});
 	$(function () {  
        $(pageInit);  
        var editor;  
        function pageInit() {  
            editor=$('#content').xheditor({tools:'full',skin:'o2007blue',width:'1240',height:'500',upImgUrl:'upload.asp'});  
        }  
    });

*/
          
    </script> 
</head>
<body>
<div class="container">
	<div style="width: 800px; margin-left: 100px;margin:5px auto;position: fixed;">
		<p>标题</p>
		<div class="article_div" ><input type="text" id="title"/></div>

		<p>摘要：(默认自动提取您文章的前200字显示在博客首页作为文章摘要，您也可以在这里自行编辑) </p>
		<div class="article_div" >
			<textarea id="desc" name="desc" rows="8" style="width: 99%;"></textarea>
		</div>
	
		<p>内容</p>
		<div class="article_div" >
			<textarea id="content" name="content" class="xheditor" style="width: 100%;height: 100%"></textarea>
		</div>
		
		
		<input id="submit" type="submit" value="提交" />
		
	</div>
</div>
</body>
</html>

