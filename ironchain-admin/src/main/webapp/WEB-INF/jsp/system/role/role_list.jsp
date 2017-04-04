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
					  	<span>名称：</span>
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${srch_LIKE_name}">
					  </div>
					  <div class="form-group">
					  	<span>状态：</span>
					    <select name="srch_EQ_status_I" class="form-control">
					    	<option>全部</option>
					    	<option value="1" ${srch_EQ_status_I == 1?"selected":""}>启用</option>
					    	<option value="0" ${srch_EQ_status_I == 0?"selected":""}>停用</option>
					    </select>
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/system/role/form';">新增</button>
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
							<th>操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${rolePage.content}" var="role">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${role.id}"></td>
								<td>${role.name}</td>
								<td>${role.code}</td>
								<td>${role.description}</td>
								<td>${role.statusStr}</td>
								<td><fmt:formatDate value="${role.createTime}" pattern="yyyy-MM-dd"/></td>
								<td>
									<a href="${ctx}/system/role/form?id=${role.id}" data-toggle="tooltip" title="编辑"><i class="icon-edit"></i></a>
									<a href="${ctx}/system/role/delete?id=${role.id}" data-toggle="tooltip" title="删除" class="text-danger"><i class="icon-trash"></i></a>
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