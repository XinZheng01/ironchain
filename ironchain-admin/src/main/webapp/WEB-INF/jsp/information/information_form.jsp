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
			<form:form id="saveForm" modelAttribute="information" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="title" class="col-sm-1 required">标题</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="title" cssClass="form-control" id="title" placeholder="请输入标题"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="source" class="col-sm-1">文章来源</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="source" cssClass="form-control" id="source" placeholder="请输入文章来源"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="img" class="col-sm-1">缩略图</label>
			    <div class="col-md-4 col-sm-6">
				  <my:uploadImg id="img" defaultValue="${information.img}"></my:uploadImg>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="sortId" class="col-sm-1">排序值</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="sortId" cssClass="form-control" id="sortId" placeholder="请输入排序值"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="keywords" class="col-sm-1">网页关键字</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="keywords" cssClass="form-control" id="keywords" placeholder="请输入网页关键字"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="description" class="col-sm-1">网页描述</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="description" cssClass="form-control" id="description" placeholder="请输入网页描述"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="type" class="col-sm-1">类型</label>
			    <div class="col-md-4 col-sm-6">
				  <form:select path="type" cssClass="form-control" id="type">
			      	<form:option value="1">文章</form:option>
			      	<form:option value="2">广告</form:option>
			      </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="status" class="col-sm-1">状态</label>
			    <div class="col-md-4 col-sm-6">
				  <form:select path="status" cssClass="form-control" id="status">
			      	<form:option value="0">未发布</form:option>
			      	<form:option value="1">已发布</form:option>
			      </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="showTime" class="col-sm-1">上架时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="showTime" cssClass="form-control form-datetime" id="showTime" placeholder="请输入上架时间" readonly="true"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="unShowTime" class="col-sm-1">下架时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="unShowTime" cssClass="form-control form-datetime" id="unShowTime" placeholder="请输入下架时间" readonly="true" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="content" class="col-sm-1 required">内容</label>
			    <div class="col-md-9 col-sm-9">
				  <my:kindeditor path="content"></my:kindeditor>
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
		location.href = "${ctx}/information/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	title: "required",
			content: "required"
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>