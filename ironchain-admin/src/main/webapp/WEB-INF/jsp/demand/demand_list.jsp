<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>需求</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/demand/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${srch_LIKE_name}" placeholder="名称">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn"><i class="icon icon-search"></i>查询</button>
					  	<button type="reset" class="btn"><i class="icon icon-history"></i>重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 	<sec:authorize url="/demand/add">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/demand/add';"><i class="icon icon-plus"></i>新增</button>
			 	</sec:authorize>
			 	<sec:authorize url="/demand/delete">
					<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
				</sec:authorize> 
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
	                	<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th>标题</th>
							<th>类型</th>
							<th>数量</th>
							<th>区域</th>
							<th>竞方数量</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>发布人</th>
							<th>需求分类</th>
							<th>进度</th>
							<th>创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${page.content}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>${item.title}</td>
								<td>${item.typeStr}</td>
								<td>${item.number}</td>
								<td>${item.gdArea.name}</td>
								<td>${item.bidNumber}</td>
								<td>${item.startDate}</td>
								<td>${item.endDate}</td>
								<td>${item.publisher.name}</td>
								<td>${item.demandClass.name}</td>
								<td>${item.progressStr}</td>
								<td>${item.createTime}</td>
								<td>
								<sec:authorize url="/demand/edit">
									<a href="${ctx}/demand/edit?id=${item.id}"></a>审核 |
								</sec:authorize>
								<sec:authorize url="/demand/delete"> 
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
			url: "${ctx}/demand/delete",
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