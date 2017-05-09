package com.ironchain.common.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ironchain.common.exception.ServiceException;

@Service
public class FileSystemUploadService implements UploadService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemUploadService.class);
	
	private String rootPath;
	
	private Pattern pattern = Pattern.compile("-");
	
	public FileSystemUploadService(String rootPath, ServletContext servletContext){
		if(StringUtils.isBlank(rootPath))
			this.rootPath = servletContext.getRealPath("/upload");
		else
			this.rootPath = rootPath;
	}
	
	/**
	 * 上传文件
	 * @param files
	 * @return
	 * @throws IOException 
	 */
	public String[] store(MultipartFile... files){
		String[] uploadPaths = new String[files.length];
		try {
			LocalDate now = LocalDate.now();
			Path dirs = Paths.get(rootPath, now.getYear()+"", now.getMonthValue()+"", now.getDayOfMonth()+"");
			//创建文件夹
			if(Files.notExists(dirs))
				Files.createDirectories(dirs);
			
			String fileName = null, suffix = null;
			int end = 0;
			String uploadFileName = null;
			
			for (int i = 0, len = files.length; i < len; i++) {
				fileName = files[i].getOriginalFilename();
				end = fileName.lastIndexOf(".");
				if(end != -1)
					suffix = fileName.substring(end);
				else
					suffix = null;
				
				uploadFileName = pattern.matcher(UUID.randomUUID().toString()).replaceAll("") + suffix;
				Files.copy(files[i].getInputStream(), dirs.resolve(uploadFileName));
				uploadPaths[i] = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth()
					+ "/" + uploadFileName;
			}
		} catch (Exception e) {
			LOGGER.error("上传文件异常", e);
			throw new ServiceException(e.getMessage());
		}
		return uploadPaths;
	}
	
}
