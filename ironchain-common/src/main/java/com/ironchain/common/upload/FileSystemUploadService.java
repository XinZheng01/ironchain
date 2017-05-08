package com.ironchain.common.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemUploadService implements UploadService{
	
	private Path rootPath;
	
	public FileSystemUploadService(String rootPath){
		this.rootPath = Paths.get(rootPath);
	}
	
	/**
	 * 上传文件
	 * @param files
	 * @return
	 */
	public String[] store(MultipartFile... files){
		for (MultipartFile multipartFile : files) {
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		Path root = Paths.get("D:/workspace4.6/ironchain/upload");
		Path path = root.resolve("2017/05/09").resolve("a.txt");
//		path.toFile().exists()
		if(!Files.exists(path)){
			Files.createDirectories(path);
		}
		Files.copy(Paths.get("D:/Backup/桌面/新建文本文档 (8).txt"), path, StandardCopyOption.REPLACE_EXISTING);
	}
}
