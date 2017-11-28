# Spring-Data-Redis demo

** The Spring Data Redis (or SDR) framework makes it easy to write Spring applications that use the Redis key value store by eliminating the redundant tasks and boiler plate code required for interacting with the store through Spring’s excellent infrastructure support.

Redis Connection Factory:
-------------------------
** One of the first tasks when using Redis and Spring is to connect to the store through the IoC container. To do that, a Java connector (or binding) is required. No matter the library one chooses, there is only one set of Spring Data Redis API that one needs to use that behaves consistently across all connectors, namely the org.springframework.data.redis.connection package and its RedisConnection and RedisConnectionFactory interfaces for working with and retrieving active connections to Redis.
** Active RedisConnection s are created through RedisConnectionFactory.
** Depending on the underlying configuration, the factory can return a new connection or an existing connection (in case a pool or shared native connection is used).

Jedis Connection Factory:
-------------------------
** Jedis is one of the connectors supported by the Spring Data Redis module through the org.springframework.data.redis.connection.jedis package. In its simplest form, the Jedis configuration looks as follow:
   <bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory" p:use-pool="true"/>

Redis Template:
---------------
** Most users are likely to use RedisTemplate and its corresponding package org.springframework.data.redis.core - the template is in fact the central class of the Redis module due to its rich feature set. The template offers a high-level abstraction for Redis interactions. While RedisConnection offers low level methods that accept and return binary values (byte arrays), the template takes care of serialization and connection management, freeing the user from dealing with such details.
   <bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate" p:connection-factory-ref="jedisConnectionFactory" />
** Once configured, the template is thread-safe and can be reused across multiple instances.
** Out of the box, RedisTemplate uses a Java-based serializer for most of its operations. This means that any object written or read by the template will be serialized/deserialized through Java. The serialization mechanism can be easily changed on the template.
** Note that the template requires all keys to be non-null - values can be null as long as the underlying serializer accepts them.
** For cases where a certain template view is needed, declare the view as a dependency and inject the template.
   <bean id="service" class="com.souro.SpringDataRedisdemo.service.ServiceImpl"> <property name="redisTemplate" ref="redisTemplate" /> </bean>


*** Once you read these once, download the demo from this repository and run and explore the same !!
