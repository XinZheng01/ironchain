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
				<form id="searchForm" action="${ctx}/information/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					    <input type="text" class="form-control" name="srch_LIKE_title" value="${srch_LIKE_title}" placeholder="标题">
					  </div>
					  <div class="form-group">
					  	<span>类型：</span>
					    <select name="srch_EQ_status_I" class="form-control">
					    	<option value="" ${srch_EQ_type_I eq ""?"selected":""}>全部</option>
					    	<option value="1" ${srch_EQ_type_I eq "1"?"selected":""}>文章</option>
					    	<option value="2" ${srch_EQ_type_I eq "2"?"selected":""}>广告</option>
					    </select>
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn" onclick=""><i class="icon icon-search"></i>查询</button>
					  	<button type="reset" class="btn" ><i class="icon icon-history"></i>重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<sec:authorize url="/information/add">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/information/add';"><i class="icon icon-plus"></i>新增</button>
			 		</sec:authorize>
			 		<sec:authorize url="/information/delete">
			 		<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
			 		</sec:authorize>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th>类型</th>
							<th>标题</th>
							<th>文章来源</th>
							<th>缩略图</th>
							<th>排序值</th>
							<th>网页关键字</th>
							<th>网页描述</th>
							<th>状态</th>
							<th>创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${page.content}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>
								<c:choose>
								<c:when test="${item.type == 1}">${item.typeStr}</c:when>
								<c:when test="${item.type == 2}">${item.typeStr}</c:when>
								</c:choose>
								</td>
								<td>${item.title}</td>
								<td>${item.source}</td>
								<td>
								<c:if test="${not empty item.img}">
								<img src="${item.img}" width="100" height="50">
								</c:if>
								</td>
								<td>${item.orderNum}</td>
								<td>${item.keywords}</td>
								<td>${item.description}</td>
								<td>
								<c:choose>
								<c:when test="${item.status == 0}"><span class="text-danger">${item.statusStr}</span></c:when>
								<c:when test="${item.status == 1}"><span class="text-success">${item.statusStr}</span></c:when>
								</c:choose>
								</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>
									<sec:authorize url="/information/edit">
									<a href="${ctx}/information/edit?id=${item.id}">编辑</a> | 
									</sec:authorize>
									<sec:authorize url="/information/delete">
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
			url: "${ctx}/information/delete",
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