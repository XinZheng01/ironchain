package com.ironchain.admin.modules.upload;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ironchain.common.base.BaseController;
import com.ironchain.common.domain.R;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.common.upload.UploadService;

@Controller
public class UploadController extends BaseController {
	
	@Autowired
	private UploadService uploadService;
	
	@PostMapping(value="/upload")
	public void upload(@RequestParam(value="file", required=false) MultipartFile[] files, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		if(files == null || files.length == 0){
			write(response, R.error().setMsg("没有要上传的文件"));
			return;
		}
		//文件类型校验
		
		try {
			String[] names = uploadService.store(files);
			write(response, R.ok(names));
		} catch (Exception e) {
			write(response, R.error().setMsg("上传文件失败"));
		}
		
	}
	
	public void write(HttpServletResponse response, R r) throws IOException{
		response.getWriter().write(JsonKit.normal().toJson(r));
	}
}
