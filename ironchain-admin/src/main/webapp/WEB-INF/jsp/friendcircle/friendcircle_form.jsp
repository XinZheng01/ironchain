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
			<form:form id="saveForm" modelAttribute="friendcircle" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="content" class="col-sm-1 required">内容</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="content" cssClass="form-control" id="content" placeholder="请输入内容"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="memberId" class="col-sm-1 required">发送人</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="memberId" cssClass="form-control" id="memberId" placeholder="请输入发送人"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="sendTime" class="col-sm-1">发送时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="sendTime" cssClass="form-control" id="sendTime" placeholder="请输入发送时间"/>
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
		location.href = "${ctx}/friendcircle/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	content: "required",
memberId: "required",
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>