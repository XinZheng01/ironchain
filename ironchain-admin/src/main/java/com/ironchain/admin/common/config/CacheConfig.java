package com.ironchain.admin.common.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ironchain.common.cache.CacheService;
import com.ironchain.common.cache.CacheServiceImpl;

@Configuration
public class CacheConfig {
	
	@Bean
	public CacheService getCacheService(){
		return new CacheServiceImpl();
	}
	
	@Autowired
	private RedisConnectionFactory connectionFactory;

	@Bean
	public RedisTemplate<String, Serializable> getRedisTemplate(){
		RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
		StringRedisSerializer serializer = new StringRedisSerializer();
		
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(serializer);
		redisTemplate.setHashKeySerializer(serializer);
		return redisTemplate;
	}
}
