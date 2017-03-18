<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${staticUrl}/css/main.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.5.0/css/zui.min.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.5.0/css/zui-theme.min.css" rel="stylesheet">
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">目录</a></li>
			<li><a href="#">系统管理</a></li>
			<li class="active">编辑</li>
		</ol>
		<div class="page">
			<form class="form-horizontal">
			  <div class="form-group">
			    <label for="exampleInputAccount4" class="col-sm-1">账号</label>
			    <div class="col-md-4 col-sm-6">
			      <input type="text" class="form-control" id="exampleInputAccount4" placeholder="电子邮件/手机号/用户名">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword4" class="col-sm-1">密码</label>
			    <div class="col-md-4 col-sm-6">
			      <input type="password" class="form-control" id="exampleInputPassword4" placeholder="密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="exampleInputAddress1" class="col-sm-1">地址</label>
			    <div class="col-sm-3">
			      <select class="form-control" id="exampleInputAddress1">
			        <option>北京</option>
			        <option>上海</option>
			        <option>上海</option>
			        <option>上海</option>
			        <option>上海</option>
			      </select>
			    </div>
			    <div class="col-sm-3">
			      <input type="text" class="form-control" id="exampleInputAddress2" placeholder="市/县">
			    </div>
			    <div class="col-sm-4">
			      <select class="form-control" id="exampleInputAddress1">
			        <option>北京</option>
			        <option>上海</option>
			        <option>上海</option>
			        <option>上海</option>
			        <option>上海</option>
			      </select>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-1 col-sm-1">
			      <button type="submit" class="btn btn-default">返回</button>
			    </div>
			    <div class="col-sm-1">
			      <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			  </div>
			</form>
		</div>
	</div>
</body>
<script src="${staticUrl}/plugins/jquery-1.12.4.min.js" charset="utf-8"></script>
<script src="${staticUrl}/plugins/zui-1.5.0/js/zui.min.js"></script>
<script type="text/javascript">
</script>
</html>