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
				<div class="panel-body">
					<form class="search-form form-inline">
					  <div class="form-group">
					    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="邮箱">
					  </div>
					  <div class="form-group">
					    <input type="text" class="form-control form-datetime" id="startTime" placeholder="培训开始时间" readonly>-
					    <input type="text" class="form-control form-datetime" id="endTime" placeholder="培训结束时间" readonly>
					  </div>
					  <div class="form-group">
					    <input type="text" class="form-control" id="exampleInputInviteCode3" placeholder="邀请码">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary" onclick="">查询</button>
					  	<button type="reset" class="btn" >重置</button>
					  </div>
					</form>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='add';">新增</button>
			 		<button class="btn deleteBtn" type="button">删除</button>
			 		<button class="btn" type="button">导出</button>
			 	</div>
				<table id="example" class="hover row-border" cellspacing="0" width="100%">
	                <thead>
						<tr>
							<th class="dt-head-center" style="width: 20px;"><input class="check-all" type="checkbox"></th>
							<th>登录名</th>
							<th>用户名</th>
							<th>状态</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
	                <tbody>
						<c:forEach items="${userPage.content}" var="user">
							<tr>
								<td class="dt-body-center"><input type="checkbox" value="${user.id}"></td>
								<td>${user.loginName}</td>
								<td>${user.name}</td>
								<td>${user.status}</td>
								<td>${user.createTime}</td>
								<td>
									<a href="###" data-toggle="tooltip" title="编辑"><i class="icon-edit"></i></a>
									<a href="###" data-toggle="tooltip" title="删除" class="text-danger"><i class="icon-trash"></i></a>
								</td>
							</tr>
						</c:forEach>
	                </tbody>
            	</table>
            	<my:pagination page="${userPage}"/>
			</div>
		</div>
	</div>
</body>
<%@include file="/WEB-INF/include/base-script.jsp" %>
<script type="text/javascript">
var table = $('#example').DataTable();
$('.check-all').on('click', function(){
	var rows = table.rows({'search':'applied'}).nodes();
	$('input[type="checkbox"]', rows).prop('checked', this.checked);
});
$('#example tbody').on('change', 'input[type="checkbox"]', function(){
	if(!this.checked){ 
		var el = $('.check-all').get(0);
		if(el && el.checked && ('indeterminate' in el))
			el.indeterminate = true;
	}
});
var getCheckedVal = function(){
	var chkArr = [];
	table.$('input[type="checkbox"]:checked').each(function(){
		chkArr.push($(this).val());
	});
	return chkArr;
};
//删除
$('.deleteBtn').on('click', function(){
	console.log(getCheckedVal());
});
//提示信息
$('[data-toggle="tooltip"]').tooltip();
//日期插件
$(".form-datetime").datetimepicker(
{
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1,
    format: "yyyy-mm-dd hh:ii"
});

</script>
</html>