package com.ironchain.test.web;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ironchain.common.domain.Demand;
import com.ironchain.common.domain.R;
import com.ironchain.intfc.Application;

//@ActiveProfiles("junit")
//@ContextConfiguration(classes=Application.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@DirtiesContext
public class DemandTest{
	
	@Autowired
	private TestRestTemplate restTemplate;

//	@Test
	public void publishDemand(){
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
		bodyMap.add("title", "测试需求-五金");
		bodyMap.add("number", 10000);
		bodyMap.add("type", Demand.TYPE_MACHINED);
		bodyMap.add("gdArea", "441900");
		bodyMap.add("bidNumber", 10);
		bodyMap.add("demandClass", 2);
//		bodyMap.put("mainEquipment", value);
		bodyMap.add("startDate", "2017-07-18 00:00:00");
		bodyMap.add("endDate", "2019-07-18 00:00:00");
		bodyMap.add("machined", "需要很多材料。。。。。");
		bodyMap.add("term", 180);
		bodyMap.add("userId", 1);
		bodyMap.add("payment", "现付定金，完成订单后支付尾款");
		
		List<Object> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "测试文件");
		map.put("path", "http://127.0.0.1:8080/asjadhas.jpg");
		list.add(map);
		map = new HashMap<>();
		map.put("name", "测试文件22");
		map.put("path", "http://127.0.0.1:8080/asjadhas22.jpg");
		list.add(map);
		bodyMap.put("demandFiles", list);
		
		ResponseEntity<R> response = restTemplate.postForEntity("/api/demand/publish", bodyMap, R.class);
		R r = response.getBody();
		System.out.println(r.getSc());
		System.out.println(r.getData());
		System.out.println(r.getMsg());
		assertEquals(r.getSc(), R.SC_SUCCESS);
	}
}
