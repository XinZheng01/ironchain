package com.ironchain.common.upload;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	List<String> fileExt = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "doc", "docx", "xls", "xlsx", "ppt", "txt", "zip", "rar", "gz", "bz2", "pdf");
	
	List<String> imgExt = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp");
	
	/**
	 * 上传文件
	 * @param files
	 * @return
	 */
	String[] store(MultipartFile... files);
	
	/**
	 * 上传文件
	 * @param compress 是否压缩
	 * @param files
	 * @return
	 */
	String[] store(boolean compress, MultipartFile... files);
}
