<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${tableComment!}</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${r"${ctx}"}/${pathName}/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					<%--
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${r"${srch_LIKE_name}"}" placeholder="名称">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
					 --%>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${r"${ctx}"}/${pathName}/add';">新增</button>
					<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
	                	<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
						<#assign ignore = ["id", "updateTime", "createBy", "updateBy"]>
						<#list columns as column>
							<#if !ignore?seq_contains(column.attrName)>
							<th data-sort-column="${column.attrName}">${column.columnComment}</th>
							</#if>
						</#list>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${r"${page.content}"}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<#list columns as column>
								<#if !ignore?seq_contains(column.attrName)>
								<td>${r"${item."}${column.attrName}${r"}"}</td>
								</#if>
								</#list>
								<td>
									<a href="${r"${ctx}"}/${pathName}/edit?id=${r"${item.id}"}">编辑</a> | 
									<a href="javascript:;" onclick="del('${r"${item.id}"}')" class="text-danger">删除</a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	<my:pagination page="${r"${page}"}"/>
            	</form>
           	</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function del(id){
	$.site.confirm("确定要删除选中的记录？", function(){
		$.site.loading();
		$.ajax({
			url: "${r"${ctx}"}/${pathName}/delete",
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