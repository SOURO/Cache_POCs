package com.souro;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisJavaDemo2 {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		System.out.println("Server is running: " + jedis.ping());

		jedis.lpush("key_list", "value1");
		jedis.lpush("key_list", "value2");
		jedis.lpush("key_list", "value3");
		List<String> list = jedis.lrange("key_list", 0, 5);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("Stored string in redis:: " + list.get(i));
		}
	}
}
