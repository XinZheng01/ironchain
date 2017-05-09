package com.ironchain.admin.modules.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ironchain.common.base.BaseController;
import com.ironchain.common.domain.R;
import com.ironchain.common.upload.UploadService;

@Controller
public class UploadController extends BaseController {
	
	@Autowired
	private UploadService uploadService;
	
	@PostMapping("/upload")
	@ResponseBody
	public R upload(@RequestParam(value="file", required=false) MultipartFile[] files){
		if(files == null || files.length == 0){
			return R.error().setMsg("没有要上传的文件");
		}
		//文件类型校验
		
		try {
			String[] names = uploadService.store(files);
			return R.ok(names);
		} catch (Exception e) {
			return R.error().setMsg("上传文件失败");
		}
	}
}
