<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>文章添加</title>
    
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
<script type="text/javascript" src="../../../resources/js/article/add.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	app.article.init();
});
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
		
		<input id="publish" type="button" value="提交" />
		<input id="save" type="button" value="保存" />
		<input id="cannel" type="button" value="取消" />
		
	</div>
</div>
</body>
</html>

