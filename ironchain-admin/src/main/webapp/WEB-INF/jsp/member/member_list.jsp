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
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/member/list" method="get" class="search-form form-inline">
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
			 	<sec:authorize url="/member/add">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/member/add';"><i class="icon icon-plus"></i>新增</button>
			 	</sec:authorize>
			 	<sec:authorize url="/member/delete">
					<button class="btn btn-danger" data-del-select type="button"><i class="icon icon-times"></i>删除</button>
				</sec:authorize> 
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
	                	<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th data-sort-column="name">用户名</th>
							<th data-sort-column="headImg">用户头像</th>
							<th data-sort-column="type">类型</th>
							<th data-sort-column="mobilephone">手机号码</th>
							<th data-sort-column="email">邮箱</th>
							<th data-sort-column="password">密码</th>
							<th data-sort-column="idcard">身份证</th>
							<th data-sort-column="companyName">企业名称</th>
							<th data-sort-column="companyLegal">企业法人</th>
							<th data-sort-column="companyLegalPhone">企业法人电话</th>
							<th data-sort-column="companyIdcard">企业法人身份证</th>
							<th data-sort-column="companyTel">企业固定电话</th>
							<th data-sort-column="companyLicenseImg">企业营业执照</th>
							<th data-sort-column="companyAddress">企业地址</th>
							<th data-sort-column="status">状态</th>
							<th data-sort-column="lastLoginTime">最后登录时间</th>
							<th data-sort-column="createTime">创建时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${page.content}" var="item">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${item.id}"></td>
								<td>${item.name}</td>
								<td>${item.headImg}</td>
								<td>${item.type}</td>
								<td>${item.mobilephone}</td>
								<td>${item.email}</td>
								<td>${item.password}</td>
								<td>${item.idcard}</td>
								<td>${item.companyName}</td>
								<td>${item.companyLegal}</td>
								<td>${item.companyLegalPhone}</td>
								<td>${item.companyIdcard}</td>
								<td>${item.companyTel}</td>
								<td>${item.companyLicenseImg}</td>
								<td>${item.companyAddress}</td>
								<td>${item.status}</td>
								<td>${item.lastLoginTime}</td>
								<td>${item.createTime}</td>
								<td>
								<sec:authorize url="/member/edit">
									<a href="${ctx}/member/edit?id=${item.id}">编辑</a> |
								</sec:authorize>
								<sec:authorize url="/member/delete"> 
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