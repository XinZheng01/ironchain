<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${staticUrl}/plugins/jquery-1.12.4.min.js" charset="utf-8"></script>
<script src="${staticUrl}/plugins/zui-1.6.0/js/zui.min.js"></script>
<!-- 
<script src="${staticUrl}/plugins/datatables-1.10.13/media/js/jquery.dataTables.js"></script>
<script src="${staticUrl}/plugins/datatables-1.10.13/media/js/dataTables.zui.js"></script>
 -->
<script src="${staticUrl}/js/site.js"></script>
<script src="${staticUrl}/js/main.js"></script>
<script src="${staticUrl}/plugins/datetimepicker/datetimepicker.min.js"></script>
<script src="${staticUrl}/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="${staticUrl}/plugins/jquery-validation/dist/jquery.validate.extend.js"></script>
<!--[if lt IE 9]>
  <script src="${staticUrl}/plugins/zui-1.6.0/lib/ieonly/html5shiv.js"></script>
  <script src="${staticUrl}/plugins/zui-1.6.0/lib/ieonly/respond.js"></script>
  <script src="${staticUrl}/plugins/zui-1.6.0/lib/ieonly/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">
var ctx = '${ctx}';
var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");  
var csrfHeader = $("meta[name='_csrf_header']").attr("content");  
var csrfToken = $("meta[name='_csrf']").attr("content");
var _csrf = {}; _csrf[csrfParameter] = csrfToken;
$.ajaxSetup({
    beforeSend: function (xhr) {
		xhr.setRequestHeader(csrfHeader, csrfToken);
    }
});
</script>