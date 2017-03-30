$(function(){
	//表格选中
	$('.check-all').on('click', function(){
		$(this).parents('table').find('tbody input[type="checkbox"]').prop('checked', this.checked)
			.parents('tr').toggleClass('active', this.checked);
	});
	$('.dataTable tbody').on('change', 'input[type="checkbox"]', function(){
		if(!this.checked){ 
			var el = $(this).parents('table').find('.check-all').get(0);
			if(el && el.checked && ('indeterminate' in el))
				el.indeterminate = true;
		}
		$(this).parents('tr').toggleClass('active');
	});
	//表格排序
	$(".sort-column").each(function(){
		var sort = $("input[name='sort']").val().split(",");
		var _cur = $(this);
		if (_cur.hasClass(sort[0])){
			_cur.addClass('sorting_'+sort[1].toLowerCase());
		}else{
			_cur.addClass('sorting');
		}
	});
	$(".sort-column").click(function(){
		var order = $(this).attr("class").split(" ");
		var sort = $("input[name='sort']").val().split(",");
		for(var i=0, len=order.length; i < len; i++){
			if (order[i] == "sort-column"){order = order[i+1]; break;}
		}
		if (order == sort[0]){
			$("input[name='sort']").val(order + "," + (sort[1].toLowerCase() == 'desc'?'asc':'desc'));
		}else{
			$("input[name='sort']").val(order + ",asc");
		}
		refreshTable();
	});
	//提示信息
	$('[data-toggle="tooltip"]').tooltip();
	
});
/** 获取表格选中的行*/
var getCheckedVal = function(selector){
	var chkArr = [];
	$(selector + ' tbody input[type="checkbox"]:checked').each(function(){
		chkArr.push($(this).val());
	});
	return chkArr;
};
var refreshTable = function(){
	$('#searchForm').submit();
};