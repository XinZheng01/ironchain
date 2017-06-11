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
.page .nav{
	margin-bottom: 20px;
}
.row, .spec-row{
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<ul class="nav nav-tabs">
			  <li class="active"><a data-tab href="#tabBase">基本信息</a></li>
			  <li id="tabCompanyLi"><a data-tab href="#tabParam">商品参数</a></li>
			  <li id="tabCompanyLi"><a data-tab href="#tabSpec">商品价格库存</a></li>
			</ul>
			<form:form id="saveForm" modelAttribute="shopProduct" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="tab-content">
			  	<div class="tab-pane active" id="tabBase">
				  <div class="form-group">
				    <label for="title" class="col-sm-1 required">商品标题</label>
				    <div class="col-md-4 col-sm-6">
					  <form:input path="title" cssClass="form-control" id="title" placeholder="请输入商品标题"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-1">所属分类</label>
				    <div class="col-md-4 col-sm-6">
					  <form:select path="shopClass" cssClass="form-control" id="shopClass">
					  	<form:options items="${shopClassList}" itemLabel="name" itemValue="id"/>
					  </form:select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="code" class="col-sm-1 required">商品编号</label>
				    <div class="col-md-4 col-sm-6">
					  <form:input path="code" cssClass="form-control" id="code" placeholder="请输入商品编号"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="freight" class="col-sm-1 required">商品运费</label>
				    <div class="col-md-4 col-sm-6">
					  <form:input path="freight" cssClass="form-control" id="freight" placeholder="请输入商品运费"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="sortId" class="col-sm-1">排序值</label>
				    <div class="col-md-4 col-sm-6">
					  <form:input path="sortId" cssClass="form-control" id="sortId" placeholder="请输入排序值"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="content" class="col-sm-1 required">商品详情</label>
				    <div class="col-md-4 col-sm-6">
					  <my:kindeditor path="content"></my:kindeditor>
				    </div>
				  </div>
			  	</div>
			  	<div class="tab-pane" id="tabParam">
				  	<div class="form-group">
				  	<label class="col-sm-1">商品参数</label>
				  	<div class="col-md-4 col-sm-6 param-content">
				  		<c:forEach items="${shopProduct.params}" var="param">
				  		<div class="row">
						  	<div class="col-sm-5">
						  		<input name="params.name" class="form-control" value="${param.name}" placeholder="请输入参数名">
						  	</div>
						  	<div class="col-sm-5">
						  		<input name="params.value" class="form-control" value="${param.value}" placeholder="请输入参数值">
						  	</div>
						  	<div class="col-sm-2">
						  		<a class="btn btn-link" onclick="delParam(this)">删除</a>
						  	</div>
				  		</div>
				  		</c:forEach>
						<a class="btn btn-link new-param" ><i class="icon icon-plus"></i>新增参数</a>
				  	</div>
				  	</div>
			  	</div>
			  	<div class="tab-pane" id="tabSpec">
					<div class="form-group">
						<label for="price" class="col-sm-1 required">商品价格</label>
						<div class="col-md-4 col-sm-6">
						<form:input path="price" cssClass="form-control" id="price" placeholder="请输入商品价格"/>
						</div>
					</div>
					<div class="form-group">
						<label for="stock" class="col-sm-1 required">商品库存</label>
						<div class="col-md-4 col-sm-6">
					 	<form:input path="stock" cssClass="form-control" id="stock" placeholder="请输入商品库存"/>
					  	</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 required">商品规格</label>
						<div class="col-sm-10">
							<div class="row">
								<div class="col-sm-2">
									<select name="" class="form-control">
										<option>全部</option>
									</select>
								</div>
								<div class="col-sm-12">
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 1
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 2
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 1
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 2
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 1
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 2
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 1
									</label>
									<label class="checkbox-inline">
									  <input type="checkbox"> 多选框 2
									</label>
								</div>
							</div>
							<a class="btn btn-link new-spec" ><i class="icon icon-plus"></i>新增规格</a>
					  	</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 required">商品库存</label>
						<div class="col-md-4 col-sm-6">
					  	</div>
					</div>
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
function delParam(btn){
	$(btn).closest('.row').remove();
}
$(function(){
	$('.back').on('click', function(){
		location.href = "${ctx}/shop/product/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	title: "required",
			code: "required",
			price: "required",
			stock: "required",
			freight: "required",
			content: "required",
			"params.name": "required",
			"params.value": "required"
	    },
	    message: {
	    	
	    }
	});
	$('.new-param').on('click', function(){
		$(['<div class="row">',
			'  	<div class="col-sm-5">',
			'  		<input name="params.name" class="form-control" value="${param.name}" placeholder="请输入参数名">',
			'  	</div>',
			'  	<div class="col-sm-5">',
			'  		<input name="params.value" class="form-control" value="${param.value}" placeholder="请输入参数值">',
			'  	</div>',
			'  	<div class="col-sm-2">',
			'  		<a class="btn btn-link" onclick="delParam(this)">删除</a>',
			'  	</div>',
			'</div>'].join("")).insertBefore($(this));
	});
	$('.new-spec').on('click', function(){
		$(['<div class="row">',
			'  	<div class="col-sm-5">',
			'  		<input name="params.name" class="form-control" value="${param.name}" placeholder="请输入参数名">',
			'  	</div>',
			'  	<div class="col-sm-5">',
			'  		<input name="params.value" class="form-control" value="${param.value}" placeholder="请输入参数值">',
			'  	</div>',
			'  	<div class="col-sm-2">',
			'  		<a class="btn btn-link" onclick="delParam(this)">删除</a>',
			'  	</div>',
			'</div>'].join("")).insertBefore($(this));
	});
})
</script>
</html>