<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${staticUrl}/css/main.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.6.0/css/zui.min.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.6.0/css/zui-theme-green.min.css" rel="stylesheet">
<link href="${staticUrl}/plugins/datatables-1.10.13/media/css/dataTables.zui.css" rel="stylesheet">
<link href="${staticUrl}/plugins/zui-1.6.0/lib/datetimepicker/datetimepicker.css" rel="stylesheet">
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
					    <!--
					    <label for="exampleInputEmail3" style="width: 80px;">邮箱</label> 
					     -->
					    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="邮箱">
					  </div>
					  <div class="form-group">
					  <!--
					    <label for="exampleInputInviteCode3" style="width: 80px;">培训时间</label>
					     -->
					    <input type="text" class="form-control form-datetime" id="startTime" placeholder="培训开始时间" readonly>-
					    <input type="text" class="form-control form-datetime" id="endTime" placeholder="培训结束时间" readonly>
					  </div>
					  <div class="form-group">
					  <!--
					    <label for="exampleInputInviteCode3" style="width: 80px;">邀请码</label>
					     -->
					    <input type="text" class="form-control" id="exampleInputInviteCode3" placeholder="邀请码">
					  </div>
					  <div class="form-group">
					  	<button type="submit" class="btn btn-primary">查询</button>
					  	<button type="submit" class="btn" >重置</button>
					  </div>
					</form>
				</div>
			 	<div class="panel-toolbar">
			 		<button class="btn btn-primary" type="button" onclick="javascript:location.href='add';">新增</button>
			 		<button class="btn deleteBtn" type="button">删除</button>
			 		<button class="btn" type="button">导出</button>
			 	</div>
			 	<!-- stripe hover order-column row-border -->
				<table id="example" class="hover row-border" cellspacing="0" width="100%">
                <thead>
					<tr>
						<th>#</th>
						<th>时间</th>
						<th>房间</th>
						<th>事件处理</th>
						<th>描述</th>
						<th>相关人物</th>
						<th>操作</th>
					</tr>
				</thead>
                <tbody>
					<tr>
						<td>1</td>
						<td>Tiger Nixon</td>
						<td>System Architect</td>
						<td>Edinburgh</td>
						<td>61</td>
						<td>2011/04/25</td>
						<td>$320,800</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Garrett Winters</td>
						<td>Accountant</td>
						<td>Tokyo</td>
						<td>63</td>
						<td>2011/07/25</td>
						<td>$170,750</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Ashton Cox</td>
						<td>Junior Technical Author</td>
						<td>San Francisco</td>
						<td>66</td>
						<td>2009/01/12</td>
						<td>$86,000</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Cedric Kelly</td>
						<td>Senior Javascript Developer</td>
						<td>Edinburgh</td>
						<td>22</td>
						<td>2012/03/29</td>
						<td>$433,060</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Airi Satou</td>
						<td>Accountant</td>
						<td>Tokyo</td>
						<td>33</td>
						<td>2008/11/28</td>
						<td>$162,700</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Brielle Williamson</td>
						<td>Integration Specialist</td>
						<td>New York</td>
						<td>61</td>
						<td>2012/12/02</td>
						<td>$372,000</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Herrod Chandler</td>
						<td>Sales Assistant</td>
						<td>San Francisco</td>
						<td>59</td>
						<td>2012/08/06</td>
						<td>$137,500</td>
					</tr>
					<tr>
						<td>1</td>
						<td>Rhona Davidson</td>
						<td>Integration Specialist</td>
						<td>Tokyo</td>
						<td>55</td>
						<td>2010/10/14</td>
						<td>$327,900</td>
					</tr>
					<tr>
					<td>1</td>
						<td>Colleen Hurst</td>
						<td>Javascript Developer</td>
						<td>San Francisco</td>
						<td>39</td>
						<td>2009/09/15</td>
						<td>$205,500</td>
					</tr>
					<tr>
					<td>1</td>
						<td>Sonya Frost</td>
						<td>Software Engineer</td>
						<td>Edinburgh</td>
						<td>23</td>
						<td>2008/12/13</td>
						<td>$103,600</td>
					</tr>
                </tbody>
            </table>
			</div>
		</div>
	</div>
</body>
<!-- 
<script src="${staticUrl}/plugins/zui-1.6.0/lib/jquery/jquery.js" charset="utf-8"></script>
 -->
<script src="${staticUrl}/plugins/jquery-1.12.4.min.js" charset="utf-8"></script>
<script src="${staticUrl}/plugins/zui-1.6.0/js/zui.min.js"></script>
<script src="${staticUrl}/plugins/datatables-1.10.13/media/js/jquery.dataTables.js"></script>
<script src="${staticUrl}/plugins/datatables-1.10.13/media/js/dataTables.zui.js"></script>
<script src="${staticUrl}/plugins/zui-1.6.0/lib/datetimepicker/datetimepicker.min.js"></script>
<script type="text/javascript">
var table = $('#example').DataTable({
	ajaxUrl: "",
	ajaxParam: null,
	ajaxCallback: function(){
		
	}
});
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