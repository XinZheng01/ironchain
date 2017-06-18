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
<script src="${staticUrl}/plugins/zui-1.6.0/lib/sortable/zui.sortable.min.js"></script>
<style type="text/css">
.page .nav{
	margin-bottom: 20px;
}
.row, .spec-row{
	margin-bottom: 10px;
}
.sku-input{
	width: 70px;
}
</style>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<ul class="nav nav-tabs">
			  <li class="active"><a data-tab href="#tabBase">基本信息</a></li>
			  <li><a data-tab href="#tabParam">商品参数</a></li>
			  <li><a data-tab href="#tabSpec">商品价格库存</a></li>
			  <li><a data-tab href="#tabImage">商品图片</a></li>
			</ul>
			<form:form id="saveForm" modelAttribute="shopProduct" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <input type="hidden" name="paramsJson">
			  <input type="hidden" name="skusJson">
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
				  		<c:forEach items="${shopProduct.params}" var="item">
				  		<div class="row">
						  	<div class="col-sm-5">
						  		<input class="form-control paramName" value="${item.name}" placeholder="请输入参数名" required>
						  	</div>
						  	<div class="col-sm-5">
						  		<input class="form-control paramValue" value="${item.value}" placeholder="请输入参数值" required>
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
							<div class="spec-content">
							</div>
							<a class="btn btn-link new-spec" ><i class="icon icon-plus"></i>新增规格</a>
							<table class="table table-auto table-bordered skuTable">
							</table>
					  	</div>
					</div>
			  	</div>
			  	<div class="tab-pane" id="tabImage">
			  		<div class="form-group">
			  			<label class="col-sm-1 required">商品图片</label>
						<div class="col-sm-8">
							<!-- 
							<div id="" class="row" data-sortable>
					  			<div class="col-sm-2"><img src="/static/images/image_blank.png" width="100" height="100"></div>
					  			<div class="col-sm-2"><img src="/static/images/image_blank.png" width="100" height="100"></div>
					  			<div class="col-sm-2"><img src="/static/images/image_blank.png" width="100" height="100"></div>
					  			<div class="col-sm-2"><img src="/static/images/image_blank.png" width="100" height="100"></div>
					  			<div class="col-sm-2"><img src="/static/images/image_blank.png" width="100" height="100"></div>
					  			<div class="col-sm-2"><img src="/static/images/image_blank.png" width="100" height="100"></div>
							</div>
							<div class="help-block">上传的图片可以任意拖拽</div>
							<a class="btn btn-link" data-click-other="File"><i class="icon icon-cloud-upload"></i>上传图片</a>
							 -->
							<my:uploadImg id="productImgs" multiple="true"/>
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
function delSpecRow(btn){
	$(btn).closest('.row').remove();
	//重绘表格
	resetSkuList();
}
$(function(){
	var id = $('input[name=id]').val();
	if(id > 0){
		$.ajax({
			url: "${ctx}/shop/product/attrbute",
			data: {"id": id},
			type: "GET",
			success: function(data){
				//规格
				var specs = data['specs'];
				//有选中的规格
				if(specs && specs.length > 0){
					//选中的id列表
					var specSelectVals = data['specVals'];
					var specStr = [];
					for(var i=0, len=specs.length; i < len; i++){
						//规格对应的属性
						var specVals = specs[i].specValues;
						var valStr = '';
						for(var j=0, vlen=specVals.length; j < vlen; j++){
							valStr += getSpecValueChecbox(specVals[j].id, specVals[j].value, specs[i].id, specs[i].name, specSelectVals);
						}
						$('.spec-content').append(getSpecRow(valStr).join(""));
						$('[name=spec] option[value='+specs[i].id+']:eq('+i+')').prop('selected', true).parent().attr('spec-id', specs[i].id);
					}
				}
				
				var skus = data['skus'];
				if(skus && skus.length > 0){
					createSkuTable(skus);
				}
			}
		});
	}
	
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
			content: "required"
	    },
	    message: {
	    	
	    }
	});
	//新增参数
	$('.new-param').on('click', function(){
		$(['<div class="row">',
			'  	<div class="col-sm-5">',
			'  		<input class="form-control paramName" value="${param.name}" placeholder="请输入参数名" required>',
			'  	</div>',
			'  	<div class="col-sm-5">',
			'  		<input class="form-control paramValue" value="${param.value}" placeholder="请输入参数值" required>',
			'  	</div>',
			'  	<div class="col-sm-2">',
			'  		<a class="btn btn-link" onclick="delRow(this)">删除</a>',
			'  	</div>',
			'</div>'].join("")).insertBefore($(this));
	});
	//新增规格
	$('.new-spec').on('click', function(){
		$('.spec-content').append(getSpecRow().join(""));
	});
	//规格值改变事件
	$('.spec-content').on('change', '[name=specValue]', function(){
		resetSkuList();
	});
	
	//$('select[name="spec"]').append(specOption);
	//设置总价格和总库存
	$('.skuTable').on('change', '.skuStock,.skuPrice', function(){
		var stock = 0;
		$('.skuStock').each(function(){
			var v = $(this).val();		
			stock += parseInt(v);
		});
		var price = 0;
		$('.skuPrice').each(function(i){
			var v = $(this).val();
			if(i == 0)
				price = parseFloat(v);
			else				
				price = Math.min(price, parseFloat(v));
		});
		
		$('#price').val(price);
		$('#stock').val(stock);
	})
	
	$('#saveForm').on('submit', function(){
		var paramArr = new Array();
		$('.param-content .row').each(function(i){
			var p = {
				name: $(this).find('.paramName').val(),
				value: $(this).find('.paramValue').val()
			}
			paramArr.push(p);
		});
		$('[name=paramsJson]').val(JSON.stringify(paramArr));
		
		var skuArr = new Array();
		$('.skuTable tbody tr').each(function(i){
			//console.log($(this));
			var s = {
				id: $(this).find('.skuId').val(),
				specItems: $(this).find('.skuSpecItems').val(),
				stock: $(this).find('.skuStock').val(),
				price: $(this).find('.skuPrice').val(),
				code: $(this).find('.skuCode').val()
			}
			//console.log(s);
			skuArr.push(s);
		});
		$('[name=skusJson]').val(JSON.stringify(skuArr));
	});
	
})
//获取规格选择记录
function getSpecRow(valueList){
	return ['<div class="row">',
		'	<div class="col-sm-2">',
		'		<select name="spec" class="form-control" onChange="changeSpecVal(this)" spec-id="">',
		'			<option value="">请选择</option>',
					specOption,
		'		</select>',
		'	</div>',
		'	<div class="col-sm-2">',
		'  		<a class="btn btn-link" onclick="delSpecRow(this)">删除</a>',
		'  	</div>',
		'	<div class="specValueList col-sm-12">',
			valueList != undefined?valueList:'',
		'</div></div>'];
}
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
			var name = _self.find('option:selected').text();
			for(var i = 0, len = data.length; i < len; i++){
				values += getSpecValueChecbox(data[i].id, data[i].value, val, name);
			}
			_self.attr('spec-id', val).closest('.row').find('.specValueList').html(values);
		}
	});
	//重绘表格
	resetSkuList();
}

