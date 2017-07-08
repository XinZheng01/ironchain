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
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<form:form id="saveForm" modelAttribute="systemPermission" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="name" class="col-sm-1 required">名称</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="name" cssClass="form-control" id="name" placeholder="请输入名称"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="parent" class="col-sm-1">上级菜单</label>
			    <div class="col-md-4 col-sm-6">
				  <form:select path="parent" cssClass="form-control" id="parent">
				  	<form:options items="${permissionList}" itemLabel="name" itemValue="id"/>
				  </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="code" class="col-sm-1">英文编码</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="code" cssClass="form-control" id="code" placeholder="请输入英文编码"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="type" class="col-sm-1">类型</label>
			    <div class="col-md-4 col-sm-6">
			      <form:select path="type" cssClass="form-control" id="type">
			      <%--
			      	<form:option value="1">目录</form:option>
			       --%>
			      	<form:option value="2">菜单</form:option>
			      	<form:option value="3">按钮</form:option>
			      </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="url" class="col-sm-1">链接</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="url" cssClass="form-control" id="url" placeholder="请输入链接"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="icon" class="col-sm-1">图标</label>
			    <div class="col-md-3 col-sm-5">
			    <form:hidden path="icon" id="icon"/>
			    <button id="iconSpan" type="button" class="form-control btn btn-default" data-icon="icon">
		    	<c:if test="${not empty systemPermission.icon}">
		    		<i class="icon ${systemPermission.icon}">   ${systemPermission.icon}</i>
		    	</c:if>
			    </button>
			    </div>
			    <button type="button" class="btn btn-danger deleteIcon">清除</button>
			  </div>
			  <div class="form-group">
			    <label for="orderNum" class="col-sm-1">排序值</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="orderNum" cssClass="form-control" id="orderNum" placeholder="请输入排序值"/>
			    </div>
			    <div class="help-block">数字范围为0~255，数字越小越靠前</div>
			  </div>
			  <div class="form-group">
			    <label for="status" class="col-sm-1">状态</label>
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
		location.href = "${ctx}/system/permission/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	//name: {
	    	//	required: true
	    	//}
	    },
	    message: {
	    	
	    }
	});
	$('#icon').on('change', function(){
		$('#iconSpan').html('<i class="icon ' + $(this).val() + '">   '+$(this).val()+'</i>');
	});
	$('.deleteIcon').on('click', function(){
		$('#iconSpan').html('');
		$('#icon').val('');
	});
})

</script>
</html>