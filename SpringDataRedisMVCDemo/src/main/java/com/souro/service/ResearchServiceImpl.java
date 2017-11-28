/**
 * 
 */
package com.souro.service;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import com.souro.dao.ResearchDao;

/**
 * @author sourabrata
 *
 */
public class ResearchServiceImpl implements ResearchService {

	private RedisTemplate<String, ResearchDao> redisTemplate;
	private final static String RESEARCH_KEY = "Research";

	public RedisTemplate<String, ResearchDao> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, ResearchDao> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void save(ResearchDao research) {
		this.redisTemplate.opsForHash().put(RESEARCH_KEY,
				research.getResearch_id(), research);
	}

	public ResearchDao find(int id) {
		return (ResearchDao) this.redisTemplate.opsForHash().get(RESEARCH_KEY, id);
	}

	public Map<Object, Object> findAll() {
		return this.redisTemplate.opsForHash().entries(RESEARCH_KEY);
	}

	public void delete(int id) {
		this.redisTemplate.opsForHash().delete(RESEARCH_KEY, id);
	}

}
