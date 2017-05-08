package com.ironchain.common.upload;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	/**
	 * 上传文件
	 * @param files
	 * @return
	 */
	String[] store(MultipartFile... files);
}
