package com.ironchain.admin.modules.upload;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
	
	private static List<String> imgExt = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp");
	
	@PostMapping(value="/editor/upload")
	public void ueditorUpload(@RequestParam(value="dir", defaultValue="image", required=false) String dir, StandardMultipartHttpServletRequest request , HttpServletResponse response) throws IOException{
		Collection<MultipartFile> files = request.getFileMap().values();
		response.setContentType("text/html;charset=UTF-8");
		if(files == null || files.size() == 0){
			write(response, getError("请选择文件。"));
			return;
		}
		String fileName = null;
		int idx = -1;
		for (MultipartFile multipartFile : files) {
			if("image".equals(dir)){
				if(multipartFile.getSize() > 10000000){//10M
					write(response, getError("上传文件大小超过限制。"));
					return;
				}
				fileName = multipartFile.getOriginalFilename();
				idx = fileName.lastIndexOf(".");
				if(idx == -1 || !imgExt.contains(fileName.substring(idx + 1).toLowerCase())){
					write(response, getError("上传文件扩展名是不允许的扩展名。\n只允许" + StringUtils.join(imgExt, ",")+ "格式。"));
					return;
				}
			}else{
				write(response, getError("不允许上传该类型的文件。"));
				return;
			}
		}
		try {
			String url = uploadService.store(files.toArray(new MultipartFile[files.size()]))[0];
			Map<String, Object> map = new HashMap<>();
			map.put("error", 0);
			map.put("url", url);
			write(response, map);
		} catch (Exception e) {
			LOGGER.error("ueditor 上传文件失败。", e);
			write(response, getError("上传文件失败。"));
		}
	}
	
	public static Map<String, Object> getError(String message){
		Map<String, Object> map = new HashMap<>();
		map.put("error", 1);
		map.put("message", message);
		return map;
	}
	
	
	public static void write(HttpServletResponse response, Object r) throws IOException{
		response.getWriter().write(JsonKit.normal().toJson(r));
	}
}
