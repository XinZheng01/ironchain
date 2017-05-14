<%@tag import="org.springframework.validation.ObjectError"%>
<%@tag import="java.util.List"%>
<%@tag import="org.springframework.validation.Errors"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="model" type="java.lang.String" required="true" description="表单模型"%>
<%
Errors errors = null;
if((errors = (Errors)request.getAttribute(
		"org.springframework.validation.BindingResult." + model)) != null){
if(errors.hasErrors()){
List<ObjectError> allErrors = errors.getAllErrors();
StringBuilder sb = new StringBuilder();
sb.append("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>");
for(ObjectError objErr : allErrors){
	sb.append("<p>").append(objErr.getDefaultMessage()).append("</p>");
}
sb.append("</div>");
out.write(sb.toString());
}}
%>