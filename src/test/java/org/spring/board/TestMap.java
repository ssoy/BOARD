package org.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.board.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class TestMap {

	@Autowired
	private MapService mapservice;
	
	@Test
	public void test() throws Exception {
			mapservice.geocoding("신림로 340");
	}


}
