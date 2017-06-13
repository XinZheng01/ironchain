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
			<form:form id="saveForm" modelAttribute="letterLog" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="title" class="col-sm-2 required">标题</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="title" cssClass="form-control" id="title" placeholder="请输入标题"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="type" class="col-sm-2">类型</label>
			    <div class="col-md-4 col-sm-6">
				  <label class="radio-inline">
				  	<form:radiobutton path="type" value="1"/> 文本内容
				  </label>
				  <label class="radio-inline">
				  	<form:radiobutton path="type" value="2"/> 链接
				  </label>
				  <label class="radio-inline">
				  	<form:radiobutton path="type" value="3"/> 加工需求、设备服务 
				  </label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="sendType" class="col-sm-2">发送类型</label>
			    <div class="col-md-4 col-sm-6">
				  <label class="radio-inline">
				  	<form:radiobutton path="sendType" value="1"/> 所有用户
				  </label>
				  <label class="radio-inline">
				  	<form:radiobutton path="sendType" value="2"/> 指定用户
				  </label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="members" class="col-sm-2">指定发送的用户</label>
			    <div class="col-md-4 col-sm-6">
				  <form:textarea path="members" cssClass="form-control" id="members" placeholder="请输入指定发送的用户" rows="5"/>
			    <div class="help-block">输入用户名或手机号码并使用回车分隔</div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="content" class="col-sm-2">内容</label>
			    <div class="col-md-4 col-sm-6">
				  <form:textarea path="content" cssClass="form-control" id="content" placeholder="请输入内容" rows="7"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="attr" class="col-sm-2">属性</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="attr" cssClass="form-control" id="attr" placeholder="请输入属性"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="url" class="col-sm-2">链接</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="url" cssClass="form-control" id="url" placeholder="请输入链接"/>
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
function changeType(){
	var type = $('[name=type]:checked').val();
	switch(type){
	case '1':
		$('#content').closest('.form-group').show();
		$('#attr').closest('.form-group').hide();
		$('#url').closest('.form-group').hide();
		break;
	case '2':
		$('#content').closest('.form-group').hide();
	    $('#attr').closest('.form-group').hide();
	    $('#url').closest('.form-group').show();
		break;
	case '3':
		$('#content').closest('.form-group').hide();
	    $('#attr').closest('.form-group').show();
	    $('#url').closest('.form-group').hide();
		break;
	}
}
function changeSendType(){
	var sendType = $('[name=sendType]:checked').val();
	$('#members').closest('.form-group').toggle(sendType == '2');
}
$(function(){
	changeType();
	changeSendType();
	$('[name=type]').on('change', changeType);
	$('[name=sendType]').on('change', changeSendType);
	
	$('.back').on('click', function(){
		location.href = "${ctx}/letter/log/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	title: "required",
			number: "required",
			type: "required",
			sendType: "required"
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>