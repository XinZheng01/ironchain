<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/include/meta.jsp" %>
	<%@include file="/WEB-INF/include/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${staticUrl}/plugins/layui/css/layui.css"  media="all">
<link rel="stylesheet" href="${staticUrl}/plugins/layui/css/grid.css"  media="all">
<style type="text/css">
.page-bar {
    border-bottom: 1px solid #e7ecf1;
    background-color: #fff;
    padding: 0 20px 5px 20px;
    margin: 5px 0px 20px 0px;
}
</style>
</head>
<body>
	<div class="layui-show">
			<div class="page-bar">
				<span class="layui-breadcrumb" style="visibility: visible;">
				  <a href="/">首页<span class="layui-box">&gt;</span></a>
				  <a href="/demo/">演示<span class="layui-box">&gt;</span></a>
				  <a><cite>导航元素</cite></a>
				</span>
			</div>
			<div style="margin: 15px;">
			<fieldset class="layui-elem-field site-demo-button">
			  <div style="margin: 20px 30px 10px;">
			  	<form class="layui-form" action="">
			  		<div class=" col-sm-4">
					    <label class="layui-form-label col-sm-4">输入框</label>
					      <input class="col-sm-8" type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
					</div>
					<div class=" col-sm-4">
					    <label class="layui-form-label">输入框</label>
					      <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
					</div>
					<div class=" col-sm-4">
					    <label class="layui-form-label">输入框</label>
					    <div class="layui-input-block">
					      <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
					    </div>
					</div>
			  	</form>			    
			  </div>
			</fieldset>
			<!-- 
			<form class="layui-form" action="" style="margin-right: 200px;">
			  <div class="layui-form-item">
			    <label class="layui-form-label">输入框</label>
			    <div class="layui-input-block">
			      <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <label class="layui-form-label">密码框</label>
			    <div class="layui-input-inline">
			      <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
			    </div>
			    <div class="layui-form-mid layui-word-aux">辅助文字</div>
			  </div>
			  <div class="layui-form-item">
			    <label class="layui-form-label">选择框</label>
			    <div class="layui-input-block">
			      <select name="city" lay-verify="required">
			        <option value=""></option>
			        <option value="0">北京</option>
			        <option value="1">上海</option>
			        <option value="2">广州</option>
			        <option value="3">深圳</option>
			        <option value="4">杭州</option>
			      </select>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <label class="layui-form-label">复选框</label>
			    <div class="layui-input-block">
			      <input type="checkbox" name="like[write]" title="写作">
			      <input type="checkbox" name="like[read]" title="阅读" checked>
			      <input type="checkbox" name="like[dai]" title="发呆">
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <label class="layui-form-label">开关</label>
			    <div class="layui-input-block">
			      <input type="checkbox" name="switch" lay-skin="switch">
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <label class="layui-form-label">单选框</label>
			    <div class="layui-input-block">
			      <input type="radio" name="sex" value="男" title="男">
			      <input type="radio" name="sex" value="女" title="女" checked>
			    </div>
			  </div>
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">文本域</label>
			    <div class="layui-input-block">
			      <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
			    </div>
			  </div>
			  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			  </div>
			</form>
			 -->
			</div>
	</div>
</body>
<script src="${staticUrl}/plugins/layui/layui.js" charset="utf-8"></script>
<script>
//Demo
layui.config({base: '${staticUrl}/plugins/layui/lay/modules/'}).use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
</script>
</html>