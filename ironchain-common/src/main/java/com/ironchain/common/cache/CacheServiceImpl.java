package com.ironchain.common.cache;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ironchain.common.domain.Constants.CacheConstants;

/**
 * Redis 缓存操作
 * @author zheng xin
 *
 */
@Service
public class CacheServiceImpl implements CacheService {
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Override
	public void set(String key, Serializable value){
		Validate.notNull(key, "the rediscache key must not be null!");
		
		redisTemplate.opsForValue().set(key, value);
	}
	
	@Override
	public void set(String key, Serializable value, int expired){
		Validate.notNull(key, "the rediscache key must not be null!");
		
		redisTemplate.opsForValue().set(key, value, expired, TimeUnit.SECONDS);
	}
	
	@Override
	public void set(CacheConstants constants, String key, Serializable value){
		Validate.notNull(constants, "the CacheConstants must not be null!");
		
		if(constants.getExpiredTime() > 0)
			set(constants.getKey(key), value, constants.getExpiredTime());
		else
			set(constants.getKey(key), value);
	}
	
	@Override
	public Serializable get(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	@Override
	public void delete(String key){
		redisTemplate.delete(key);
	}
}
