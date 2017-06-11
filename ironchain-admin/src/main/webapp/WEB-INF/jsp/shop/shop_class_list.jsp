<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分类</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
<link href="${staticUrl}/plugins/jquery-treetable/css/jquery.treetable.css" rel="stylesheet">
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/shop/class/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					<%--
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${srch_LIKE_name}" placeholder="名称">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn"><i class="icon icon-search"></i>查询</button>
					  	<button type="reset" class="btn" ><i class="icon icon-history"></i>重置</button>
					  </div>
					 --%>
				</div>
			 	<div class="panel-toolbar">
			 	<sec:authorize url="/shop/class/add">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/shop/class/add';"><i class="icon icon-plus"></i>新增</button>
			 	</sec:authorize>
			 	<sec:authorize url="/shop/class/delete">
					<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
				</sec:authorize> 
			 	</div>
				<table class="row-border table-hover dataTable treetable">
	                <thead>
						<tr>
	                	<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th>分类名称</th>
							<th>排序值</th>
							<th>创建时间</th>
							<th width="140">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${shopClassList}" var="item">
							<tr data-tt-id="${item.id}" data-tt-parent-id="${item.parent.id}">
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>${item.name}</td>
								<td>${item.sortId}</td>
								<td>${item.createTime}</td>
								<td>
								<sec:authorize url="/shop/class/edit">
									<a href="${ctx}/shop/class/edit?id=${item.id}">编辑</a> |
								</sec:authorize>
									<c:if test="${empty item.parent}">
									<a href="${ctx}/shop/class/add?parentId=${item.id}">新增子级</a> | 
									</c:if>
								<sec:authorize url="/shop/class/delete"> 
									<a href="javascript:;" onclick="del('${item.id}')" class="text-danger">删除</a>
								</sec:authorize>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	</form>
           	</div>
		</div>
	</div>
</body>
<script src="${staticUrl}/plugins/jquery-treetable/jquery.treetable.js"></script>
<script type="text/javascript">
$(function(){
	$('.treetable').treetable({expandable:true, column:1, initialState:'expanded'});
});
function del(id){
	$.site.confirm("确定要删除选中的记录？", function(){
		$.site.loading();
		$.ajax({
			url: "${ctx}/shop/class/delete",
			data: {"ids": id},
			type: "POST",
			success: function(data){
				$.site.close();
				if(data.sc == 200){
					location.reload();
					$.site.success(data.msg);
				}else{
					$.site.error(data.msg);
				}
			},
			error: function(){
				$.site.error("操作失败");
			}
		});
	})
}
</script>
</html>