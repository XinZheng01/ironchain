<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
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
			<form action="${ctx}/system/user/save" method="post" class="form-horizontal">
				<sec:csrfInput />
				<input name="id" type="hidden" value="${systemUser.id}">
			  <div class="form-group">
			    <label for="loginName" class="col-sm-1">登录名</label>
			    <div class="col-md-4 col-sm-6">
			      <input name="loginName" type="text" class="form-control" id="loginName" value="${systemUser.loginName}" placeholder="登录名">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="name" class="col-sm-1">用户名</label>
			    <div class="col-md-4 col-sm-6">
			      <input name="name" type="text" class="form-control" id="name" value="${systemUser.name}" placeholder="用户名">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="password" class="col-sm-1">登录密码</label>
			    <div class="col-md-4 col-sm-6">
			      <input name="password" type="password" class="form-control" id="password" placeholder="登录密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email" class="col-sm-1">邮箱</label>
			    <div class="col-md-4 col-sm-6">
			      <input name="email" type="text" class="form-control" id="email" value="${systemUser.email}" placeholder="邮箱">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="mobilephone" class="col-sm-1">手机号码</label>
			    <div class="col-md-4 col-sm-6">
			      <input name="mobilephone" type="text" class="form-control" id="mobilephone" value="${systemUser.mobilephone}" placeholder="手机号码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="status" class="col-sm-1">状态</label>
			    <div class="col-sm-3">
			      <select name="status" class="form-control" id="status">
			        <option value="1" ${systemUser.status == 1?"selected":""}>正常</option>
			        <option value="2" ${systemUser.status == 2?"selected":""}>锁定</option>
			        <option value="0" ${systemUser.status == 0?"selected":""}>删除</option>
			      </select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="roleIds" class="col-sm-1">角色</label>
			    <div class="col-sm-3">
			      <select name="roleIds" class="form-control" id="roleIds" multiple="multiple">
			        <option value="1" >管理员</option>
			        <option value="2" >运营</option>
			        <option value="3" >研发</option>
			      </select>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-1 col-sm-1">
			      <button type="button" class="btn btn-default back">返回</button>
			    </div>
			    <div class="col-sm-1">
			      <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			  </div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
$('.back').on('click', function(){
	location.href = "${ctx}/system/user/list";
});
</script>
</html>