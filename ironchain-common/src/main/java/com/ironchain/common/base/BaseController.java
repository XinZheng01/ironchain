package com.ironchain.common.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

import com.ironchain.common.kits.SpecificationKit;

public abstract class BaseController {

//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//
//		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
//		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
//			@Override
//			public void setAsText(String text) {
//				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
//			}
//		});
//
//		// Date 类型转换
//		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//			@Override
//			public void setAsText(String text) {
//				setValue(DateKit.parseDate(text));
//			}
//		});
//	}

	// -- 常用数值定义 --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	public static final String DEFAULT_PREFIX = "srch_";

	/**
	 * 设置客户端缓存过期时间 的Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + (expiresSeconds * 1000));
		// Http 1.1 header, set a time after now.
		response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader(HttpHeaders.EXPIRES, 1L);
		response.addHeader(HttpHeaders.PRAGMA, "no-cache");
		// Http 1.1 header
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response,
			String fileName) {
		// 中文文件名支持
		String encodedfileName = null;
		// 替换空格，否则firefox下有空格文件名会被截断,其他浏览器会将空格替换成+号
		encodedfileName = fileName.trim().replaceAll(" ", "_");
		String agent = request.getHeader("User-Agent");
		boolean isMSIE = (agent != null && agent.toUpperCase().indexOf("MSIE") != -1);
		if (isMSIE) {
			try {
				encodedfileName = URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		} else {
			encodedfileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
		}

		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");

	}

	public static <T> Specification<T> bySearchFilter(HttpServletRequest request){
		return SpecificationKit.bySearchFilter(getParamsStartWith(request, DEFAULT_PREFIX));
	}
	
	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParamsStartWith(HttpServletRequest request) {
		return getParamsStartWith(request, DEFAULT_PREFIX);
	}

	/**
	 * 取得带相同前缀的Request Parameters,数据存入request copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParamsStartWith(HttpServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while ((paramNames != null) && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if ((values == null) || (values.length == 0)) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
					request.setAttribute(paramName, values);
				} else {
					params.put(unprefixed, values[0]);
					request.setAttribute(paramName, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 组合Parameters生成Query String的Parameter部分, 并在paramter name上加上prefix.
	 * 
	 * @see #getParametersStartingWith
	 */
	public static String encodeParameStringWithPrefix(Map<String, Object> params) {
		return encodeParameStringWithPrefix(params, DEFAULT_PREFIX);
	}

	/**
	 * 组合Parameters生成Query String的Parameter部分, 并在paramter name上加上prefix.
	 * 
	 * @see #getParametersStartingWith
	 */
	public static String encodeParameStringWithPrefix(Map<String, Object> params, String prefix) {
		if (params == null || params.isEmpty()) {
			return "";
		}
		if (prefix == null) {
			prefix = "";
		}
		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append('&');
			}
		}
		return queryStringBuilder.toString();
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (StringUtils.isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	
	/**
	 * 获取Pageable对象
	 * @param page 页码
	 * @param size 行数
	 * @param sort 排序（可为空）
	 * @return Pageable
	 */
	public Pageable getPageable(int page, int size, Sort sort){
		if(page - 1 < 0 || size <= 0)
			throw new IllegalArgumentException("非法分页参数");
		return new PageRequest(page - 1, size, sort);
	}
}
