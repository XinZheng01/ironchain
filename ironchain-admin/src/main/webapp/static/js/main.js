$(function(){
	JPlaceHolder.init();
	
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
	//提示信息
	$('[data-toggle="tooltip"]').tooltip();
	this.$body = $('body');
	/*! 注册 data-sort-column 事件行为 */
	$("th[data-sort-column]").each(function(){
		var sort = $("input[name='sort']").val().split(",");
		var _cur = $(this);
		if (_cur.attr('data-sort-column') == sort[0]){
			_cur.addClass('sorting_'+sort[1].toLowerCase());
		}else{
			_cur.addClass('sorting');
		}
	});
	this.$body.on('click', '[data-sort-column]', function () {
		var order = $(this).attr("data-sort-column");
		var sort = $("input[name='sort']").val().split(",");
		if (order == sort[0]){
			$("input[name='sort']").val(order + "," + (sort[1].toLowerCase() == 'desc'?'asc':'desc'));
		}else{
			$("input[name='sort']").val(order + ",asc");
		}
		refreshTable();
	});
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
    this.$body.on('click', '[data-click-other]', function () {
    	$('#'+$(this).attr('data-click-other')).click();
    });
    /*! 注册 data-upload-img 事件行为 */
    this.$body.on('change', '[data-upload-img]', function () {
    	var $upload = $(this);
		$.ajaxFileUpload({
			url: ctx + '/upload',
			allowType: $upload.attr('data-allow-type') || "bmp|gif|jpg|jpeg|png",
			data: _csrf,
			secureuri: false,
			fileElementId: $upload.attr('id'),
			dataType: 'json',
			success: function (data, status) {
				if (data.sc == 200) {
					if($upload.prop('multiple')){
						var imgs = data.data;
						var imgDiv = '', width = $upload.attr('data-img-width'), height = $upload.attr('data-img-height');
						for(var i=0, len=imgs.length; i < len; i++){
							imgDiv += '<div class="col-sm-2"><img src="'+imgs[i]+'" width="'+width+'" height="'+height+'"></div>';
						}
						var fn = window['callback'];
						fn(imgDiv);
						//$('#' + $upload.attr('data-upload-img')).append(imgDiv);
					}else{
						$('#' + $upload.attr('data-upload-img')).attr('src', data.data[0]);
						$('#' + $upload.attr('data-upload-input')).val(data.data[0]);
					}
				}else
					$.site.error(data.msg);
			},
			error: function (data, status, e) {
				$.site.error('图片上传失败，请重试。');
			}
		});
    });
    /*! 注册 data-tips-image 事件行为 */
    this.$body.on('click', '[data-tips-image]', function () {
        var src = this.getAttribute('data-tips-image') || this.src, tag = false;
        var imgWidth = this.getAttribute('data-width') || '480px';
        var $img = top.$("<img style=\"background:#fff;width:"+imgWidth+";height:auto;display:none;\" src=\""+src+"\">").appendTo('body');
        $img.on('load', function(){
        	top.layer.open({
        		type: 1, area: imgWidth, title: false, closeBtn: 1, skin: 'layui-layer-nobg', shadeClose: true,
        		content: $img,
        		end: function () {
        			$img.remove();
        		}
        	});
        });
    });
    //delete all btn
    this.$body.on('click', '[data-del-select]', function () {
    	var ids = getCheckedVal('.dataTable');
		if(ids.length == 0){
			return $.site.alert("请选择一条记录");
		}
		del(ids.join(','));
    });
    //拖拽排序
    //this.$body.on('load', '[data-sortable]', function () {
    	//$(this).sortable();
    //});
    //面包屑导航
    if($('.admin-breadcrumb').length > 0){
    	var _crumb_li = '<li><a href="javascript:;"><i class="icon icon-home"></i> 首页</a></li>';
    	var _pathname = location.pathname;
    	var _group = _pathname.match(/(.+)(\/add|\/edit)$/);
    	if(_group != null)
    		_pathname = _group[1] + '/list';
    	
    	top.$('.site-demo-nav dl a').each(function(){
    		if($(this).attr('href') == _pathname){
    			_crumb_li += '<li><a href="javascript:;">'
    				+ $(this).closest('dl.layui-nav-child').siblings('a').text()+'</a></li>';
    			_crumb_li += '<li'+(_group == null?' class="active">':'><a href="'+$(this).attr('href')+'">')
    				+$(this).children('cite').text()+(_group == null?'':'</a>')+'</li>';
    			if(_group != null)
    				_crumb_li += '<li class="active">'+(_group[2] == '/add'?'新增':'编辑')+'</li>';
    			return false;
    		}
    	});
    	$('.admin-breadcrumb').html(_crumb_li);
    }
    //日期插件
	$('.form-datetime').datetimepicker({
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd hh:ii'
    });
    $('.form-date').datetimepicker({
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'yyyy-mm-dd'
    });
    $('.form-time').datetimepicker({
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0,
        format: 'hh:ii'
    });
    /*! 注册 data-start-time 事件行为 */
    this.$body.on('change', '[data-start-time]', function () {
    	$('#'+$(this).data('start-time')).data('datetimepicker').setEndDate($(this).val());
    });
    /*! 注册 data-end-time 事件行为 */
    this.$body.on('change', '[data-end-time]', function () {
    	$('#'+$(this).data('end-time')).data('datetimepicker').setStartDate($(this).val());
    });
});
/*!
 * jQuery placeholder, fix for IE6,7,8,9
 */
var JPlaceHolder = {
    _check: function () {
        return 'placeholder' in document.createElement('input');
    },
    init: function () {
        !this._check() && this.fix();
    },
    fix: function () {
        $(':input[placeholder]').map(function () {
            var self = $(this), txt = self.attr('placeholder');
            self.wrap($('<div></div>').css({zoom: '1', margin: 'none', border: 'none', padding: 'none', background: 'none', position: 'relative'}));
            var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
            var holder = $('<span></span>').text(txt).css({position: 'absolute', left: pos.left, top: pos.top, height: h, lineHeight: h + 'px', paddingLeft: paddingleft, color: '#aaa'}).appendTo(self.parent());
            self.on('focusin focusout change keyup', function () {
                self.val() ? holder.hide() : holder.show();
            });
            holder.click(function () {
                self.get(0).focus();
            });
            self.val() && holder.hide();
        });
    }
};
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