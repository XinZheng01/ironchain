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
			<form:form id="saveForm" modelAttribute="memberLevelup" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="memberId" class="col-sm-1 required">会员</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="memberId" cssClass="form-control" id="memberId" placeholder="请输入会员"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="memberLevelId" class="col-sm-1 required">会员等级</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="memberLevelId" cssClass="form-control" id="memberLevelId" placeholder="请输入会员等级"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="startDate" class="col-sm-1 required">会员开始时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="startDate" cssClass="form-control" id="startDate" placeholder="请输入会员开始时间"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="endDate" class="col-sm-1 required">会员结束时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="endDate" cssClass="form-control" id="endDate" placeholder="请输入会员结束时间"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="income" class="col-sm-1 required">支付金额</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="income" cssClass="form-control" id="income" placeholder="请输入支付金额"/>
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
		location.href = "${ctx}/member/levelup/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	memberId: "required",
memberLevelId: "required",
startDate: "required",
endDate: "required",
income: "required",
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>