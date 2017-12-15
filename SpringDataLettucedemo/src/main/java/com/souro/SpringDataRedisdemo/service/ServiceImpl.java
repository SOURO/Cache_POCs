/**
 * 
 */
package com.souro.SpringDataRedisdemo.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.souro.SpringDataRedisdemo.dao.Research;

/**
 * @author sourabrata
 *
 */
public class ServiceImpl implements Service {

	private RedisTemplate<String, Research> redisTemplate;
	private final static String RESEARCH_KEY = "Research_ML";

	public RedisTemplate<String, Research> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Research> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void save(Research research) {
		this.redisTemplate.opsForHash().put(RESEARCH_KEY,research.getResearch_id(), research);
		
		this.redisTemplate.opsForValue().set("souro_ttl_key", research);
		this.redisTemplate.expire("souro_ttl_key", 1, TimeUnit.MINUTES);
		
		//BoundValueOperations<String, Research> boundValueOperations = this.redisTemplate.boundValueOps(RESEARCH_KEY);

	    //boundValueOperations.set(research);
	    //boundValueOperations.expire(1, TimeUnit.MINUTES);
	}

	public Research find(int id) {
		return (Research) this.redisTemplate.opsForHash().get(RESEARCH_KEY, id);
	}

	public Map<Object, Object> findAll() {
		return this.redisTemplate.opsForHash().entries(RESEARCH_KEY);
	}

	public void delete(int id) {
		this.redisTemplate.opsForHash().delete(RESEARCH_KEY, id);
	}

}
