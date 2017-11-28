# Java Cache Coherence Basic Demo

To run this java application, cache coherence should be running in your system. Follow the below steps for the same,

Installation:
-------------

1. Download the coherence jar first from this link: - http://www.oracle.com/technetwork/middleware/coherence/downloads/index.html
2. There are various types, but you can go for "Coherence Stand-Alone Install", in case if you are confused.
3. Once downloaded then go to the directory location where jar exists.
4. Then open command promt from this location and run the below command,
   java -jar <jar name>.jar (Sample command: java -jar fmw_12.2.1.3.0_coherence.jar)
5. You will see oracle wizard, press "next" step by step and complete the installation. 
6. Once installation completed, go to the below location,
   "C:\Oracle\Middleware\coherence_3.6", you will see bin, lib .. all these folders.
7. You are now done with the installation. 
   

Run Cache Coherence locally:
---------------------------

1. Open the ../bin/cache-server.cmd script from coherence folder.
2. Modify the java_opts variable to include the tangosol.coherence.cluster and the tangosol.coherence.clusterport system properties as follows: 
   set java_opts="-Xms%memory% -Xmx%memory% -Dtangosol.coherence.cluster=<cluster_name> -Dtangosol.coherence.clusterport=<port>"
   (Sample: set java_opts="-Xms%memory% -Xmx%memory% -Dtangosol.coherence.cluster=souro -Dtangosol.coherence.clusterport=0794")
3. Save the file and close it.
4. Open the ../bin/coherence.cmd script from coherence folder.
5. Repeat the 2-3 steps in this file also.
6. Run "cache-server.cmd" to start the cache server and "coherence.cmd" to start each instance that provides information about the respective cluster members.
   

Demo Testing:
-------------

1. Where your coherence command promt is running, run the below command to create a cache instance,
   cache demoinstance
2. Run the below command to store in cache,
   put key1 souro
3. Run the below command to retrieve the same,
   get key1    (Output => souro)
   
*** Now we are done !! Download and run the java application now !!


# Redis Java Demo

** Redis is a NoSQL database which follows the principle of key-value store.

Redis Setup:
------------
1. Download Redis from this link: https://redis.io/download.
2. Unzip the same and go to the, ../redis-2.4.5-win32-win64/64bit. (You can either select 64bit/32 bit, it is as per you).
3. Start the scripts (redis-server.exe and redis-cli.exe) for redis server and redis client. You can talk to redis through "redis-cli.exe" or through code using "Jedis" connector.
4. To check the connection status, you can execute "ping" command in "redis-cli.exe". If the connection is active it will revert back with the "PONG" response.
5. Also to check all the configuration of your running redis server, you can execute the command "config get *" in redis client.

Jedis Setup:
------------
1. Download "Jedis" jar from this link : http://www.java2s.com/Code/JarDownload/jedis/jedis-2.0.0.jar.zip
2. Include this jar in your project's build path.

** Once you are done with these setup, download the code from this repository and run the java application, you can see the output.




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
