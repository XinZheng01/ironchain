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
			<form:form id="saveForm" modelAttribute="banner" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
		      <form:hidden path="picturePath"/>
			  <div class="form-group">
			    <label for="title" class="col-sm-1 required">标题</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="title" cssClass="form-control" id="title" placeholder="请输入标题"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="url" class="col-sm-1">链接</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="url" cssClass="form-control" id="url" placeholder="请输入链接"/>
			      <div class="help-block">如：http://www.baidu.com</div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="picturePath" class="col-sm-1 required">图片</label>
			    <div class="col-md-4 col-sm-6">
			    <my:uploadImg id="picturePath" defaultValue="${picturePath}" width="200" help="建议尺寸：750px * 350px，支持上传bmp/png/jpeg/jpg/gif格式"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="showTime" class="col-sm-1 required">上架时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="showTime" cssClass="form-control form-datetime" id="showTime" placeholder="请输入上架时间" readonly="true"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="unShowTime" class="col-sm-1">下架时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="unShowTime" cssClass="form-control form-datetime" id="unShowTime" placeholder="请输入下架时间" readonly="true"/>
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
		location.href = "${ctx}/banner/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	/* loginName: {
	    		required: true
	    	},
	    	name: {
	    		required: true
	    	},
	    	newPassword: "pwd",
	    	email: "email",
	    	mobilephone: "mobilephone",
	    	roles: "required" */
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>