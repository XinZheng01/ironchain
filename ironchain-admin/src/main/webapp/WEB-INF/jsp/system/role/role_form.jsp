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
<style type="text/css">
.tree-box input[type=checkbox], .tree-toolbar input[type=checkbox]{
	vertical-align:middle; margin:0 3px 0 0;padding:0;
}
.tree-box{
	border: 1px solid rgb(204, 204, 204);padding: 5px 0 0 5px;
}
.tree-toolbar{
	margin: 0 0 5px 0;
}
.tree-toolbar label, .tree-box label{
	margin: 0 7px 0 0;
	font-weight: normal;
}
</style>
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
			<form:form id="saveForm" modelAttribute="systemRole" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="name" class="col-sm-1 required">名称</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="name" cssClass="form-control" id="name" placeholder="请输入名称"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="code" class="col-sm-1 required">英文编码</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="code" cssClass="form-control" id="code" placeholder="请输入英文编码"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="status" class="col-sm-1 required">状态</label>
			    <div class="col-md-4 col-sm-6">
			      <form:select path="status" cssClass="form-control" id="status">
			      	<form:option value="1">启用</form:option>
			      	<form:option value="0">停用</form:option>
			      </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="description" class="col-sm-1">描述</label>
			    <div class="col-md-4 col-sm-6">
			      <form:textarea path="description" cssClass="form-control" id="description" placeholder="请输入描述" rows="5"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-1">权限</label>
			    <div class="col-md-4 col-sm-6">
			    	<div class="tree-toolbar">
				    	<input type="checkbox" id="tree-checkall"><label for="tree-checkall">全选</label>
				    	<input type="checkbox" id="tree-checkreverse"><label for="tree-checkreverse">全反选</label>
			    	</div>
				    <div class="tree-box">
						<ul class="tree tree-lines" data-ride="tree" data-initial-state="expand">
							<li>
								<input type="checkbox" name="" value="">系统管理
								<ul>
								<li>
								<input type="checkbox">权限管理
								</li>
								<li>
								<input type="checkbox">角色管理
								</li>
								<li>
								<input type="checkbox">用户管理
								</li>
								</ul>
							</li>
						</ul>
				    </div>
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
			</form:form>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('.back').on('click', function(){
		location.href = "${ctx}/system/role/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	name: {
	    		required: true
	    	}
	    },
	    message: {
	    	
	    }
	});
	$('#tree-checkall').on('click', function(){
		
	});
})
var nodes = [
	{id: 1, parentId: 0, name: '系统管理'},
	{id: 2, parentId: 1, name: '权限管理'},
	{id: 3, parentId: 1, name: '角色管理'},
	{id: 4, parentId: 1, name: '用户管理'},
	{id: 5, parentId: 3, name: '用户管理'}
];
console.log($.site.transformToTreeFormat(nodes));
</script>
</html>