<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>动态查询</title>
    <link href="../../../resources/css/common/circular.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
		.dd{
 		   width:200px;
 		  folat:left;
		}

    </style>
</head>
<body>
<div class="container">
	<div  id="head_img" class="circular_img">
		<figure>
			<div>
				<img style="width:200px;height:200px;background:green;float:left" alt="游风" \
				src="http://7xqpkx.com1.z0.glb.clouddn.com/1001.jpg"/>
			</div>  
		</figure>
	 </div>
	 <div></div>
	 <span>游风</span>
	<div style="width:100px;height:300px;background:blue;float:left">div1哈哈哈哈</div>
 	<div style="width:300px;height:300px;background:red;float:left">div2呵呵呵呵<div/>
	<div id="list">
		<table>
			<tr>
				<td>发布人姓名：xxx</td>
				<td>发布人头像：
				</td>
				<td>发布人职业：xxx</td>
			
				<td>标题：第一个此时彼刻</td>
				<td>内容：这是我的第一个此时彼刻，好开心！！！</td>
				<td>发布时间</td>
				<td>点赞数：4</td>
				<td>浏览数：4</td>
				<td>收藏数：5</td>
				<td>收藏人列表：</td>
				<td>点赞人数</td>
			</tr>
		</table>
	</div>
</div>

<script type="text/javascript" src="../../../resources/js/lib/jquery.js"></script>
<script type="text/javascript" src="../../../resources/js/moment/list.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
    	app.moment.init();
    });

</script>
</body>
</html>

