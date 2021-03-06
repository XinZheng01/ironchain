<%@tag import="org.apache.commons.lang3.StringUtils"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="Input Id"%>
<%@ attribute name="help" type="java.lang.String" required="false" description="帮助文字"%>
<%@ attribute name="width" type="java.lang.Integer" required="false" description="图片宽度"%>
<%@ attribute name="height" type="java.lang.Integer" required="false" description="图片高度"%>
<%@ attribute name="defaultValue" type="java.lang.String" required="false" description="默认显示的图片"%>
<%@ attribute name="allowType" type="java.lang.String" required="false" description="文件格式"%>
<%@ attribute name="multiple" type="java.lang.Boolean" required="false" description="是否多文件上传"%>
<%@ attribute name="compress" type="java.lang.Boolean" required="false" description="是否压缩图片"%>
<%@ attribute name="isLoadScript" type="java.lang.Boolean" description="是否需要加载script"%>
<%
if(width == null)
	width = Integer.valueOf(100);
if(height == null)
	height = Integer.valueOf(100);
if(allowType == null)
	allowType = "";
String imgSrc = request.getContextPath() + "/static/images/image_blank.png";
if(StringUtils.isBlank(defaultValue))
	defaultValue = "";
else
	imgSrc = defaultValue;
if(multiple == null)
	multiple = false;
if(compress == null)
	compress = false;
if(multiple && help == null)
	help = "上传的图片可以任意拖拽";
else if(help == null)
	help = "支持上传bmp/png/jpeg/jpg/gif格式";
%>
<%if(!multiple){ %>
<input type="text" class="form-control" id="<%=id%>" name="<%=id%>" value="<%=defaultValue%>" onchange="$('#<%=id%>Img').attr('src', this.value);" placeholder="请输入图片地址">
<div class="help-block"><%=help%></div>
<img id="<%=id%>Img" width="<%=width%>" height="<%=height%>" src="<%=imgSrc%>" data-tips-image>
<a class="btn btn-link" data-click-other="<%=id%>File"><i class="icon icon-cloud-upload"></i>上传图片</a>
<input type="file" name="file" id="<%=id%>File" data-upload-input="<%=id%>" data-upload-compress="<%=compress%>" data-upload-img="<%=id%>Img" data-allow-type="<%=allowType%>" style="display: none;" accept="image/gif,image/jpeg,image/jpg,image/png">
<%}else {%>
<div id="<%=id%>" class="row"></div>
<div class="help-block"><%=help%></div>
<a class="btn btn-link" data-click-other="<%=id%>File"><i class="icon icon-cloud-upload"></i>上传图片</a>
<input type="file" name="file" id="<%=id%>File" multiple data-upload-img="<%=id%>" data-upload-compress="<%=compress%>" data-img-width="<%=width%>" data-img-height="<%=height%>" data-allow-type="<%=allowType%>" style="display: none;" accept="image/gif,image/jpeg,image/jpg,image/png">
<%}%>