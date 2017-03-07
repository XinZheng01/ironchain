package com.ironchain.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ironchain.admin.Application;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@ActiveProfiles("devel")
/**
 * @RunWith(SpringRunner.class) 等同于 @RunWith(SpringJUnit4ClassRunner.class)
 * @SpringBootTest 默认MOCK环境
 * @author Administrator
 *
 */
public abstract class AbstractTest {
	
	@Before
	public void before(){
		System.setProperty("catalina.home", ".");
	}
}
