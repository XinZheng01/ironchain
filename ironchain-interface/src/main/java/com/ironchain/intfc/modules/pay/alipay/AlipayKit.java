package com.ironchain.intfc.modules.pay.alipay;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

public class AlipayKit {
	
	public static boolean rsaCheck(HttpServletRequest request) throws AlipayApiException{
		TreeMap<String, String> params = new TreeMap<>();
		Map<String, String[]> requestParams = request.getParameterMap();
		StringBuilder valueStr = new StringBuilder();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			valueStr.setLength(0);
			for (int i = 0, len = values.length; i < len; i++) {
				valueStr.append(values[i]);
				if(i != values.length - 1)
					valueStr.append(',');
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr.toString());
		}
		//TODO 公钥
		return AlipaySignature.rsaCheckV1(params, "", "UTF-8", "RSA2");
	}
}