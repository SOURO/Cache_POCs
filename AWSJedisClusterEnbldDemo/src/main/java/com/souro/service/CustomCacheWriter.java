/**
 * 
 */
package com.souro.service;

import java.time.Duration;

import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.Assert;

/**
 * @author sourabrata
 *
 */
public class CustomCacheWriter implements RedisCacheWriter {

	private final RedisConnectionFactory connectionFactory;
	private final Duration sleepTime;

	public CustomCacheWriter(RedisConnectionFactory connectionFactory,
			Duration sleepTime) {

		Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");

		this.connectionFactory = connectionFactory;
		if (sleepTime != null) {
			sleepTime = Duration.ZERO;
		}
		this.sleepTime = sleepTime;
	}

	public void put(String name, byte[] key, byte[] value, Duration ttl) {
		// TODO Auto-generated method stub

	}

	public byte[] get(String name, byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] putIfAbsent(String name, byte[] key, byte[] value,
			Duration ttl) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(String name, byte[] key) {
		// TODO Auto-generated method stub

	}

	public void clean(String name, byte[] pattern) {
		// TODO Auto-generated method stub

	}

}
