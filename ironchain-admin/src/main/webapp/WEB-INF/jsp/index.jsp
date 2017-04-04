<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>系统后台</title>
  <%@include file="/WEB-INF/include/meta.jsp" %>
  <link rel="stylesheet" href="${staticUrl}/plugins/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="${staticUrl}/plugins/layui/css/global.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header header header-demo">
  <div class="layui-main">
    <a class="logo" href="/">
      <img src="${staticUrl}/images/logo.png" alt="layui">
    </a>
    <ul class="layui-nav">
      <li class="layui-nav-item ">
        <a href="/doc/">文档</a>
      </li>
      <li class="layui-nav-item layui-this">
        <a href="/demo/">演示</a>
      </li>
      
      <li class="layui-nav-item">
        <a>退出</a>
      </li>
    </ul>
  </div>
</div>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
	<ul class="layui-nav layui-nav-tree site-demo-nav">
	  
	  <li class="layui-nav-item layui-nav-itemed">
	    <a href="javascript:;">开发工具</a>
	    <dl class="layui-nav-child">
	      <dd>
	        <a href="/demo/">调试预览</a>
	      </dd>
	    </dl>
	  </li>
	  <li class="layui-nav-item layui-nav-itemed">
	    <a href="javascript:;">系统管理</a>
	    <dl class="layui-nav-child">
	      <dd>
	        <a href="/system/user/list" target="rightIframe">用户管理</a>
	      </dd>
	      <dd>
	        <a href="/system/role/list" target="rightIframe">角色管理</a>
	      </dd>
	    </dl>
	  </li>
	  
	  <li class="layui-nav-item layui-nav-itemed">
	    <a href="javascript:;">官方组件</a>
	    <dl class="layui-nav-child">
	      <dd>
	        <a href="/demo/layer.html">
	          <i class="layui-icon" style="top: 3px;">&#xe638;</i><cite>弹出层</cite>
	        </a>
	      </dd>
	       <dd>
	        <a href="/demo/layim.html">
	          <i class="layui-icon" style="position: relative; top: 3px;">&#xe63a;</i>
	          <cite>即时通讯</cite>
	        </a>
	      </dd>
	       <dd>
	        <a href="/demo/laydate.html">
	          <i class="layui-icon" style="top: 1px;">&#xe637;</i><cite>日期时间选择</cite>
	        </a>
	      </dd>
	       <dd>
	        <a href="/demo/laypage.html">
	          <i class="layui-icon">&#xe633;</i><cite>多功能分页</cite>
	        </a>
	      </dd>
	       <dd>
	        <a href="/demo/laytpl.html">
	          <i class="layui-icon">&#xe628;</i><cite>模板引擎</cite>
	        </a>
	      </dd>
	       <dd>
	        <a href="/demo/layedit.html">
	          <i class="layui-icon">&#xe639;</i>
	          <cite>富文本编辑器</cite>
	        </a>
	      </dd>
	       <dd>
	        <a href="/demo/upload.html">
	          <i class="layui-icon">&#xe62f;</i>
	          <cite>文件上传</cite>
	        </a>
	      </dd>
	       <dd>
	        <a href="/demo/tree.html">
	          <i class="layui-icon">&#xe62e;</i>
	          <cite>树形菜单</cite>
	        </a>
	      </dd>
	      <dd>
	        <a href="/demo/util.html">
	          <i class="layui-icon">&#xe631;</i>
	          <cite>工具块</cite>
	        </a>
	      </dd>
	      <dd>
	        <a href="/demo/flow.html">
	          <i class="layui-icon">&#xe636;</i>
	          <cite>流加载</cite>
	        </a>
	      </dd>
	      <dd>
	        <a href="/demo/code.html">
	          <i class="layui-icon" style="top: 1px;">&#xe635;</i>
	          <cite>代码修饰器</cite>
	        </a>
	      </dd>
	    </dl>
	  </li>
	  <li class="layui-nav-item" style="height: 30px; text-align: center"></li>
	  </ul>
 </div>
</div>
<!-- 内容 -->
<div class="layui-body site-demo" style="bottom: 0px;">
	<iframe frameborder="0" src="${ctx}/system/user/list" id="rightIframe" name="rightIframe"></iframe>
</div>
<!-- 底部
<div class="layui-footer footer footer-demo">
  <div class="layui-main">
    <p>2016 &copy; <a href="javascript:;">zheng xin</a> MIT license</p>
  </div>
</div>
 -->
<div class="site-tree-mobile layui-hide">
  <i class="layui-icon">&#xe602;</i>
</div>
<div class="site-mobile-shade"></div>
<script src="${staticUrl}/plugins/jquery-1.12.4.min.js" charset="utf-8"></script>
<script src="${staticUrl}/plugins/layui/layui.js" charset="utf-8"></script>
<script>
layui.config({base: '${staticUrl}/plugins/layui/lay/modules/'}).use('global');
</script>
</div>
</body>
</html>