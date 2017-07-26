package com.ironchain.intfc.modules.pay.alipay;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pay/alipay")
public class AlipayController{
	
	public static final Logger logger = LoggerFactory.getLogger(AlipayController.class);
	
	@PostMapping("/notify")
	@ResponseBody
	public String notifyUrl(HttpServletRequest request,
			@RequestParam String out_trade_no,//商户订单号
			@RequestParam String trade_no,//支付宝交易号
			@RequestParam String trade_status){//交易状态
		try {
			if(AlipayKit.rsaCheck(request)){//验证成功
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					//如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
				} else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
				}
				return "success";
			}else{//验证失败
				return "fail";
			}
		} catch (Exception e) {
			logger.error("支付宝支付异常 out_trade_no:{}, trade_no:{}, trade_status:{}",
					out_trade_no, trade_no, trade_status);
			logger.error("异常信息", e);
		}
		return "fail";
	}
	
}
