package com.ironchain.common.cache;

import java.io.Serializable;

import com.ironchain.common.domain.Constants.CacheConstants;

public interface CacheService {

	void set(String key, Serializable value);

	void set(CacheConstants constants, String key, Serializable value);

	Serializable get(String key);

	void set(String key, Serializable value, int expired);

	void delete(String key);

}
