<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${tableComment!}</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<div class="panel">
				<form id="searchForm" action="$\{ctx}/${packageName}/list" method="get" class="search-form form-inline">
				<div class="panel-body">
					<%--
					  <div class="form-group">
					    <input type="text" name="srch_LIKE_name" class="form-control" value="$\{srch_LIKE_name}" placeholder="名称">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
					 --%>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='$\{ctx}/${packageName}/add';">新增</button>
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
						<c:forEach items="$\{page.content}" var="item">
							<tr>
								<td>$\{member.head}</td>
								<td>$\{member.name}</td>
								<td>$\{member.mobilephone}</td>
								<td>$\{member.typeStr}</td>
								<td>$\{member.companyName}</td>
								<td>$\{member.companyLegal}</td>
								<td><fmt:formatDate value="$\{member.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<a href="$\{ctx}/${packageName}/edit?id=">编辑</a> | 
									<a href="javascript:;" onclick="delete('$\{item.id}')" class="text-danger">删除</a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	<my:pagination page="$\{page}"/>
            	</form>
           	</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//删除
	$('.deleteBtn').on('click', function(){
	});
});
function delete(id){
	$.site.loading();
	$.ajax({
		url: "$\{ctx}/${packageName}/delete",
		data: {"id": id},
		type: "POST",
		success: function(data){
			$.site.close();
			if(data.sc == 200)
				$.site.success(data.msg);
			else
				$.site.error(data.msg);
		}
	});
}
</script>
</html>