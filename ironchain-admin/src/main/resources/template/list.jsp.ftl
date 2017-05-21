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
				<form id="searchForm" action="$\{ctx}/${pathName}/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					<%--
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_name" class="form-control" value="$\{srch_LIKE_name}" placeholder="名称">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
					 --%>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='$\{ctx}/${pathName}/add';">新增</button>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
						<#assign ignore = ["id", "updateTime", "createBy", "updateBy"]>
						<#list columns as column>
							<#if !ignore?seq_contains(column.attrName)>
							<th>${column.columnComment}</th>
							</#if>
						</#list>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="$\{page.content}" var="item">
							<tr>
								<#list columns as column>
								<#if !ignore?seq_contains(column.attrName)>
								<td>$\{item.${column.attrName}}</td>
								</#if>
								</#list>
								<td>
									<a href="$\{ctx}/${packageName}/edit?id=">编辑</a> | 
									<a href="javascript:;" onclick="del('$\{item.id}')" class="text-danger">删除</a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	<my:pagination page="$\{page}"/>
            	</form>
           	</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//删除
	$('.deleteBtn').on('click', function(){
		var ids = getCheckedVal('.dataTable');
		if(ids.length == 0){
			return $.site.alert("请选择一条记录");
		}
		del(ids.join(','));
	});
});
function del(id){
	$.site.confirm("确定要删除选中的记录？", function(){
		$.site.loading();
		$.ajax({
			url: "${ctx}/banner/delete",
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