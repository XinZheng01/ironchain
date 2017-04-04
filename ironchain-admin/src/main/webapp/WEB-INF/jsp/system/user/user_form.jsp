<%@page import="java.util.Enumeration"%>
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
			<form:form id="saveForm" modelAttribute="systemUser" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="loginName" class="col-sm-1 required">登录名</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="loginName" cssClass="form-control" id="loginName" placeholder="请输入登录名"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="name" class="col-sm-1 required">用户名</label>
			    <div class="col-md-4 col-sm-6">
			      <form:input path="name" cssClass="form-control" id="name" placeholder="请输入用户名"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="password" class="col-sm-1">登录密码</label>
			    <div class="col-md-4 col-sm-6">
			      <form:password path="password" cssClass="form-control" id="password" placeholder="请输入登录密码"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email" class="col-sm-1">邮箱</label>
			    <div class="col-md-4 col-sm-6">
			      <form:input path="email" cssClass="form-control" id="email" placeholder="请输入邮箱"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="mobilephone" class="col-sm-1">手机号码</label>
			    <div class="col-md-4 col-sm-6">
			      <form:input path="mobilephone" cssClass="form-control" id="mobilephone" placeholder="请输入手机号码"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="status" class="col-sm-1">状态</label>
			    <div class="col-md-4 col-sm-6">
			      <form:select path="status" cssClass="form-control" id="status">
			      	<form:option value="1">正常</form:option>
			      	<form:option value="2">锁定</form:option>
			      	<form:option value="0">删除</form:option>
			      </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="roles" class="col-sm-1 required">角色</label>
			    <div class="col-md-4 col-sm-6">
			      <form:select path="roles" cssClass="form-control" id="roles" multiple="multiple">
			      	<form:options items="${roleList}" 
			      		itemLabel="name" itemValue="id"/>
			      </form:select>
			      <div class="help-block">按住Ctrl可以多选或取消选择</div>
			    </div>
			    <%--
			    <form:errors path="roles" cssClass="errorMessage" element="div"/>
			     --%>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-1 col-sm-1">
			      <button type="button" class="btn btn-default back">返回</button>
			    </div>
			    <div class="col-sm-1">
			      <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			  </div>
			</form:form>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('.back').on('click', function(){
		location.href = "${ctx}/system/user/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	loginName: {
	    		required: true
	    	},
	    	name: {
	    		required: true
	    	},
	    	email: "email",
	    	mobilephone: "mobilephone",
	    	roles: "required"
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>