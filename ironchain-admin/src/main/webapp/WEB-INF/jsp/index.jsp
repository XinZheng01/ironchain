<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>系统后台</title>
  <%@include file="/WEB-INF/include/meta.jsp" %>
  <link rel="stylesheet" href="${staticUrl}/css/icon.min.css"  media="all">
  <link rel="stylesheet" href="${staticUrl}/plugins/layui/css/layui.css"  media="all">
  <link rel="stylesheet" href="${staticUrl}/plugins/layui/css/global.css" media="all">
  <style type="text/css">
  .layui-nav-tree .layui-nav-child a{
  	padding: 0 20px 0 40px!important;
  }
  .header-demo .site-system a{
  	color: #333!important;
  }
  .header-demo .site-system .layui-this a{
  	padding: 0 20px;
  }
  </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header header header-demo">
  <div class="layui-main">
    <a class="logo" href="/">
      <img src="${staticUrl}/images/logo.png" alt="ironchain">
    </a>
    <!--
    <ul class="layui-nav site-system" style="left: 185px;">
      <li class="layui-nav-item layui-this" style="margin: 0;">
        <a href="/doc/">OA系统</a>
      </li>
      <li class="layui-nav-item" style="margin: 0;">
        <a href="/demo/">CMS系统</a>
      </li>
    </ul>
     -->
    <ul class="layui-nav">
      <li class="layui-nav-item">
        <a><i class="icon icon-user"></i>  <sec:authentication property="name"/></a>
        <dl class="layui-nav-child layui-anim layui-anim-upbit">
          <dd><a href="javascript:;" onclick="changePassword()"><i class="glyphicon glyphicon-lock"></i>修改密码</a></dd>
          <dd><a href="javascript:;" onclick="logout()"><i class="glyphicon glyphicon-off"></i>退出</a></dd>
        </dl>
      </li>
    </ul>
  </div>
</div>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
	<ul class="layui-nav layui-nav-tree site-demo-nav">
	  <c:forEach items="${sessionScope.userMenu}" var="p_menu">
	  	<c:if test="${p_menu.parent.id == null}">
  		<li class="layui-nav-item layui-nav-itemed">
    		<a href="javascript:;">${p_menu.name}</a>
    		<dl class="layui-nav-child">
    		<c:forEach items="${sessionScope.userMenu}" var="c_menu">
    			<c:if test="${c_menu.parent.id == p_menu.id}">
    			<dd><a href="<c:choose><c:when test="${not empty c_menu.url}">${ctx}${c_menu.url}</c:when><c:otherwise>javascript:;</c:otherwise></c:choose>" target="rightIframe"><i class="icon ${c_menu.icon}"></i><cite>${c_menu.name}</cite></a></dd>
    			</c:if>
    		</c:forEach>
		    </dl>
    	</li>
	  	</c:if>
	  </c:forEach>
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
<script src="${staticUrl}/js/site.js" charset="utf-8"></script>
<script>
layui.config({base: '${staticUrl}/plugins/layui/lay/modules/'}).use('global');
var layer;
layui.use(['layer'], function(){
	layer = layui.layer;
});
//iframe
var rightContent = window.frames['rightIframe'];

function changePassword(){
	$.site.iframe('${ctx}/system/user/change_pwd', '修改密码', ['800px', '390px']);
}
//退出登录
function logout(){
	$.ajax({
		url : "${ctx}/system/user/logout?${_csrf.parameterName}=${_csrf.token}",
		type : 'POST',
		success : function() {
			location.reload();
		}
	});
}
</script>
</div>
</body>
</html>