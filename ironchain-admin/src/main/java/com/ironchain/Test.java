package com.ironchain;

import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.buf.HexUtils;

public class Test {
	public static void main(String[] args) throws Exception {
		byte[] b = "爱傻傻的黄金卡萨傲娇受打击卡萨卡接收到阿贾克斯队就卡死的环境啊哈计划说大家防守打法和盛开的积分撒旦法撒旦峰会上客户附近可适当就分手东复活甲速度快发送框架的 是东方时空就垫付很少见对方后视镜快递费就开始对方给读后感和的看法光电开关活动经费工行贷款纠纷更简单客服根据对方搞活动反馈鬼地方价格和的看法改好的快感对方即可打开改好接电话给看见飞机货到付款根据对方打卡机个电话费  大概垫付给对方改掉".getBytes("UTF-8");
		System.out.println(Base64.getEncoder().encodeToString(b));
		System.out.println(new String(org.apache.commons.codec.binary.Base64.encodeBase64(b)));
		//54ix5YK75YK755qE6buE6YeR5Y2h6JCo5YKy5aiH5Y+X5omT5Ye75Y2h6JCo5Y2h5o6l5pS25Yiw6Zi/6LS+5YWL5pav6Zif5bCx5Y2h5q2755qE546v5aKD5ZWK5ZOI6K6h5YiS6K+05aSn5a626Ziy5a6I5omT5rOV5ZKM55ub5byA55qE56ev5YiG5pKS5pem5rOV5pKS5pem5bOw5Lya5LiK5a6i5oi36ZmE6L+R5Y+v6YCC5b2T5bCx5YiG5omL5Lic5aSN5rS755Sy6YCf5bqm5b+r5Y+R6YCB5qGG5p6255qEIOaYr+S4nOaWueaXtuepuuWwseWeq+S7mOW+iOWwkeingeWvueaWueWQjuinhumVnOW/q+mAkui0ueWwseW8gOWni+WvueaWuee7meivu+WQjuaEn+WSjOeahOeci+azleWFieeUteW8gOWFs+a0u+WKqOe7j+i0ueW3peihjOi0t+asvue6oOe6t+abtOeugOWNleWuouacjeagueaNruWvueaWueaQnua0u+WKqOWPjemmiOmsvOWcsOaWueS7t+agvOWSjOeahOeci+azleaUueWlveeahOW/q+aEn+WvueaWueWNs+WPr+aJk+W8gOaUueWlveaOpeeUteivnee7meeci+ingemjnuacuui0p+WIsOS7mOasvuagueaNruWvueaWueaJk+WNoeacuuS4queUteivnei0uSAg5aSn5qaC5Z6r5LuY57uZ5a+55pa55pS55o6J
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
//			org.apache.commons.codec.binary.Base64.encodeBase64(b);//2455 2528 2395
			//Base64.encodeBase64(b);//931 965 940
			//Base64.getEncoder().encode(b);// 221 226 223
			Hex.encodeHexString(b);//243
			HexUtils.toHexString(b);//523
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
