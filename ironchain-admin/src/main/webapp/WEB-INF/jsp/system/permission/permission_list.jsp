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
<link href="${staticUrl}/plugins/jquery-treetable/css/jquery.treetable.css" rel="stylesheet">
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<div class="panel-body">
			 	</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/system/permission/add';"><i class="icon icon-plus"></i>新增</button>
			 		<button class="btn btn-danger deleteBtn" type="button"><i class="icon icon-times"></i>删除</button>
			 	</div>
				<table class="row-border table-hover dataTable treetable">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"></th>
							<th>名称</th>
							<th>英文编码</th>
							<th>类型</th>
							<th>链接</th>
							<th>排序值</th>
							<th>状态</th>
							<th>描述</th>
							<th width="200">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${permissionList}" var="permission">
							<tr data-tt-id="${permission.id}" data-tt-parent-id="${permission.parent.id}">
								<td class="dt-body-center"><input type="checkbox" value="${permission.id}"></td>
								<td><c:if test="${not empty permission.icon}">
						    		<i class="icon ${permission.icon}"></i>
						    	</c:if>${permission.name}</td>
								<td>${permission.code}</td>
								<td>${permission.typeStr}</td>
								<td>${permission.url}</td>
								<td>${permission.orderNum}</td>
								<td>
								<c:choose>
								<c:when test="${permission.status == 1}"><span class="text-success">${permission.statusStr}</span></c:when>
								<c:when test="${permission.status == 0}"><span class="text-danger">${permission.statusStr}</span></c:when>
								</c:choose>
								</td>
								<td>${permission.description}</td>
								<td>
									<a href="${ctx}/system/permission/edit?id=${permission.id}">编辑</a> | 
									<c:if test="${permission.type != 3}">
									<a href="${ctx}/system/permission/add?parentId=${permission.id}">新增子级</a> | 
									</c:if>
									<a href="javascript:;" onclick="del(${permission.id})"class="text-danger deleteThis">删除</a>  
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
			</div>
		</div>
	</div>
</body>
<script src="${staticUrl}/plugins/jquery-treetable/jquery.treetable.js"></script>
<script type="text/javascript">
$(function(){
	//删除
	$('.deleteBtn').on('click', function(){
		console.log(getCheckedVal('.dataTable'));
	});
	
	$('.treetable').treetable({expandable:true, column:1});
});
function del(id){
	top.layer.msg(id);
}
</script>
</html>