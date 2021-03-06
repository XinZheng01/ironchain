<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<form:form id="saveForm" modelAttribute="shopClass" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="name" class="col-sm-2 required">分类名称</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="name" cssClass="form-control" id="name" placeholder="请输入分类名称"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="parent" class="col-sm-2">上级分类</label>
			    <div class="col-md-4 col-sm-6">
				  <form:select path="parent" cssClass="form-control" id="parent">
				  	<form:option value="">顶级分类</form:option>
				  	<form:options items="${shopClassList}" itemLabel="name" itemValue="id"/>
				  </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="sortId" class="col-sm-2">排序值</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="sortId" cssClass="form-control" id="sortId" placeholder="请输入排序值"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-1">
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
		location.href = "${ctx}/shop/class/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	name: "required",
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>