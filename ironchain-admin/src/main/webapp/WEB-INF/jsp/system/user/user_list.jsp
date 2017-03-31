<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
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
				<form id="searchForm" action="${ctx}/system/user/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					  	<span>登录名：</span>
					    <input type="text" class="form-control" name="srch_LIKE_loginName" value="${srch_LIKE_loginName}">
					  </div>
					  <div class="form-group">
					  	<span>用户名：</span>
					    <input type="text" class="form-control" name="srch_LIKE_name" value="${srch_LIKE_name}">
					  </div>
					  <div class="form-group">
					  	<span>创建时间：</span>
					    <input type="text" class="form-control form-datetime" id="startTime" name="srch_GTE_createTime_D" value="${srch_GTE_createTime_D}" readonly>-
					    <input type="text" class="form-control form-datetime" id="endTime" name="srch_LTE_createTime_D" value="${srch_LTE_createTime_D}" readonly>
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary" onclick="">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='create';">新增</button>
			 		<button class="btn deleteBtn" type="button">删除</button>
			 		<button class="btn" type="button">导出</button>
			 	</div>
				<table id="example" class="hover row-border table-hover dataTable" cellspacing="0" width="100%">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th class="sort-column loginName">登录名</th>
							<th class="sort-column name">用户名</th>
							<th class="sort-column status">状态</th>
							<th class="sort-column createTime">创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${userPage.content}" var="user">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${user.id}"></td>
								<td>${user.loginName}</td>
								<td>${user.name}</td>
								<td>${user.status}</td>
								<td>${user.createTime}</td>
								<td>
									<a href="update/${user.id}" data-toggle="tooltip" title="编辑"><i class="icon-edit"></i></a>
									<a href="delete/${user.id}" data-toggle="tooltip" title="删除" class="text-danger"><i class="icon-trash"></i></a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	<my:pagination page="${userPage}"/>
            	</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//$("#example").DataTable();
	//删除
	$('.deleteBtn').on('click', function(){
		console.log(getCheckedVal('.dataTable'));
	});
	//日期插件
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
});
</script>
</html>