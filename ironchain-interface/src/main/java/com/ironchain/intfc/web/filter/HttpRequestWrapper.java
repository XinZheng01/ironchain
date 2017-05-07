package com.ironchain.intfc.web.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.ironchain.common.kits.JsonKit;

/**
 * 请求修饰类
 * @author zheng xin
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper{
	
	private Map<String, String[]> paramMap = new HashMap<>();
	
	public HttpRequestWrapper(HttpServletRequest request, Map<String, Object> paramMap) {
		super(request);
		this.paramMap.putAll(super.getParameterMap());
		Object value = null;
		for (Map.Entry<String, Object> param : paramMap.entrySet()) {
			value = param.getValue();
			
			if(value == null || value instanceof String[]){
			} else if(value instanceof String || value instanceof Number){
				value = new String[]{value.toString()};
			} else {
				value = new String[]{JsonKit.normal().toJson(value)};
			}
			
			this.paramMap.put(param.getKey(), (String[])value);
		}
	}

	@Override
	public String getParameter(String name) {
		String[] values = this.paramMap.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return this.paramMap;
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		return new ParamEnumeration(this.paramMap.keySet().iterator());
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return this.paramMap.get(name);
	}
	
	private class ParamEnumeration implements Enumeration<String>{
		
		private Iterator<String> it = null;
		
		public ParamEnumeration(Iterator<String> it){
			this.it = it;
		}
		
		@Override
		public boolean hasMoreElements() {
			return it.hasNext();
		}

		@Override
		public String nextElement() {
			return it.next();
		}
	};
}
