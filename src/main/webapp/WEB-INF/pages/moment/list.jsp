<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>动态查询</title>
    <link href="../../../resources/css/lib/pagebar.css" rel="stylesheet" type="text/css"/>
    <link href="../../../resources/css/feed/list.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
    <div class="search"><input id="sequence" class="input" type="text" placeholder="请输入用户机遇号" name=""/><input
            type="button" id="search" class="btn_blue" value="查询"/></div>
    <div class="content" id="content">
        <div class="element">
        </div>
    </div>
</div>
<div id="pagebar">
    <a href="javascript:void(0);" id="prev">上一页</a>
    <span id="go_pg_num">1</span>
    <a href="javascript:void(0);" id="next">下一页</a>
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

