package com.souro;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisJavaDemo3 {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		System.out.println("Server is running: " + jedis.ping());

		Set<String> keySets = jedis.keys("*");

		System.out.println("Set of stored keys:: " + keySets);
	}
}
