package com.souro;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisJedisTransactionDemo {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		System.out.println("Server is running: " + jedis.ping());
		
		
		jedis.set("Souro_Key1", "Souro_Value1");
		jedis.watch("Souro_Key1");
		
		Transaction multi = jedis.multi();
		multi.set("Souro_Key4", "Souro_Value4");
		multi.discard();
		multi.exec();
		
		
		jedis.unwatch();
		System.out.println("Stored string in redis:: " + jedis.get("Souro_Key2"));
		//jedis.close();
	}
}
