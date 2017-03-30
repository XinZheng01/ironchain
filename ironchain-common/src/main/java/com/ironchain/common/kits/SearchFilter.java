package com.ironchain.common.kits;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class SearchFilter {

	public enum Operator {
		EQ, LIKE, NOTLIKE, GT, LT, GTE, LTE, NULL, NOTNULL, IN, NOTIN
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<>();
		String key = null;
		Object value = null;
		String[] names = null;
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			key = entry.getKey();
			value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			names = StringUtils.split(key, "_");
			switch (names.length) {
			case 3://带类型
				filters.put(key, new SearchFilter(names[1],
						Operator.valueOf(names[0].toUpperCase()), convert(names[2], (String) value)));
				break;
			case 2:
				filters.put(key, new SearchFilter(names[1],
						Operator.valueOf(names[0].toUpperCase()), value));
				break;
			default:
//				throw new IllegalArgumentException(key + " is not a valid search filter name");
				continue;
			}
		}

		return filters;
	}
	
	private static Object convert(String type, String value){
		type = type.toUpperCase();
		switch (type) {
		case "I":
			return Integer.parseInt(value, 10);
		case "L":
			return Long.parseLong(value, 10);
		case "D":
			return DateKit.parseDate(value);
		case "B":
			return Boolean.parseBoolean(value);
		default:
			return value;
		}
	}
	
}
