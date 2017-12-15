package com.souro;

import redis.clients.jedis.Jedis;

public class RedisJavaDemo1 {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		System.out.println("Server is running: " + jedis.ping());
		jedis.set("Souro_Key", "Souro_Value");
		System.out
				.println("Stored string in redis:: " + jedis.get("Souro_Key"));
	}
}
