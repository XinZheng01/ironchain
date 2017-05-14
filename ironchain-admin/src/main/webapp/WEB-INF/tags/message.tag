<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="r" type="com.ironchain.common.domain.R" required="true" description="响应的消息"%>
<%if(r != null){%>
<script type="text/javascript">
$.site.<%=r.getSc() == 200?"success":"error"%>('<%=r.getMsg() == null?"":r.getMsg()%>');
</script>
<%}%>