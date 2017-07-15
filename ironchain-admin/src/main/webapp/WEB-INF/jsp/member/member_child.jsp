<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员子账户管理</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<div class="page">
			<div class="panel">
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/member/level/add';"><i class="icon icon-plus"></i>新增</button>
					<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
	                	<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th>用户名</th>
							<th>价格</th>
							<th>需求重置次数</th>
							<th>状态</th>
							<th>创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${childs}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>${item.name}</td>
								<td>${item.price}</td>
								<td>${item.resetCount}</td>
								<td>${item.statusStr}</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>
								<sec:authorize url="/member/level/edit">
									<a href="${ctx}/member/level/edit?id=${item.id}">编辑</a> |
								</sec:authorize>
								<sec:authorize url="/member/level/delete"> 
									<a href="javascript:;" onclick="del('${item.id}')" class="text-danger">删除</a>
								</sec:authorize>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
           	</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function del(id){
	$.site.confirm("确定要删除选中的记录？", function(){
		$.site.loading();
		$.ajax({
			url: "${ctx}/member/level/delete",
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