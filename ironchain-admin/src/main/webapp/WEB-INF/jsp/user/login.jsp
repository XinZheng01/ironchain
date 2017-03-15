<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<%@ include file="/WEB-INF/include/meta.jsp" %>
<title>Insert title here</title>
</head>
<body>
       <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">  
	       <div>login failed,try again!</div>
            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
       </c:if>  
	<form action="${ctx}/user/login" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
           <div><label> User Name : <input type="text" name="username"/> </label></div>
           <div><label> Password: <input type="password" name="password"/> </label></div>
           <div><input type="submit" value="Sign In"/></div>
    </form>
</body>
</html>