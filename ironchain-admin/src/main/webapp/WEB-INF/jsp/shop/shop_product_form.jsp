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
							<div class="spec-content">
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
							</div>
							<a class="btn btn-link new-spec" ><i class="icon icon-plus"></i>新增规格</a>
							<table class="table table-auto table-bordered skuTable">
								<thead>
									<tr>
									<th>颜色</th>
									<th>库存</th>
									<th>价格</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									<input type="hidden" name="" value="">
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
	var id = $('input[name=id]').val();
	if(id > 0){
		$.ajax({
			url: "${ctx}/shop/product/attrbute",
			data: {"id": id},
			type: "GET",
			success: function(data){
				var specs = data['specs'];
				if(specs != null && specs.length > 0){
					var specVals;
					var specSelectVals = data['specVals'];
					var specStr = [];
					for(var i=0, len=specs.length; i < len; i++){
						specVals = specs.specValues;
						var valStr = '';
						for(var j=0, vlen=specVals.length; j < vlen; j++){
							valStr += '<div class="checkbox col-sm-2"><label>'+
							'<input type="checkbox" value="' + specVals[i].id + '" '+$.inArray(specVals[i].id, specSelectVals)?'checked':''+'> '+ specVals[i].value +
							'</label></div>';
						}
						specStr.concat(['<div class="row">',
							'	<div class="col-sm-2">',
							'		<select name="spec" class="form-control" onChange="changeSpecVal(this)" spec-id="'+specs.id+'">',
							'			<option value="">请选择</option>',
										specOption,
							'		</select>',
							'	</div>',
							'	<div class="col-sm-2">',
							'  		<a class="btn btn-link" onclick="delRow(this)">删除</a>',
							'  	</div>',
							'	<div class="specValueList col-sm-12">',
								valStr,
							'</div>',
							'</div>']);				
					}
					$(specStr.join("")).insertBefore('.new-spec');
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
		$('.spec-content').append(['<div class="row">',
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
			'</div>'].join(""));
	});
	//规格值改变事件
	$('.spec-content').on('change', '[name=specValue]', function(){
		alert(1);
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
				'<input type="checkbox" name="specValue" value="' + data[i].id + '" spec-id="'+val+'" spec-item="'+val+':'+ data[i].id+'"> '+ data[i].value +
				'</label></div>';
			}
			_self.attr('spec-id', val).closest('.row').find('.specValueList').html(values);
		}
	});
}
//重绘SKU表格
function resetSkuList(){
	//获取当前已存在的sku表格
	
	
	//获取当前已勾选的规格
	$('input[name=specValue]:checked').each(function(i){
		$(this).attr('spec-item');
		
	});
	
	
}
/**
 * 初始化库存配置行
 */
var initStockTrLen = function(){
	var skuTrArr = new Array($("[name=skuDo]").length);
	$("[name=skuDo]").each(function(y,x){
		var oldSpec = {
			id:$(this).attr("specValueId"),
			price:$(this).find("[name=price]").val(),
			marketPrice:$(this).find("[name=market_price]").val(),
			costPrice:$(this).find("[name=cost_price]").val(),
			sku:$(this).find("[name=sku]").val(),
			huohao:$(this).find("[name=huohao]").val(),
			isopen:$(this).find("[name=isopen]").attr("checked")
		};
		skuTrArr[y] = oldSpec;
	});
	return skuTrArr;
};
var initEachSpec = function(){
	var SpecArray = [];
	//设置参数
	var k = 0;
	// 循环每个规格,判断是否有选择规格
	$("[nctype=spec_group_dl]").each(function(i,val){
		// 规格id
		var spId = $(this).attr("spId");
		// 规格名称
		var spName = $(this).attr("spName");
		// 如果没有选择任何规格隐藏库存配置项，商品库存置为0且允许输入值,商品价格允许输入值
		// 定义规格值数组
		var SpecValueArray = new Array($(this).find(":checked").length);
		// 循环,判断获取每个规格中选中的值
		if($(this).find(":checked").length > 0){
			$(this).find(":checked").each(function(j,specVal){
				//创建规格对象
				var spec = {
					spId:spId,
					spName:spName,
					specValueId:$(this).val(),
					specValueName:$(this).attr("spValueName")
				};
				//将规格值放入数组中
				SpecValueArray[j] = spec;
			});
			//将规格值数组放入规格数组中
			SpecArray[k] = SpecValueArray;
			k++;
		}
	});
	return SpecArray;
};

/**
 * 有规格时执行的函数
 */
var specGroupFun = function(){
	// 选择规格时初始化库存配置(价格、库存、商品货号)已存在的值
	var skuTrArr = initStockTrLen();
	// 移除已存在的库存配置里的内容，重新计算好后再添加上
	$("[nc_type=spec_table]").empty();
	var tag = true;
	// 定义规格数组变量
	var SpecArray = initEachSpec();
	// 有选择的规格进行的操作
	if(tag){
		initGoodsStockValue(skuTrArr, SpecArray);
	}
};

/**
 * 获取规格的tr行数
 * arr为选取的规格数组
 */
