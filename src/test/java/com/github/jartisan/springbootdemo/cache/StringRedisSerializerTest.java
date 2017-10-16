package com.github.jartisan.springbootdemo.cache;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;
/***
 * 
 * @author wjl
 * @date: 2016年2月26日 上午11:39:24
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest
public class StringRedisSerializerTest {
	private static Logger log = LoggerFactory.getLogger(StringRedisSerializerTest.class);
	@Autowired
	private RedisTemplate<String,String> stringRedisTemplate;
	
	@Autowired
	private RedisAtomicLong redisAtomicLong;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	
	@Test
	public void addLink() throws MalformedURLException, URISyntaxException {
		String userId = "111";
	    listOps.leftPush(userId, "11111111111");
	}

	@Test
	public void addOrUpdateTest() {
		StopWatch stopWatch = new StopWatch("redis test :");
		stopWatch.start("opsForValue");
		final int testCount =10000;
		for (int i = 0; i < testCount; i++) {
			stringRedisTemplate.opsForValue().set("user.userid."+i, "张三" + i);
		}
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
	}

	@Test
	public void load() {
		log.info(stringRedisTemplate.opsForValue().get("user.userid.1"));
	}

	@Test
	public void delete() {
		StopWatch stopWatch = new StopWatch("redis test :");
		stopWatch.start("delete");
		final int testCount =10000;
		for (int i = 0; i < testCount; i++) {
			stringRedisTemplate.delete("user.userid."+i);	
		}
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
	}
	
	@Test
	public void addOrUpdateTest1() {
		HashOperations<String, String, String> listOper = stringRedisTemplate.opsForHash();
		listOper.put("city", "316", "1378564----");
		listOper.put("city", "316", "1388564----");
		
		
		log.info(listOper.get("city", "316"));
	}
	
	@Test
	public void setTest() {
		BoundSetOperations<String, String> setOper =stringRedisTemplate.boundSetOps("city:316:13785641151");
		setOper.add("13785641152");
		setOper.add("13785641153");
		
	}
	
	@Test
	public void setAtomicLong() {
		// 第一次，设置初始值
		long original = 1000L;

		// 获取 code 值
		original = redisAtomicLong.get();
		System.out.println("*****************original:"+original);

		// 第一次，设置初始值
		if (original == 0L) {
		    redisAtomicLong.set(5L);
		}
		//获得加1后的值
		long now = redisAtomicLong.incrementAndGet();
		System.out.println("*****************now:"+now);

		
	}

	
	/*@Test
	public void addJson() {
		jsonRedisTemplate.setKeySerializer(new StringRedisSerializer());
		jsonRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
		jsonRedisTemplate.opsForValue().set("user.name.18", new User("张三",18));
		log.info(jsonRedisTemplate.opsForValue().get("user.name.18").toString());
	}

	
	@Test
	public void addPhoneHashTest() {
		jsonRedisTemplate.setKeySerializer(new StringRedisSerializer());
		jsonRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
		StopWatch stopWatch = new StopWatch("redis test :");
		stopWatch.start("delete");
		for(int i =0;i<660000;i++){
			jsonRedisTemplate.opsForSet().add("cmcc:0316", new User("张三",i));	
		}
		//获取set数量
		log.info(jsonRedisTemplate.opsForSet().size("cmcc:0316").toString());
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		//遍历所有key
		Set<User> users =jsonRedisTemplate.opsForSet().members("cmcc:0316");
		for(User u : users){
			log.info(u.toString());
		}
		
		User user =jsonRedisTemplate.opsForSet().pop("cmcc:0316");
		log.info(user.toString());
		log.info(jsonRedisTemplate.opsForSet().size("cmcc:0316").toString());
		//log.info(jsonRedisTemplate.opsForSet().members("cmcc:0316").toString());
		
	}*/
}