//获取规格checkbox
function getSpecValueChecbox(valId, valName, specId, specName, selectIds){
	return (['<div class="checkbox col-sm-2"><label>',
	'<input type="checkbox" name="specValue" value="',valId,
	'" spec-id="',specId,
	'" spec-item="',specId,':',valId,
	'" spec-value-name="',valName,
	'" spec-name="',specName,
	'" ', ((selectIds != undefined && $.inArray(valId, selectIds)) > -1?'checked':''),'> ',
	valName,'</label></div>'].join(""));
}

function getSkuTr(){
	//获取当前已存在的sku表格
	var skuTrArr = new Array();
	$('.skuTable tbody tr').each(function(){
		var skuTr = {
			id: $(this).find('.skuId').val(),
			specItems: $(this).find('.skuSpecItems').val(),
			stock: $(this).find('.skuStock').val(),
			price: $(this).find('.skuPrice').val(),
			code: $(this).find('.skuCode').val()
		};
		skuTrArr.push(skuTr);
	});
	return skuTrArr;
}

//重绘SKU表格
function resetSkuList(){
	//获取当前已存在的sku表格
	var skuTrArr = getSkuTr();
	//console.log(skuTrArr);
	createSkuTable(skuTrArr);
}

function createSkuTable(selectSku){
	$('.skuTable').empty();
	//获取当前已勾选的规格
	var specArr = new Array();
	var specNameArr = new Array();
	$('.spec-content .row').each(function(i){
		var $checkVals = $(this).find('input[name=specValue]:checked');
		if($checkVals.length > 0){
			var specValueArr = new Array();
			$checkVals.each(function(j, checkVal){
				var $check = $(checkVal);
				var specValue = {
					id: $check.attr('spec-id'),//规格属性id
					name: $check.attr('spec-name'),
					valueId: $check.val(),//规格属性值id
					valueName: $check.attr('spec-value-name'),//规格属性值名称
					item: $check.attr('spec-item')//规格属性id:规格属性值id
				};
				specValueArr.push(specValue);
			});
			specArr.push(specValueArr);
			specNameArr.push($($checkVals[0]).attr('spec-name'));
		}
	});
	//console.log(specArr);
	//console.log(specNameArr);
	
	if(specNameArr.length > 0){
		//价格 库存 readonly
		$('#price').prop('readonly', true);
		$('#stock').prop('readonly', true);
		
		//生成头部
		var th = '<thead>';
		for(var i=0, len=specNameArr.length; i < len; i++){
			th += '<th>'+specNameArr[i]+'</th>'; 	
		}
		th += '<th>库存</th><th>价格</th><th>商品编号</th></thead>';
		$('.skuTable').append(th);
		
		//求笛卡尔积
		var newArray = specArr[0];//第一个
		for(var i=1, len=specArr.length; i < len; i++){
			var arr2 = specArr[i];
			newArray = doExchange(newArray,arr2);
		}
		//生成行
		var tr = '<tbody>';
		var stock = 0, price = 0;
		
		for(var i=0, l1=newArray.length; i < l1; i++){
			var v = newArray[i].valueName.split(',');
			var skuTr = {};
			for(var n=0, nl=selectSku.length; n < nl; n++){
				if(selectSku[n]['specItems'] == newArray[i].item){
					skuTr = selectSku[n];
					break;
				}
			}
			tr += '<tr>'+
			'	<input type="hidden" class="skuId" value="'+ (skuTr['id'] != undefined?skuTr['id']:'') +'">'+
			'	<input type="hidden" class="skuSpecItems" value="'+newArray[i].item+'">';
			
			for(var j=0, l2=v.length; j < l2; j++){
				tr += '<td><p class="form-control-static">'+v[j]+'</p></td>';	
			}
			if(skuTr['stock'] != undefined)
				stock += parseInt(skuTr['stock']);
			
			if(skuTr['price'] != undefined){
				if(i == 0)
					price = parseFloat(skuTr['price']);
				else
					price = Math.min(price, parseFloat(skuTr['price']));
			}
			
			tr += '	<td><input class="form-control sku-input skuStock" value="'+ (skuTr['stock'] != undefined?skuTr['stock']:'') +'" required></td>'+
			'	<td><input class="form-control sku-input skuPrice" value="'+ (skuTr['price'] != undefined?skuTr['price']:'') +'" required></td>'+
			'	<td><input class="form-control skuCode" value="'+ (skuTr['code'] != undefined?skuTr['code']:'') +'"></td>'+
			'</tr>';
		}
		tr += '</tbody>';
		$('.skuTable').append(tr);
		
		$('#price').val(price);
		$('#stock').val(stock);
	}else{
		$('#price').prop('readonly', false);
		$('#stock').prop('readonly', false);
	}
}

//求笛卡尔积
function doExchange(arr1, arr2){
	var l1 = arr1.length;
	var l2 = arr2.length;
	var newArray = new Array();
	
	for(var i=0; i < l1; i++){
		for(var j=0; j < l2; j++){
			var specValue = {
				id: arr1[i].id + ',' + arr2[j].id,//规格属性id
				name: arr1[i].name + ',' + arr2[j].name,
				valueId: arr1[i].valueId + ',' + arr2[j].valueId,//规格属性值id
				valueName: arr1[i].valueName + ',' + arr2[j].valueName,//规格属性值名称
				item: arr1[i].item + ',' + arr2[j].item//规格属性id:规格属性值id
			};
			newArray.push(specValue);
		}
	}
	return newArray;
}
var pimg = $('#productImgs').sortable();
function callback(imgDiv){
	pimg.append(imgDiv);
}
</script>
</html>