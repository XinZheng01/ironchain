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
				<div class="panel-body">
			 	</div>
				<form id="searchForm" action="${ctx}/system/gen/list" method="get" class="search-form form-inline">
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button"><i class="icon icon-rocket"></i> 生成代码</button>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th>表空间</th>
							<th>表名称</th>
							<th>引擎</th>
							<th>描述</th>
							<th>创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${page.content}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>${item.tableSchema}</td>
								<td>${item.tableName}</td>
								<td>${item.engine}</td>
								<td>${item.tableComment}</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>
								<a href="JavaScript:;" onclick="gen('${item.tableName}')">生成代码</a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	<my:pagination page="${page}"/>
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
	});
	
});
function gen(tableName){
	var form=$("<form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("method","POST");
	form.attr("action","${ctx}/system/gen/zip");
	var input=$("<input>");
	input.attr("type","hidden");
	input.attr("name","tableNames");
	input.attr("value",tableName);
	var c=$("<input>");
	c.attr("type","hidden");
	c.attr("name",csrfParameter);
	c.attr("value",csrfToken);
	$("body").append(form);//将表单放置在web中
	form.append(input);
	form.append(c);
	
	form.submit();//表单提交
}
</script>
</html>