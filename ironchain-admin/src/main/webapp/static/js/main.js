$(function(){
	//表格选中
	$('.check-all').on('click', function(){
		$(this).parents('table').find('tbody input[type="checkbox"]').prop('checked', this.checked)
			.parents('tr').toggleClass('active', this.checked);
	});
	$('table tbody').on('change', 'input[type="checkbox"]', function(){
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
	this.$body = $('body');
	/*! 注册 data-icon 事件行为 */
    this.$body.on('click', '[data-icon]', function () {
        var field = $(this).attr('data-icon') || $(this).attr('data-field') || 'icon';
        var url = '/icon?field=' + field;
        $.site.iframe(url, '选择图标');
    });
    /*! 注册 data-open 事件行为 */
    this.$body.on('click', '[data-open]', function () {
        var url = $(this).attr('data-open');
        $.site.open(url);
    });
    
    if($('.admin-breadcrumb').length > 0){
    	var _crumb_li = '<li><a href="javascript:;"><i class="icon icon-home"></i> 首页</a></li>';
    	var _pathname = location.pathname;
    	var _group = _pathname.match(/(.+)(\/add|\/edit)$/);
    	if(_group != null)
    		_pathname = _group[1];
    	
    	top.$('.site-demo-nav dl a').each(function(){
    		if($(this).attr('href').indexOf(_pathname) > -1){
    			_crumb_li += '<li><a href="javascript:;">'
    				+ $(this).closest('dl.layui-nav-child').siblings('a').text()+'</a></li>';
    			_crumb_li += '<li'+(_group == null?' class="active">':'><a href="'+$(this).attr('href')+'">')
    				+$(this).children('cite').text()+(_group == null?'':'</a>')+'</li>';
    			if(_group != null)
    				_crumb_li += '<li class="active">'+(_group[2] == '/add'?'新增':'编辑')+'</li>';
    			return;
    		}
    	});
    	$('.admin-breadcrumb').html(_crumb_li);
    }
	
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