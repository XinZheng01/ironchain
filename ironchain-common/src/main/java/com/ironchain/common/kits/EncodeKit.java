package com.ironchain.common.kits;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 封装各种格式的编码解码工具类.
 * 
 * 1.Commons-Codec的 hex/base64 编码
 * 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * 
 * @author zheng xin
 */
public class EncodeKit {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	 /**
     * Used to build output as Hex
     */
    private static final char[] DIGITS_LOWER =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Used to build output as Hex
     */
//    private static final char[] DIGITS_UPPER =
//        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }
	
	protected static int toDigit(final char ch, final int index) {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }
	
	/**
	 * Hex编码.
	 */
	public static char[] encodeHex(byte[] input) {
		return encodeHex(input, DIGITS_LOWER);
	}
	
	/**
	 * Hex编码.
	 */
	public static String encodeHex2String(byte[] input) {
		return new String(encodeHex(input));
	}
	
	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
			final char[] data = input.toCharArray();
			final int len = data.length;
	
	        if ((len & 0x01) != 0) {
	        	return null;
	        }
	
	        final byte[] out = new byte[len >> 1];
	
	        try {
		        // two characters form the hex value.
		        for (int i = 0, j = 0; j < len; i++) {
		            int f = toDigit(data[j], j) << 4;
		            j++;
		            f = f | toDigit(data[j], j);
		            j++;
		            out[i] = (byte) (f & 0xFF);
		        }
			} catch (Exception e) {
				ExceptionKit.unchecked(e);
			}
	
	        return out;
	}
	
	//md5
	
	//sha1
	
	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.getEncoder().encodeToString(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.getUrlEncoder().encodeToString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.getDecoder().decode(input);
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[(input[i] & 0xFF) % BASE62.length];
		}
		return new String(chars);
	}

//	/**
//	 * Html 转码.
//	 */
//	public static String escapeHtml(String html) {
//		return StringEscapeUtils.escapeHtml4(html);
//	}
//
//	/**
//	 * Html 解码.
//	 */
//	public static String unescapeHtml(String htmlEscaped) {
//		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
//	}
//
//	/**
//	 * Xml 转码.
//	 */
//	public static String escapeXml(String xml) {
//		return StringEscapeUtils.escapeXml11(xml);
//	}
//
//	/**
//	 * Xml 解码.
//	 */
//	public static String unescapeXml(String xmlEscaped) {
//		return StringEscapeUtils.unescapeXml(xmlEscaped);
//	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw ExceptionKit.unchecked(e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 */
	public static String urlDecode(String part) {

		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw ExceptionKit.unchecked(e);
		}
	}
}
