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
				<form id="searchForm" action="${ctx}/system/role/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					  	<!-- <span>名称：</span> -->
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${srch_LIKE_name}" placeholder="名称">
					  </div>
					  <div class="form-group">
					  	<span>状态：</span>
					    <select name="srch_EQ_status_I" class="form-control">
					    	<option value="" ${srch_EQ_status_I eq ""?"selected":""}>全部</option>
					    	<option value="1" ${srch_EQ_status_I eq "1"?"selected":""}>启用</option>
					    	<option value="0" ${srch_EQ_status_I eq "0"?"selected":""}>停用</option>
					    </select>
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/system/role/add';">新增</button>
			 		<button class="btn deleteBtn" type="button">删除</button>
			 	</div>
				<table class="hover row-border table-hover dataTable" cellspacing="0" width="100%">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th class="sort-column name">名称</th>
							<th class="sort-column code">英文名称</th>
							<th>描述</th>
							<th class="sort-column status">状态</th>
							<th class="sort-column createTime">创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${rolePage.content}" var="role">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${role.id}"></td>
								<td>${role.name}</td>
								<td>${role.code}</td>
								<td>${role.description}</td>
								<td>
								<c:if test="${role.status == 1}"><span class="label label-sm label-success">${role.statusStr}</span></c:if>
								<c:if test="${role.status == 0}"><span class="label label-sm label-danger">${role.statusStr}</span></c:if>
								</td>
								<td><fmt:formatDate value="${role.createTime}" pattern="yyyy-MM-dd"/></td>
								<td>
									<a href="${ctx}/system/role/edit?id=${role.id}">编辑</a> | 
									<a href="${ctx}/system/role/delete?id=${role.id}" class="text-danger">删除</a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	<my:pagination page="${rolePage}"/>
            	</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//删除
	$('.deleteBtn').on('click', function(){
		console.log(getCheckedVal('.dataTable'));
	});
});
</script>
</html>