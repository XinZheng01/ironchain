package com.ironchain.test.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ironchain.common.dao.DemandClassDao;
import com.ironchain.common.dao.SystemUserDao;
import com.ironchain.common.domain.DemandClass;
import com.ironchain.common.domain.SystemUser;
import com.ironchain.common.kits.SpringKit;
import com.ironchain.common.kits.SqlKit;
import com.ironchain.common.persistence.dialect.Dialect;
import com.ironchain.test.AbstractNoneWebTest;

public class SqlKitTest extends AbstractNoneWebTest{
	
//	@Autowired
//	private SqlHelper sqlHelper;
	
	@Autowired
	@Qualifier("pageDialect")
	private Dialect dialect;
	
	@Autowired
	private SystemUserDao systemUserDao;
	
	@Autowired
	private DemandClassDao demandClassDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void sqlTest(){
		long start = System.currentTimeMillis();
//		Page<SystemUser> page = SqlKit.create().append("select * from system_user").query2Page(systemUserDao, new PageRequest(0, 10));
//		Page<DemandClass> page = SqlKit.create().append("select * from demand_class").query2Page(demandClassDao, new PageRequest(0, 10));
		
//		Page<Map<String, Object>> page = SqlKit.create().append("select * from system_user").query2PageMap(jdbcTemplate, new PageRequest(0, 10));
		
//		Page<SystemUser> page = systemUserDao.createSqlHelper().from(SystemUser.class)
//			.appendSql("select * from system_user").query2Page(new PageRequest(0, 10));
		
//		System.out.println(page);
//		Page<Map> page = systemUserDao.createSqlHelper().from(HashMap.class)
//			.appendSql("select * from system_user").query2Page(new PageRequest(0, 10));
//		System.out.println(System.currentTimeMillis() - start);
//		System.out.println(page);
//		System.out.println(page.getContent());
		
//		System.out.println(sqlHelper == this.sqlHelper);
	}
	
//	@Test
	public void test1(){//28 44 20 18
		long start = System.currentTimeMillis();
		List<DemandClass> list =  demandClassDao.findAll();
		List<Map<String, Object>> mlist = new ArrayList<>();
		Map<String, Object> map = null;
		for (DemandClass demandClass : list) {
			map = new HashMap<>();
			map.put("id", demandClass.getId());
			map.put("name", demandClass.getName());
			mlist.add(map);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
//	@Test
	public void test2(){//50 52 44
		long start = System.currentTimeMillis();
		List<Object[]> list =  demandClassDao.findIdAndNameAll();
		List<Map<String, Object>> mlist = new ArrayList<>();
		Map<String, Object> map = null;
		for (Object[] arr : list) {
			map = new HashMap<>();
			map.put("id", arr[0]);
			map.put("name", arr[1]);
			mlist.add(map);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
//	@Test
	public void test3(){//50 52 44
		long start = System.currentTimeMillis();
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select id, name from demand_class");
		new PageImpl<>(list, null, list.size());
		System.out.println(System.currentTimeMillis() - start);
	}
	
//	@Test
	public void test4(){//50 52 44
		long start = System.currentTimeMillis();
//		Page<Map<String, Object>> page = SqlHelper.queryPage(jdbcTemplate, "select id, name from demand_class", null);
		System.out.println(System.currentTimeMillis() - start);
//		System.out.println(page.getSize());
//		System.out.println(page.getTotalPages());
//		System.out.println(page.getTotalElements());
//		System.out.println(page.getContent());
	}
}
