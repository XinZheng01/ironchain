/* 
  131108-xxj-ajaxFileUpload.js 无刷新上传图片 jquery 插件，支持 ie6-ie10  
  依赖：jquery-1.6.1.min.js 
  主方法：ajaxFileUpload 接受 json 对象参数 
  参数说明： 
  fileElementId：必选,上传文件域ID 
  url：必选，发送请求的URL字符串 
  allowType：可选，限定上传文件的格式（.jpg,.bmp,.gif,.png） 
  fileSize:可选，0 为无限制(IE浏览器不兼容) 
  data:可选，将和文件域一同post的参数（json对象） 
  其它：$.ajax 的参数均为可选参数 
  注：如遇到‘无法访问’的脚本错误提示则需要在响应流中加一段脚块一同输出：<script ...>document.domain = 'xxx.com';</script> 
*/  
jQuery.extend({  
  //创建 iframe 元素,接受提交及响应  
  createUploadIframe: function(id, uri) {
	  var frameId = 'jUploadFrame' + id;
	  var iframe = $('<iframe id="'+ frameId +'" name="'+ frameId +'"></iframe>');
	  iframe.css('position', 'absolute');
	  iframe.css('top', '-1000px');
	  iframe.css('left', '-1000px');
	  $('body').append(iframe);
	  return iframe;
  },
  //创建 from 元素，用于提交的表单  
  createUploadForm: function(id, fileElementId, postData) {
    //create form
    var formId = 'jUploadForm' + id;
    var form = $('<form  action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');
    var newElement = $('#' + fileElementId).clone();
  
    $(newElement).attr('id', 'jUploadFile' + id);
    $(newElement).appendTo(form);
    //添加自定义参数  
    if (postData) {
      //遍历JSON所有键值  
      for (var i in postData) {
         $("<input name='" + i + "' id='" + i + "' value='" + postData[i] + "' />").appendTo(form);  
      }  
    }  
    //set attributes  
    form.css('position', 'absolute');  
    form.css('top', '-1200px');  
    form.css('left', '-1200px');  
    form.appendTo('body');  
    return form;  
  },  
  //上传文件  
  //s 参数：json对象  
  ajaxFileUpload: function(s) {  
    s = jQuery.extend({allowType:"",fileSize:0}, jQuery.ajaxSettings, s);  
    //文件筛选  
    var fielName = $('#' + s.fileElementId).val();
    
    if (s.allowType && !RegExp('\\w\\.('+ s.allowType +')$', 'i').test(escape(fielName))) { 
      top.layer.msg("仅支持 (" + s.allowType + ") 为后缀名的文件!", {icon: 2});
      return;  
    }  
    //文件大小限制  
    if (s.fileSize > 0) {  
      var fs = 0;  
      try {  
        if (window.ActiveXObject) {  
          //IE浏览器  
          var image = new Image();  
          image.dynsrc = fielName;  
          fs = image.fileSize;  
        } else {  
          fs = $('#' + s.fileElementId)[0].files[0].size;  
        }  
      } catch(e) {  
      }
      if (fs > s.fileSize) {  
    	top.layer.msg("当前文件大小 (" + fs/1024000 + "M) 超过允许的限制值 (" + s.fileSize/1024000 +"M)！", {icon: 2});
        return;  
      }  
    }
    
    var id = new Date().getTime();  
    //创建 form 表单元素  
    var form = jQuery.createUploadForm(id, s.fileElementId, s.data);  
    //创建 iframe 元素  
    var io = jQuery.createUploadIframe(id, s.secureuri);  
    var frameId = 'jUploadFrame' + id;  
    var formId = 'jUploadForm' + id;
    
    //监测是否有新的请求  
    if (s.global && !jQuery.active++) {  
      jQuery.event.trigger("ajaxStart"); //触发 AJAX 请求开始时执行函数。Ajax 事件。  
    }  
    var requestDone = false;  
    //创建请求对象  
    var xml = {};
    if (s.global)  
      jQuery.event.trigger("ajaxSend", [xml, s]); //触发 AJAX 请求发送前事件  
    //上载完成的回调函数  
    var uploadCallback = function(isTimeout) {
      try {
    	  xml.responseText = io.contents().find('body').text();
    	  xml.responseXML = io.contents();
      } catch (e) {
    	  top.layer.msg('上传接口存在跨域', {icon: 2});
      }
      if (xml || isTimeout == "timeout") {  
        requestDone = true;  
        var status;  
        try {  
          status = isTimeout != "timeout" ? "success" : "error";  
          // Make sure that the request was successful or notmodified  
          if (status != "error") {  
            //处理数据（运行XML通过httpData不管回调）  
            var data = jQuery.uploadHttpData(xml, s.dataType);  
            // If a local callback was specified, fire it and pass it the data  
            if (s.success)  
              s.success(data, status);  
  
            // Fire the global callback  
            if (s.global)  
              jQuery.event.trigger("ajaxSuccess", [xml, s]);  
          } else  
            jQuery.handleErrorExt(s, xml, status);  
        } catch(e) {  
          status = "error";  
          jQuery.handleErrorExt(s, xml, status, e);  
        }  
  
        // The request was completed  
        if (s.global)  
          jQuery.event.trigger("ajaxComplete", [xml, s]);  
  
        // Handle the global AJAX counter  
        if (s.global && !--jQuery.active)  
          jQuery.event.trigger("ajaxStop");  
  
        // Process result  
        if (s.complete)  
          s.complete(xml, status);  
  
        io.unbind();
  
        setTimeout(function() {  
            io.remove();  
            form.remove();  
        }, 100);
  
        xml = null;
  
      }  
    };  
    //超时检查，s.timeout 毫秒后调用 uploadCallback 回调函数提示请求超时  
    if (s.timeout > 0) {  
      setTimeout(function() {  
        // Check to see if the request is still happening  
        if (!requestDone) uploadCallback("timeout");  
      }, s.timeout);  
    }
    //设置动态 form 表单的提交参数  
    form.attr('action', s.url);  
    form.attr('target', frameId); 
    form.submit();
    
    //向动态表单的页面加载事件中注册回调函数  
    if (window.attachEvent) {  
      document.getElementById(frameId).attachEvent('onload', uploadCallback);  
    } else {  
      document.getElementById(frameId).addEventListener('load', uploadCallback, false);  
    }  
    return {  
      abort: function() {  
      }  
    };  

  },  
  //上传文件  
  uploadHttpData: function(r, type) {
    var data = r.responseText;
    if (type == "json")
      eval("data = " + data);  
    else if (type == "script")
      jQuery.globalEval(data);  
    else if (type == "html")
      jQuery("<div>").html(data).evalScripts();
    else if (type == "xml")
      data = r.responseXML;
    return data;  
  },  
  handleErrorExt: function(s, xhr, status, e) {  
    // If a local callback was specified, fire it  
    if (s.error) {  
      s.error.call(s.context || s, xhr, status, e);  
    }  
    // Fire the global callback  
    if (s.global) {  
      (s.context ? jQuery(s.context) : jQuery.event).trigger("ajaxError", [xhr, s, e]);  
    }  
  }  
});