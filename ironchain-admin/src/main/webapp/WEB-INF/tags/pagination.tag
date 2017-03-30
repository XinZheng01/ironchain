<%@tag import="org.springframework.data.domain.Sort.Order"%>
<%@tag import="org.springframework.data.domain.Sort"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true" description="Spring Data Jpa分页对象"%>
<%
	int totalPages = page.getTotalPages();//总页数
	int currentPage = page.getNumber() + 1;//当前页码
	int size = page.getSize();
%>
<input name="page" type="hidden" id="paramPage" value="<%=currentPage%>">
<input name="size" type="hidden" id="paramSize" value="<%=size%>">
<%
Sort sort = page.getSort();
String sortStr = "";
if(sort != null){
	Order order = sort.iterator().next();
	sortStr = order.getProperty() + "," + order.getDirection().name().toLowerCase();
}
%>
<input name="sort" type="hidden" id="paramSort" value="<%=sortStr%>">
<%
	if(totalPages <= 0 || currentPage > totalPages)
		return;
	
	int startPage = currentPage - 4;//开始页
	int endPage = currentPage + 4;//结束页
	if (startPage < 1 || currentPage <= 8)
		startPage = 1;
	if (endPage > totalPages || (totalPages - currentPage) < 8)
		endPage = totalPages;
%>
<div class="dataTables_wrapper">
	<div class="dataTables_length" id="page_length" style="color: #353535;">
		<label>显示第 <%=page.getNumber()*size+1%> 到第 <%=(currentPage*size <= page.getTotalElements())?currentPage*size:page.getTotalElements()%> 条记录，总共 <%=page.getTotalElements()%>条记录 每页显示 <select name="page_length" id="page_select" style="display: inline-block;width: auto;vertical-align: middle;" class="form-control input-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> 条</label>
	</div>
	<div class="dataTables_paginate" id="page_paginate">
		<ul class="pager" style="margin-top: 0.755em;">
			<%if (currentPage == 1){ %>
			<li class="previous disabled"><a href="javascript:;">上一页</a></li>
			<%}else {%>
			<li class="previous"><a href="javascript:goPage(<%=currentPage-1%>);">上一页</a></li>
			<%} %>
			<%if (currentPage > 8){ %>
			<li><a href="javascript:goPage(1);">1</a></li>
			<li><a href="javascript:goPage(2);">2</a></li>
			<li class="disabled"><a href="javascript:;">...</a></li>
			<%} %>
			<%for(int i = startPage; i <= endPage; i++) { %>
				<%if (currentPage == i){%>
			<li class="active"><a href="javascript:;"><%=i %></a></li>
				<%}else {%>
			<li><a href="javascript:goPage(<%=i%>);"><%=i %></a></li>
				<%} %>
			<%} %>
			<%if ((totalPages - currentPage) >= 8){ %>
			<li><a href="javascript:goPage(<%=totalPages - 1%>);"><%=totalPages - 1%></a></li>
			<li><a href="javascript:goPage(<%=totalPages%>);"><%=totalPages%></a></li>
			<%} %>
			<%if (currentPage == totalPages){ %>
			<li class="next disabled"><a href="javascript:;">下一页</a></li>
			<%}else { %>
			<li class="next"><a href="javascript:goPage(<%=currentPage+1%>);">下一页</a></li>
			<%} %>
		</ul>
	</div>
</div>
<script>
$(function(){
	$('#page_select option[value="<%=size%>"]').prop('selected', true);
	$('#page_select').on('change', function(){});
});
function goPage(page, size){
	var _page = $('#paramPage');
	size = size?size:$('#page_select').val();
	_page.val(page);
	$('#paramSize').val(size);
	_page.parent('form').submit();
}
</script>