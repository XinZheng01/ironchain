<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
<style type="text/css">
.head-img{
	width: 25px;
	height: 25px;
	border-radius: 50%;
	margin-right: 10px;
}
</style>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/member/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${srch_LIKE_name}" placeholder="名称">
					  </div>
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_mobilephone" class="form-control" value="${srch_LIKE_mobilephone}" placeholder="手机号码">
					  </div>
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_idcard" class="form-control" value="${srch_LIKE_idcard}" placeholder="身份证">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 	<sec:authorize url="/member/add">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/member/add';"><i class="icon icon-plus"></i>新增</button>
			 	</sec:authorize>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
	                		<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th data-sort-column="name">用户名</th>
							<th data-sort-column="type">类型</th>
							<th data-sort-column="mobilephone">手机号码</th>
							<th data-sort-column="email">邮箱</th>
							<th data-sort-column="idcard">身份证</th>
							<th data-sort-column="level.name">用户等级</th>
							<th data-sort-column="status">状态</th>
							<th data-sort-column="lastLoginTime">最后登录时间</th>
							<th data-sort-column="createTime">创建时间</th>
							<th width="200">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${page.content}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>
									<c:choose>
									<c:when test="${not empty item.headImg}">
									<img class="head-img" src="${item.headImg}">
									</c:when>
									<c:otherwise>
									<img class="head-img" src="${staticUrl}/images/default_head.png">
									</c:otherwise>
									</c:choose>
									${item.name}
								</td>
								<td>${item.typeStr}</td>
								<td>${item.mobilephone}</td>
								<td>${item.email}</td>
								<td>${item.idcard}</td>
								<td>${item.level.name}</td>
								<td>${item.statusStr}</td>
								<td>${item.lastLoginTime}</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>
								<sec:authorize url="/member/edit">
									<a href="${ctx}/member/edit?id=${item.id}">编辑</a>
								</sec:authorize>
								 | <a href="javascript:;" onclick="del('${item.id}')" class="">修改等级</a>
								 | <a href="javascript:;" onclick="del('${item.id}')" class="">设置位置</a> |
								<sec:authorize url="/member/delete"> 
									<a href="javascript:;" onclick="del('${item.id}')" class="text-danger">锁定</a>
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
			url: "${ctx}/member/delete",
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