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
					  	<!-- <span>登录名：</span> -->
					    <input type="text" class="form-control" name="srch_LIKE_loginName" value="${srch_LIKE_loginName}" placeholder="登录名">
					  </div>
					  <div class="form-group">
					  	<!-- <span>用户名：</span> -->
					    <input type="text" class="form-control" name="srch_LIKE_name" value="${srch_LIKE_name}" placeholder="用户名">
					  </div>
					  <div class="form-group">
					  	<!-- <span>创建时间：</span> -->
					    <input type="text" class="form-control form-datetime" id="startTime" name="srch_GTE_createTime_D" value="${srch_GTE_createTime_D}" readonly placeholder="创建开始时间">-
					    <input type="text" class="form-control form-datetime" id="endTime" name="srch_LTE_createTime_D" value="${srch_LTE_createTime_D}" readonly placeholder="创建结束时间">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary" onclick="">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/system/user/add';">新增</button>
			 		<button class="btn deleteBtn" type="button">删除</button>
			 	</div>
				<table id="example" class="hover row-border table-hover dataTable" cellspacing="0" width="100%">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th class="sort-column loginName">登录名</th>
							<th class="sort-column name">用户名</th>
							<th class="sort-column email">邮箱</th>
							<th class="sort-column mobilephone">手机号码</th>
							<th class="sort-column status">状态</th>
							<th class="sort-column createTime">创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${userPage.content}" var="user">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${user.id}"></td>
								<td>${user.loginName}</td>
								<td>${user.name}</td>
								<td>${user.email}</td>
								<td>${user.mobilephone}</td>
								<td>
								<c:if test="${user.status == 0}"><span class="label label-sm label-danger">${user.statusStr}</span></c:if>
								<c:if test="${user.status == 1}"><span class="label label-sm label-success">${user.statusStr}</span></c:if>
								<c:if test="${user.status == 2}"><span class="label label-sm label-warning">${user.statusStr}</span></c:if>
								</td>
								<td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/></td>
								<td>
									<a href="${ctx}/system/user/edit?id=${user.id}">编辑</a> | 
									<a href="${ctx}/system/user/edit?id=${user.id}">锁定</a> | 
									<a href="${ctx}/system/user/delete?id=${user.id}" class="text-danger">删除</a>
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
		//console.log(getCheckedVal('.dataTable'));
		$.msg.iframe('/icon?field=icon');
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