package com.ironchain.admin.modules.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ironchain.common.base.BaseController;

@Controller
public class UploadController extends BaseController {
	
	@Autowired
	private UploadService uploadService;
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam(value="file", required=false) MultipartFile[] files){
		if(files == null || files.length == 0){
			return "{\"error\":1, \"message\":\"没有要上传的文件\"}";
		}
		
		return null;
	}
}
