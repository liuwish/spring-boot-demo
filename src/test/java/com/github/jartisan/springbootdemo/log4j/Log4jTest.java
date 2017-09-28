package com.github.jartisan.springbootdemo.log4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest
public class Log4jTest {
	private static final Logger log = LoggerFactory.getLogger(Test.class);
	@Test
	public void log4jTest() {
		log.debug("this level is {}","debug");
		log.info("this level is {}","info","hello");
		log.warn("this level is {}","warn","world");
		log.error("this level is {}","error");
	}
}
