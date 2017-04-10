<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
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
			<li><a href="#">系统管理</a></li>
			<li class="active">编辑</li>
		</ol>
		<div class="page">
			<form:form id="saveForm" modelAttribute="systemRole" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label for="name" class="col-sm-1 required">名称</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="name" cssClass="form-control" id="name" placeholder="请输入名称"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="code" class="col-sm-1 required">英文编码</label>
			    <div class="col-md-4 col-sm-6">
				  <form:input path="code" cssClass="form-control" id="code" placeholder="请输入英文编码"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="status" class="col-sm-1 required">状态</label>
			    <div class="col-md-4 col-sm-6">
			      <form:select path="status" cssClass="form-control" id="status">
			      	<form:option value="1">启用</form:option>
			      	<form:option value="0">停用</form:option>
			      </form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="description" class="col-sm-1">描述</label>
			    <div class="col-md-4 col-sm-6">
			      <form:textarea path="description" cssClass="form-control" id="description" placeholder="请输入描述" rows="5"/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="description" class="col-sm-1">权限</label>
			    <div class="col-md-4 col-sm-6">
				<ul class="tree tree-lines" data-ride="tree" data-initial-state="expand">
					<li>
						<span style="height:24px;line-height:24px; vertical-align:middle;"><input style="margin: 0;" type="checkbox">系统管理</span>
						<ul>
						<li>
						<input style="margin: 0;" type="checkbox">权限管理
						</li>
						<li>
						<input style="margin: 0;" type="checkbox">角色管理
						</li>
						<li>
						<input style="margin: 0;" type="checkbox">用户管理
						</li>
						</ul>
					</li>
				</ul>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-1 col-sm-1">
			      <button type="button" class="btn btn-default back">返回</button>
			    </div>
			    <div class="col-sm-1">
			      <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			  </div>
			</form:form>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('.back').on('click', function(){
		location.href = "${ctx}/system/role/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	name: {
	    		required: true
	    	}
	    },
	    message: {
	    	
	    }
	});
})
var nodes = [
	{id: 1, parentId: 0, name: '系统管理'},
	{id: 2, parentId: 1, name: '权限管理'},
	{id: 3, parentId: 1, name: '角色管理'},
	{id: 4, parentId: 1, name: '用户管理'},
	{id: 5, parentId: 3, name: '用户管理'}
];
var transformTozTreeFormat = function (sNodes) {
    var i, l,
        key = 'id',
        parentKey = 'parentId',
        childKey = 'childen';
    if (!key || key == "" || !sNodes) return [];

        var r = [];
        var tmpMap = {};
        for (i = 0, l = sNodes.length; i < l; i++) {
            tmpMap[sNodes[i][key]] = sNodes[i];
        }
        //key为id 值为节点的map
        //迭代list
        for (i = 0, l = sNodes.length; i < l; i++) {
        	//item的父id存在在map中 与 item的id不等于item的父id
            if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
            	//初始化子节点列表
                if (!tmpMap[sNodes[i][parentKey]][childKey])
                    tmpMap[sNodes[i][parentKey]][childKey] = [];
            	//map中取item父id的node添加子节点
                tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
            } else {
            	//不存在 直接添加节点
                r.push(sNodes[i]);
            }
        }
        return r;
};
console.log(transformTozTreeFormat(nodes));
</script>
</html>