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
		<ol class="breadcrumb admin-breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">目录</a></li>
			<li class="active">系统管理</li>
		</ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="${ctx}/member/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					  <div class="form-group">
					  	<!-- <span>名称：</span> -->
					    <input type="text" name="srch_LIKE_name" class="form-control" value="${srch_LIKE_name}" placeholder="用户名">
					  </div>
					  <div class="form-group">
					  	<!-- <span>手机号码：</span> -->
					    <input type="text" name="srch_LIKE_mobilephone" class="form-control" value="${srch_LIKE_mobilephone}" placeholder="手机号码">
					  </div>
					  <div class="form-group">
					  	<!-- <span>公司名称：</span> -->
					    <input type="text" name="srch_LIKE_companyName" class="form-control" value="${srch_LIKE_companyName}" placeholder="公司名称">
					  </div>
					  <div class="form-group">
					  	<!-- <span>企业法人：</span> -->
					    <input type="text" name="srch_LIKE_companyLegal" class="form-control" value="${srch_LIKE_companyLegal}" placeholder="企业法人">
					  </div>
					  <div class="form-group">
					  	<span>用户类型：</span>
					    <select name="srch_EQ_type_I" class="form-control">
					    	<option value="" ${srch_EQ_type_I eq ""?"selected":""}>全部</option>
					    	<option value="1" ${srch_EQ_type_I eq "1"?"selected":""}>个人</option>
					    	<option value="2" ${srch_EQ_type_I eq "2"?"selected":""}>企业</option>
					    </select>
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='${ctx}/system/role/add';">新增</button>
			 	</div>
				<table class="row-border table-hover dataTable">
	                <thead>
						<tr>
							<th>头像</th>
							<th>用户名</th>
							<th>手机号码</th>
							<th>用户类型</th>
							<th>公司名称</th>
							<th>企业法人</th>
							<th>最后登录时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${memberPage.content}" var="member">
							<tr>
								<td>${member.head}</td>
								<td>${member.name}</td>
								<td>${member.mobilephone}</td>
								<td>${member.typeStr}</td>
								<td>${member.companyName}</td>
								<td>${member.companyLegal}</td>
								<td><fmt:formatDate value="${member.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<a href="${ctx}/system/role/edit?id=">编辑</a> | 
									<a href="${ctx}/system/role/delete?id=" class="text-danger">锁定</a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	</div>
            	<my:pagination page="${memberPage}"/>
            	</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//删除
	$('.deleteBtn').on('click', function(){
		console.log(getCheckedVal('.dataTable'));
	});
});
</script>
</html>