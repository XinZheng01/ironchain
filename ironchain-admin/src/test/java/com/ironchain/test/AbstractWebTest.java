package com.ironchain.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.ironchain.admin.Application;

@ContextConfiguration(classes=Application.class)
@SpringBootTest
public abstract class AbstractWebTest extends AbstractTest{
	
	protected TestRestTemplate template = new TestRestTemplate();
}
