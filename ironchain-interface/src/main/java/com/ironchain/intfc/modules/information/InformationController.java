package com.ironchain.intfc.modules.information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.ironchain.common.dao.InformationDao;
import com.ironchain.common.domain.Information;
import com.ironchain.common.domain.R;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.web.ApiBaseController;

/**
 * 资讯
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/information")
public class InformationController extends ApiBaseController{
	
	@Autowired
	private InformationService informationService;
	
	@Autowired
	private InformationDao informationDao;
	
	@IgnoreAuth
	@IgnoreApiSecurity
	@GetMapping("/list")
	@ResponseBody
	public R list(Pageable pageable){
		return R.ok(informationService.findAll(pageable));
	}
	
	@IgnoreAuth
	@IgnoreApiSecurity
	@GetMapping("/details")
	public String details(@RequestParam Long id, Model model){
		Information information = informationDao.findByIdAndStatus(id, Information.STATUS_SHOW);
		information.setContent(HtmlUtils.htmlUnescape(information.getContent()));
		model.addAttribute("information", information);
		return "template";
	}
}
