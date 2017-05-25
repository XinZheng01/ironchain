<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/banner/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					    <input type="text" class="form-control" name="srch_LIKE_title" value="${srch_LIKE_title}" placeholder="标题">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn" onclick=""><i class="icon icon-search"></i>查询</button>
					  	<button type="reset" class="btn" ><i class="icon icon-history"></i>重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/banner/add';"><i class="icon icon-plus"></i>新增</button>
			 		<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
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
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>${item.title}</td>
								<td><img alt="" src="${item.picturePath}" width="158" height="58"></td>
								<td>${item.url}</td>
								<td>${item.typeStr}</td>
								<td>${item.sortId}</td>
								<td><fmt:formatDate value="${item.showTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td><fmt:formatDate value="${item.unShowTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>
									<a href="${ctx}/banner/edit?id=${item.id}">编辑</a> | 
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