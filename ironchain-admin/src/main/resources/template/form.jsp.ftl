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
			<form:form id="saveForm" modelAttribute="${modelName}" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <#assign ignore = ["id", "createTime", "updateTime", "createBy", "updateBy"]>
			  <#list columns as column>
			  <#if !ignore?seq_contains(column.attrName)>
			  <div class="form-group">
			    <label for="${column.attrName}" class="col-sm-1${(column.isNullable == "NO")?string(" required", "")}">${column.columnComment}</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="${column.attrName}" cssClass="form-control" id="${column.attrName}" placeholder="请输入${column.columnComment}"/>
			    </div>
			  </div>
			  </#if>
			  </#list>
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
		location.href = "${r"${ctx}"}/${pathName}/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	<#assign rules="">
	    	<#list columns as column>
	    	<#if !ignore?seq_contains(column.attrName) && column.isNullable == "NO">
	    		<#assign rules=rules+column.attrName+": \"required\",\n">
	    	</#if>
	    	</#list>
	    	${rules?substring(0,rules?length-2)}
	    },
	    message: {
	    	
	    }
	});
})
</script>
</html>