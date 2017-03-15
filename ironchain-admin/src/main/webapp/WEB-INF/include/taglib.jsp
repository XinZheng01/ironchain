<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String ctx = request.getContextPath();
pageContext.setAttribute("ctx", ctx);
pageContext.setAttribute("staticUrl", ctx + "/static");
pageContext.setAttribute("uploadUrl", ctx + "/upload");
%>