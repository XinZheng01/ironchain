<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
<link href="${staticUrl}/plugins/zui-1.6.0/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<script src="${staticUrl}/plugins/zui-1.6.0/lib/uploader/zui.uploader.min.js"></script>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">目录</a></li>
			<li><a href="#">系统管理</a></li>
			<li class="active">编辑</li>
		</ol>
		<div class="page">
			<div class="alert alert-warning alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
			  <p>您可以点击右上角的 <i class="icon-remove"></i> 来关闭这条消息。</p>
			</div>
			<form id="saveForm" action="/upload" class="form-horizontal" method="post" enctype="multipart/form-data">
			  <div class="form-group">
			    <label for="description" class="col-sm-1">名称</label>
			    <div class="col-md-4 col-sm-6">
			    <input type="text" class="form-control">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="description" class="col-sm-1">文件</label>
			    <div class="col-md-4 col-sm-6">
			    	<div class="uploader">
					  <div class="uploader-message">
					    <div class="content"></div>
					    <button type="button" class="close">×</button>
					  </div>
					  <!-- file-list-lg 大号文件 file-list-grid 网格 -->
					  <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
					  <div class="uploader-actions">
					    <div class="uploader-status pull-right text-muted"></div>
					    <button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
					    <button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始上传</button>
					  </div>
					</div>
					<img alt="" src="">
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
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('.back').on('click', function(){
		location.href = "${ctx}/system/permission/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	name: {
	    		required: true
	    	}
	    },
	    message: {
	    	
	    }
	});
	$('#icon').on('change', function(){
		$('#iconSpan').html('<i class="icon ' + $(this).val() + '">   '+$(this).val()+'</i>');
	});
	$('.deleteIcon').on('click', function(){
		$('#iconSpan').html('');
		$('#icon').val('');
	});
	$('.uploader').uploader({
	    //autoUpload: true,            // 当选择文件后立即自动进行上传操作
	    multipart_params: {"${_csrf.parameterName}": "${_csrf.token}"},
	    multi_selection: false,//能否上传多个文件
	    //staticFiles: [{id:1, name:'test.jpg'}],//默认显示的文件
	    rename: false, //能否重命名
	    responseHandler: function(responseObject, file){console.log(responseObject);console.log(file);},//服务器响应处理
	    filters: {
	        // 只允许上传图片或图标（.ico）
	        mime_types: [
	            {title: '图片', extensions: 'jpg,gif,png'},
	            {title: '图标', extensions: 'ico'}
	        ],
	        // 最大上传文件为 1MB
	        max_file_size: '1mb',
	        // 不允许上传重复文件
	        prevent_duplicates: true
	    },
	    //removeUploaded: true,//移除已上传文件
	    limitFilesCount:1,
	    deleteActionOnDone: function(file, doRemoveFile){},
	    file_data_name: 'file',//文件域在表单中的名称
	    url: '${ctx}/upload'  // 文件上传提交地址
	});
})

</script>
</html>