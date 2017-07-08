<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地图位置选择</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div class="page">
		<form class="form-horizontal" style="margin-top: 10px;">
		<div class="form-group">
		    <label for="name" class="col-sm-2">详细地址</label>
		    <div class="col-sm-3">
		    	<select id="province" class="form-control">
					<option value="">请选择</option>
				</select>
		    </div>
		    <div class="col-sm-3">
				<select id="city" class="form-control">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="col-sm-3">
				<select id="district" class="form-control">
					<option value="">请选择</option>
				</select>
			</div>
	  	</div>
	  	<div class="form-group">
			<label class="col-sm-2 control-label">街道地址</label>
			<div class="col-sm-6">
				<div class="input-group">
					<input id="address" type="text" class="form-control">
					<span class="input-group-btn" id="address_btn">
                        <button type="button" class="btn btn-primary">搜索</button>
                    </span>
				</div>
				<span class="help-block m-b-none">请用鼠标选取地址，在输入框中可以模糊搜索</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">经纬度</label>
			<div class="col-sm-6">
				<input type="text" id="latlng" class="form-control" disabled>
			</div>
			<div class="col-sm-4">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<div id="container" style="width:500px; height:290px"></div>
			</div>
		</div>
		</form>
	</div>
</body>
<script src="//webapi.amap.com/maps?v=1.3&key=b863f25f116ce046e44c2c98ac17cb30&callback=init"></script>
<script src="${staticUrl}/js/area.js"></script>
<script>
    function init(){
        //var map = new AMap.Map('container');
        var auto, placeSearch;
        AMap.plugin(["AMap.Geocoder"], function() {
	        var geocoder = new AMap.Geocoder({
	            city: "010", //城市，默认：“全国”
	            radius: 500 //范围，默认：500
	        });
	        //地理编码,返回地理编码结果
	        geocoder.getLocation("北京市海淀区苏州街", function(status, result) {
	            if (status === 'complete' && result.info === 'OK') {
	                geocoder_CallBack(result);
	            }
	        });
        });
        /* map.plugin(["AMap.ToolBar","AMap.Autocomplete","AMap.PlaceSearch"], function() {
            //地图工具条
        	map.addControl(new AMap.ToolBar());
            //自动提示
            //auto = new AMap.Autocomplete({input: "address"});
            //搜索
            placeSearch = new AMap.PlaceSearch({
                city:'深圳',
                //type:'商务住宅|政府机构及社会团体|地名地址信息|公司企业|道路附属设施|公共设施',
                map:map
       		});
            
          	//注册监听，当选中某条记录时会触发
            //AMap.event.addListener(auto, "select", function(e) {
            	//console.log(placeSearch);
            	//placeSearch.search(e.poi.name);
            //	console.log(e);
            //	if (e.poi && e.poi.location) {
            //        map.setZoom(15);
            //        map.setCenter(e.poi.location);
            //    }
            //});
        }); */
        
      	//为地图注册click事件获取鼠标点击出的经纬度坐标
        var clickEventListener = map.on('click', function (e) {
            $("#latlng").val(e.lnglat.getLng() + ',' + e.lnglat.getLat());
        });
      	
      	$('#address_btn .btn').on('click', function(){
      		placeSearch.search($(this).val(), function(status, result){
      			console.log(status);
      			console.log(result);
      		});
      	});
      	initComplexArea('province', 'city', 'district');
      	$('#province,#city,#district').on('change', function(){
      		if($(this).val() != ''){
				map.setCity($(this).val());      			
      		}
      			//placeSearch.setCity($(this).val());
      	});
      	
        //var clickEventListener = map.on('mousemove', function (e) {
        //    $("#latlng").text(e.lnglat.getLng() + ',' + e.lnglat.getLat());
        //});
    }
    function initComplexArea(p, c, d){
    	var pSel = $('#'+p);
    	var cSel = $('#'+c);
    	var dSel = $('#'+d);
    	//初始化省数据
    	appendOption(areajson.p, pSel);
    	pSel.on('change', function(){
    		cSel.html('<option value="">请选择</option>');
    		dSel.html('<option value="">请选择</option>');
    		var v = $(this).val();
    		if(v != ''){
    			appendOption(areajson.c[v], cSel);
    		}
    	});
    	cSel.on('change', function(){
    		dSel.html('<option value="">请选择</option>');
    		var v = $(this).val();
    		if(v != ''){
    			appendOption(areajson.d[v], dSel);
    		}
    	});
    }
    function appendOption(arr, sel){
    	for(var i=0, len=arr.length; i < len; i++){
    		sel.append('<option value="'+arr[i].adcode+'">'+ arr[i].name +'</option>');
		}
    }
</script>
</html>