package com.ironchain.intfc.modules.demand;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironchain.common.domain.Demand;
import com.ironchain.common.domain.Member;
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.modules.member.MemberService;
import com.ironchain.intfc.web.ApiBaseController;

/**
 * 需求
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/demand")
public class DemandController extends ApiBaseController{
	
	@Autowired
	private DemandService demandService;

	@Autowired
	private MemberService memberService;
	
	/**
	 * 获取需求分类
	 * @param type 类型 1加工需求 2设备服务3塑胶4电子
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/class")
	public R demandClass(@RequestParam(required=false, defaultValue="-1") int type){
		return R.ok(demandService.findClassIdAndNameByType(type));
	}
	
	/**
	 * 获取需求列表
	 * @param type 类型 1加工需求 2设备服务3塑胶4电子
	 * @param classId 需求分类id
	 * @param progress 进度
	 * @param adcode 区域
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param pageable 分页对象
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/list")
	public R list(@RequestParam(required=false, defaultValue="-1") int type, 
			@RequestParam(required=false) Long classId,
			@RequestParam(required=false, defaultValue="-1") int progress,
			@RequestParam(required=false) String adcode,
			@RequestParam(required=false) Date startDate,
			@RequestParam(required=false) Date endDate, Pageable pageable){
		
		Page<Map<String, Object>> page = demandService.findDemandByParam(type, classId, progress,
				adcode, startDate, endDate, pageable);
		Map<String, Object> map = new HashMap<>();
		map.put("content", page.getContent());
		map.put("hasNext", page.hasNext()?1:0);
		return R.ok(map);
	}
	
	/**
	 * 需求详情
	 * @param id
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/details")
	public R details(@RequestParam Long id, @RequestParam(required=false) Long userId, @RequestParam(required=false) String token){
		//检验登录
		if(userId != null && token != null && !memberService.isLogin(userId, token)){
			throw new ServiceException(R.SC_NOLOGIN, "登录信息不正确");
		}
		Map<String, Object> demand = demandService.findDemandDetails(id, userId);
		return R.ok(demand);
	}
	
	/**
	 * 竞标企业列表
	 * @return
	 */
	public R bidMemberList(@RequestParam Long id, @RequestParam(required=false) Long userId, Pageable pageable){
		demandService.findBidMemberList(id, userId, pageable);
		return R.ok();
	}
	
	/**
	 * 发布需求
	 * @param demand
	 * @param demandFile
	 * @param userId
	 * @return
	 */
	@RequestMapping("/publish")
	public R publish(@Valid Demand demand, @RequestParam String demandFile, @RequestParam Long userId){
		if(demand == null)
			throw new ServiceException(R.SC_PARAMERROR, "非法请求");
		switch (demand.getType()) {
		case Demand.TYPE_MACHINED://检验机加工
			Assert.hasLength(demand.getMachined(), "材料不能为空");
			Assert.isTrue(demand.getTerm() != null && demand.getTerm() > 0, "交期天数必须大于0");
			Assert.hasLength(demand.getPayment(), "付款描述不能为空");
			break;
		case Demand.TYPE_EQUIPMENT://校验设备服务
			Assert.hasLength(demand.getBrand(), "品牌不能为空");
			Assert.hasLength(demand.getKinds(), "型号不能为空");
			Assert.isTrue(demand.getLife() != null && demand.getLife() > 0, "年限必须大于0");
			break;
		case Demand.TYPE_PLASTIC://校验塑胶
			break;
		case Demand.TYPE_ELECTRONIC://校验电子
			break;
		default:
			throw new ServiceException(R.SC_PARAMERROR, "非法需求类型");
		}
		
		Member publisher = new Member();
		publisher.setId(userId);
		demand.setPublisher(publisher);
		//保存到数据库
		demandService.publishDemand(demand, demandFile);
		
		return R.ok();
	}
	
	/**
	 * 企业竞标
	 * @param userId 用户id
	 * @param id 需求id
	 * @param price 价格
	 * @return
	 */
	@RequestMapping("/bid")
	public R bid(@RequestParam Long userId, @RequestParam Long id, @RequestParam BigDecimal price){
		demandService.bid(userId, id, price);
		return R.ok();
	}
	
	/**
	 * 主要设备列表
	 * @param type
	 * @return
	 */
	@IgnoreAuth
	@RequestMapping("/equipment_list")
	public R equipmentList(@RequestParam(required=false, defaultValue="-1") int type){
		return R.ok(demandService.findEquipmentList(type));
	}
	
	/**
	 * 删除需求文件
	 * @param id
	 * @return
	 */
	@RequestMapping("/file/delete")
	public R deleteDemandFile(@RequestParam Long id, @RequestParam Long userId){
		demandService.deleteDemandFileByUserId(id, userId);
		return R.ok();
	}
}
