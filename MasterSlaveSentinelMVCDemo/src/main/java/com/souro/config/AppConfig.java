/**
 * 
 */
package com.souro.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.souro.service.ResearchService;

/**
 * @author sourabrata
 *
 */

@Configuration
@EnableCaching
@ComponentScan("com.memorynotfound")
@PropertySource("classpath:/redis.properties")
public class AppConfig {

	/*private @Value("${redis.host}") String redisHost;
	private @Value("${redis.port}") int redisPort;*/
	
	/*private @Value("${redis.sentinel.node.1.host}") String masterHost;
	private @Value("${redis.sentinel.node.1.port}") int masterPort;
	private @Value("${redis.sentinel.node.2.host}") String slave1Host;
	private @Value("${redis.sentinel.node.2.port}") int slave1Port;
	private @Value("${redis.sentinel.node.3.host}") String slave2Host;
	private @Value("${redis.sentinel.node.3.port}") int slave2Port;*/
	
	private static final RedisSentinelConfiguration SENTINEL_CONFIG = new RedisSentinelConfiguration().master("redis_cluster") //
			.sentinel("192.168.56.1", 16379) //
			.sentinel("192.168.56.1", 16380) //
			.sentinel("192.168.56.1", 16381);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public RedisSentinelConfiguration sentinelConfig() {
		return SENTINEL_CONFIG;
	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory(sentinelConfig());
		return factory;
	}

	@Bean
	RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		/*redisTemplate.setDefaultSerializer(stringRedisSerializer());
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());*/
		return redisTemplate;
	}

	@Bean
	CacheManager cacheManager() {
		return new RedisCacheManager(redisTemplate());
	}

	@Bean
	ResearchService reserachService() {
		return new ResearchService();
	}
}