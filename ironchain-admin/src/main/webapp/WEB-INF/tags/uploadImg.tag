<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="Input Id"%>
<%@ attribute name="help" type="java.lang.String" required="false" description="帮助文字"%>
<%@ attribute name="width" type="java.lang.Integer" required="false" description="图片宽度"%>
<%@ attribute name="height" type="java.lang.Integer" required="false" description="图片高度"%>
<%@ attribute name="defaultValue" type="java.lang.String" required="false" description="默认显示的图片"%>
<%
if(help == null)
	help = "点击图片选择文件";
if(width == null)
	width = Integer.valueOf(100);
if(height == null)
	height = Integer.valueOf(100);
String imgStr = defaultValue == null?"":defaultValue;
%>
<input type="hidden" id="<%=id%>" value="<%=imgStr%>">
<img id="<%=id%>Img" width="<%=width%>" height="<%=height%>" data-click-other="<%=id%>File" >
<input type="file" name="file" id="<%=id%>File" data-upload-input="<%=id%>" data-upload-img="<%=id%>Img" style="display: none;" accept="image/gif,image/jpeg,image/jpg,image/png">
<div class="help-block"><%=help%></div>
<script>
$('#<%=id%>Img').attr('src', '<%=imgStr%>'? (UPLOADURL + '/' + '<%=imgStr%>'): '/static/images/image_blank.png');
</script>