var getSkuTr = function(arr){
	//定义变量,用于记规格的组合数
	var a = 1;
	for(var r = 0; r < arr.length - 1; r++){
		//判断遍历的数组为有意义的,即不为空的
		if(arr[r] != undefined){
			a *= arr[r].length;
		}
	}
	//定义新的数组,为sku组合行
	var newArray = new Array(a);
	newArray = arr[0];
	for(var m=1;m<arr.length;m++){
		var arr2 =arr[m];
		newArray = dosku(newArray,arr2);
	}
	return newArray;
};
/**
 * 库存配置
 */
var initGoodsStockValue = function(skuTrArr, SpecArray){
	var goodsPrice = $("[name=goods_store_price]").val();
	var marketPrice = $("[name=goods_market_price]").val();
	var costPrice = $("[name=goods_cost_price]").val();
	if(goodsPrice == ""){
		goodsPrice = 0;
	}
	if(marketPrice == ""){
		marketPrice = 0;
	}
	if(costPrice == ""){
		costPrice = 0;
	}
	// 显示库存配置
	$("[nc_type=spec_dl]").css("display","");
	// 获得控制库存的数组
	var arr = getSkuTr(SpecArray);
	if (arr != undefined && arr.length > 0){
		//获取规格名称
		var newSpName = arr[0].spName;
		//将规格名称拆分为数组
		var spNameArr = newSpName.split(",");
		var title = "";
		//遍历数组,将规格名赋值到规格列表的标题中
		for(var d = 0;d < spNameArr.length;d++){
			title += '<th>' + spNameArr[d] + '</th>';
		}
		//拼接新的规格标题
		title += '<th><span class="red">*</span>价格</th>' +
			'<th><span class="red">*</span>市场价</th>' +
			'<th>成本价</th>' +
			'<th>商品货号</th>' +
			'<th>是否开启</th>';
		$("[nctype=spTitle]").html(title);
		for(var s = 0; s < arr.length; s++){
			var args = arr[s].specValueName;
			var valueNames = args.split(",");
			var str = "";
			for(var l = 0; l < valueNames.length; l++){
				str += '<td>'+valueNames[l]+'</td>';
			}
			//判断是否已经存在tr
			var vaId = arr[s].specValueId;
			var flg = true;
			for(var e = 0; e < skuTrArr.length; e++){
				if(skuTrArr[e].id == vaId){
					flg = false;
					var isOpen = skuTrArr[e].isopen;
					var openTr = "";
					if(isOpen){
						openTr = '<td><input class="text" name="isopen" type="checkbox" checked="checked"/>开启规格</td>';
					}else{
						openTr = '<td><input class="text" name="isopen" type="checkbox"/>开启规格</td>';
					}
					var tr = '<tr name="skuDo" spId=' + arr[s].spId + ' spName="' + arr[s].spName + '" specValueName="' + arr[s].specValueName + '" specValueId="' + arr[s].specValueId + '" value="' + arr[s].specValueId + '">' +
						str+
						'<td><input class="text" name="price" style="width: 50px;" type="text" value="'+skuTrArr[e].price+'"/></td>'+
						'<td><input class="text" name="market_price" style="width: 50px;" type="text" value="'+skuTrArr[e].marketPrice+'"/></td>'+
						'<td><input class="text" name="cost_price" style="width: 50px;" type="text" value="'+skuTrArr[e].costPrice+'"/></td>'+
						'<td><input class="text" name="huohao" type="text" value="'+skuTrArr[e].huohao+'"/></td>'+
						openTr +
						'</tr>';
				}
			}
			if(flg){
				var tr = '<tr name="skuDo" spId=' + arr[s].spId + ' spName="' + arr[s].spName + '" specValueName="' + arr[s].specValueName + '" specValueId="' + arr[s].specValueId + '" value="' + arr[s].specValueId + '">' +
					str+
					'<td><input class="text" name="price" style="width: 50px;"  type="text" value="' + goodsPrice + '"/></td>'+
					'<td><input class="text" name="market_price" style="width: 50px;" type="text" value="'+ marketPrice +'"/></td>'+
					'<td><input class="text" name="cost_price" style="width: 50px;" type="text" value="'+ costPrice +'"/></td>'+
					'<td><input class="text" name="huohao" type="text"/></td>'+
					'<td><input class="text" name="isopen" type="checkbox"/>开启规格</td>'+
					'</tr>';
			}

			$("[nc_type=spec_table]").append(tr);
		}

	} else {
		$("[nctype=spTitle]").html("");
	}

	//判断库存
	if($("[nc_type=spec_table]").length > 0 && arr != undefined){
		//商品库存不能手动修改
		$("[name=goods_storage]").attr("disabled",true);
		//商品价格不能手动修改
		$("[name=goods_store_price]").attr("disabled",true);
		$("[name=goods_market_price]").attr("disabled",true);
		$("[name=goods_cost_price]").attr("disabled",true);
	}else{
		$("[name=goods_storage]").attr("disabled",false);
		$("[name=goods_store_price]").attr("disabled",false);
		$("[name=goods_market_price]").attr("disabled",false);
		$("[name=goods_cost_price]").attr("disabled",false);
	}
};
</script>
</html>