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
<script type="text/javascript">
$.ajaxSetup({
    beforeSend: function (xhr) {
		xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
    }
});
</script>