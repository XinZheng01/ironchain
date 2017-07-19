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

import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileSystemUploadService implements UploadService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemUploadService.class);
	
	private String rootPath;
	
	private Pattern pattern = Pattern.compile("-");
	
	private String baseUrl;
	
	public FileSystemUploadService(String rootPath, String baseUrl, ServletContext servletContext){
		this.baseUrl = baseUrl;
		if(StringUtils.isBlank(rootPath))
			this.rootPath = servletContext.getRealPath("/upload");
		else
			this.rootPath = rootPath;
	}
	
	/**
	 * 上传文件
	 * @param compress
	 * @param files
	 * @return
	 * @throws IOException 
	 */
	@Override
	public String[] store(boolean compress, MultipartFile... files){
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
			Path uploadPath = null;
			
			for (int i = 0, len = files.length; i < len; i++) {
				fileName = files[i].getOriginalFilename();
				end = fileName.lastIndexOf(".");
				if(end != -1)
					suffix = fileName.substring(end);
				else
					suffix = null;
				
				uploadFileName = pattern.matcher(UUID.randomUUID().toString()).replaceAll("") + suffix;
				uploadPath = dirs.resolve(uploadFileName);
				Files.copy(files[i].getInputStream(), uploadPath);
				//压缩
				if(compress){
					compress(uploadPath);
				}
				uploadPaths[i] = baseUrl + "/" + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth()
					+ "/" + uploadFileName;
			}
		} catch (Exception e) {
			LOGGER.error("上传文件异常", e);
			throw new ServiceException(e.getMessage());
		}
		return uploadPaths;
	}
	
	@Override
	public String[] store(MultipartFile... files) {
		return store(false, files);
	}
	
	private static int[][] SIZE_ARR = {{700,700},{360,360},{240,240}};
	
	public static void compress(Path filePath) throws IOException{
		String filePathStr = filePath.toString();
		int lastIdx = filePathStr.lastIndexOf(".");
		String ext = filePathStr.substring(lastIdx);
		String d = filePathStr.substring(0, lastIdx);
		int i = 1;
		for (int[] size : SIZE_ARR) {
			Thumbnails.of(filePath.toFile())
				.size(size[0], size[1])
				.outputQuality(0.8)
				.toFile(d + "_" + i + ext);
			i++;
		}
	}
	
}
