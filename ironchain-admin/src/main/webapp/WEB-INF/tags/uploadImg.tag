<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="Input Id"%>
<%@ attribute name="help" type="java.lang.String" required="false" description="帮助文字"%>
<%@ attribute name="width" type="java.lang.Integer" required="false" description="图片宽度"%>
<%@ attribute name="height" type="java.lang.Integer" required="false" description="图片高度"%>
<%@ attribute name="defaultValue" type="java.lang.String" required="false" description="默认显示的图片"%>
<%@ attribute name="allowType" type="java.lang.String" required="false" description="文件格式"%>
<%
if(help == null)
	help = "支持上传bmp/png/jpeg/jpg/gif格式";
if(width == null)
	width = Integer.valueOf(100);
if(height == null)
	height = Integer.valueOf(100);
if(allowType == null)
	allowType = "";
String imgSrc = "/static/images/image_blank.png";
if(StringUtils.isBlank(defaultValue))
	defaultValue = "";
else
	imgSrc = defaultValue;
%>
<input type="text" class="form-control" id="<%=id%>" name="<%=id%>" value="<%=defaultValue%>" onchange="$('#<%=id%>Img').attr('src', this.value);" placeholder="请输入图片地址">
<div class="help-block"><%=help%></div>
<img id="<%=id%>Img" width="<%=width%>" height="<%=height%>" src="<%=imgSrc%>" data-tips-image>
<a class="btn btn-link" data-click-other="<%=id%>File"><i class="icon icon-cloud-upload"></i>上传图片</a>
<input type="file" name="file" id="<%=id%>File" data-upload-input="<%=id%>" data-upload-img="<%=id%>Img" data-allow-type="<%=allowType%>" style="display: none;" accept="image/gif,image/jpeg,image/jpg,image/png">