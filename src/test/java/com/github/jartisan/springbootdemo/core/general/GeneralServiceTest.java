package com.github.jartisan.springbootdemo.core.general;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.github.jartisan.springbootdemo.dao.general.entity.Term;
import com.github.jartisan.springbootdemo.service.general.GeneralService;

/***
 * 
 * @author ejb3 2015年2月4日 下午12:27:47
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest
public class GeneralServiceTest {
	private static Logger log = LoggerFactory.getLogger(GeneralServiceTest.class);
	@Autowired
	private GeneralService termService;
	
	@Test
	public void testSelectByDeleted() {
		final int testCount=10000;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		for(int i=0;i<testCount;i++){
			termService.selectTermByUsable();
		}
		stopWatch.stop();
		log.info("共计耗时:{}",stopWatch.getTotalTimeSeconds());
		log.info("共计耗时:{2}",stopWatch.getTotalTimeSeconds());
	}
	
	
	@Test
	public void testSelectByCode() {
		Term term  = termService.selectTermByCode(20);
		log.info(term.toString());
	}

}
