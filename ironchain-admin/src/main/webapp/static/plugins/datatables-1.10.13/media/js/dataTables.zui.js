/*! DataTables ZUI integration
 * ©2011-2015 SpryMedia Ltd - datatables.net/license
 */

/**
 * DataTables integration for ZUI. This requires Bootstrap 3 and
 * DataTables 1.10 or newer.
 *
 * This file sets the defaults and adds options to DataTables to style its
 * controls using Bootstrap. See http://datatables.net/manual/styling/bootstrap
 * for further information.
 */
(function( factory ){
	if ( typeof define === 'function' && define.amd ) {
		// AMD
		define( ['jquery', 'datatables.net'], function ( $ ) {
			return factory( $, window, document );
		} );
	}
	else if ( typeof exports === 'object' ) {
		// CommonJS
		module.exports = function (root, $) {
			if ( ! root ) {
				root = window;
			}

			if ( ! $ || ! $.fn.dataTable ) {
				// Require DataTables, which attaches to jQuery, including
				// jQuery if needed and have a $ property so we can access the
				// jQuery object that is used
				$ = require('datatables.net')(root, $).$;
			}

			return factory( $, root, root.document );
		};
	}
	else {
		// Browser
		factory( jQuery, window, document );
	}
}(function( $, window, document, undefined ) {
'use strict';
var DataTable = $.fn.dataTable;

/* Set the defaults for DataTables initialisation */
$.extend( true, DataTable.defaults, {
	dom:
		//"<'row'<'col-sm-6'l><'col-sm-6'f>>" +
		//"<'row'<'col-sm-12'tr>>" +
		//"<'row'<'col-sm-5'i><'col-sm-7'p>>"+
		"rt<'bottom'iflp<'clear'>>",
	renderer: 'bootstrap',
	pagingType:   "full_numbers",
	sLoadingRecords: "正在加载数据...",
	sZeroRecords: "暂无数据",
	stateSave: true,
	searching: false,
	order: [],
	serverSide: true,
	ajax: function (data, callback, settings) {
		console.log(data);
        //封装请求参数
        var param = settings.oInit.ajaxParam || {};
        param.size = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
        param.start = data.start;//开始的记录序号
        param.page = (data.start / data.length)+1;//当前页码
        //ajax请求数据
        $.ajax({
            type: settings.oInit.ajaxType || "GET",
            url: settings.oInit.ajaxUrl || "",
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: settings.oInit.ajaxDatatype || "json",
            success: function (result) {
                var returnData = {};
                returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                returnData.recordsTotal = result.totalElements;//返回数据全部记录
                returnData.recordsFiltered = result.totalElements;//后台不实现过滤功能，每次查询均视作全部结果
                returnData.data = result.content;//返回的数据列表
                if(settings.oInit.ajaxCallback != undefined)
                	settings.oInit.ajaxCallback(result, returnData);
                //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                callback(returnData);
            },
            error: function(){
            	alert("抱歉，请求出错，请重新刷新页面");
            }
        });
	},
	language: {
        "processing": "正在加载数据...",
		"lengthMenu": "显示 _MENU_ 项结果",
		"zeroRecords": "暂无数据",
		"info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		"infoEmpty": "显示第 0 至 0 项结果，共 0 项",
		"infoFiltered": "(由 _MAX_ 项结果过滤)",
		"infoPostFix": "",
		"url": "",
		"paginate": {
			"first":    "首页",
			"previous": "上一页",
			"next":     "下一页",
			"last":     "末页"
		}
   }
} );


/* Default class modification */
$.extend( DataTable.ext.classes, {
	sWrapper:      "dataTables_wrapper form-inline dt-bootstrap",
	sFilterInput:  "form-control input-sm",
	sLengthSelect: "form-control input-sm",
	sProcessing:   "dataTables_processing panel panel-default"
} );


/* Bootstrap paging button renderer */
DataTable.ext.renderer.pageButton.bootstrap = function ( settings, host, idx, buttons, page, pages ) {
	var api     = new DataTable.Api( settings );
	var classes = settings.oClasses;
	var lang    = settings.oLanguage.oPaginate;
	var aria = settings.oLanguage.oAria.paginate || {};
	var btnDisplay, btnClass, counter=0;

	var attach = function( container, buttons ) {
		var i, ien, node, button;
		var clickHandler = function ( e ) {
			e.preventDefault();
			if ( !$(e.currentTarget).hasClass('disabled') && api.page() != e.data.action ) {
				api.page( e.data.action ).draw( 'page' );
			}
		};

		for ( i=0, ien=buttons.length ; i<ien ; i++ ) {
			button = buttons[i];

			if ( $.isArray( button ) ) {
				attach( container, button );
			}
			else {
				btnDisplay = '';
				btnClass = '';

				switch ( button ) {
					case 'ellipsis':
						btnDisplay = '&#x2026;';
						btnClass = 'disabled';
						break;

					case 'first':
						btnDisplay = lang.sFirst;
						btnClass = button + (page > 0 ?
							'' : ' disabled');
						break;

					case 'previous':
						btnDisplay = lang.sPrevious;
						btnClass = button + (page > 0 ?
							'' : ' disabled');
						break;

					case 'next':
						btnDisplay = lang.sNext;
						btnClass = button + (page < pages-1 ?
							'' : ' disabled');
						break;

					case 'last':
						btnDisplay = lang.sLast;
						btnClass = button + (page < pages-1 ?
							'' : ' disabled');
						break;

					default:
						btnDisplay = button + 1;
						btnClass = page === button ?
							'active' : '';
						break;
				}

				if ( btnDisplay ) {
					node = $('<li>', {
							'class': classes.sPageButton+' '+btnClass,
							'id': idx === 0 && typeof button === 'string' ?
								settings.sTableId +'_'+ button :
								null
						} )
						.append( $('<a>', {
								'href': '#',
								'aria-controls': settings.sTableId,
								'aria-label': aria[ button ],
								'data-dt-idx': counter,
								'tabindex': settings.iTabIndex
							} )
							.html( btnDisplay )
						)
						.appendTo( container );

					settings.oApi._fnBindAction(
						node, {action: button}, clickHandler
					);

					counter++;
				}
			}
		}
	};

	// IE9 throws an 'unknown error' if document.activeElement is used
	// inside an iframe or frame. 
	var activeEl;

	try {
		// Because this approach is destroying and recreating the paging
		// elements, focus is lost on the select button which is bad for
		// accessibility. So we want to restore focus once the draw has
		// completed
		activeEl = $(host).find(document.activeElement).data('dt-idx');
	}
	catch (e) {}

	attach(
		$(host).empty().html('<ul class="pager"/>').children('ul'),
		buttons
	);

	if ( activeEl !== undefined ) {
		$(host).find( '[data-dt-idx='+activeEl+']' ).focus();
	}
};


return DataTable;
}));
