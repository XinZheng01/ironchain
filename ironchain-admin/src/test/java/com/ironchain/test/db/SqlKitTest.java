package com.ironchain.test.db;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ironchain.common.persistence.SqlHelper;
import com.ironchain.test.AbstractNoneWebTest;

public class SqlKitTest extends AbstractNoneWebTest{
	
	@Autowired
	private SqlHelper sqlHelper;
	
	@Test
	public void sqlTest(){
		sqlHelper.from(String.class).appendSql("select version()").query2Single();
	}
}
