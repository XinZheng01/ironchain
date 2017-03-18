<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${staticUrl}/css/main.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.5.0/css/zui.min.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.5.0/css/zui-theme.min.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.5.0/lib/datatable/zui.datatable.min.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.5.0/lib/datetimepicker/datetimepicker.css" rel="stylesheet">
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">目录</a></li>
			<li class="active">系统管理</li>
		</ol>
		<div class="page">
			<div class="panel">
				<div class="panel-body">
					<form class="search-form form-inline">
					  <div class="form-group">
					    <!--
					    <label for="exampleInputEmail3" style="width: 80px;">邮箱</label> 
					     -->
					    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="邮箱">
					  </div>
					  <div class="form-group">
					  <!--
					    <label for="exampleInputInviteCode3" style="width: 80px;">培训时间</label>
					     -->
					    <input type="text" class="form-control form-datetime" id="startTime" placeholder="培训开始时间" readonly>-
					    <input type="text" class="form-control form-datetime" id="endTime" placeholder="培训结束时间" readonly>
					  </div>
					  <div class="form-group">
					  <!--
					    <label for="exampleInputInviteCode3" style="width: 80px;">邀请码</label>
					     -->
					    <input type="text" class="form-control" id="exampleInputInviteCode3" placeholder="邀请码">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="submit" class="btn" >重置</button>
					  </div>
					</form>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='add';">新增</button>
			 		<button class="btn" type="button">删除</button>
			 		<button class="btn" type="button">导出</button>
			 	</div>
				<table class="table datatable">
				  <thead>
				    <tr>
				      <!-- 以下两列左侧固定 -->
				      <th>#</th>
				      <th>时间</th>
				      <th>房间</th>
				      <!-- 以下三列中间可滚动 -->
				      <th class="flex-col">事件类型</th> 
				      <th class="flex-col">描述</th>
				      <th class="flex-col">相关人物</th>
				      <!-- 以下列右侧固定 -->
				      <th>评分</th>
				      <th>操作</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				    	<td>1</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>1</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>1</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>1</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>1</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>1</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>1</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>2</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>3</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				    <tr>
				    	<td>4</th>
				    	<td>2017-03-05</td>
				    	<td>201</td>
				    	<td>阿大声道</td>
				    	<td>苏打粉岁的傅诉讼费红烧豆腐是撒地方发送</td>
				    	<td>小明、小黄</td>
				    	<td>8.0</td>
				    	<td>删除</td>
				    </tr>
				  </tbody>
				</table>
				<div class="clearfix table-pager">
					<div class="pull-left" style="line-height: 32px;">第1页/共51页（共502条记录）</div>
					<ul class="pager pull-right">
					  <li class="previous"><a href="your/nice/url">«</a></li>
					  <li><a href="your/nice/url">1</a></li>
					  <li class="active"><a href="your/nice/url">2</a></li>
					  <li><a href="your/nice/url">3</a></li>
					  <li><a href="your/nice/url">4</a></li>
					  <li><a href="your/nice/url">5</a></li>
					  <li class="next"><a href="your/nice/url">»</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${staticUrl}/plugins/jquery-1.12.4.min.js" charset="utf-8"></script>
<script src="${staticUrl}/plugins/zui-1.5.0/js/zui.min.js"></script>
<script src="${staticUrl}/plugins/zui-1.5.0/lib/datatable/zui.datatable.min.js"></script>
<script src="${staticUrl}/plugins/zui-1.5.0/lib/datetimepicker/datetimepicker.min.js"></script>
<script type="text/javascript">
$('table.datatable').datatable({checkable: true, sortable: true});
$(".form-datetime").datetimepicker(
{
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1,
    format: "yyyy-mm-dd hh:ii"
});
</script>
</html>