/**
 * 
 */
package com.souro.config;

import java.time.Duration;

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
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.souro.controller.ResearchControllerDemo;
import com.souro.errorhandler.CustomCacheErrorHandler;
import com.souro.service.CustomCacheWriter;
import com.souro.service.ResearchService;


/**
 * @author sourabrata
 *
 */

@Configuration
@EnableCaching
@ComponentScan("com.souro")
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
	public ViewResolver configureViewResolver() {
		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setPrefix("/WEB-INF/jsp/");
		viewResolve.setSuffix(".jsp");
		return viewResolve;
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
        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration());
        return factory;
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
    	//rm.initializeCaches();
        //rm.setDefaultExpiration(300);
    	return rm;
    }
    
    @Bean
    ResearchService reserachService(){
    	return new ResearchService();
    }
    
    @Bean
	ResearchControllerDemo researchControllerDemo(){
		ResearchControllerDemo researchControllerDemo = new ResearchControllerDemo();
		return researchControllerDemo;
	}
    
    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }
}