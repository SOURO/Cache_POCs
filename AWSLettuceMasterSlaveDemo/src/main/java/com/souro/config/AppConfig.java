/**
 * 
 */
package com.souro.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import com.souro.errorhandler.CustomCacheErrorHandler;
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
		 RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
	        clusterConfiguration.addClusterNode(new RedisNode("redis://souro-cluster-dsbld-001.ltvsn1.0001.use2.cache.amazonaws.com",6379));
	        clusterConfiguration.addClusterNode(new RedisNode("redis://souro-cluster-dsbld-002.ltvsn1.0001.use2.cache.amazonaws.com",6379));
	        clusterConfiguration.addClusterNode(new RedisNode("redis://souro-cluster-dsbld-003.ltvsn1.0001.use2.cache.amazonaws.com",6379));
	        return clusterConfiguration;
	    }

	    @Bean
	    RedisConnectionFactory  redisConnectionFactory() {
	    	/*RedisClient redisClient = RedisClient.create();

	       Iterable<RedisNode> nodes = Arrays.asList(RedisURI.create("redis://souro-cluster-dsbld-001.ltvsn1.0001.use2.cache.amazonaws.com:6379"),
	                RedisURI.create("redis://souro-cluster-dsbld-002.ltvsn1.0001.use2.cache.amazonaws.com:6379"),
	                RedisURI.create("redis://souro-cluster-dsbld-003.ltvsn1.0001.use2.cache.amazonaws.com:6379"));*/

	        /*StatefulRedisMasterSlaveConnection<String, String> connection = MasterSlave
	                .connect(redisClient, new Utf8StringCodec(), nodes);
	        connection.setReadFrom(ReadFrom.MASTER_PREFERRED);*/
	        
	        
	       LettuceConnectionFactory conectionFactory = new LettuceConnectionFactory(redisClusterConfiguration());
	        
	        return conectionFactory;
	    } 

	   /* @Bean
	    RedisTemplate<Object, Object> redisTemplate() {
	        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
	        redisTemplate.setConnectionFactory(redisConnectionFactory());
	        return redisTemplate;
	    }
	    
	    @Bean
	    CustomCacheWriter customCacheWriter(){
	    	CustomCacheWriter customCacheWriter = new CustomCacheWriter(redisConnectionFactory(), Duration.ZERO);
	    	return customCacheWriter;
	    }*/

	    @Bean
		public CacheManager cacheManager() {
	    	Map<String, RedisCacheConfiguration> initialCacheConfigurations = new ConcurrentHashMap<String, RedisCacheConfiguration>();
	    	initialCacheConfigurations.put("Souro_MSCache3", RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues());
	    	RedisCacheManager cm = RedisCacheManager.builder(redisConnectionFactory())
	    			.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
	    			.withInitialCacheConfigurations(initialCacheConfigurations)
	    			.transactionAware()
	    			.build();
	    	//System.out.println("CacheCollection  "+ cm.getCache("Souro_MSCache3").getName());
	    	return cm;
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