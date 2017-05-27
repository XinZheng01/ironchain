package com.ironchain.test.db;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ironchain.common.dao.SystemUserDao;
import com.ironchain.common.domain.SystemUser;
import com.ironchain.test.AbstractNoneWebTest;

public class SqlKitTest extends AbstractNoneWebTest{
	
//	@Autowired
//	private SqlHelper sqlHelper;
	
	@Autowired
	private SystemUserDao systemUserDao;
	
	@Test
	public void sqlTest(){
		Page<SystemUser> page = systemUserDao.createSqlHelper().from(SystemUser.class)
			.appendSql("select * from system_user").query2Page(new PageRequest(0, 10));
		
		System.out.println(page);
//		Page<Map> page = systemUserDao.createSqlHelper().from(HashMap.class)
//			.appendSql("select * from system_user").query2Page(new PageRequest(0, 10));
//		System.out.println(page);
//		System.out.println(page.getContent());
		
//		System.out.println(sqlHelper == this.sqlHelper);
	}
}
