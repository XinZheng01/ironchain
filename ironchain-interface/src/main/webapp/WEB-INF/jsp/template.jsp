<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${information.title}</title>
<meta name="keywords" content="${information.keywords}" />
<meta name="description" content="${information.description}" />
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
<meta name="apple-touch-fullscreen" content="YES" />
<meta name="apple-mobile-web-app-capable" content="yes">
<style type="text/css">
img { height: auto; width:100%;}
#sx_m_main {position:relative;padding-bottom:20px;}
/* 模版 */
.sx_app_template {margin:10px;}
.sx_app_template h2 {font-weight:normal; padding:10px 10px 0px; font-size:110%; }
.sx_app_template h3 {padding:10px 10px 0px; text-align:center;}
.sx_app_template p {padding:0px 10px 5px 10px;}
.sx_app_template_t1 {padding:10px; font-size:120%; margin-top:20px;} 
.sx_app_template_title {font-size:140%; padding:10px; line-height:150%;}
</style>
</head>
<body style="background:#FFFFFF;">
<div id="sx_m_main">
   <div class="sx_app_template" id="template">
   <h3 class="sx_app_template_title">${information.title}</h3>
   <p style="text-align:right;"><fmt:formatDate value="${information.updateTime}" pattern="yyyy-MM-dd"/></p>
   ${information.content}
   </div>
</div>
<script type="text/javascript">
var imgs = document.getElementsByTagName("img");
for (var i = 0; i < imgs.length; i++) {
	imgs[i].style.height = 'auto';
	imgs[i].style.width = '100%';
}

//$(function() {
//	initReady();
//});
//function initReady(){
//	if(typeof(window.slJSBridge) == 'object')
//		window.slJSBridge.share("${requestScope.title}-善行创投", "${requestScope.requestUrl}<%="?"+request.getQueryString()%>", "http://www.myshanxing.com:8888/app/images/icon.png", "${requestScope.description}",['weiXin','qq','sms','sina']);
//}
</script>
</body>
</html>
