<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
	<%@ include file="/WEB-INF/include/meta.jsp" %>
    <title>铁链后台管理系统</title>
    <link href="${staticUrl}/css/login.css" rel="stylesheet" type="text/css"/>
    <link href="${staticUrl}/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${staticUrl}/plugins/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${staticUrl}/plugins/jquery-validation/dist/jquery.validate.min.js" ></script>
    <script type="text/javascript" src="${staticUrl}/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${staticUrl}/plugins/jquery.cookie/jquery.cookie.js" ></script>
<style>
.login-form .button {
	  width: 93%;
	  padding: 8px 10px;
	  border: 1px solid #f28300;
	  border-top: 1px solid #f6ad00;
	  background-color: #f39800;
	  font-weight: 700;
	  font-size: 14px;
	  color: #fff;
	  text-transform: uppercase;
	  moz-transition: all 0.2s ease-out 0s;
	  -webkit-transition: all 0.2s ease-out 0s;	
	  transition: all 0.2s ease-out 0s;	
	  text-align: center;
	}
	.logo{
		font-size:22px;
		font-weight: 700;
		color: #fff;
	    text-shadow: 0 1px 4px rgba(0,0,0,.2);
	}
</style>

</head>
<body class="login-page" style="">
<div class="login-content">
    <div class="login-panel">
        <p class="logo animate0 bounceIn">
        	铁链后台管理系统
        </p>

        <form id="login_form"  action="${ctx}/system/user/login" method="post">
        	<%--
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        	 --%>
        	<sec:csrfInput/>
            <ol class="login-form">
                <li class="animate1 bounceIn">
                    <input class="acc" type="text" name="username" id="username" placeholder="用户名" autocomplete="off"/>
                </li>
                <li class="animate2 bounceIn">
                    <input class="pass" type="password" name="password" id="password" placeholder="密码" autocomplete="off"/>
                </li>
                <li class="animate3 bounceIn">
                    <input class="code" type="text" name="captcha" id="captcha" placeholder="验证码" autocomplete="off"/>
                    <span class="code-mod">
                        <img class="code-img" id="captcha_img" src="" title="刷新"/>
                        <a href="javascript:void(0);" class="code-change" title="刷新" onclick="changeCaptcha()"></a>
                    </span>
                </li>
                <li class="animate4 bounceIn">
                <label id="submitBtn" class = "button">登录</label>
                <!-- 
                <label><input class="remember" type="checkbox" name="remember_me" value="1"/>记住密码</label>
                 -->
            </li>
            </ol>
        </form>
    </div>
</div>
<script type="text/javascript">
    var imgArr=['bg1.jpg','bg3.jpg','bg4.jpg'];
    function switchBg(){
        var randomBgIndex = Math.floor(Math.random() * imgArr.length);
        var img_url='${staticUrl}/images/'+imgArr[randomBgIndex];
        $("body").css("background","url("+img_url+")");
    }
  	//变换验证码
	function changeCaptcha() {
	    var captchaImg = '/admin/generateImage?t=' + Math.random();
	    $("#captcha_img").attr("src", captchaImg);
	}

    $(function(){
    	//$("#year").html(new Date().getFullYear());
        //changeCaptcha();
        switchBg();
        setInterval("switchBg()",5000);
        //跳出框架在主窗口登录
        if(top.location!=this.location)	top.location=this.location;
        $('#username').focus();
		
        $("#login_form").validate({
	        errorPlacement: function(error, element){
	           var error_td = element.parent('div');
	           error_td.find('span').hide();
	           $(error).width(288);
	           $(error).css("color","#FD0D0D");
	           element.before(error);
	        },
	        rules: {
	            username: "required",
	            password: "required"//,
				//captcha:{
				//	required:true,
				//	remote:true
			   //}
	        },
	        messages: {
				username:{required:"用户名不能为空"},
				password:{required:"密码不能为空"},
				captcha:{
			  		required:"验证码不能为空",
			  		remote:"验证码不正确"
				}
		  }
	    });
	    
	    $('#submitBtn').click(function(){
	    	loginFun();       
	    });   
	    
	    //回车登陆事件
	    document.onkeydown = function(e){ 
	    	var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
	        	loginFun();    
		    }
	    }
		var username_cookie=$.cookie("username");
		if(username_cookie!=null&&username_cookie!=""){
	        $("#username").val(username_cookie);
		}
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		layui.use('layer', function(){
			var layer = layui.layer;
			layer.msg("${SPRING_SECURITY_LAST_EXCEPTION.message}",{icon: 2,time:3000});
		});
    	</c:if>
    });
	
	
	// 登录方法函数
	var loginFun = function(){
		if($("#login_form").valid()){
			if($("#remember").attr("checked")){
                var username = $.cookie("username",$("#username").val());
			}else{
                var username = $.cookie("username","");
			}
			$("#login_form").submit();
		}
	}
</script>
</body>
</html>