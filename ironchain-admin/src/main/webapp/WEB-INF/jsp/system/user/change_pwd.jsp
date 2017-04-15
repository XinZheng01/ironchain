<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<%@include file="/WEB-INF/include/base-style.jsp" %>
<%@include file="/WEB-INF/include/base-script.jsp" %>
</head>
<body>
	<div>
		<div class="page">
			<form id="saveForm" method="post" class="form-horizontal">
			  <sec:csrfInput/>
			  <div class="form-group">
			    <label class="col-sm-2">登录名</label>
			    <div class="col-sm-8">
			      <p class="form-control-static"><sec:authentication property="name"/></p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="oldPassword" class="col-sm-2 required">旧的密码</label>
			    <div class="col-sm-8">
			      <input type="password" name="oldPassword" class="form-control" id="oldPassword" placeholder="请输入旧的密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="newPassword" class="col-sm-2 required">新的密码</label>
			    <div class="col-sm-8">
			      <input type="password" name="newPassword" class="form-control" id="newPassword" placeholder="请输入新的密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="reNewPassword" class="col-sm-2 required">重复密码</label>
			    <div class="col-sm-8">
			      <input type="password" name="reNewPassword" class="form-control" id="reNewPassword" placeholder="请输入重复密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-1">
			      <button type="button" class="btn btn-default closeBtn">关闭</button>
			    </div>
			    <div class="col-sm-1">
			      <button type="submit" class="btn btn-primary">保存</button>
			    </div>
			  </div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('.closeBtn').on('click', function(){
		$.site.closeAll();
	});
	$('#saveForm').validate({
	    rules: {
	    	oldPassword: "required",
	    	newPassword: {
	    		required: true,
	    		pwd: true	
	    	},
	    	reNewPassword:  {
	    		required: true,
	    		pwd: true,
	    		equalTo: '#newPassword'
	    	}
	    },
	    submitHandler: function(form){
	    	$.ajax({
	    	    type: 'post',
	    	    url: 'change_pwd',
	    	    data: $(form).serialize(),
	    	    success: function(data) {
	    	    	if(data.sc == 200){
	    	    		$.site.closeAll();
	    	    		$.site.success(data.msg);
	    	    	}else
	    	    		$.site.error(data.msg);
	    	    }
	    	});
	    }
	});
})
</script>
</html>