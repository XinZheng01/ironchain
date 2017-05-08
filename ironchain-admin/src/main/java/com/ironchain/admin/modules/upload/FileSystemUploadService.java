package com.ironchain.admin.modules.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemUploadService implements UploadService{
	
	/**
	 * 上传文件
	 * @param files
	 * @return
	 */
	public String[] store(MultipartFile... files){
		
		return null;
	}
}
