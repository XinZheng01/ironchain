package com.ironchain.common.base;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public abstract class BaseService{
	
	/**
	 * 转换图片
	 * @param source
	 * @param type 1 700*700 2 360*360 3 240*240
	 * @return
	 */
	public String[] convertImg(String[] source, int type){
		String[] imgArray = new String[source.length];
		int idx = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0, len = source.length; i < len; i++) {
			idx = source[i].lastIndexOf(".");
			sb.setLength(0);
			imgArray[i] = sb.append(source[i].substring(0, idx)).append('_').append(type)
					.append(source[i].substring(idx)).toString();
		}
		return imgArray;
	}
	
	/**
	 * 转换图片
	 * @param source
	 * @param type 1 700*700 2 360*360 3 240*240
	 * @return
	 */
	public String convertImg(String source, int type){
		int idx = source.lastIndexOf(".");
		return source.substring(0, idx)+"_"+type+source.substring(idx);
	}
}
