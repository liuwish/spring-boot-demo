package com.github.jartisan.springbootdemo.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class RedisConfig  extends CachingConfigurerSupport{
	 	@Bean(name="redisTemplate")
	    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
	        RedisTemplate<String, String> template = new RedisTemplate<>();
	        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
	        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);
	        template.setConnectionFactory(factory);
	        //key序列化方式
	        template.setKeySerializer(redisSerializer);
	        //value序列化
	        template.setValueSerializer(jackson2JsonRedisSerializer);
	        //value hashmap序列化
	        template.setHashValueSerializer(jackson2JsonRedisSerializer);

	        return template;
	    }

	    @Bean
	    public CacheManager cacheManager(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate) {
	        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
	        Set<String> cacheNames = new HashSet<>();
	        cacheNames.add("general");
	        cacheManager.setCacheNames(cacheNames);
	        Map<String,Long> expires = new HashMap<>();
	        expires.put("general", 6000L);
	        cacheManager.setExpires(expires);
	        return cacheManager;
	    }
	    
	    
	    @Bean(name="redisAtomicLong")
	    public RedisAtomicLong redisAtomicLong(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate) {
	    	RedisAtomicLong redisAtomicLong = new RedisAtomicLong("atomickey",redisTemplate.getConnectionFactory());
	        return redisAtomicLong;
	    }
}
