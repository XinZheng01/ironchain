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
			<form:form id="saveForm" modelAttribute="memberLevel" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="name" class="col-sm-2 required">名称</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="name" cssClass="form-control" id="name" placeholder="请输入名称"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="price" class="col-sm-2 required">价格</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="price" cssClass="form-control" id="price" placeholder="请输入价格"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="resetCount" class="col-sm-2 required">需求重置次数</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="resetCount" cssClass="form-control" id="resetCount" placeholder="请输入需求重置次数"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="status" class="col-sm-2">状态</label>
			    <div class="col-md-4 col-sm-6">
			      <form:select path="status" cssClass="form-control" id="status">
			      	<form:option value="1">启用</form:option>
			      	<form:option value="0">停用</form:option>
			      </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="description" class="col-sm-2">描述</label>
			    <div class="col-md-4 col-sm-6">
				  <form:textarea path="description" cssClass="form-control" id="description" placeholder="请输入描述" rows="5"/>
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
		location.href = "${ctx}/member/level/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	name: "required",
			price: {
				required: true,
				number: true
			},
			resetCount: {
				required: true,
				digits: true
			}
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>