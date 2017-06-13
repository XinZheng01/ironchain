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
						  		<a class="btn btn-link" onclick="delRow(this)">删除</a>
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
									<select name="spec" class="form-control" onChange="changeSpecVal(this)" spec-id="">
										<option value="">请选择</option>
									</select>
								</div>
								<div class="col-sm-2">
							  		<a class="btn btn-link" onclick="delRow(this)">删除</a>
							  	</div>
								<div class="specValueList col-sm-12"></div>
							</div>
							<a class="btn btn-link new-spec" ><i class="icon icon-plus"></i>新增规格</a>
							<table class="table table-auto table-bordered">
								<thead>
									<tr>
									<th>颜色</th>
									<th>库存</th>
									<th>价格</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									<td><p class="form-control-static">黑色</p></td>
									<td><input class="form-control"></td>
									<td><input class="form-control"></td>
									</tr>
									<tr>
									<td><p class="form-control-static">红色</p></td>
									<td><input class="form-control"></td>
									<td><input class="form-control"></td>
									</tr>
								</tbody>
							</table>
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
//规格option
var specOption = [
	<c:forEach items="${specList}" var="spec" varStatus="status">
	'<option value="${spec.id}">${spec.name}</option>'${status.last?"":","}
	</c:forEach>
	].join("");
//删除行
function delRow(btn){
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
	//新增参数
	$('.new-param').on('click', function(){
		$(['<div class="row">',
			'  	<div class="col-sm-5">',
			'  		<input name="params.name" class="form-control" value="${param.name}" placeholder="请输入参数名">',
			'  	</div>',
			'  	<div class="col-sm-5">',
			'  		<input name="params.value" class="form-control" value="${param.value}" placeholder="请输入参数值">',
			'  	</div>',
			'  	<div class="col-sm-2">',
			'  		<a class="btn btn-link" onclick="delRow(this)">删除</a>',
			'  	</div>',
			'</div>'].join("")).insertBefore($(this));
	});
	//新增规格
	$('.new-spec').on('click', function(){
		$(['<div class="row">',
			'	<div class="col-sm-2">',
			'		<select name="spec" class="form-control" onChange="changeSpecVal(this)" spec-id="">',
			'			<option value="">请选择</option>',
						specOption,
			'		</select>',
			'	</div>',
			'	<div class="col-sm-2">',
			'  		<a class="btn btn-link" onclick="delRow(this)">删除</a>',
			'  	</div>',
			'	<div class="specValueList col-sm-12"></div>',
			'</div>'].join("")).insertBefore($(this));
	});
	
	$('select[name="spec"]').append(specOption);
})
//改变规格
function changeSpecVal(select){
	var _self = $(select);
	var val = _self.find('option:selected').val();
	
	if(val == ""){
		_self.attr('spec-id', '').closest('.row').find('.specValueList').empty();
		return;		
	}
	if($('select[spec-id="'+val+'"]').size() > 0){
		$.site.alert("已存在相同的规格属性");
		_self.find('option[value="'+_self.attr('spec-id')+'"]').prop('selected', true);
		return;
	}
	$.ajax({
		url: "${ctx}/shop/product/spec_value_list",
		data: {"id": val},
		type: "GET",
		success: function(data){
			var values = '';
			for(var i = 0, len = data.length; i < len; i++){
				values += '<div class="checkbox col-sm-2"><label>'+
				'<input type="checkbox" value="' + data[i].id + '"> '+ data[i].value +
				'</label></div>';
			}
			_self.attr('spec-id', val).closest('.row').find('.specValueList').html(values);
		}
	});
}
</script>
</html>