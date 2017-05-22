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
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/system/user/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					    <input type="text" class="form-control" name="srch_LIKE_loginName" value="${srch_LIKE_loginName}" placeholder="登录名">
					  </div>
					  <div class="form-group">
					    <input type="text" class="form-control" name="srch_LIKE_name" value="${srch_LIKE_name}" placeholder="用户名">
					  </div>
					  <div class="form-group">
					    <input type="text" class="form-control form-datetime" id="startTime" name="srch_GTE_createTime_D" value="${srch_GTE_createTime_D}" readonly placeholder="创建开始时间" data-end-time="endTime">
					  </div>
					  <div class="form-group">
					    <input type="text" class="form-control form-datetime" id="endTime" name="srch_LTE_createTime_D" value="${srch_LTE_createTime_D}" readonly placeholder="创建结束时间" data-start-time="startTime">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn" onclick=""><i class="icon icon-search"></i>查询</button>
					  	<button type="reset" class="btn" ><i class="icon icon-history"></i>重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/system/user/add';"><i class="icon icon-plus"></i>新增</button>
			 		<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
			 	</div>
				<table class="row-border table-hover dataTable">
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
								<td>
								<c:choose>
								<c:when test="${empty user.email}"><span style="color:#ccc">还没有设置邮箱</span></c:when>
								<c:otherwise>${user.email}</c:otherwise>
								</c:choose>
								</td>
								<td>
								<c:choose>
								<c:when test="${empty user.mobilephone}"><span style="color:#ccc">还没有设置手机</span></c:when>
								<c:otherwise>${user.mobilephone}</c:otherwise>
								</c:choose>
								</td>
								<td>
								<c:choose>
								<c:when test="${user.status == 0}"><span class="text-danger">${user.statusStr}</span></c:when>
								<c:when test="${user.status == 1}"><span class="text-success">${user.statusStr}</span></c:when>
								<c:when test="${user.status == 2}"><span class="text-warning">${user.statusStr}</span></c:when>
								</c:choose>
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
</script>
</html>