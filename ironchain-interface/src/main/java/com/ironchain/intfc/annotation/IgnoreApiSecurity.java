package com.ironchain.intfc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略加密
 * @author zhengxin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreApiSecurity {
	
	/**
	 * 忽略 RequestBody 加密
	 * @see @RequestBody
	 * @return
	 */
	boolean ignoreRequest() default true;
	
	/**
	 * 忽略 ResponseBody 加密
	 * @see @ResponseBody
	 * @return
	 */
	boolean ignoreResponse() default true; 
}