package com.ironchain.intfc.modules.upload;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ironchain.common.base.BaseController;
import com.ironchain.common.domain.R;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.common.upload.UploadService;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.annotation.IgnoreAuth;

@Controller
public class UploadController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
	@Autowired
	private UploadService uploadService;
	
	@IgnoreApiSecurity
	@IgnoreAuth
	@PostMapping(value="/api/upload")
	public void upload(@RequestParam(value="file", required=false) MultipartFile[] files, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		if(files == null || files.length == 0){
			write(response, R.error().setMsg("没有要上传的文件"));
			return;
		}
		//文件类型校验
		String fileName = null;
		int idx = -1;
		for (MultipartFile multipartFile : files) {
//			if(multipartFile.getSize() > 10000000){//10M
//				write(response, R.error().setMsg("上传文件大小超过限制。"));
//				return;
//			}
			fileName = multipartFile.getOriginalFilename();
			idx = fileName.lastIndexOf(".");
			if(idx == -1 || !UploadService.fileExt.contains(fileName.substring(idx + 1).toLowerCase())){
				write(response, R.error().setMsg("上传文件扩展名是不允许的扩展名。\n只允许" + StringUtils.join(UploadService.fileExt, ",")+ "格式。"));
				return;
			}
		}
		try {
			String[] names = uploadService.store(files);
			write(response, R.ok(names));
		} catch (Exception e) {
			LOGGER.error("上传文件失败", e);
			write(response, R.error().setMsg("上传文件失败"));
		}
		
	}
	
	public static void write(HttpServletResponse response, Object r) throws IOException{
		response.getWriter().write(JsonKit.normal().toJson(r));
	}
}
