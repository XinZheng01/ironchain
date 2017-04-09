(function ($) {
	/**
	 * 表单构造函数
	 * @private
	 */
	function _site() {
	    this.version = '1.0';
	    this.errMsg = '{status}服务器繁忙，请稍候再试！';
	    this.shade = [0.02, '#000'];
	    this.closeIndexs = {};
	}
	/**
     * 弹出警告消息框
     * @param msg 消息内容
     * @param callback 回调函数
     * @return {*|undefined}
     */
    _site.prototype.alert = function (msg, callback) {
        this.close();
        return this.index = top.layer.alert(msg, {end: callback, scrollbar: false});
    };

    /**
     * 显示正在加载中的提示
     * @param msg 提示内容
     * @param callback 回调方法
     * @return {common_L11._site|*}
     */
    _site.prototype.loading = function (msg, callback) {
        this.close();
        return this.index = msg
            ? top.layer.msg(msg, {icon: 16, scrollbar: false, shade: this.shade, time: 0, end: callback})
            : top.layer.load(2, {time: 0, scrollbar: false, shade: this.shade, end: callback});
    };
    /**
     * 确认对话框
     * @param msg 提示消息内容
     * @param ok 确认的回调函数
     * @param no 取消的回调函数
     * @return {undefined|*}
     */
    _site.prototype.confirm = function (msg, ok, no) {
        var self = this;
        return this.index = top.layer.confirm(msg, {btn: ['确认', '取消']}, function () {
            typeof ok === 'function' && ok.call(this);
            self.close();
        }, function () {
            typeof no === 'function' && ok.call(this);
            self.close();
        });
    };

    /**
     * 显示成功类型的消息
     * @param msg 消息内容
     * @param time 延迟关闭时间
     * @param callback 回调函数
     * @return {common_L11._site|*}
     */
    _site.prototype.success = function (msg, time, callback) {
        this.close();
        return this.index = top.layer.msg(msg, {
            icon: 1,
            shade: this.shade,
            scrollbar: false,
            end: callback,
            time: (time || 2) * 1000,
            shadeClose: true
        });
    };

    /**
     * 显示失败类型的消息
     * @param msg 消息内容
     * @param time 延迟关闭时间
     * @param callback 回调函数
     * @return {common_L11._site|*}
     */
    _site.prototype.error = function (msg, time, callback) {
        this.close();
        return this.index = top.layer.msg(msg, {
            icon: 2,
            shade: this.shade,
            scrollbar: false,
            time: (time || 3) * 1000,
            end: callback,
            shadeClose: true
        });
    };

    /**
     * 状态消息提示
     * @param msg 消息内容
     * @param time 显示时间s
     * @param callback 回调函数
     * @return {common_L11._site|*}
     */
    _site.prototype.tips = function (msg, time, callback) {
        this.close();
        return this.index = top.layer.msg(msg, {
            time: (time || 3) * 1000,
            shade: this.shade,
            end: callback,
            shadeClose: true
        });
    };
	/**
	 * 打开一个iframe窗口
	 * @param url iframe打开的URL地址
	 * @param title 窗口标题
	 * @param area 窗口大小
	 */
	_site.prototype.iframe = function (url, title, area) {
	    return top.layer.open({
	        title: title || '窗口',
	        type: 2,
	        area: area || ['800px', '530px'],
	        fix: true,
	        maxmin: false,
	        content: url
	    });
	};
	/**
     * 关闭消息框
     */
	_site.prototype.close = function () {
        if (!this.closeIndexs['_' + this.index]) {
            this.closeIndexs['_' + this.index] = true;
            return top.layer.close(this.index);
        }
    };
    
    /**
     * 异步加载的数据
     * @param url 请求的地址
     * @param data 额外请求数据
     * @param type 提交的类型 GET|POST
     * @param callback 成功后的回调方法
     * @param loading 是否显示加载层
     * @param tips 提示消息
     * @param time 消息提示时间
     */
    _site.prototype.load = function (url, data, type, callback, loading, tips, time) {
        var self = this;
        if (loading !== false) {
            var index = $.site.loading(tips);
        }
        var send_data = data;
        if (typeof data === 'object' && data.tagName === 'FORM') {
            send_data = $(data).serialize();
        }
        $.ajax({
            type: type || 'GET',
            url: parseUri(url),
            data: send_data || {},
            statusCode: {
                404: function () {
                    $.site.tips(self.errMsg.replace('{status}', 'E404 - '));
                },
                500: function () {
                    $.site.tips(self.errMsg.replace('{status}', 'E500 - '));
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.site.tips(self.errMsg.replace('{status}', 'E' + textStatus + ' - '));
            },
            success: function (res) {
                if (loading !== false) {
                    $.site.close(index);
                }
                if (typeof callback === 'function' && callback.call(self, res) === false) {
                    return false;
                }
                if (typeof (res) === 'object') {
                    //return $.site.auto(res, time);
                }
                //self.show(res);
            }
        });
    };
    
    /**
     * 显示HTML到中主内容区
     * @param html
     */
    //_site.prototype.show = function (html) {
    //    var $container = $('.layer-main-container').html(html);
    //};
    /**
     * 自动处理显示Think返回的Json数据
     * @param data JSON数据对象
     * @param time 延迟关闭时间
     * @return {common_L11._msg|*}
     */
    /*_site.prototype.auto = function (data, time) {
        var self = this;
        if (parseInt(data.code) === 1) {
            return self.success(data.msg, time, function () {
                !!data.url ? (window.location.href = data.url) : $.site.reload();
                if (self.autoSuccessCloseIndexs && self.autoSuccessCloseIndexs.length > 0) {
                    for (var i in self.autoSuccessCloseIndexs) {
                        layer.close(self.autoSuccessCloseIndexs[i]);
                    }
                    self.autoSuccessCloseIndexs = [];
                }
            });
        }
        self.error(data.msg, 3, function () {
            !!data.url && (window.location.href = data.url);
        });
    };*/
    /**
     * 加载HTML到目标位置
     * @param url 目标URL地址
     * --@param data URL参数
     * --@param callback 回调函数
     * --@param loading 是否显示加载
     * --@param tips 提示消息
     */
    _site.prototype.open = function (url) {
//        this.load(url, data, 'GET', function (res) {
//            if (typeof (res) === 'object') {
//                return $.site.auto(res);
//            }
//            var $container = $('.layer-main-container').html(res);
//            reinit.call(this), setTimeout(reinit, 500), setTimeout(reinit, 1000);
//            return (typeof callback === 'function') && callback.call(this);
//        }, loading, tips);
    	//var self = this;
        //if (loading !== false) {
        //    var index = $.site.loading(tips);
        //}
        //var send_data = data;
        //if (typeof data === 'object' && data.tagName === 'FORM') {
        //    send_data = $(data).serialize();
        //}
        top.rightContent.location.href = url;
    };
	/*!表单实例挂载*/
	$.site = new _site();
})(jQuery);