<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/shop/product/list" method="get" class="search-form form-inline">
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
			 	<sec:authorize url="/shop/product/add">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/shop/product/add';"><i class="icon icon-plus"></i>新增</button>
			 	</sec:authorize>
			 	<sec:authorize url="/shop/product/delete">
					<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
				</sec:authorize> 
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
	                	<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th data-sort-column="title">商品标题</th>
							<th>所属分类</th>
							<th data-sort-column="code">商品编号</th>
							<th data-sort-column="price">商品价格</th>
							<th data-sort-column="stock">商品库存</th>
							<th data-sort-column="freight">商品运费</th>
							<th data-sort-column="status">状态</th>
							<th data-sort-column="sortId">排序值</th>
							<th data-sort-column="saleDate">上下架时间</th>
							<th data-sort-column="createTime">创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${page.content}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>${item.title}</td>
								<td>${item.shopClass.name}</td>
								<td>${item.code}</td>
								<td>${item.price}</td>
								<td>${item.stock}</td>
								<td>${item.freight}</td>
								<td>${item.status}</td>
								<td>${item.sortId}</td>
								<td>${item.saleDate}</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>
								<sec:authorize url="/shop/product/edit">
									<a href="${ctx}/shop/product/edit?id=${item.id}">编辑</a> |
								</sec:authorize>
								<sec:authorize url="/shop/product/delete"> 
									<a href="javascript:;" onclick="del('${item.id}')" class="text-danger">删除</a>
								</sec:authorize>
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
			url: "${ctx}/shop/product/delete",
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