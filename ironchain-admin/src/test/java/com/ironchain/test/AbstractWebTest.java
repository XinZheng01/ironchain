package com.ironchain.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest
public abstract class AbstractWebTest extends AbstractTest{
	
	protected TestRestTemplate template = new TestRestTemplate();
}
