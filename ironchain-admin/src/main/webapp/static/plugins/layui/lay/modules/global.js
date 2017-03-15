layui.define(['layer', 'code', 'form', 'element', 'util'], function(exports){
  var $ = layui.jquery
  ,layer = layui.layer
  ,form = layui.form() 
  ,util = layui.util
  ,device = layui.device();

  //阻止IE7以下访问
  if(device.ie && device.ie < 8){
    layer.alert('Layui最低支持ie8，您当前使用的是古老的 IE'+ device.ie + '，请升级您的浏览器！');
  }
//  var iframe = $('#LAY_demo').prop('contentWindow');
//  $('#LAY_demo_run').on('click', function(){
//    iframe.layui.jquery('body').html($('#LAY_editor').val());
//    if(iframe.layui.form){
//      iframe.layui.form().render();
//    }
//    if(iframe.layui.element){
//      iframe.layui.element().init();
//    }
//  });
  $('#rightIframe')
  //手机设备的简单适配
  var treeMobile = $('.site-tree-mobile')
  ,shadeMobile = $('.site-mobile-shade')

  treeMobile.on('click', function(){
    $('body').addClass('site-mobile');
  });

  shadeMobile.on('click', function(){
    $('body').removeClass('site-mobile');
  });
  exports('global', {});
});