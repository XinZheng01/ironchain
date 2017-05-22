<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/shop/banner/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					<%--
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${srch_LIKE_name}" placeholder="名称">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
					 --%>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/shop/banner/add';">新增</button>
					<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
							<th data-sort-column="title">标题</th>
							<th data-sort-column="picturePath">图片</th>
							<th data-sort-column="url">链接</th>
							<th data-sort-column="type">类型</th>
							<th data-sort-column="sortId">排序值</th>
							<th data-sort-column="showTime">上架时间</th>
							<th data-sort-column="unShowTime">下架时间</th>
							<th data-sort-column="createTime">创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${page.content}" var="item">
							<tr>
								<td>${item.title}</td>
								<td>${item.picturePath}</td>
								<td>${item.url}</td>
								<td>${item.type}</td>
								<td>${item.sortId}</td>
								<td>${item.showTime}</td>
								<td>${item.unShowTime}</td>
								<td>${item.createTime}</td>
								<td>
									<a href="${ctx}/shop/banner/edit?id=">编辑</a> | 
									<a href="javascript:;" onclick="del('${item.id}')" class="text-danger">删除</a>
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
function del(id){
	$.site.confirm("确定要删除选中的记录？", function(){
		$.site.loading();
		$.ajax({
			url: "${ctx}/shop/banner/delete",
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