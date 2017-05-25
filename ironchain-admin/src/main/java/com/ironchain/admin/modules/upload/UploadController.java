package com.ironchain.admin.modules.upload;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.ironchain.common.base.BaseController;
import com.ironchain.common.domain.R;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.common.upload.UploadService;

@Controller
public class UploadController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
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
	
	@PostMapping(value="/editor/upload")
	public void ueditorUpload(StandardMultipartHttpServletRequest request , HttpServletResponse response) throws IOException{
		Collection<MultipartFile> files = request.getFileMap().values();
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<>();
		if(files == null || files.size() == 0){
			map.put("error", 1);
			map.put("message", "请选择文件。");
			write(response, map);
			return;
		}
		try {
			String url = uploadService.store(files.toArray(new MultipartFile[files.size()]))[0];
			System.out.println("上传文件个数：" + files.size());
			System.out.println("上传url：" + url);
			map.put("error", 0);
			map.put("url", url);
			write(response, map);
		} catch (Exception e) {
			LOGGER.error("ueditor 上传文件失败。", e);
			map.put("error", 1);
			map.put("message", "上传文件失败。");
			write(response, map);
		}
	}
	
	public void write(HttpServletResponse response, Object r) throws IOException{
		response.getWriter().write(JsonKit.normal().toJson(r));
	}
}
