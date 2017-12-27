/**
 * 
 */
package com.souro.config;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.souro.errorhandler.CustomCacheErrorHandler;
import com.souro.service.CustomCacheWriter;
import com.souro.service.ResearchService;

/**
 * @author sourabrata
 *
 */

@Configuration
@EnableCaching
@ComponentScan("com.memorynotfound")
@PropertySource("classpath:/redis.properties")
public class AppConfig extends CachingConfigurerSupport {

	private @Value("${redis.host}") String redisHost;
	private @Value("${redis.port}") int redisPort;
	private @Value("${ttl.expire}") long ttlExpire;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	 @Bean
	    RedisClusterConfiguration redisClusterConfiguration() {
	    	RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration();
	    	RedisNode rn = new RedisNode(redisHost,redisPort);
	    	clusterConfig.addClusterNode(rn);
	        return clusterConfig;
	    }

	    @Bean
	    RedisConnectionFactory redisConnectionFactory() {
	    	RedisClient redisClient = RedisClient.create();

	        List<RedisURI> nodes = Arrays.asList(RedisURI.create("redis://host1"),
	                RedisURI.create("redis://host2"),
	                RedisURI.create("redis://host3"));

	        StatefulRedisMasterSlaveConnection<String, String> connection = MasterSlave
	                .connect(redisClient, new Utf8StringCodec(), nodes);
	        connection.setReadFrom(ReadFrom.MASTER_PREFERRED);
	    }

	    @Bean
	    RedisTemplate<Object, Object> redisTemplate() {
	        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
	        redisTemplate.setConnectionFactory(redisConnectionFactory());
	        return redisTemplate;
	    }
	    
	    @Bean
	    CustomCacheWriter customCacheWriter(){
	    	CustomCacheWriter customCacheWriter = new CustomCacheWriter(redisConnectionFactory(), Duration.ZERO);
	    	return customCacheWriter;
	    }

	    @Bean
		public CacheManager cacheManager() {
	    	RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig();
	    	cacheConfig.entryTtl(Duration.ofSeconds(ttlExpire));
	    	//RedisCacheWriter cacheWriter = new RedisCacheWriter();
	    	RedisCacheManager rm= new RedisCacheManager(customCacheWriter(),cacheConfig);
	        //RedisCacheManager rm= RedisCacheManager.create(redisConnectionFactory());
	    	//rm.setExpires(cacheMap);
	        //rm.setDefaultExpiration(300);
	    	return rm;
	    }
	
	@Bean
	ResearchService reserachService() {
		return new ResearchService();
	}

	@Override
	@Bean
	public CacheErrorHandler errorHandler() {
		return new CustomCacheErrorHandler();
	}
}