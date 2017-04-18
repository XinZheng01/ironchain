package com.ironchain.admin.modules.upload;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemUploadService {
	
	private Path rootLocation;
	
	@Autowired
	public FileSystemUploadService(@Value("site.upload.root-location") String rootLocation){
		this.rootLocation = Paths.get(rootLocation);
	}
	
	/**
	 * 上传文件
	 * @param files
	 * @return
	 */
	public String[] store(MultipartFile... files){
		
		return null;
	}
}
