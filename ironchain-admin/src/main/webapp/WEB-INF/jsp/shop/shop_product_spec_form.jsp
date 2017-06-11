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
<style type="text/css">
.row{
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<form:form id="saveForm" modelAttribute="shopProductSpec" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="name" class="col-sm-1 required">规格名</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="name" cssClass="form-control" id="name" placeholder="请输入规格名"/>
			    </div>
			  </div>
			  <div class="form-group">
			  	<label class="col-sm-1 required">规格值</label>
			  	<div class="col-md-4 col-sm-6 spec-value-content">
			  		<c:forEach items="${shopProductSpec.specValues}" var="specValue">
			  		<div class="row">
					  	<div class="col-sm-10 spec-value">
					  		<input name="value" class="form-control" value="${specValue.value}" placeholder="请输入规格值">
					  	</div>
					  	<div class="col-sm-2">
					  		<a class="btn btn-link" onclick="delSpecValue(this)">删除</a>
					  	</div>
			  		</div>
			  		</c:forEach>
			  		<c:if test="${fn:length(shopProductSpec.specValues) == 0}">
			  		<div class="row">
					  	<div class="col-sm-10 spec-value">
					  		<input name="value" class="form-control" placeholder="请输入规格值">
					  	</div>
					  	<div class="col-sm-2">
					  		<a class="btn btn-link" onclick="delSpecValue(this)">删除</a>
					  	</div>
			  		</div>
			  		</c:if>
					<a class="btn btn-link new-spec-value" ><i class="icon icon-plus"></i>新增规格值</a>
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
function delSpecValue(btn){
	if($('.spec-value-content .row').size() > 1){
		$(btn).closest('.row').remove();
	}else{
		$.site.alert("至少存在一个规格值");
	}
}
$(function(){
	$('.back').on('click', function(){
		location.href = "${ctx}/shop/product/spec/list";
	});
	$('#saveForm').validate({
	    rules: {
			name: "required",
			value: "required"
	    },
	    message: {
	    	
	    }
	});
	$('.new-spec-value').on('click', function(){
		$(['<div class="row">',
			'	<div class="col-sm-10 spec-value">',
			'		<input name="value" class="form-control" placeholder="请输入规格值">',
			'	</div>',
			'	<div class="col-sm-2">',
			'		<a class="btn btn-link" onclick="delSpecValue(this)">删除</a>',
			'	</div>',
			'</div>'].join("")).insertBefore($(this));
	});
})
</script>
</html>