$.validator.setDefaults({
	ignore: "",
    errorElement: "span",
    errorClass: "help-block help-block-error",
    highlight: function (element, errorClass, validClass) {
    	console.log('highlight');
    	console.log(element);
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function (element, errorClass, validClass) {
    	console.log('unhighlight');
    	console.log(element);
        $(element).closest('.form-group').removeClass('has-error');
    },
    success: function (label) {
        label.closest('.form-group').removeClass('has-error');
    },
    errorPlacement: function (error, element) {
    	console.log('errorPlacement');
    	console.log(element);
    	console.log(error);
        if (element.parent('.input-group').length || element.prop('type') === 'checkbox' || element.prop('type') === 'radio') {
            error.insertAfter(element.parent());
        } else {
            error.insertAfter(element);
        }
    }
});

$.validator.addMethod( "ipv4", function( value, element ) {
	return this.optional( element ) || /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/i.test( value );
}, "请输入正确的IPV4地址" );

$.validator.addMethod( "adaptrequired", function(value, element, param) {
	var result = true;
	if($('#'+param).prop('checked')){
		switch( element.nodeName.toLowerCase() ) {
		case 'select':
			if($(element).attr('multiple')){
				return $(element).children('option').length > 0
			}else{
				var val = $(element).val();
				return val && val.length > 0;
			}
		case 'input':
			if ( this.checkable(element) )
				return this.getLength(value, element) > 0;
		default:
			return $.trim(value).length > 0;
		}
	}
	return result;
}, "不能为空" );

$.validator.addMethod( "method", function(value, element) {
	return eval($(element).attr('validate'));
}, "校验不通过" );

$.validator.addMethod( "pwd", function(value, element) {
	return this.optional(element) || /^[\dA-Za-z(!@#$%&)]{6,20}$/.test(value);
}, "请输入有效的密码" );

$.validator.addMethod( "email", function(value, element) {
	return this.optional(element) || /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
}, "请输入有效的邮箱" );

$.validator.addMethod( "mobilephone", function(value, element) {
	return this.optional(element) || /^(13|15|18|14|17)[0-9]{9}$/.test(value);
}, "请输入有效的手机号码" );

$.extend( $.validator.messages, {
	required: "不能为空",
	remote: "请修正此字段",
	url: "请输入有效的网址",
	date: "请输入有效的日期",
	number: "请输入有效的数字",
	digits: "只能输入数字",
	creditcard: "请输入有效的信用卡号码",
	equalTo: "你的输入不相同",
	extension: "请输入有效的后缀",
	maxlength: $.validator.format( "最多可以输入 {0} 个字符" ),
	minlength: $.validator.format( "最少要输入 {0} 个字符" ),
	rangelength: $.validator.format( "请输入长度在 {0} 到 {1} 之间的字符串" ),
	range: $.validator.format( "请输入范围在 {0} 到 {1} 之间的数值" ),
	max: $.validator.format( "请输入不大于 {0} 的数值" ),
	min: $.validator.format( "请输入不小于 {0} 的数值" )
} );