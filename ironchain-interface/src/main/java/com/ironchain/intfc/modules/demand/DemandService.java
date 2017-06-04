package com.ironchain.intfc.modules.demand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.DemandDao;
import com.ironchain.common.dao.DemandFileDao;
import com.ironchain.common.dao.MemberDao;
import com.ironchain.common.domain.Demand;
import com.ironchain.common.domain.DemandFile;
import com.ironchain.common.domain.EquipmentClass;
import com.ironchain.common.domain.Member;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.kits.DateKit;
import com.ironchain.common.kits.SqlKit;

@Service
public class DemandService extends BaseService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DemandDao demandDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private DemandFileDao demandFileDao;
//	@Autowired
//	private DemandClassDao demandClassDao;
	
	/**
	 * 获取需求分类
	 * @return
	 */
	public List<Map<String, Object>> findClassIdAndNameByType(int type){
		return SqlKit.create().append("select id, name from demand_class where 1=1")
				.append(type > 0, " and type=?", type)
				.query2Map(jdbcTemplate);
	}

	/**
	 * 根据类型获取需求列表
	 * @param type 类型
	 * @param classId 分类id
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findDemandByParam(int type, Long classId, int progress, String adcode,
			Date startDate, Date endDate, Pageable pageable) {
		Page<Map<String, Object>> page = SqlKit.create().append("select d.id, d.title, d.description,")
			.append(" c.name, d.start_date startDate, d.end_date endDate, d.progress")
			.append(" from demand d, demand_class c where d.class_id = c.id")
			.append(type > 0, " and d.type = ?", type)//类型
			.append(classId > 0, " and d.class_id = ?", classId)//分类
			.append(progress > -1, " d.progress = ?", progress)//筛选状态
			.append(progress == -1, " and d.progress in(?,?,?,?,?) ",
					Demand.PROGRESS_PUBLISH, Demand.PROGRESS_BID, Demand.PROGRESS_SEND,
					Demand.PROGRESS_PAY, Demand.PROGRESS_COMPLETE)//默认状态，已发布
			.append(adcode != null, " and d.adcode = ?", adcode)//区域
			.append(startDate != null, " and d.startDate = ?", startDate)//开始时间
			.append(endDate != null, " and d.endDate = ?", endDate)//结束时间
			.query2Page(jdbcTemplate, pageable);
		//处理数据
		Date date = null;
		Number progressN = null;
		for (Map<String, Object> map : page) {
			if((date = (Date) map.get("startDate")) != null)
				map.put("startDate", DateKit.formatDate(date, "yyyy/MM/dd"));
			if((date = (Date) map.get("endDate")) != null)
				map.put("endDate", DateKit.formatDate(date, "yyyy/MM/dd"));
			if((progressN = (Number) map.get("progress")) != null)
				map.put("progress", Demand.parseProgress(progressN.intValue()));
		}
		return page;
	}

	/**
	 * 获取需求详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> findDemandDetails(Long id, Long userId) {
		Demand demand = demandDao.findOne(id);
		if(demand == null)
			throw new ServiceException("需求不存在");
		
		Map<String, Object> result = new HashMap<>();
		result.put("id", demand.getId());
		result.put("title", demand.getTitle());//标题
		result.put("type", demand.getType());//类型
		result.put("progress", demand.getProgress());//进度
		result.put("demandClass", demand.getDemandClass().toMap());//分类名称
		result.put("number", demand.getNumber());//数量
		result.put("publisher", demand.getPublisher().getId());//发布人id
		
		result.put("bidNumber", demand.getBidNumber());//竞方数量
		result.put("mainEquipment", equClassToMap(demand.getMainEquipment()));//主要设备
		result.put("area", demand.getGdArea().toSimpleMap());//区域
		result.put("startDate", DateKit.formatDate(demand.getStartDate(), "yyyy/MM/dd"));//开始时间
		result.put("endDate", DateKit.formatDate(demand.getEndDate(), "yyyy/MM/dd"));//结束时间
		
		if(demand.getType() == Demand.TYPE_MACHINED){//加工服务
			result.put("machined", demand.getMachined());
			result.put("term", demand.getTerm());
			result.put("payment", demand.getPayment());
		}else if(demand.getType() == Demand.TYPE_EQUIPMENT){//设备服务
			result.put("brand", demand.getBrand());
			result.put("kinds", demand.getKinds());
			result.put("life", demand.getLife());
		}
		//当前登录用户是否能竞标 非发布者且为企业用户
		result.put("canBid", demand.getPublisher().getId().compareTo(userId) != 0
				&& memberDao.findTypeById(userId) == Member.TYPE_COMPANY);
		//竞标企业列表
		//TODO 星级 成交数量
		result.put("offerCompany", jdbcTemplate.queryForList(
				"select m.id, m.company_name from demand_offer o, member m "
				+ "where o.member_id = m.id and o.demand_id=?", demand.getId()));
		//图档文件
		result.put("demandFile", jdbcTemplate.queryForList(
				"select id, type, path from demand_file where demand_id=?", demand.getId()));
		return result;
	}
	
	/**
	 * 设备类型转map
	 * @param equs
	 * @return
	 */
	public List<Map<String, Object>> equClassToMap(Collection<EquipmentClass> equs){
		List<Map<String, Object>> list = new ArrayList<>(equs.size());
		for (EquipmentClass equClass : equs) {
			list.add(equClass.toMap());
		}
		return list;
	}

	/**
	 * 发布需求
	 * @param demand
	 * @param demandFile
	 */
	public void publishDemand(Demand demand, List<DemandFile> demandFile) {
		//待审核状态
		demand.setProgress(Demand.PROGRESS_AUDIT);
		demand.setCreateTime(new Date());
		
		demand = demandDao.save(demand);
		for (DemandFile file : demandFile) {
			file.setDemandId(demand.getId());
		}
		demandFileDao.save(demandFile);
	}

}
