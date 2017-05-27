<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
<style type="text/css">
	.page .nav{
		margin-bottom: 20px;
	}
</style>
</head>
<body>
	<div>
		<ol class="breadcrumb admin-breadcrumb"></ol>
		<div class="page">
			<ul class="nav nav-tabs">
			  <li class="active"><a data-tab href="#tabPerson">个人信息</a></li>
			  <li id="tabCompanyLi"><a data-tab href="#tabCompany">企业信息</a></li>
			</ul>
			<form:form id="saveForm" modelAttribute="member" action="save" cssClass="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="tab-content">
			  	  <div class="tab-pane active" id="tabPerson">
					  <div class="form-group">
					    <label for="name" class="col-sm-1">用户名</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="name" cssClass="form-control" id="name" placeholder="请输入用户名"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="headImg" class="col-sm-1">用户头像</label>
					    <div class="col-md-4 col-sm-6">
						  <my:uploadImg id="headImg" defaultValue="${member.headImg}" ></my:uploadImg>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="type" class="col-sm-1">类型</label>
					    <div class="col-md-4 col-sm-6">
						  <form:select path="type" cssClass="form-control" id="type">
					      	<form:option value="1">个人</form:option>
					      	<form:option value="2">企业</form:option>
					      </form:select>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="mobilephone" class="col-sm-1 required">手机号码</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="mobilephone" cssClass="form-control" id="mobilephone" placeholder="请输入手机号码"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="email" class="col-sm-1">邮箱</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="email" cssClass="form-control" id="email" placeholder="请输入邮箱"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="password" class="col-sm-1 required">密码</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="password" cssClass="form-control" id="password" placeholder="请输入密码"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="idcard" class="col-sm-1">身份证</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="idcard" cssClass="form-control" id="idcard" placeholder="请输入身份证"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="status" class="col-sm-1">状态</label>
					    <div class="col-md-4 col-sm-6">
					      <form:select path="status" cssClass="form-control" id="status">
					      	<form:option value="1">正常</form:option>
					      	<form:option value="2">审核中</form:option>
					      	<form:option value="3">锁定</form:option>
					      </form:select>
					    </div>
					  </div>
				  </div>
				  <div class="tab-pane" id="tabCompany">
					  <div class="form-group">
					    <label for="companyName" class="col-sm-1">企业名称</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="companyName" cssClass="form-control" id="companyName" placeholder="请输入企业名称"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="companyLegal" class="col-sm-1">企业法人</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="companyLegal" cssClass="form-control" id="companyLegal" placeholder="请输入企业法人"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="companyLegalPhone" class="col-sm-1">企业法人电话</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="companyLegalPhone" cssClass="form-control" id="companyLegalPhone" placeholder="请输入企业法人电话"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="companyIdcard" class="col-sm-1">企业法人身份证</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="companyIdcard" cssClass="form-control" id="companyIdcard" placeholder="请输入企业法人身份证"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="companyTel" class="col-sm-1">企业固定电话</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="companyTel" cssClass="form-control" id="companyTel" placeholder="请输入企业固定电话"/>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="companyLicenseImg" class="col-sm-1">企业营业执照</label>
					    <div class="col-md-4 col-sm-6">
						  <my:uploadImg id="companyLicenseImg" defaultValue="${member.companyLicenseImg}" allowType="jpg|jpeg|png" help="支持上传png/jpeg/jpg格式"></my:uploadImg>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="companyAddress" class="col-sm-1">企业地址</label>
					    <div class="col-md-4 col-sm-6">
						  <form:input path="companyAddress" cssClass="form-control" id="companyAddress" placeholder="请输入企业地址"/>
					    </div>
					  </div>
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
		location.href = "${ctx}/member/list";
	});
	$('#saveForm').validate({
	    rules: {
	    	mobilephone: "required",
			password: "required",
	    },
	    message: {
	    	
	    }
	});
	//企业信息隐藏显示
	if($('#type option:selected').val() == 1)
		$('#tabCompanyLi').hide();
	$('#type').on('change', function(){
		$('#tabCompanyLi').toggle();
	});
})
</script>
</html>