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
			<form:form id="saveForm" modelAttribute="demand" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="title" class="col-sm-2 required">标题</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="title" cssClass="form-control" id="title" placeholder="请输入标题"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="number" class="col-sm-2 required">数量</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="number" cssClass="form-control" id="number" placeholder="请输入数量"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="adcode" class="col-sm-2">区域</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="adcode" cssClass="form-control" id="adcode" placeholder="请输入区域"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="bidNumber" class="col-sm-2">竞方数量</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="bidNumber" cssClass="form-control" id="bidNumber" placeholder="请输入竞方数量"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="startDate" class="col-sm-2">开始时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="startDate" cssClass="form-control" id="startDate" placeholder="请输入开始时间"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="endDate" class="col-sm-2">结束时间</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="endDate" cssClass="form-control" id="endDate" placeholder="请输入结束时间"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="memberId" class="col-sm-2 required">发布人</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="memberId" cssClass="form-control" id="memberId" placeholder="请输入发布人"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="classId" class="col-sm-2 required">需求分类</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="classId" cssClass="form-control" id="classId" placeholder="请输入需求分类"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="progress" class="col-sm-2">进度</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="progress" cssClass="form-control" id="progress" placeholder="请输入进度"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="description" class="col-sm-2">描述</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="description" cssClass="form-control" id="description" placeholder="请输入描述"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="machined" class="col-sm-2">材料</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="machined" cssClass="form-control" id="machined" placeholder="请输入材料"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="term" class="col-sm-2">交期天数</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="term" cssClass="form-control" id="term" placeholder="请输入交期天数"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="payment" class="col-sm-2">付款描述</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="payment" cssClass="form-control" id="payment" placeholder="请输入付款描述"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="brand" class="col-sm-2">品牌</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="brand" cssClass="form-control" id="brand" placeholder="请输入品牌"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="kinds" class="col-sm-2">型号</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="kinds" cssClass="form-control" id="kinds" placeholder="请输入型号"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="life" class="col-sm-2">年限</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="life" cssClass="form-control" id="life" placeholder="请输入年限"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="type" class="col-sm-2"></label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="type" cssClass="form-control" id="type" placeholder="请输入"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="budget" class="col-sm-2"></label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="budget" cssClass="form-control" id="budget" placeholder="请输入"/>
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
		location.href = "${ctx}/demand/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	title: "required",
number: "required",
memberId: "required",
classId: "required",
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>