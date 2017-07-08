<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="模型属性"%>
<%@ attribute name="height" type="java.lang.String" description="文本框高度"%>
<%@ attribute name="isLoadScript" type="java.lang.Boolean" description="是否需要加载script"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
if(height == null)
	height = "height:450px;";
else
	height = "height:"+height+"px;";
String ctx = request.getContextPath();
String staticUrl = ctx + "/static";
%>
<form:textarea path="<%=path %>" cssClass="form-control kindeditor" id="content" cssStyle="<%=height %>"/>
<%if(isLoadScript == null || isLoadScript){%>
<script src="<%=staticUrl %>/plugins/kindeditor/kindeditor-all-min.js"></script>
<script src="<%=staticUrl %>/plugins/kindeditor/lang/zh-CN.js"></script>
<%} %>
<script type="text/javascript">
KindEditor.ready(function(K) {
	var <%=path %>Editor = K.create('textarea[name="<%=path %>"]', {
		//去掉 preview code template flash insertfile about selectall media
		items : [
			'source', '|', 'undo', 'redo', '|', 'print', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'quickformat', '|',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
		    'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			'anchor', 'link', 'unlink', '|', 'fullscreen'
		],
		uploadJson : '<%=ctx %>/editor/upload',
		filterMode : false,//过滤html
		formatUploadUrl : false,
		extraFileUploadParams: _csrf,
		afterBlur: function(){this.sync();}
	});
});
</script>